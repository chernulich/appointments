package appointer.calendar.single;

import appointer.calendar.single.EventFacade;
import appointer.calendar.single.ICalendarsLocal;
import appointer.calendar.single.SingleCalendar;
import appointer.user.AppUser;
import appointer.user.IUser;
import appointer.util.io.console.CalendarPrinter;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.util.Frequency;


// add to tests that a localtime free/busy;

public class EventFacadeTest {

	/**
	 * Exploration of biWeekly ICalendar and VEvent; 
	 */
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

		IUser user = new AppUser("Jane Smith");

		ICalendar appCalendar = SingleCalendar.getLocalCalendar(user);

		VEvent vEventOne = EventFacade.createEventCurrentTime(); // we still might need an adapter;

		EventFacade.setOrganiser(vEventOne, user.getName());

		appCalendar.addEvent(vEventOne);

		CalendarPrinter.printCalendar(appCalendar);

		System.out.println("Event display finished");
	}
}
