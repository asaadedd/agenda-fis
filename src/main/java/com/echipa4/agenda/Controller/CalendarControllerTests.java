package main.java.com.echipa4.agenda.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CalendarControllerTests {
    private final CalendarController calendarController = new CalendarController();

    @Test
    void addition() {
        assertEquals("Ianuarie", calendarController.getCurrentMonth(0));
    }
}