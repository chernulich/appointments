package appointer.calendar;

import appointer.calendar.AppCalendar;
import appointer.calendar.CalendarPrinter;
import appointer.calendar.EventFacade;
import appointer.commands.AppCommand;
import appointer.commands.CmdAddEvent;
import appointer.commands.CmdComposite;
import appointer.commands.CmdSetEventDuration;
import appointer.commands.CmdSetEventRepeats;
import appointer.user.AppUser;
import biweekly.component.VEvent;
import biweekly.util.Duration;
import biweekly.util.Frequency;

/**
 * Test commands of AppCalendar;
 */
public class AppCommandsTest {

	public static void main(String[] args) {

		compositeCommandTest();
		// next step is to try free busy test; or something like the command queue;
	}
	
	/**
	 * Tests composite command;
	 */
	private static void compositeCommandTest() {
		AppCalendar appCalendar = AppCalendar.getAppCalendar(new AppUser("Alyssa P. Hacker"));

		VEvent event = EventFacade.createEventCurrentTime();

		AppCommand cmdComposite = new CmdComposite(appCalendar, event);

		cmdComposite.add(new CmdAddEvent(appCalendar, event));

		cmdComposite.add(new CmdSetEventDuration(appCalendar, event, Duration.builder().hours(1).build()));

		cmdComposite.add(new CmdSetEventRepeats(appCalendar, event, Frequency.DAILY));

		cmdComposite.execute();

		cmdComposite.execute();

		CalendarPrinter.printCalendar(appCalendar.getCalendar());

		cmdComposite.undo();

		CalendarPrinter.printCalendar(appCalendar.getCalendar());

		cmdComposite.execute();

		CalendarPrinter.printCalendar(appCalendar.getCalendar());

		System.out.println("Testing composite command finished");

	}

	/**
	 * Tests undo-redo functionality of the command pattern
	 */
	@SuppressWarnings("unused")
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
}
