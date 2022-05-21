package main.java.com.echipa4.agenda.View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class CalendarMonthly extends Composite {
	private Label[] headers;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CalendarMonthly(Composite parent, int style) {
		super(parent, style);
		this.setSize(1000, 800);
		this.createHeader("Luni");
		this.createHeader("Marti");
		this.createHeader("Miercuri");
		this.createHeader("Joi");
		this.createHeader("Vineri");
		this.createHeader("Sambata");
		this.createHeader("Duminica");
	}
	
	private void createHeader(String text) {
		setLayout(new FillLayout(SWT.HORIZONTAL));
		Label lblNewLabel_1 = new Label(this, SWT.BORDER | SWT.CENTER);
		lblNewLabel_1.setText(text);
	}

}
