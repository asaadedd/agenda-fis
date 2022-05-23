package main.java.com.echipa4.agenda.Controller;

import java.time.YearMonth;
import java.util.Calendar;

import org.eclipse.swt.graphics.Color;

public class CalendarController {
	private static CalendarController instance = null;

	public static CalendarController getInstance() {
		if (instance == null) { 
			instance = new CalendarController();
		}
		
		return instance;
	}
	
	public String getCurrentMonth(int month) {		
		switch(month) {
		case 0:
			return "Ianuarie";
		case 1:
			return "Februarie";
		case 2:
			return "Martie";
		case 3:
			return "Aprilie";
		case 4:
			return "Mai";
		case 5:
			return "Iunie";
		case 6:
			return "Iulie";
		case 7:
			return "August";
		case 8:
			return "Septembrie";
		case 9:
			return "Octombrie";
		case 10:
			return "Noiembrie";
		case 11:
			return "Decembrie";
		default:
			return "";
		}
	}
	
	public String getCurrentDay(int day) {		
		switch(day) {
		case Calendar.MONDAY:
			return "Luni";
		case Calendar.TUESDAY:
			return "Marti";
		case Calendar.WEDNESDAY:
			return "Miercuri";
		case Calendar.THURSDAY:
			return "Joi";
		case Calendar.FRIDAY:
			return "Vineri";
		case Calendar.SATURDAY:
			return "Sambata";
		case Calendar.SUNDAY:
			return "Duminca";
		default:
			return "";
		}
	}
	
	public String getCurrentShortMonth(int month) {		
		switch(month) {
		case 0:
			return "Ian";
		case 1:
			return "Feb";
		case 2:
			return "Mar";
		case 3:
			return "Apr";
		case 4:
			return "Mai";
		case 5:
			return "Iun";
		case 6:
			return "Iul";
		case 7:
			return "Aug";
		case 8:
			return "Sap";
		case 9:
			return "Oct";
		case 10:
			return "Noi";
		case 11:
			return "Dec";
		default:
			return "";
		}
	}

	public Calendar getFirstDayOfMonth(Calendar currentDate) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTimeInMillis(currentDate.getTimeInMillis());
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.clear(Calendar.HOUR_OF_DAY);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		
		return cal;
	}
	
	public int getDaysFromPreviousMonth(Calendar currentDate) {
		Calendar firstDayOfMonth = getFirstDayOfMonth(currentDate);
		int dayOfTheWeek = firstDayOfMonth.get(Calendar.DAY_OF_WEEK);
		int daysToAdd = 0;
		if (dayOfTheWeek != Calendar.MONDAY) {
			daysToAdd = (dayOfTheWeek - 2);
			if (daysToAdd < 0) {
				daysToAdd = 6;
			}
		}
		
		return daysToAdd;
	}
	
	public int getDaysInMonth(Calendar currentDate) {
		YearMonth yearMonthObject = YearMonth.of(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH) + 1);
		
		return yearMonthObject.lengthOfMonth();
	}
	
	public Color getContrastColor(Color color)
	{
	    int d = 0;
	      
	    double luminance = (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue())/255;
	    
	    if (luminance > 0.5)
	       d = 0;
	    else
	       d = 255;
	                
	    return new Color(d, d, d);
	}

}
