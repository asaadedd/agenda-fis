package main.java.com.echipa4.agenda.View;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import main.java.com.echipa4.agenda.Interfaces.EventViewTypes;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

public class EventView extends Composite {
	private EventViewTypes viewType;
	private EventTable eventTable;
	private CalendarWeekly calendarWeekly;
	private CalendarYearly calendarYearly;
	private CalendarMonthly calendarMonthly;
	
	public EventView(Composite parent, int style, EventViewTypes type) {
		super(parent, style);
		this.setSize(1000, 800);
		
		viewType = type;
		setLayout(new GridLayout(1, false));
		
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
	    
		this.updateVizibility();
	}
	
	public void changeType(EventViewTypes type) {
		viewType = type;
		this.updateVizibility();
	}
	
	private void updateVizibility() {
		switch (viewType) {
		case CALENDAR_WEEKLY:
			this.setControllVizibility(eventTable, false);
			this.setControllVizibility(calendarWeekly, true);
			this.setControllVizibility(calendarYearly, false);
			this.setControllVizibility(calendarMonthly, false);
			break;
		case CALENDAR_MONTHLY:
			this.setControllVizibility(eventTable, false);
			this.setControllVizibility(calendarWeekly, false);
			this.setControllVizibility(calendarYearly, true);
			this.setControllVizibility(calendarMonthly, false);
			break;
		case CALENDAR_YEARLY:
			this.setControllVizibility(eventTable, false);
			this.setControllVizibility(calendarWeekly, false);
			this.setControllVizibility(calendarYearly, false);
			this.setControllVizibility(calendarMonthly, true);
			break;
		case TABLE:
			this.setControllVizibility(eventTable, true);
			this.setControllVizibility(calendarWeekly, false);
			this.setControllVizibility(calendarYearly, false);
			this.setControllVizibility(calendarMonthly, false);
			break;
		}
		
		this.layout(true);
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
