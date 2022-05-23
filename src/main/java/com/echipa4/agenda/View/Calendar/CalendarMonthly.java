package main.java.com.echipa4.agenda.View.Calendar;

import java.sql.Date;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import main.java.com.echipa4.agenda.Controller.CalendarController;
import main.java.com.echipa4.agenda.Controller.EventController;
import main.java.com.echipa4.agenda.Controller.EventListener;
import main.java.com.echipa4.agenda.Controller.EventViewController;
import main.java.com.echipa4.agenda.Controller.EventViewListener;
import main.java.com.echipa4.agenda.Model.Eveniment;

public class CalendarMonthly extends Composite {
	private Calendar currentDate;
	private Label lblLuna;
	private Label lblNewLabel_1;
	private Label lblNewLabel_2;
	private Label lblNewLabel_3;
	private Label lblNewLabel_4;
	private Label lblNewLabel_5;
	private Label lblNewLabel_6;
	private Label lblNewLabel_7;
	private ArrayList<Control> days = new ArrayList<Control>();
	private ArrayList<Eveniment> events = new ArrayList<Eveniment>();
	private EventController eventController = EventController.getInstance();
	private EventViewController eventViewController = EventViewController.getInstance();
	private CalendarController calendarController = CalendarController.getInstance();
	
	public CalendarMonthly(Composite parent, int style) {
		super(parent, style);

		currentDate = eventViewController.getCurrentDate();
		events = eventController.getAll();
		GridLayout gridLayout = new GridLayout(7, false);
		gridLayout.horizontalSpacing = 0;
		gridLayout.verticalSpacing = 0;
		gridLayout.marginLeft = 50;
		setLayout(gridLayout);

		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				eventViewController.decreaseByOneMonth();
			}
		});
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 150;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("Previous");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		lblLuna = new Label(this, SWT.NONE);
		GridData gd_lblSaptamana = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_lblSaptamana.widthHint = 150;
		lblLuna.setLayoutData(gd_lblSaptamana);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				eventViewController.increaseByOneMonth();
			}
		});
		GridData gd_btnNewButton_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_1.widthHint = 150;
		btnNewButton_1.setLayoutData(gd_btnNewButton_1);
		btnNewButton_1.setText("Next");
		
		lblNewLabel_1 = new Label(this, SWT.BORDER | SWT.CENTER);
		GridData gd_lblNewLabel_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_1.widthHint = 150;
		lblNewLabel_1.setLayoutData(gd_lblNewLabel_1);

		lblNewLabel_2 = new Label(this, SWT.BORDER | SWT.CENTER);
		GridData gd_lblNewLabel_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_2.widthHint = 150;
		lblNewLabel_2.setLayoutData(gd_lblNewLabel_2);

		lblNewLabel_3 = new Label(this, SWT.BORDER | SWT.CENTER);
		GridData gd_lblNewLabel_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_3.widthHint = 150;
		lblNewLabel_3.setLayoutData(gd_lblNewLabel_3);

		lblNewLabel_4 = new Label(this, SWT.BORDER | SWT.CENTER);
		GridData gd_lblNewLabel_4 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_4.widthHint = 150;
		lblNewLabel_4.setLayoutData(gd_lblNewLabel_4);

		lblNewLabel_5 = new Label(this, SWT.BORDER | SWT.CENTER);
		GridData gd_lblNewLabel_5 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_5.widthHint = 150;
		lblNewLabel_5.setLayoutData(gd_lblNewLabel_5);

		lblNewLabel_6 = new Label(this, SWT.BORDER | SWT.CENTER);
		GridData gd_lblNewLabel_6 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_6.widthHint = 150;
		lblNewLabel_6.setLayoutData(gd_lblNewLabel_6);

		lblNewLabel_7 = new Label(this, SWT.BORDER | SWT.CENTER);
		GridData gd_lblNewLabel_7 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_7.widthHint = 150;
		lblNewLabel_7.setLayoutData(gd_lblNewLabel_7);
		
		eventViewController.addEventViewListener(new EventViewListener() {
			@Override
			public void updateCurrentDate() {
				currentDate = eventViewController.getCurrentDate();
				
				updateMonth();
				updateHeaders();
				updateDays();
			}
		});
		
		eventController.addEventListener(new EventListener() {
		    @Override
		    public void eventsUpdated() {
				events = eventController.getAll();
				
				updateMonth();		
				updateHeaders();
				updateDays();
		    }
		});
		
		updateMonth();		
		updateHeaders();
		updateDays();
	}
	
	private void updateMonth() {
		lblLuna.setText(calendarController.getCurrentMonth(currentDate.get(Calendar.MONTH)) + " " + currentDate.get(Calendar.YEAR));
	}
	
	private void updateHeaders() {
		lblNewLabel_1.setText("Luni ");
		lblNewLabel_2.setText("Marti ");
		lblNewLabel_3.setText("Miercuri");
		lblNewLabel_4.setText("Joi");
		lblNewLabel_5.setText("Vineri");
		lblNewLabel_6.setText("Sambata");
		lblNewLabel_7.setText("Duminica");		
	}
	
	private void updateDays() {		
		days.forEach((n) -> n.dispose());
		days.clear();
		
		addDaysFromPreviousMonth();
		
		for(int day = 0; day < calendarController.getDaysInMonth(currentDate); day++) {
			Date startDate = getDate(day);
			Date endDate = getDate(day + 1);
			String header = day == 0 ? calendarController.getCurrentShortMonth(currentDate.get(Calendar.MONTH)) + " " + (day + 1) : ( day + 1) + "";
			CalendarEvents label = new CalendarEvents(this, SWT.BORDER | SWT.CENTER, eventController.getEventsForPeriod(events, startDate, endDate), header);
			GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gridData.widthHint = 150;
			label.setLayoutData(gridData);
			
			days.add(label);
		}
		updateHeights();
		this.layout(true);
	}
	
	private void addDaysFromPreviousMonth() {
		int daysToAdd = calendarController.getDaysFromPreviousMonth(currentDate);
	
		for(int day = 1; day <= daysToAdd; day++) {
			Date startDate = getDate(0 - day - 1);
			Date endDate = getDate(0 - day);
			Calendar previousMonth = Calendar.getInstance();
			previousMonth.setTimeInMillis(currentDate.getTimeInMillis());
			previousMonth.add(Calendar.MONTH, -1);
			int daysInMonth = calendarController.getDaysInMonth(previousMonth);
			String header = (daysInMonth - daysToAdd + day) + "";
			CalendarEvents label = new CalendarEvents(this, SWT.BORDER | SWT.CENTER, eventController.getEventsForPeriod(events, startDate, endDate), header);
			GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gridData.widthHint = 150;
			label.setLayoutData(gridData);
			
			days.add(label);
		}
	}
	
	private void updateHeights() {
		int maxHeight = 0;
		ArrayList<Control> controls = new ArrayList<Control>();
		for(int index = 0; index < days.size(); index++) {
			Control control = days.get(index);
			
			if (control.computeSize(SWT.DEFAULT, SWT.DEFAULT).y > maxHeight) {
				maxHeight = control.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
			}

			controls.add(control);
			
			boolean isSunday = (index + 1) % 7 == 0;
			boolean isEndOfMonth = index == (days.size() - 1);
			
			if (isSunday || isEndOfMonth) {
				for (Control ctr: controls) {
					GridData gridData = (GridData) ctr.getLayoutData();
					gridData.heightHint = maxHeight;
				}
				controls.clear();
				maxHeight = 0;
			}
		}
		
	}
	
	private Date getDate(int day) {
		Calendar firstDayOfMonth = calendarController.getFirstDayOfMonth(currentDate);
		firstDayOfMonth.add(Calendar.DATE, day);
		
		return new Date(firstDayOfMonth.getTimeInMillis());
	}
}
