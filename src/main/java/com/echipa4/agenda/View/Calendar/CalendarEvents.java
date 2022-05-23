package main.java.com.echipa4.agenda.View.Calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import main.java.com.echipa4.agenda.Controller.CalendarController;
import main.java.com.echipa4.agenda.Model.Eveniment;
import main.java.com.echipa4.agenda.Model.Interval;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

public class CalendarEvents extends Composite {
	private CalendarController calendarController = CalendarController.getInstance();
	
	public CalendarEvents(Composite parent, int style, ArrayList<Eveniment> eventsForPeriod) {
		super(parent, style);
		setLayout(new FillLayout(SWT.VERTICAL));
		
		if (eventsForPeriod.size() == 0) {
			new Label(this, SWT.CENTER);
		} else {
	        for (Eveniment event : eventsForPeriod) {
	        	Interval interval = event.getInterval();
	        	String startDate = "";
	        	String endDate = "";
	        	if (interval != null) {
	        		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");	
	        		
	        		startDate = sdf.format(interval.getDataInceput().getTime());
	        		endDate = "-" +sdf.format(interval.getDataSfarsit().getTime());
	        		
	        	}

				Label label = new Label(this, SWT.CENTER | SWT.WRAP);
				label.setText(event.getTitlu() + "\n" + startDate + endDate);

				RGB rgb = new RGB(event.getCuloare().getRed(), event.getCuloare().getGreen(), event.getCuloare().getBlue());
				Color backgroundColor = new Color(rgb, event.getCuloare().getAlpha());
				Color textColor = calendarController.getContrastColor(backgroundColor);
				
				label.setBackground(backgroundColor);
				label.setForeground(textColor);
	        }
		}
	}
	public CalendarEvents(Composite parent, int style, ArrayList<Eveniment> eventsForPeriod, String headerText) {
		super(parent, style);
		setLayout(new FillLayout(SWT.VERTICAL));
		
		if (eventsForPeriod.size() == 0) {
			new Label(this, SWT.CENTER).setText(headerText);
		} else {
			new Label(this, SWT.CENTER).setText(headerText);
	        for (Eveniment event : eventsForPeriod) {
	        	Interval interval = event.getInterval();
	        	String startDate = "";
	        	String endDate = "";
	        	if (interval != null) {
	        		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");	
	        		
	        		startDate = sdf.format(interval.getDataInceput().getTime());
	        		endDate = "-" +sdf.format(interval.getDataSfarsit().getTime());
	        		
	        	}

				Label label = new Label(this, SWT.CENTER | SWT.WRAP);
				label.setText(event.getTitlu() + "\n" + startDate + endDate);

				RGB rgb = new RGB(event.getCuloare().getRed(), event.getCuloare().getGreen(), event.getCuloare().getBlue());
				Color backgroundColor = new Color(rgb, event.getCuloare().getAlpha());
				Color textColor = calendarController.getContrastColor(backgroundColor);
				
				label.setBackground(backgroundColor);
				label.setForeground(textColor);			
	        }
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
