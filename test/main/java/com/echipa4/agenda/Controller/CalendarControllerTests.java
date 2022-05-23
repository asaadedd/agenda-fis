import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import main.java.com.echipa4.agenda.Controller.CalendarController;

class CalendarControllerTests {
    private final CalendarController calendarController = new CalendarController();

    @Test
    void addition() {
        assertEquals("Ianuarie", calendarController.getCurrentMonth(0));
    }
}