package appointer.commands;

import java.util.List;

import appointer.calendar.single.ICalendarsLocal;
import biweekly.component.VEvent;
import biweekly.property.Attendee;
import biweekly.property.DurationProperty;
import biweekly.util.Duration;

public class CmdAddLocalAttendee implements AppCommand {
	
	private final ICalendarsLocal appCalendar;
	private final VEvent event;  
	private List<Attendee> currentAttendee;
 		
	public CmdAddLocalAttendee(ICalendarsLocal appCalendar, VEvent event) {
		this.appCalendar = appCalendar;
		this.event = event;
	}

	@Override 
	public void execute() {
		currentAttendee = event.getAttendees();
		event.addAttendee(new Attendee(appCalendar.getName(), ""));
	}

	@Override
	public void undo() {
		//TODO: remove Attendee
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
