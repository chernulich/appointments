package appointer.commands;

import appointer.calendar.single.ICalendarsLocal;
import biweekly.component.VEvent;

public class CmdAddEvent implements AppCommand {

	final ICalendarsLocal appCalendar;
	final VEvent event;
	boolean executed = false;

	public CmdAddEvent(ICalendarsLocal appCalendar, VEvent event) {
		this.appCalendar = appCalendar;
		this.event = event;
	}
	
	@Override
	public void execute() {
		appCalendar.getLocalCalendar().addEvent(event);
		executed = true;
	}

	@Override
	public void undo() {
		if (executed) appCalendar.getLocalCalendar().removeComponent(event);
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
