package appointer.commands;

import appointer.calendar.AppCalendar;
import biweekly.component.VEvent;

public class CmdRemoveEvent implements AppCommand {


	final AppCalendar appCalendar;
	final VEvent event;
	boolean executed = false;

	public CmdRemoveEvent(AppCalendar appCalendar, VEvent event) {
		this.appCalendar = appCalendar;
		this.event = event;
	}
	
	@Override
	public void execute() {
		executed = appCalendar.getCalendar().removeComponent(event);
	}

	@Override
	public void undo() {
		if (executed) appCalendar.getCalendar().addEvent(event);
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
