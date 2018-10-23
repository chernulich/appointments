package appointer.calendar;

import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Test;

import appointer.commands.AppCommand;
import appointer.commands.CmdAddEvent;
import appointer.commands.CmdRemoveEvent;
import appointer.commands.CmdSetEventDuration;
import appointer.commands.CmdSetEventRepeats;
import appointer.commands.CmdSetEventStart;
import appointer.user.AppUser;
import biweekly.component.VEvent;
import biweekly.util.Duration;
import biweekly.util.Frequency;

public class AppCommandsTestAuto {

	private static Random gen = new Random();

	private final static int STARTINGEVENTS = 10;

	
	@Test
	public void addRemoveExecRedo() {

		AppCalendar appCalendar = AppCalendar.getAppCalendar(new AppUser("Alyssa P. Hacker"));

		addNEvents(appCalendar, STARTINGEVENTS);

		final int eventsInit = appCalendar.getCalendar().getEvents().size();
		
		VEvent event = EventFacade.createEventCurrentTime();

		List<AppCommand> commands = createTestCommandAddRemove(appCalendar, event);

		commands.stream().forEach(AppCommand::execute);
		
		Collections.reverse(commands);
		
		commands.stream().forEach(AppCommand::undo);

		final List<VEvent> eventsEnd = appCalendar.getCalendar().getEvents();

//		System.out.println("Rolled number of commands: " + commands.size());
//
//		System.out.println("Events in collection: " + eventsEnd.size() + " Original events " + eventsInit);
//
//		commands.stream().forEach(c -> System.out.println(c.getClass()));

		assertTrue(eventsEnd.size() == eventsInit);
	}


	@Test
	public void randomExecRedo() {

		AppCalendar appCalendar = AppCalendar.getAppCalendar(new AppUser("Alyssa P. Hacker"));

		addNEvents(appCalendar, STARTINGEVENTS);

		final int eventsInit = appCalendar.getCalendar().getEvents().size();
		// need an immutable event + event list in order to compare pre/post event states;
		
		VEvent event = EventFacade.createEventCurrentTime();

		List<AppCommand> commands = createTestCommandList(appCalendar, event);

		int size = commands.size();

		int testMaxCommands = gen.nextInt(size + 1); //gen.nexint upper is excusive;

		Collections.shuffle(commands);

		List<AppCommand> commandsTrimmed = commands.stream().limit(testMaxCommands).collect(Collectors.toCollection(ArrayList::new));
		
		commandsTrimmed.stream().forEach(AppCommand::execute);

		Collections.reverse(commandsTrimmed); 
		// Command stack is ADT by itself; we must preserve order of the commands 
		// when doint execute() and redo();
		
		commandsTrimmed.stream().forEach(AppCommand::undo);

		final List<VEvent> eventsEnd = appCalendar.getCalendar().getEvents();

		assertTrue(eventsEnd.size() == eventsInit);
	}

	/**
	 * Adds n event to the calendar
	 * @param appCalendar
	 * @param NEvents
	 */
	private void addNEvents(AppCalendar appCalendar, int NEvents) {

		VEvent eventOne;

		for (int i = 0; i < NEvents; i++) {

			eventOne = EventFacade.createEventCurrentTime();

			CmdAddEvent cae = new CmdAddEvent(appCalendar, eventOne);

			cae.execute();

		}

	}

	/**
	 * Creates a list of commands for unit testing AppCommand;
	 * 
	 * @param appCalendar
	 * @param event
	 * @return
	 */
	private List<AppCommand> createTestCommandList(AppCalendar appCalendar, VEvent event) {
		List<AppCommand> appCommands = new ArrayList<>();

		appCommands.add(new CmdAddEvent(appCalendar, event));

		appCommands.add(new CmdRemoveEvent(appCalendar, event));

		appCommands.add(new CmdSetEventDuration(appCalendar, event, Duration.builder().hours(1).build()));

		appCommands.add(new CmdSetEventDuration(appCalendar, event, Duration.builder().hours(1).build()));

		appCommands.add(new CmdSetEventRepeats(appCalendar, event, Frequency.DAILY));

		appCommands.add(new CmdSetEventRepeats(appCalendar, event, Frequency.DAILY));

		appCommands.add(new CmdSetEventStart(appCalendar, event, LocalDate.now()));

		appCommands.add(new CmdSetEventStart(appCalendar, event,
				LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY))));

		return appCommands;
	}

	
	/**
	 * Creates a list of add/remove for unit testing AppCommand;
	 * 
	 * @param appCalendar
	 * @param event
	 * @return
	 */
	private List<AppCommand> createTestCommandAddRemove(AppCalendar appCalendar, VEvent event) {
		List<AppCommand> appCommands = new ArrayList<>();

		appCommands.add(new CmdAddEvent(appCalendar, event));

		appCommands.add(new CmdRemoveEvent(appCalendar, event));

		return appCommands;
	}

}
