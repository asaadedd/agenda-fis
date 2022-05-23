import static org.junit.jupiter.api.Assertions.assertEquals;

class CalendarControllerTests{
  private final CalendarController calendarController = new CallendarController{};
  
  @Test
  void additionMonth() {
      assertEquals("Ianuarie", calendarController.getCurrentMonth(0));
  }
  
  void additionMonth1() {
      assertEquals("Decembrie", calendarController.getCurrentMonth(11));
  }
  
  void additionMonth2() {
      assertEquals("Iunie", calendarController.getCurrentMonth(5));
  }
  
  void additionDay() {
      assertEquals("Luni", calendarController.getCurrentDay(Calendar.MONDAY));
  }
  
   void additionDay1() {
      assertEquals("Duminica", calendarController.getCurrentDay(Calendar.SUNDAY));
  }
  
   void additionDay2() {
      assertEquals("Joi", calendarController.getCurrentDay(Calendar.THURSDAY  ));
  }
  
   void additionShortMonth() {
      assertEquals("Ian", calendarController.getCurrentShortMonth(0));
  }
  
  void additionShortMonth1() {
      assertEquals("Dec", calendarController.getCurrentShortMonth(11));
  }
  
  void additionShortMonth2() {
      assertEquals("Iuni", calendarController.getCurrentShortMonth(5));
  }
  
  
