package main.java.com.echipa4.agenda.View;

import org.eclipse.swt.widgets.Composite;

import main.java.com.echipa4.agenda.Interfaces.CalendarTypes;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.layout.GridData;

public class Calendar extends Composite {
	private CalendarTypes calendarType;
	private Label[] headers;
	
	public Calendar(Composite parent, int style, CalendarTypes type) {
		super(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(7, true);
		gridLayout.marginWidth = 0;
		setLayout(gridLayout);
		this.setSize(1000, 800);
		this.createHeader("Luni");
		this.createHeader("Marti");
		this.createHeader("Miercuri");
		this.createHeader("Joi");
		this.createHeader("Vineri");
		this.createHeader("Sambata");
		this.createHeader("Duminica");
		calendarType = CalendarTypes.MONTHLY;
	}
	
	private void createHeader(String text) {
		Label lblNewLabel_1 = new Label(this, SWT.BORDER | SWT.CENTER);
		GridData gd_lblNewLabel_1 = new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1);
		gd_lblNewLabel_1.widthHint = 133;
		lblNewLabel_1.setLayoutData(gd_lblNewLabel_1);
		lblNewLabel_1.setText(text);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void changeType(CalendarTypes type) {
		calendarType = type;
	}
}
