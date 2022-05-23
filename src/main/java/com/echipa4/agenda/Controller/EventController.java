package main.java.com.echipa4.agenda.Controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.eclipse.swt.widgets.DateTime;

import main.java.com.echipa4.agenda.Database.EvenimentDao;
import main.java.com.echipa4.agenda.Model.Alarma;
import main.java.com.echipa4.agenda.Model.Eveniment;
import main.java.com.echipa4.agenda.Model.Interval;
import main.java.com.echipa4.agenda.Model.Recurenta;

class Period {
	public Date startDate;
	public Date endDate;
	
	public Period(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
}

public class EventController {
	private static EventController evenimentControllerInstance = null;
    private ArrayList<EventListener> listeners = new ArrayList<EventListener>();

	
	public static EventController getInstance() {
		if (evenimentControllerInstance == null) { 
			evenimentControllerInstance = new EventController();
		}
		
		return evenimentControllerInstance;
	}
	
	private Eveniment eventToModify;
	private boolean isAlarmVisible;
	private boolean isRepVisible;
	
	public void createEvenimentToModify(Eveniment event) {
		if (event == null) {
			this.eventToModify = new Eveniment();
		} else {
			this.eventToModify = event.clone();
		}
		this.isAlarmVisible = eventToModify.getAlarma() != null;
		this.isRepVisible = eventToModify.getRecurenta() != null;
	}
	
	public Eveniment getEvenimentToModify() {
		return this.eventToModify;
	}
	
	public void setStartDate(DateTime daySelector, DateTime timeSelector) {
		if (this.eventToModify == null) {
			return;
		}

		Interval interval = this.eventToModify.getInterval();
		
		if (interval == null) {
			interval = new Interval();
			this.eventToModify.setInterval(interval);
		}
		
		
		Calendar cal = Calendar.getInstance();
		cal.set(daySelector.getYear(), daySelector.getMonth(), daySelector.getDay(), timeSelector.getHours(), timeSelector.getMinutes(), timeSelector.getSeconds());

		interval.setDataInceput(new Date(cal.getTimeInMillis()));
	}
	
	public void setEndDate(DateTime daySelector, DateTime timeSelector) {
		if (this.eventToModify == null) {
			return;
		}
		
		Interval interval = this.eventToModify.getInterval();
		
		if (interval == null) {
			interval = new Interval();
			this.eventToModify.setInterval(interval);
		}
		
		Calendar cal = Calendar.getInstance();
		cal.set(daySelector.getYear(), daySelector.getMonth(), daySelector.getDay(), timeSelector.getHours(), timeSelector.getMinutes(), timeSelector.getSeconds());
		
		interval.setDataSfarsit(new Date(cal.getTimeInMillis()));
	}
	
	public void toogleAlarmVisible() {
		this.isAlarmVisible = !this.isAlarmVisible;	
		if (!this.isAlarmVisible) {
			this.eventToModify.setAlarma(null);
		}	
	}
	
	public void toogleRepVisible() {
		this.isRepVisible = !this.isRepVisible;
		if (!this.isRepVisible) {
			this.eventToModify.setRecurenta(null);
		}
	}
	
	public boolean shouldDisplayAlarmInputs() {
		return this.isAlarmVisible;
	}
	
	public boolean shouldDisplayRepInputs() {
		return this.isRepVisible;
	}
	
	public void ensureRecurenta() {
		if (this.eventToModify == null) {
			return;
		}
		
		Recurenta recurenta = this.eventToModify.getRecurenta();
		
		if (recurenta == null) {
			recurenta = new Recurenta();
			this.eventToModify.setRecurenta(recurenta);;
		}
	}
	
	public void ensureAlarma() {
		if (this.eventToModify == null) {
			return;
		}
		
		Alarma alarma = this.eventToModify.getAlarma();
		
		if (alarma == null) {
			alarma = new Alarma();
			this.eventToModify.setAlarma(alarma);;
		}
	}
	
	public boolean saveEvenimentToAdd() {
		try {
			if (this.eventToModify.getId() == -1) {
				EvenimentDao.getInstance().insert(this.eventToModify);
			} else {
				EvenimentDao.getInstance().update(this.eventToModify);
			}
			this.modifyEvents();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public void cancelEvenimentToAdd() {
		this.eventToModify = null;
	}
	
	public void deleteEvenimentToAdd() {
		if (this.eventToModify.getId() != -1) {
			try {
				EvenimentDao.getInstance().delete(eventToModify);
				this.modifyEvents();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
	
    public void addEventListener(EventListener toAdd) {
        listeners.add(toAdd);
    }
	
	public ArrayList<Eveniment> getAll() {
		try {
			return EvenimentDao.getInstance().getEvents();
		} catch (SQLException e) {
			return new ArrayList<Eveniment>();
		}
	}

	public ArrayList<Eveniment> getEventsForPeriod(ArrayList<Eveniment> allEvents, Date startDate, Date endDate) {
		ArrayList<Eveniment> eventsForPeriod = new ArrayList<Eveniment>();
		
		for (Eveniment event: allEvents) {
			ArrayList<Period> viziblePeriods = getAllVisiblePeriodsForEvent(event);
			boolean isAnyPeriodBetweenDates = isAnyViziblePeriodBetweenDate(viziblePeriods, startDate, endDate);
			
			if (isAnyPeriodBetweenDates) {
				eventsForPeriod.add(event);
			}
		}
			
		return eventsForPeriod;
	}
    
    private void modifyEvents() {
        for (EventListener hl : listeners)
            hl.eventsUpdated();
    }
    
    private boolean isAnyViziblePeriodBetweenDate(ArrayList<Period> periods, Date startDate, Date endDate) {
    	boolean isBetweenDates = false;
    	
    	for (Period period: periods) {
    		boolean isPeriodInsideInterval = startDate.compareTo(period.startDate) >= 0 && endDate.compareTo(period.endDate) <= 0;
    		boolean isPeriodOutsideInterval = startDate.compareTo(period.startDate) < 0 && endDate.compareTo(period.endDate) > 0;
    		boolean isStartDateInInterval = startDate.compareTo(period.startDate) >= 0 && startDate.compareTo(period.endDate) < 0;
    		boolean isEndDateInInterval = endDate.compareTo(period.startDate) > 0 && endDate.compareTo(period.endDate) <= 0;
    		
    		isBetweenDates = isBetweenDates || isStartDateInInterval || isEndDateInInterval || isPeriodInsideInterval || isPeriodOutsideInterval;
    	}
    	
    	return isBetweenDates;
    }
    
    private ArrayList<Period> getAllVisiblePeriodsForEvent(Eveniment event) {
    	ArrayList<Period> periods = new ArrayList<Period>();
    	Interval interval = event.getInterval();
    	
    	if (interval != null) {
    		periods.add(new Period(interval.getDataInceput(), interval.getDataSfarsit()));
    	}
    	
    	periods.addAll(getAllRecurringPeriodsForEvent(event));
    	
    	return periods;
    }
    
    private ArrayList<Period> getAllRecurringPeriodsForEvent(Eveniment event) {
    	ArrayList<Period> periods = new ArrayList<Period>();
		Recurenta recurenta = event.getRecurenta();
    	Interval interval = event.getInterval();
    	
    	if (interval == null || recurenta == null) {
    		return periods;
    	}
		
		for(int i=1; i<= recurenta.getRecurenta(); i++) {
			Calendar periodStartDate = Calendar.getInstance();
			Calendar periodEndDate = Calendar.getInstance();
			periodStartDate.setTimeInMillis(interval.getDataInceput().getTime());
			periodEndDate.setTimeInMillis(interval.getDataSfarsit().getTime());
			int periodToAdd = getPeriodToAddFromRecurenta(recurenta);
			
			if (periodToAdd != -1) {
				periodStartDate.add(periodToAdd, i);
				periodEndDate.add(periodToAdd, i);
			}

    		periods.add(new Period(new Date(periodStartDate.getTimeInMillis()), new Date(periodEndDate.getTimeInMillis())));
		}
    	
    	return periods;
    }
    
    private int getPeriodToAddFromRecurenta(Recurenta recurenta) {
		int periodToAdd = -1;
		switch (recurenta.getReperate()) {
		case DAILY:
			periodToAdd = Calendar.DATE;
			break;
		case WEEKLY:
			periodToAdd = Calendar.WEEK_OF_YEAR;
			break;
		case MONTHLY:
			periodToAdd = Calendar.MONTH;
			break;
		case YEARLY:
			periodToAdd = Calendar.YEAR;
			break;
		default:
			break;
		}
		
		return periodToAdd;
    }
}
