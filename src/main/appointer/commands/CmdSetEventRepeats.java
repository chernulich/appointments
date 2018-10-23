package appointer.commands;

import appointer.calendar.single.ICalendarsLocal;
import biweekly.component.VEvent;
import biweekly.property.RecurrenceRule;
import biweekly.util.Frequency;
import biweekly.util.Recurrence;

public class CmdSetEventRepeats implements AppCommand {
	
	final ICalendarsLocal appCalendar;
 	final VEvent event; 
 	final Frequency frequency;
	RecurrenceRule currentRule;
	RecurrenceRule previousRule;
	
	
	public CmdSetEventRepeats(ICalendarsLocal appCalendar, VEvent event, Frequency frequency) {
		this.appCalendar = appCalendar;
		this.event = event;
		this.frequency = frequency;
	}

	@Override 
	public void execute() {
		previousRule = event.getRecurrenceRule();
		Recurrence recur = new Recurrence.Builder(frequency).build();
		currentRule = new RecurrenceRule(recur);
		event.setRecurrenceRule(currentRule);
	}

	@Override
	public void undo() {
		event.setRecurrenceRule(previousRule);
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
