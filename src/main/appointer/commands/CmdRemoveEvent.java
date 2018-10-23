package appointer.commands;

import appointer.calendar.single.ICalendarsLocal;
import biweekly.component.VEvent;

/**
 * 
 * Removing event from local user's calendar;
 *
 */
public class CmdRemoveEvent implements AppCommand {

	private final ICalendarsLocal appCalendar;
	private final VEvent event;
	private boolean executed = false;

	public CmdRemoveEvent(ICalendarsLocal appCalendar, VEvent event) {
		this.appCalendar = appCalendar;
		this.event = event;
	}
	
	@Override
	public void execute() {
		executed = appCalendar.getValue().removeComponent(event);
	}

	@Override
	public void undo() {
		if (executed) appCalendar.getValue().addEvent(event);
	}

	// leaf node
	@Override
	public void add(AppCommand appCommand) {
	}

	@Override
	public void remove(AppCommand appCommand) {
	}

	@Override
	public AppCommand getChild(int i) {
		return new CmdEmpty();
	}


}
