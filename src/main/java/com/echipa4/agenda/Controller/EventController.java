package main.java.com.echipa4.agenda.Controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import org.eclipse.swt.widgets.DateTime;

import main.java.com.echipa4.agenda.Database.EvenimentDao;
import main.java.com.echipa4.agenda.Model.Alarma;
import main.java.com.echipa4.agenda.Model.Eveniment;
import main.java.com.echipa4.agenda.Model.Interval;
import main.java.com.echipa4.agenda.Model.Recurenta;
import main.java.com.echipa4.agenda.Model.Reperate;

public class EventController {
	private static EventController evenimentControllerInstance = null;
	
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
		this.isAlarmVisible = false;
		this.isRepVisible = false;
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
		cal.set(daySelector.getYear(), daySelector.getMonth(), daySelector.getDay(), timeSelector.getHours(), timeSelector.getMinutes());
		
		interval.setDataInceput(new Date(cal.getTimeInMillis()));
		System.out.println("timestamps: " + cal.getTimeInMillis());
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
		cal.set(daySelector.getYear(), daySelector.getMonth(), daySelector.getDay(), timeSelector.getHours(), timeSelector.getMinutes());
		
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
			EvenimentDao.getInstance().insert(this.eventToModify);
			return true;
		} catch (SQLException e) {

			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public void cancelEvenimentToAdd() {
		this.eventToModify = null;
	}

}
