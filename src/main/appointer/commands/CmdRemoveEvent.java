package appointer.commands;

import appointer.calendar.IAppCal;
import biweekly.component.VEvent;

public class CmdRemoveEvent implements AppCommand {


	final IAppCal appCalendar;
	final VEvent event;
	boolean executed = false;

	public CmdRemoveEvent(IAppCal appCalendar, VEvent event) {
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
