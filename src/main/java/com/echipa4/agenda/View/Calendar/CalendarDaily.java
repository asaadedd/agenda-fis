package main.java.com.echipa4.agenda.View.Calendar;

import java.sql.Date;
import java.text.SimpleDateFormat;
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

public class CalendarDaily extends Composite {
	private Calendar currentDate;
	private Label lblDay;
	private ArrayList<Control> hours = new ArrayList<Control>();
	private ArrayList<Eveniment> events = new ArrayList<Eveniment>();
	private EventController eventController = EventController.getInstance();
	private EventViewController eventViewController = EventViewController.getInstance();
	private CalendarController calendarController = CalendarController.getInstance();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CalendarDaily(Composite parent, int style) {
		super(parent, style);
		currentDate = eventViewController.getCurrentDate();
		events = eventController.getAll();
		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.horizontalSpacing = 0;
		gridLayout.verticalSpacing = 0;
		gridLayout.marginRight = 50;
		setLayout(gridLayout);

		new Label(this, SWT.NONE);
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				eventViewController.decreaseByOneDay();
			}
		});
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		btnNewButton.setLayoutData(gd_btnNewButton);
		gd_btnNewButton.widthHint = 150;
		btnNewButton.setText("Previous");
		
		lblDay = new Label(this, SWT.CENTER);
		GridData gd_lblSaptamana = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_lblSaptamana.widthHint = 750;
		lblDay.setLayoutData(gd_lblSaptamana);
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				eventViewController.increaseByOneDay();
			}
		});
		GridData gd_btnNewButton_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_1.widthHint = 150;
		btnNewButton_1.setLayoutData(gd_btnNewButton_1);
		btnNewButton_1.setText("Next");
		
		updateDay();
		updateHours();
		updateHeights();
		
		eventViewController.addEventViewListener(new EventViewListener() {
			@Override
			public void updateCurrentDate() {
				currentDate = eventViewController.getCurrentDate();
				
				updateDay();
				updateHours();
				updateHeights();
			}
		});
		
		eventController.addEventListener(new EventListener() {
		    @Override
		    public void eventsUpdated() {
				events = eventController.getAll();
				
				updateDay();
				updateHours();
				updateHeights();
		    }
		});

	}
	
	private void updateDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MM");
		
		lblDay.setText(calendarController.getCurrentDay(currentDate.get(Calendar.DAY_OF_WEEK)) + "\n" + sdf.format(currentDate.getTime()));
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
			
			hours.add(hoursNumber);

			int endDateDay = 0;
			int endDateHour = hour + 1;
			if(hour == 24) {
				hour = 0;
				endDateDay = 1;
			}
			Date startDate = getDate(0, hour);
			Date endDate = getDate(endDateDay, endDateHour);				
			
			CalendarEvents label = new CalendarEvents(this, SWT.BORDER | SWT.CENTER, eventController.getEventsForPeriod(events, startDate, endDate));
			GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
			gridData.widthHint = 1050;
			label.setLayoutData(gridData);
			
			hours.add(label);
		}
		this.layout(true);
	}
	
	private Date getDate(int day, int hour) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTimeInMillis(currentDate.getTimeInMillis());
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		cal.add(Calendar.DATE, day);
		
		return new Date(cal.getTimeInMillis());
	}
	
	private void updateHeights() {
		for(int index = 0; index < hours.size(); index = index + 2) {
			Control label = hours.get(index);
			Control day = hours.get(index + 1);

			GridData gridData = (GridData) label.getLayoutData();
			gridData.heightHint = day.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
		}
	}
}
