package main.java.com.echipa4.agenda.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

class CalendarControllerTests {
    private final CalendarController calendarController = new CalendarController();

    @Test
    void addition() {
        assertEquals("Ianuarie", calendarController.getCurrentMonth(0));
    }

@Test
void getCurrentMonthFeb() {
    assertEquals("Februarie", calendarController.getCurrentMonth(1));
}

@Test
void getCurrentMonthMar() {
    assertEquals("Martie", calendarController.getCurrentMonth(2));
}
@Test
void getCurrentMonthApr() {
    assertEquals("Aprilie", calendarController.getCurrentMonth(3));
}
@Test
    void getCurrentMonthMai() {
        assertEquals("Mai", calendarController.getCurrentMonth(4));
    }
@Test
    void getCurrentMonthIun() {
        assertEquals("Iunie", calendarController.getCurrentMonth(5));
    }
@Test
    void getCurrentMonthIul() {
        assertEquals("Iulie", calendarController.getCurrentMonth(6));
    }
@Test
    void getCurrentMonthAug() {
        assertEquals("August", calendarController.getCurrentMonth(7));
    }
@Test
    void getCurrentMonthSept() {
        assertEquals("Septembrie", calendarController.getCurrentMonth(8));
    }
@Test
    void getCurrentMonthOct() {
        assertEquals("Octombrie", calendarController.getCurrentMonth(9));
    }
@Test
    void getCurrentMonthNoi() {
        assertEquals("Noiembrie", calendarController.getCurrentMonth(10));
    }
@Test
    void getCurrentMonthDec() {
        assertEquals("Decembrie", calendarController.getCurrentMonth(11));
        
}
@Test
void getCurrentMonthError() {
    assertEquals("", calendarController.getCurrentMonth(12));
}
@Test
void getCurrentDayMonday() {
    assertEquals("Luni", calendarController.getCurrentDay(Calendar.MONDAY));
}
@Test
void getCurrentDayTuesday() {
    assertEquals("Marti", calendarController.getCurrentDay(Calendar.TUESDAY));
}
    @Test
    void getCurrentDayWednesday() {
        assertEquals("Miercuri", calendarController.getCurrentDay(Calendar.WEDNESDAY));
    }
    @Test
    void getCurrentDayThursday() {
        assertEquals("Joi", calendarController.getCurrentDay(Calendar.THURSDAY));
    }
    @Test
    void getCurrentDayFriday() {
        assertEquals("Vineri", calendarController.getCurrentDay(Calendar.FRIDAY));
    }
    @Test
    void getCurrentDaySaturday() {
        assertEquals("Sambata", calendarController.getCurrentDay(Calendar.SATURDAY));
    }
    @Test
    void getCurrentDaySunday() {
        assertEquals("Duminica", calendarController.getCurrentDay(Calendar.SUNDAY));
    }
    
    @Test
    void getCurrentDayError() {
        assertEquals("", calendarController.getCurrentDay(0));
    }
    
    
}

   