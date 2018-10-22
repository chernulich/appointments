package appointer.commands;

import appointer.calendar.AppCalendar;
import biweekly.component.VEvent;

public class CmdAddEvent implements AppCommand {
	
	final AppCalendar appCalendar;
 	final VEvent event; 
	
	public CmdAddEvent(AppCalendar appCalendar, VEvent event) {
		this.appCalendar = appCalendar;
		this.event = event;
	}

	@Override 
	public void execute() {
		appCalendar.getCalendar().addEvent(event);
	}

	@Override
	public void undo() {
		appCalendar.getCalendar().removeComponent(event);
	}

}
