package main.java.com.echipa4.agenda.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.ibm.icu.util.Calendar;

import main.java.com.echipa4.agenda.Database.EvenimentDao;
import main.java.com.echipa4.agenda.Model.Eveniment;
import main.java.com.echipa4.agenda.Model.Interval;
import main.java.com.echipa4.agenda.Model.Recurenta;
import main.java.com.echipa4.agenda.Model.Reperate;

public class EventControllerTest {
    private final EventController eventController = new EventController();

    @Test
    void itShouldGetEventsForOneHourInFullInterval() {
    	Date startDate = getDate(2022, 0, 0, 15, 0, 0);
    	Date endDate = getDate(2022, 0, 0, 16, 0, 0);
    	ArrayList<Eveniment> allEvents = getEvents();
    	ArrayList<Eveniment> expectedEvents = new ArrayList<Eveniment>();
    	ArrayList<Eveniment> actualEvents = eventController.getEventsForPeriod(allEvents, startDate, endDate);
    	
    	expectedEvents.add(allEvents.get(0));
    	expectedEvents.add(allEvents.get(1));    	
    	
    	assertEquals(expectedEvents, actualEvents);
    }
    
    @Test
    void itShouldGetEventsForOneHourOutOfStartInterval() {
    	Date startDate = getDate(2022, 0, 0, 2, 0, 0);
    	Date endDate = getDate(2022, 0, 0, 3, 0, 0);
    	ArrayList<Eveniment> allEvents = getEvents();
    	ArrayList<Eveniment> expectedEvents = new ArrayList<Eveniment>();
    	ArrayList<Eveniment> actualEvents = eventController.getEventsForPeriod(allEvents, startDate, endDate);
    	
    	assertEquals(expectedEvents, actualEvents);
    }
    
    @Test
    void itShouldGetEventsForOneHourOutOfEndInterval() {
    	System.out.println();
    	Date startDate = getDate(2022, 0, 0, 21, 0, 0);
    	Date endDate = getDate(2022, 0, 0, 23, 0, 0);
    	ArrayList<Eveniment> allEvents = getEvents();
    	ArrayList<Eveniment> expectedEvents = new ArrayList<Eveniment>();
    	ArrayList<Eveniment> actualEvents = eventController.getEventsForPeriod(allEvents, startDate, endDate);
    	
    	assertEquals(expectedEvents, actualEvents);
    }
    
    @Test
    void itShouldGetEventsForOneHourOutOfBothInterval() {
    	System.out.println();
    	Date startDate = getDate(2022, 0, 0, 22, 0, 0);
    	Date endDate = getDate(2022, 0, 0, 23, 0, 0);
    	ArrayList<Eveniment> allEvents = getEvents();
    	ArrayList<Eveniment> expectedEvents = new ArrayList<Eveniment>();
    	ArrayList<Eveniment> actualEvents = eventController.getEventsForPeriod(allEvents, startDate, endDate);
    	
    	assertEquals(expectedEvents, actualEvents);
    }
    
    @Test
    void itShouldGetEventsForDailyRec() {  
    	Date startDate = getDate(2022, 0, 1, 20, 0, 0);
    	Date endDate = getDate(2022, 0, 1, 21, 0, 0);
    	ArrayList<Eveniment> allEvents = getEvents();
    	ArrayList<Eveniment> expectedEvents = new ArrayList<Eveniment>();
    	ArrayList<Eveniment> actualEvents = eventController.getEventsForPeriod(allEvents, startDate, endDate);
    	
    	expectedEvents.add(allEvents.get(1));
    	
    	assertEquals(expectedEvents, actualEvents);
    }
    
    @Test
    void itShouldGetEventsForWeekly() {  
    	Date startDate = getDate(2022, 0, 7, 5, 0, 0);
    	Date endDate = getDate(2022, 0, 7, 7, 0, 0);
    	ArrayList<Eveniment> allEvents = getEvents();
    	ArrayList<Eveniment> expectedEvents = new ArrayList<Eveniment>();
    	ArrayList<Eveniment> actualEvents = eventController.getEventsForPeriod(allEvents, startDate, endDate);
    	
    	expectedEvents.add(allEvents.get(2));
    	
    	assertEquals(expectedEvents, actualEvents);
    }
    
    @Test
    void itShouldGetEventsForMontly() {  
    	Date startDate = getDate(2022, 1, 0, 5, 0, 0);
    	Date endDate = getDate(2022, 1, 0, 5, 30, 0);
    	ArrayList<Eveniment> allEvents = getEvents();
    	ArrayList<Eveniment> expectedEvents = new ArrayList<Eveniment>();
    	ArrayList<Eveniment> actualEvents = eventController.getEventsForPeriod(allEvents, startDate, endDate);
    	
    	expectedEvents.add(allEvents.get(3));
    	
    	assertEquals(expectedEvents, actualEvents);
    }
    
    @Test
    void itShouldGetEventsForYearly() {  
    	Date startDate = getDate(2023, 0, 0, 5, 0, 0);
    	Date endDate = getDate(2023, 0, 0, 5, 45, 0);
    	ArrayList<Eveniment> allEvents = getEvents();
    	ArrayList<Eveniment> expectedEvents = new ArrayList<Eveniment>();
    	ArrayList<Eveniment> actualEvents = eventController.getEventsForPeriod(allEvents, startDate, endDate);
    	
    	expectedEvents.add(allEvents.get(4));
    	
    	assertEquals(expectedEvents, actualEvents);
    }
    
    @Test
    void itShouldGetEventsForLittleTimeDiff() {  
    	Date startDate = getDate(2020, 0, 0, 19, 0, 0);
    	Date endDate = getDate(2020, 0, 0, 20, 0, 0);
    	ArrayList<Eveniment> allEvents = getEvents();
    	ArrayList<Eveniment> expectedEvents = new ArrayList<Eveniment>();
    	ArrayList<Eveniment> actualEvents = eventController.getEventsForPeriod(allEvents, startDate, endDate);
    	
    	expectedEvents.add(allEvents.get(5));
    	
    	assertEquals(expectedEvents, actualEvents);
    }
    
    @Test
    void itShouldGetEventsForSameEndAndStartDate() { 
    	Date startDate = getDate(2025, 0, 4, 0, 0, 0);
    	Date endDate = getDate(2025, 0, 5, 0, 0, 0);
    	ArrayList<Eveniment> allEvents = getEvents();
    	ArrayList<Eveniment> expectedEvents = new ArrayList<Eveniment>();
    	ArrayList<Eveniment> actualEvents = eventController.getEventsForPeriod(allEvents, startDate, endDate);
    	
    	assertEquals(expectedEvents, actualEvents);
    }
    
    @Test
    void itShouldReturnAllEvents() { 
    	ArrayList<Eveniment> allEvents = getEvents();
    	EvenimentDaoMock evenimentDaoMock = new EvenimentDaoMock();
    	evenimentDaoMock.events = allEvents;
    	Field instance;
		try {
			instance = EvenimentDao.class.getDeclaredField("evenimentDaoInstance");
	    	instance.setAccessible(true);
	    	instance.set(null, evenimentDaoMock);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e ) {
			e.printStackTrace();
		}

    	ArrayList<Eveniment> actualEvents = eventController.getAll();
    	
    	assertEquals(allEvents, actualEvents);
    }
    
    @Test
    void itShouldReturnThrowError() { 
    	EvenimentDaoMock evenimentDaoMock = new EvenimentDaoMock();
    	evenimentDaoMock.shouldThrow = true;
    	Field instance;
		try {
			instance = EvenimentDao.class.getDeclaredField("evenimentDaoInstance");
	    	instance.setAccessible(true);
	    	instance.set(null, evenimentDaoMock);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e ) {
			e.printStackTrace();
		}

    	ArrayList<Eveniment> actualEvents = eventController.getAll();
    	
    	assertEquals(new ArrayList<Eveniment>(), actualEvents);
    }
    
    private ArrayList<Eveniment> getEvents() {
    	ArrayList<Eveniment> eventsForPeriod = new ArrayList<Eveniment>();
    	Eveniment event1 = new Eveniment();
    	Interval interval1 = new Interval();
    	interval1.setDataInceput(getDate(2022, 0, 0, 15, 0, 0));
    	interval1.setDataSfarsit(getDate(2022, 0, 0, 16, 0, 0));
    	event1.setInterval(interval1);
    	eventsForPeriod.add(event1);

    	Eveniment event2 = new Eveniment();
    	Interval interval2 = new Interval();
    	interval2.setDataInceput(getDate(2022, 0, 0, 3, 0, 0));
    	interval2.setDataSfarsit(getDate(2022, 0, 0, 21, 0, 0));
    	event2.setInterval(interval2);
    	Recurenta recurenta = new Recurenta();
    	recurenta.setRecurenta(3);
    	recurenta.setRepetare(Reperate.DAILY);
    	event2.setRecurenta(recurenta);
    	eventsForPeriod.add(event2);

    	Eveniment event3 = new Eveniment();
    	Interval interval3 = new Interval();
    	interval3.setDataInceput(getDate(2022, 0, 0, 5, 30, 0));
    	interval3.setDataSfarsit(getDate(2022, 0, 0, 6, 0, 0));
    	event3.setInterval(interval3);
    	Recurenta recurenta1 = new Recurenta();
    	recurenta1.setRecurenta(1);
    	recurenta1.setRepetare(Reperate.WEEKLY);
    	event3.setRecurenta(recurenta1);
    	eventsForPeriod.add(event3);

    	Eveniment event4 = new Eveniment();
    	Interval interval4 = new Interval();
    	interval4.setDataInceput(getDate(2022, 0, 0, 4, 30, 0));
    	interval4.setDataSfarsit(getDate(2022, 0, 0, 6, 0, 0));
    	event4.setInterval(interval4);
    	Recurenta recurenta2 = new Recurenta();
    	recurenta2.setRecurenta(2);
    	recurenta2.setRepetare(Reperate.MONTHLY);
    	event4.setRecurenta(recurenta2);
    	eventsForPeriod.add(event4);

    	Eveniment event5 = new Eveniment();
    	Interval interval5 = new Interval();
    	interval5.setDataInceput(getDate(2022, 0, 0, 5, 30, 0));
    	interval5.setDataSfarsit(getDate(2022, 0, 0, 6, 0, 0));
    	event5.setInterval(interval5);
    	Recurenta recurenta3 = new Recurenta();
    	recurenta3.setRecurenta(5);
    	recurenta3.setRepetare(Reperate.YEARLY);
    	event5.setRecurenta(recurenta3);
    	eventsForPeriod.add(event5);

    	Eveniment event6 = new Eveniment();
    	Interval interval6 = new Interval();
    	interval6.setDataInceput(getDate(2020, 0, 0, 15, 3, 0));
    	interval6.setDataSfarsit(getDate(2020, 0, 0, 19, 3, 0));
    	event6.setInterval(interval6);
    	eventsForPeriod.add(event6);

    	Eveniment event7 = new Eveniment();
    	Interval interval7 = new Interval();
    	interval7.setDataInceput(getDate(2025, 0, 5, 0, 0, 0));
    	interval7.setDataSfarsit(getDate(2025, 0, 5, 0, 0, 0));
    	event7.setInterval(interval7);
    	eventsForPeriod.add(event7);
    	
    	return eventsForPeriod;
    }
    
    private Date getDate(int year, int month, int day, int hours, int min, int sec) {
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.YEAR, year);
    	cal.set(Calendar.MONTH, month);
    	cal.set(Calendar.DATE, day);
    	cal.set(Calendar.HOUR_OF_DAY, hours);
    	cal.set(Calendar.MINUTE, min);
    	cal.set(Calendar.SECOND, sec);
    	cal.set(Calendar.MILLISECOND, 0);
    	
    	return new Date(cal.getTimeInMillis());    	
    }
}


class EvenimentDaoMock extends EvenimentDao {
	public ArrayList<Eveniment> events = new ArrayList<Eveniment>();
	public boolean shouldThrow = false;
	
	@Override()
	public ArrayList<Eveniment> getEvents() throws SQLException {
		if (shouldThrow == false) {
			return events;
		} else {
			throw new SQLException("test message");
		}
	}
}
