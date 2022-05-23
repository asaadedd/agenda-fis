package main.java.com.echipa4.agenda.View;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import main.java.com.echipa4.agenda.Controller.EventViewController;
import main.java.com.echipa4.agenda.Controller.EventViewListener;
import main.java.com.echipa4.agenda.Interfaces.EventViewTypes;
import main.java.com.echipa4.agenda.View.Calendar.CalendarDaily;
import main.java.com.echipa4.agenda.View.Calendar.CalendarMonthly;
import main.java.com.echipa4.agenda.View.Calendar.CalendarWeekly;
import main.java.com.echipa4.agenda.View.Calendar.CalendarYearly;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

public class EventView extends Composite {
	private EventViewController eventViewController = EventViewController.getInstance();
	private EventViewTypes viewType;
	private EventToolbar eventToolbar;
	private EventTable eventTable;
	private CalendarWeekly calendarWeekly;
	private CalendarYearly calendarYearly;
	private CalendarMonthly calendarMonthly;
	private CalendarDaily calendarDaily;
	
	public EventView(Composite parent, int style) {
		super(parent, style);
		
		viewType = EventViewTypes.CALENDAR_WEEKLY;
		setLayout(new GridLayout(1, false));
		
		eventToolbar = new EventToolbar(this, SWT.NONE);
		
		eventTable = new EventTable(this, SWT.NONE);
	    GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
	    eventTable.setLayoutData(data);
	    
		calendarWeekly = new CalendarWeekly(this, SWT.NONE);
		GridData data1 = new GridData(SWT.FILL, SWT.FILL, true, true);
		calendarWeekly.setLayoutData(data1);
	    
		calendarYearly = new CalendarYearly(this, SWT.NONE);
		GridData data2 = new GridData(SWT.FILL, SWT.FILL, true, true);
		calendarYearly.setLayoutData(data2);
	    
		calendarMonthly = new CalendarMonthly(this, SWT.NONE);
		GridData data3 = new GridData(SWT.FILL, SWT.FILL, true, true);
		calendarMonthly.setLayoutData(data3);
	    
		calendarDaily = new CalendarDaily(this, SWT.NONE);
		GridData data4 = new GridData(SWT.FILL, SWT.FILL, true, true);
		calendarDaily.setLayoutData(data4);
		
		eventViewController.addEventViewListener(new EventViewListener() {
			@Override
			public void updateViewMode() {
				viewType = eventViewController.getViewType();
				updateVizibility();
			}
			
			@Override
			public void updateCurrentDate() {
				layout(true);
				setSize(computeSize(SWT.DEFAULT, SWT.DEFAULT));
			}
		});
	    
		this.updateVizibility();
	}
	
	private void updateVizibility() {
		switch (viewType) {
		case CALENDAR_DAILY:
			this.setControllVizibility(eventTable, false);
			this.setControllVizibility(calendarDaily, true);
			this.setControllVizibility(calendarWeekly, false);
			this.setControllVizibility(calendarMonthly, false);
			this.setControllVizibility(calendarYearly, false);
			break;
		case CALENDAR_WEEKLY:
			this.setControllVizibility(eventTable, false);
			this.setControllVizibility(calendarDaily, false);
			this.setControllVizibility(calendarWeekly, true);
			this.setControllVizibility(calendarMonthly, false);
			this.setControllVizibility(calendarYearly, false);
			break;
		case CALENDAR_MONTHLY:
			this.setControllVizibility(eventTable, false);
			this.setControllVizibility(calendarDaily, false);
			this.setControllVizibility(calendarWeekly, false);
			this.setControllVizibility(calendarMonthly, true);
			this.setControllVizibility(calendarYearly, false);
			break;
		case CALENDAR_YEARLY:
			this.setControllVizibility(eventTable, false);
			this.setControllVizibility(calendarDaily, false);
			this.setControllVizibility(calendarWeekly, false);
			this.setControllVizibility(calendarMonthly, false);
			this.setControllVizibility(calendarYearly, true);
			break;
		case TABLE:
			this.setControllVizibility(eventTable, true);
			this.setControllVizibility(calendarDaily, false);
			this.setControllVizibility(calendarWeekly, false);
			this.setControllVizibility(calendarMonthly, false);
			this.setControllVizibility(calendarYearly, false);
			break;
		}
		
		this.layout(true);
		this.setSize(this.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
	
	private void setControllVizibility(Control control, boolean show) {
		control.setEnabled(show);
		control.setVisible(show);
		GridData data = (GridData) control.getLayoutData();
        if (data != null) {
            data.exclude = !show;
        }
	}
}
