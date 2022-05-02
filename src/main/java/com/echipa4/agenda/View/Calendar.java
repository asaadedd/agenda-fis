package main.java.com.echipa4.agenda.View;

import org.eclipse.swt.widgets.Composite;

import main.java.com.echipa4.agenda.Interfaces.CalendarTypes;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

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
