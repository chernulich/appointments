package appointer.commands;

import appointer.calendar.AppCalendar;
import biweekly.component.VEvent;

public class CmdCreateEvent implements AppCommand {
	
	final AppCalendar appCalendar;
 	VEvent event; 
	
	public CmdCreateEvent(AppCalendar appCalendar) {
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

}
