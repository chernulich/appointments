package appointer;

import appointer.calendar.AppCalendar;
import appointer.calendar.CalendarPrinter;
import appointer.calendar.EventFacade;
import appointer.user.AppUser;
import appointer.user.User;
import biweekly.component.VEvent;
import biweekly.util.Frequency;

public class AppCalendarTest {

	// TODO: test methods for instance of AppCalendar
	public static void main(String[] args) {

		createJaneEventAndPrint();
		
		recurrenceDemo();
	}

	/**
	 * Recurrency works as event recurrence rule that holds recurrence as value;
	 */
	private static void recurrenceDemo() {

		VEvent vEventOne = EventFacade.createEventCurrentTime(); // we still might need an adapter;

		EventFacade.setEventRepeats(vEventOne, Frequency.DAILY);

		EventFacade.getLocalDateStream(vEventOne).limit(100).forEach(System.out::println);
		
		System.out.println("Recurring event display finished");
	}



	/**
	 * Creates and prints an event belonging to user;
	 */
	public static void createJaneEventAndPrint() {

		User user = new AppUser("Jane Smith");

		AppCalendar appCalendar = AppCalendar.getAppCalendar(user);

		VEvent vEventOne = EventFacade.createEventCurrentTime(); // we still might need an adapter;

		EventFacade.setOrganiser(vEventOne, user.getName());

		appCalendar.getCalendar().addEvent(vEventOne);

		CalendarPrinter.printCalendar(appCalendar.getCalendar());

		System.out.println("Event display finished");
	}
}
