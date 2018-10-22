package appointer;

import appointer.calendar.AppCalendar;
import appointer.calendar.CalendarPrinter;
import appointer.calendar.EventFacade;
import appointer.commands.CmdAddEvent;
import appointer.commands.CmdSetEventDuration;
import appointer.commands.CmdSetEventRepeats;
import appointer.user.AppUser;
import appointer.user.User;
import biweekly.component.VEvent;
import biweekly.util.Duration;
import biweekly.util.Frequency;

public class AppCalendarTest {

	// TODO: test methods for instance of AppCalendar
	public static void main(String[] args) {

		commandTest();

		createJaneEventAndPrint();
		
		recurrenceDemo();
		// next step is to try free busy test;
	}

	/**
	 * Tests undo-redo functionality of the command pattern
	 */
	private static void commandTest() {
		
		AppCalendar appCalendar = AppCalendar.getAppCalendar(new AppUser("Alyssa P. Hacker"));

		VEvent event = EventFacade.createEventCurrentTime();

		CmdAddEvent cmdAE = new CmdAddEvent(appCalendar, event);

		cmdAE.execute();

		CalendarPrinter.printCalendar(appCalendar.getCalendar());

		cmdAE.undo();

		CalendarPrinter.printCalendar(appCalendar.getCalendar());

		cmdAE.execute();

		CalendarPrinter.printCalendar(appCalendar.getCalendar());

		CmdSetEventDuration cmdSD = new CmdSetEventDuration(appCalendar, event, Duration.builder().hours(1).build());

		cmdSD.execute();

		CalendarPrinter.printCalendar(appCalendar.getCalendar());

		cmdSD.undo();

		CalendarPrinter.printCalendar(appCalendar.getCalendar());

		cmdSD.execute();

		CalendarPrinter.printCalendar(appCalendar.getCalendar());

		CmdSetEventRepeats cmdSR = new CmdSetEventRepeats(appCalendar, event, Frequency.DAILY);

		cmdSR.execute();

		CalendarPrinter.printCalendar(appCalendar.getCalendar());

		cmdSR.undo();

		CalendarPrinter.printCalendar(appCalendar.getCalendar());

		cmdSR.execute();

		CalendarPrinter.printCalendar(appCalendar.getCalendar());
		
		System.out.println("Testing commands finished");
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
