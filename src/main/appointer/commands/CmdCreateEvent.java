package appointer.commands;

import appointer.calendar.IAppCal;
import biweekly.component.VEvent;

public class CmdCreateEvent implements AppCommand {
	
	final IAppCal appCalendar;
 	VEvent event; 
	
	public CmdCreateEvent(IAppCal appCalendar) {
		this.appCalendar = appCalendar;
	}

	@Override 
	public void execute() {
		event = new VEvent();
		CmdAddEvent CAEvent = new CmdAddEvent(appCalendar, event);
		CAEvent.execute();
	}

	@Override
	public void undo() {
		CmdAddEvent CAEvent = new CmdAddEvent(appCalendar, event);
		CAEvent.undo();
		event = null;
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
