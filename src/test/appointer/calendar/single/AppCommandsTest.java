package appointer.calendar.single;

import appointer.commands.AppCommand;
import appointer.commands.CmdAddEvent;
import appointer.commands.CmdComposite;
import appointer.commands.CmdSetEventDuration;
import appointer.commands.CmdSetEventRepeats;
import appointer.util.io.console.CalendarPrinter;
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
		
		ICalendarsLocal appCalendar = new SingleCalendar("Alyssa P. Hacker");

		VEvent event = EventFacade.createEventCurrentTime();

		AppCommand cmdComposite = new CmdComposite(appCalendar, event);

		cmdComposite.add(new CmdAddEvent(appCalendar, event));

		cmdComposite.add(new CmdSetEventDuration(appCalendar, event, Duration.builder().hours(1).build()));

		cmdComposite.add(new CmdSetEventRepeats(appCalendar, event, Frequency.DAILY));

		cmdComposite.execute();

		cmdComposite.execute();

		CalendarPrinter.printCalendar(appCalendar.getLocalCalendar());

		cmdComposite.undo();

		CalendarPrinter.printCalendar(appCalendar.getLocalCalendar());

		cmdComposite.execute();

		CalendarPrinter.printCalendar(appCalendar.getLocalCalendar());

		System.out.println("Testing composite command finished");

	}

	/**
	 * Tests undo-redo functionality of the command pattern
	 */
	@SuppressWarnings("unused")
	private static void commandTest() {

		ICalendarsLocal appCalendar = new SingleCalendar("Alyssa P. Hacker");

		VEvent event = EventFacade.createEventCurrentTime();

		CmdAddEvent cmdAE = new CmdAddEvent(appCalendar, event);

		cmdAE.execute();

		CalendarPrinter.printCalendar(appCalendar.getLocalCalendar());

		cmdAE.undo();

		CalendarPrinter.printCalendar(appCalendar.getLocalCalendar());

		cmdAE.execute();

		CalendarPrinter.printCalendar(appCalendar.getLocalCalendar());

		CmdSetEventDuration cmdSD = new CmdSetEventDuration(appCalendar, event, Duration.builder().hours(1).build());

		cmdSD.execute();

		CalendarPrinter.printCalendar(appCalendar.getLocalCalendar());

		cmdSD.undo();

		CalendarPrinter.printCalendar(appCalendar.getLocalCalendar());

		cmdSD.execute();

		CalendarPrinter.printCalendar(appCalendar.getLocalCalendar());

		CmdSetEventRepeats cmdSR = new CmdSetEventRepeats(appCalendar, event, Frequency.DAILY);

		cmdSR.execute();

		CalendarPrinter.printCalendar(appCalendar.getLocalCalendar());

		cmdSR.undo();

		CalendarPrinter.printCalendar(appCalendar.getLocalCalendar());

		cmdSR.execute();

		CalendarPrinter.printCalendar(appCalendar.getLocalCalendar());

		System.out.println("Testing commands finished");
	}
}
