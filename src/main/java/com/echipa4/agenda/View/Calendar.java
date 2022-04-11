package main.java.com.echipa4.agenda.View;

import org.eclipse.swt.widgets.Composite;

import main.java.com.echipa4.agenda.Interfaces.CalendarTypes;

public class Calendar extends Composite {
	private CalendarTypes calendarType;
	
	public Calendar(Composite parent, int style, CalendarTypes type) {
		super(parent, style);
		calendarType = type;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void changeType(CalendarTypes type) {
		calendarType = type;		
	}

}
