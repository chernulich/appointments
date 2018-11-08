package appointer.commands;


import biweekly.ICalendar;
import biweekly.component.VEvent;

/**
 * 
 * Removing event from local user's calendar;
 *
 */
public class CmdRemoveEvent extends CmdLeaf implements AppCommand {

	private final ICalendar appCalendar;
	private final VEvent event;
	private boolean executed = false;

	public CmdRemoveEvent(ICalendar appCalendar, VEvent event) {
		this.appCalendar = appCalendar;
		this.event = event;
	}
	
	@Override
	public void execute() {
		executed = appCalendar.removeComponent(event);
	}

	@Override
	public void undo() {
		if (executed) appCalendar.addEvent(event);
	}
}
