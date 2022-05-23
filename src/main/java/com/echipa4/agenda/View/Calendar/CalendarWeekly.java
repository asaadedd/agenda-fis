package main.java.com.echipa4.agenda.View.Calendar;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import main.java.com.echipa4.agenda.Controller.EventController;
import main.java.com.echipa4.agenda.Controller.EventListener;
import main.java.com.echipa4.agenda.Controller.EventViewController;
import main.java.com.echipa4.agenda.Controller.EventViewListener;
import main.java.com.echipa4.agenda.Model.Eveniment;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CalendarWeekly extends Composite {
	private Calendar currentDate;
	private Label lblSaptamana;
	private Label lblNewLabel_1;
	private Label lblNewLabel_2;
	private Label lblNewLabel_3;
	private Label lblNewLabel_4;
	private Label lblNewLabel_5;
	private Label lblNewLabel_6;
	private Label lblNewLabel_7;
	private ArrayList<Control> hours = new ArrayList<Control>();
	private ArrayList<Eveniment> events = new ArrayList<Eveniment>();
	private EventController eventController = EventController.getInstance();
	private EventViewController eventViewController = EventViewController.getInstance();

	public CalendarWeekly(Composite parent, int style) {
		super(parent, style);
		currentDate = eventViewController.getCurrentDate();
		events = eventController.getAll();
		GridLayout gridLayout = new GridLayout(8, false);
		gridLayout.horizontalSpacing = 0;
		gridLayout.verticalSpacing = 0;
		setLayout(gridLayout);

		new Label(this, SWT.NONE);
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				eventViewController.decreaseByOneWeek();
			}
		});
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 150;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("Previous");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		lblSaptamana = new Label(this, SWT.NONE);
		GridData gd_lblSaptamana = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_lblSaptamana.widthHint = 150;
		lblSaptamana.setLayoutData(gd_lblSaptamana);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				eventViewController.increaseByOneWeek();
			}
		});
		GridData gd_btnNewButton_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_1.widthHint = 150;
		btnNewButton_1.setLayoutData(gd_btnNewButton_1);
		btnNewButton_1.setText("Next");

		Label lblNewLabel = new Label(this, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.widthHint = 50;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		
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
				
				updateWeekNumber();
				updateHeaders();
				updateHours();
			}
		});
		
		eventController.addEventListener(new EventListener() {
		    @Override
		    public void eventsUpdated() {
				events = eventController.getAll();
				
		    	updateWeekNumber();		
				updateHeaders();
				updateHours();
		    }
		});
		
		updateWeekNumber();		
		updateHeaders();
		updateHours();
	}
	
	private void updateWeekNumber() {
		lblSaptamana.setText("Saptamana " + currentDate.get(Calendar.WEEK_OF_YEAR));
	}
	
	private void updateHeaders() {
		lblNewLabel_1.setText("Luni " + getDateForHeader(0));
		lblNewLabel_2.setText("Marti " + getDateForHeader(1));
		lblNewLabel_3.setText("Miercuri " + getDateForHeader(2));
		lblNewLabel_4.setText("Joi " + getDateForHeader(3));
		lblNewLabel_5.setText("Vineri " + getDateForHeader(4));
		lblNewLabel_6.setText("Sambata " + getDateForHeader(5));
		lblNewLabel_7.setText("Duminica " + getDateForHeader(6));		
	}
	
	private void updateHours() {		
		hours.forEach((n) -> n.dispose());
		hours.clear();

		for(int hour = 0; hour < 24; hour++) {
			Label hoursNumber = new Label(this, SWT.BORDER | SWT.CENTER);
			GridData hoursGridData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			hoursGridData.widthHint = 50;
			hoursNumber.setLayoutData(hoursGridData);
			String hourText = hour < 10 ? "0" + hour + ":00" : hour + ":00";
			hoursNumber.setText(hourText);
			int maxHeight = 0;
			
			hours.add(hoursNumber);
			
			for(int day = 0; day < 7; day++) {
				int endDateDay = day;
				int endDateHour = hour + 1;
				if(hour == 24) {
					hour = 0;
					endDateDay = day + 1;
				}
				Date startDate = getDate(day, hour);
				Date endDate = getDate(endDateDay, endDateHour);
				CalendarEvents label = new CalendarEvents(this, SWT.BORDER | SWT.CENTER, eventController.getEventsForPeriod(events, startDate, endDate));
				GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gridData.widthHint = 150;
				label.setLayoutData(gridData);
				
				
				if (label.computeSize(SWT.DEFAULT, SWT.DEFAULT).y > maxHeight) {
					maxHeight = label.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
				}
				
				
				hours.add(label);
			}

			for (int index = 1; index <= 8; index ++) {
				Control control = hours.get(hours.size() - index);
				
				GridData gridData = (GridData) control.getLayoutData();
				gridData.heightHint = maxHeight;
			}
		}
		this.layout(true);
	}
	
	private Date getDate(int day, int hour) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTimeInMillis(currentDate.getTimeInMillis());
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		cal.add(Calendar.DATE, day);
		
		return new Date(cal.getTimeInMillis());
	}
	
	private String getDateForHeader(int day) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTimeInMillis(currentDate.getTimeInMillis());
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.clear(Calendar.HOUR_OF_DAY);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		cal.add(Calendar.DATE, day);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd MM");	
		
		return sdf.format(cal.getTime());
	}
}
