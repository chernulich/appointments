package appointer.commands;

import appointer.calendar.AppCalendar;
import biweekly.component.VEvent;
import biweekly.property.DurationProperty;
import biweekly.util.Duration;

public class CmdSetEventDuration implements AppCommand {
	
	final AppCalendar appCalendar;
 	final VEvent event;  
 	final Duration currentDuration;
	Duration previousDuration;
 	
	public CmdSetEventDuration(AppCalendar appCalendar, VEvent event, Duration duration) {
		this.appCalendar = appCalendar;
		this.event = event;
		this.currentDuration = duration;
	}

	@Override 
	public void execute() {
		DurationProperty durationProperty = event.getDuration();
		
		previousDuration = durationProperty == null ? null : durationProperty.getValue() ;
		event.setDuration(currentDuration);
	}

	@Override
	public void undo() {
		event.setDuration(previousDuration);
	}

}
