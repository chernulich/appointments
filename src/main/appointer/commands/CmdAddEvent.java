package appointer.commands;

import appointer.calendar.single.ICalendarsLocal;
import biweekly.component.VEvent;

public class CmdAddEvent implements AppCommand {

	private final ICalendarsLocal appCalendar;
	private final VEvent event;
	private boolean executed = false;

	public CmdAddEvent(ICalendarsLocal appCalendar, VEvent event) {
		this.appCalendar = appCalendar;
		this.event = event;
	}
	
	@Override
	public void execute() {
		appCalendar.getValue().addEvent(event);
		executed = true;
	}

	@Override
	public void undo() {
		if (executed) appCalendar.getValue().removeComponent(event);
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
