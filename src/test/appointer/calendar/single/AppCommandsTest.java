package appointer.calendar.single;

import appointer.calendar.facades.EventFacade;
import appointer.commands.AppCommand;
import appointer.commands.CmdAddEvent;
import appointer.commands.CmdComposite;
import appointer.commands.CmdSetEventDuration;
import appointer.commands.CmdSetEventRepeats;
import appointer.user.SingletonAppUser;
import appointer.util.io.console.CalendarPrinter;
import biweekly.ICalendar;
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
		
		SingletonAppUser.lazyGet("Alyssa P. Hacker");
		
		ICalendar appCalendar = new ICalendar();

		VEvent event = EventFacade.createEventCurrentTime();

		AppCommand cmdComposite = new CmdComposite();

		cmdComposite.add(new CmdAddEvent(appCalendar, event));

		cmdComposite.add(new CmdSetEventDuration(event, Duration.builder().hours(1).build()));

		cmdComposite.add(new CmdSetEventRepeats(event, Frequency.DAILY));

		cmdComposite.execute();

		cmdComposite.execute();

		CalendarPrinter.printCalendar(appCalendar);

		cmdComposite.undo();

		CalendarPrinter.printCalendar(appCalendar);

		cmdComposite.execute();

		CalendarPrinter.printCalendar(appCalendar);

		System.out.println("Testing composite command finished");

	}

	/**
	 * Tests undo-redo functionality of the command pattern
	 */
	@SuppressWarnings("unused")
	private static void commandTest() {

		SingletonAppUser.lazyGet("Alyssa P. Hacker");
		
		ICalendar appCalendar = new ICalendar();
		
		VEvent event = EventFacade.createEventCurrentTime();

		CmdAddEvent cmdAE = new CmdAddEvent(appCalendar, event);

		cmdAE.execute();

		CalendarPrinter.printCalendar(appCalendar);

		cmdAE.undo();

		CalendarPrinter.printCalendar(appCalendar);

		cmdAE.execute();

		CalendarPrinter.printCalendar(appCalendar);

		CmdSetEventDuration cmdSD = new CmdSetEventDuration(event, Duration.builder().hours(1).build());

		cmdSD.execute();

		CalendarPrinter.printCalendar(appCalendar);

		cmdSD.undo();

		CalendarPrinter.printCalendar(appCalendar);

		cmdSD.execute();

		CalendarPrinter.printCalendar(appCalendar);

		CmdSetEventRepeats cmdSR = new CmdSetEventRepeats(event, Frequency.DAILY);

		cmdSR.execute();

		CalendarPrinter.printCalendar(appCalendar);

		cmdSR.undo();

		CalendarPrinter.printCalendar(appCalendar);

		cmdSR.execute();

		CalendarPrinter.printCalendar(appCalendar);

		System.out.println("Testing commands finished");
	}
}
