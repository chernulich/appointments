package appointer.commands;

import java.time.LocalDate;

import appointer.calendar.AppCalendar;
import appointer.util.date.DateAdapter;
import biweekly.component.VEvent;

public class CmdSetEventStart implements AppCommand {
	
	final AppCalendar appCalendar;
	final VEvent event;
	LocalDate currentStart;
	LocalDate previousStart; 
	
	public CmdSetEventStart(AppCalendar appCalendar, VEvent event, LocalDate start) {
		this.appCalendar = appCalendar;
		this.event = event;
		this.currentStart = start;
	}

	@Override 
	public void execute() {
		previousStart = DateAdapter.asLocalDate(event.getDateStart().getValue());
		event.setDateStart(DateAdapter.asDate(currentStart));		
	}

	@Override
	public void undo() {
		event.setDateStart(DateAdapter.asDate(previousStart));
	}

}
