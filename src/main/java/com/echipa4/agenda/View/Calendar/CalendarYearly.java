package main.java.com.echipa4.agenda.View.Calendar;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import main.java.com.echipa4.agenda.Controller.CalendarController;
import main.java.com.echipa4.agenda.Controller.EventController;
import main.java.com.echipa4.agenda.Controller.EventListener;
import main.java.com.echipa4.agenda.Controller.EventViewController;
import main.java.com.echipa4.agenda.Controller.EventViewListener;
import main.java.com.echipa4.agenda.Interfaces.EventViewTypes;
import main.java.com.echipa4.agenda.Model.Eveniment;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.wb.swt.SWTResourceManager;

public class CalendarYearly extends Composite {
	private Calendar currentDate;
	private Label yearLabel;
	private ArrayList<Composite> months = new ArrayList<Composite>();
	private ArrayList<Eveniment> events = new ArrayList<Eveniment>();
	private EventController eventController = EventController.getInstance();
	private EventViewController eventViewController = EventViewController.getInstance();
	private CalendarController calendarController = CalendarController.getInstance();
	
	public CalendarYearly(Composite parent, int style) {
		super(parent, style);
		currentDate = eventViewController.getCurrentDate();
		events = eventController.getAll();
		GridLayout gridLayout = new GridLayout(4, true);
		gridLayout.horizontalSpacing = 50;
		gridLayout.verticalSpacing = 50;
		setLayout(gridLayout);
		
		updateHeaders();
		updateYear();
		updateYearNumber();

		eventViewController.addEventViewListener(new EventViewListener() {
			@Override
			public void updateCurrentDate() {
				currentDate = eventViewController.getCurrentDate();
				
				updateYear();
				updateYearNumber();
			}
		});

		eventController.addEventListener(new EventListener() {
		    @Override
		    public void eventsUpdated() {
				events = eventController.getAll();
				
				updateYear();
				updateYearNumber();
		    }
		});

	}
	
	private void updateHeaders() {
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				eventViewController.decreaseByOneYear();
			}
		});
		btnNewButton.setText("Previous");
		
		yearLabel = new Label(this, SWT.CENTER);
		yearLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				eventViewController.increaseByOneYear();
			}
		});
		btnNewButton_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnNewButton_1.setText("Next");
	}
	
	private void updateYear() {
		months.forEach((n) -> n.dispose());
		months.clear();
		
		for(int index = 0; index < 12; index++) {
			Calendar monthDate = getCurrentMonth(index);
			Composite month = new Composite(this, SWT.NONE);
			GridLayout gridLayout = new GridLayout(1, true);
			month.setLayout(gridLayout);
			
			Composite header = new Composite(month, SWT.NONE);
			GridLayout headerGridLayout = new GridLayout(1, true);
			header.setLayout(headerGridLayout);
			Label label = new Label(header, SWT.NONE);
			label.setText(calendarController.getCurrentMonth(index));

			Composite days = new Composite(month, SWT.NONE);
			GridLayout daysGridLayout = new GridLayout(7, true);
			daysGridLayout.horizontalSpacing = 10;
			daysGridLayout.verticalSpacing = 10;
			days.setLayout(daysGridLayout);
			
			addMonthHeaders(days);
			addDaysFromPreviousMonth(days, monthDate);
			
			for(int day = 0; day < calendarController.getDaysInMonth(monthDate); day++) {
				int dateNumber = day + 1;
				String text = dateNumber + "";
				Calendar startDate = getCurrentMonth(index);
				Calendar endDate = getCurrentMonth(index);
				startDate.add(Calendar.DATE, day);
				endDate.add(Calendar.DATE, day + 1);
				
				ArrayList<Eveniment> dayEvents = eventController.getEventsForPeriod(events, new Date(startDate.getTimeInMillis()), new Date(endDate.getTimeInMillis()));
				Button dateLabel = new Button(days, SWT.CENTER);
				GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				
				dateLabel.setBackground(new Color(240, 240, 240));
				label.setLayoutData(gridData);
				dateLabel.setText(text);
				dateLabel.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						Calendar cal = Calendar.getInstance();
						cal.setTimeInMillis(monthDate.getTimeInMillis());
						cal.set(Calendar.DATE, dateNumber);
						eventViewController.setCurrentDate(cal);
						eventViewController.changeViewType(EventViewTypes.CALENDAR_DAILY);
					}
				});
				
				if (dayEvents.size() > 0) {
					if (dayEvents.size() == 1) {
						Eveniment event = dayEvents.get(0);
						if (event.getCuloare() != null) {
							Color backgroundColor = new Color(event.getCuloare().getRed(), event.getCuloare().getGreen(), event.getCuloare().getBlue());
							Color textColor = calendarController.getContrastColor(new Color(event.getCuloare().getRed(), event.getCuloare().getGreen(), event.getCuloare().getBlue()));
							dateLabel.setBackground(backgroundColor);
							dateLabel.setForeground(textColor);
						}
					} else {
						dateLabel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
					}
				}
			}

			months.add(month);			
		}
		
		this.layout(true);
	}
	
	private void updateYearNumber() {
		yearLabel.setText("Anul " + currentDate.get(Calendar.YEAR));
	}
	
	private void addMonthHeaders(Composite month) {
		Label lblNewLabel_1 = new Label(month, SWT.CENTER);
		GridData gd_lblNewLabel_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		lblNewLabel_1.setLayoutData(gd_lblNewLabel_1);
		lblNewLabel_1.setText("L");

		Label lblNewLabel_2 = new Label(month, SWT.CENTER);
		GridData gd_lblNewLabel_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		lblNewLabel_2.setLayoutData(gd_lblNewLabel_2);
		lblNewLabel_2.setText("M");

		Label lblNewLabel_3 = new Label(month, SWT.CENTER);
		GridData gd_lblNewLabel_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		lblNewLabel_3.setLayoutData(gd_lblNewLabel_3);
		lblNewLabel_3.setText("M");

		Label lblNewLabel_4 = new Label(month, SWT.CENTER);
		GridData gd_lblNewLabel_4 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		lblNewLabel_4.setLayoutData(gd_lblNewLabel_4);
		lblNewLabel_4.setText("J");

		Label lblNewLabel_5 = new Label(month, SWT.CENTER);
		GridData gd_lblNewLabel_5 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		lblNewLabel_5.setLayoutData(gd_lblNewLabel_5);
		lblNewLabel_5.setText("V");

		Label lblNewLabel_6 = new Label(month, SWT.CENTER);
		GridData gd_lblNewLabel_6 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		lblNewLabel_6.setLayoutData(gd_lblNewLabel_6);
		lblNewLabel_6.setText("S");

		Label lblNewLabel_7 = new Label(month, SWT.CENTER);
		GridData gd_lblNewLabel_7 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		lblNewLabel_7.setLayoutData(gd_lblNewLabel_7);
		lblNewLabel_7.setText("D");
	}
	
	private void addDaysFromPreviousMonth(Composite month, Calendar monthDate) {
		int daysToAdd = calendarController.getDaysFromPreviousMonth(monthDate);
	
		for(int day = 1; day <= daysToAdd; day++) {
			Calendar previousMonth = Calendar.getInstance();
			previousMonth.setTimeInMillis(monthDate.getTimeInMillis());
			previousMonth.add(Calendar.MONTH, -1);
			
			int daysInMonth = calendarController.getDaysInMonth(previousMonth);
			int dateNumber = daysInMonth - daysToAdd + day;
			String text = dateNumber + "";

			Calendar startDate = Calendar.getInstance();
			startDate.setTimeInMillis(previousMonth.getTimeInMillis());
			Calendar endDate = Calendar.getInstance();
			endDate.setTimeInMillis(previousMonth.getTimeInMillis());
			startDate.set(Calendar.DATE, dateNumber);
			endDate.set(Calendar.DATE, dateNumber + 1);
			
			ArrayList<Eveniment> dayEvents = eventController.getEventsForPeriod(events, new Date(startDate.getTimeInMillis()), new Date(endDate.getTimeInMillis()));
			
			Button label = new Button(month, SWT.FLAT);
			GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			label.setBackground(new Color(240, 240, 240));
			label.setGrayed(true);
			label.setLayoutData(gridData);
			label.setText(text);
			label.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					Calendar cal = Calendar.getInstance();
					cal.setTimeInMillis(previousMonth.getTimeInMillis());
					cal.set(Calendar.DATE, dateNumber);
					eventViewController.setCurrentDate(cal);
					eventViewController.changeViewType(EventViewTypes.CALENDAR_DAILY);
				}
			});
			if (dayEvents.size() > 0) {
				if (dayEvents.size() == 1) {
					Eveniment event = dayEvents.get(0);
					if (event.getCuloare() != null) {
						Color backgroundColor = new Color(event.getCuloare().getRed(), event.getCuloare().getGreen(), event.getCuloare().getBlue());
						Color textColor = calendarController.getContrastColor(new Color(event.getCuloare().getRed(), event.getCuloare().getGreen(), event.getCuloare().getBlue()));
						label.setBackground(backgroundColor);
						label.setForeground(textColor);
					}
				} else {
					label.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
				}
			}
		}
	}
	
	private Calendar getCurrentMonth(int month) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTimeInMillis(currentDate.getTimeInMillis());
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		
		return cal;
	}

}
