package main.java.com.echipa4.agenda.Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import main.java.com.echipa4.agenda.Interfaces.EventViewTypes;

public class EventViewController {
	private static EventViewController instance = null;
    private ArrayList<EventViewListener> listeners = new ArrayList<EventViewListener>();
    private EventViewTypes eventViewType;
	private Calendar currentDate;

	public static EventViewController getInstance() {
		if (instance == null) { 
			instance = new EventViewController();
		}
		
		return instance;
	}
	
	private EventViewController() {
		currentDate = new GregorianCalendar();
		currentDate.setFirstDayOfWeek(Calendar.MONDAY);
		currentDate.clear(Calendar.HOUR_OF_DAY);
		currentDate.clear(Calendar.MINUTE);
		currentDate.clear(Calendar.SECOND);
		currentDate.clear(Calendar.MILLISECOND);
	}
	
	public void increaseByOneDay() {
		currentDate.add(Calendar.DATE, 1);
		modifyCurrentDateEvents();
	}
	
	public void decreaseByOneDay() {
		currentDate.add(Calendar.DATE, -1);
		modifyCurrentDateEvents();
	}
	
	public void increaseByOneWeek() {
		currentDate.add(Calendar.WEEK_OF_YEAR, 1);
		modifyCurrentDateEvents();
	}
	
	public void decreaseByOneWeek() {
		currentDate.add(Calendar.WEEK_OF_YEAR, -1);
		modifyCurrentDateEvents();
	}
	
	public void increaseByOneMonth() {
		currentDate.add(Calendar.MONTH, 1);
		modifyCurrentDateEvents();
	}
	
	public void decreaseByOneMonth() {
		currentDate.add(Calendar.MONTH, -1);
		modifyCurrentDateEvents();
	}
	
	public void increaseByOneYear() {
		currentDate.add(Calendar.YEAR, 1);
		modifyCurrentDateEvents();
	}
	
	public void decreaseByOneYear() {
		currentDate.add(Calendar.YEAR, -1);
		modifyCurrentDateEvents();
	}
	
	public Calendar getCurrentDate() {
		return currentDate;
	}
	
	public void setCurrentDate(Calendar newDate) {
		currentDate = newDate;
		modifyCurrentDateEvents();
	}
	
    public void addEventViewListener(EventViewListener toAdd) {
        listeners.add(toAdd);
    }
    
    public void changeViewType(EventViewTypes type) {
    	this.eventViewType = type;
    	this.modifyViewModeEvents();
    }
    
    public EventViewTypes getViewType() {
    	return eventViewType;
    }
    
    private void modifyViewModeEvents() {
        for (EventViewListener hl : listeners)
            hl.updateViewMode();
    }
    
    private void modifyCurrentDateEvents() {
        for (EventViewListener hl : listeners)
    		hl.updateCurrentDate();
    }

}
