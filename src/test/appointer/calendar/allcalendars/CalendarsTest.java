package appointer.calendar.allcalendars;


/**
 * Test commands of AppCalendar;
 */
public class CalendarsTest {

	public static void main(String[] args) {
		Calendars calendars = new Calendars("Louis E. Reasoner");
		calendars.addAppointment("Eva Lu Ator");
		System.out.println(calendars);
	}
	
	
}