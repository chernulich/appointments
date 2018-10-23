package appointer.commands;

import appointer.calendar.single.ICalendarsLocal;
import biweekly.component.VEvent;

/**
 * 
 * Removing event from local user's calendar;
 *
 */
public class CmdRemoveEvent implements AppCommand {

	final ICalendarsLocal appCalendar;
	final VEvent event;
	boolean executed = false;

	public CmdRemoveEvent(ICalendarsLocal appCalendar, VEvent event) {
		this.appCalendar = appCalendar;
		this.event = event;
	}
	
	@Override
	public void execute() {
		executed = appCalendar.getLocalCalendar().removeComponent(event);
	}

	@Override
	public void undo() {
		if (executed) appCalendar.getLocalCalendar().addEvent(event);
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
