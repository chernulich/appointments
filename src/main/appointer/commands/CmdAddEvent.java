package appointer.commands;

import biweekly.ICalendar;
import biweekly.component.VEvent;

public class CmdAddEvent implements AppCommand {

	private final ICalendar appCalendar;
	private final VEvent event;
	private boolean executed = false;

	public CmdAddEvent(ICalendar appCalendar, VEvent event) {
		this.appCalendar = appCalendar;
		this.event = event;
	}
	
	@Override
	public void execute() {
		appCalendar.addEvent(event);
		executed = true;
	}

	@Override
	public void undo() {
		if (executed) appCalendar.removeComponent(event);
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
