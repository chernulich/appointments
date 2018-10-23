package appointer.commands;

import java.time.LocalDate;

import appointer.calendar.single.ICalendarsLocal;
import appointer.util.date.DateAdapter;
import biweekly.component.VEvent;

public class CmdSetEventStart implements AppCommand {
	
	private final ICalendarsLocal appCalendar;
	private final VEvent event;
	private LocalDate currentStart;
	private LocalDate previousStart; 
	
	public CmdSetEventStart(ICalendarsLocal appCalendar, VEvent event, LocalDate start) {
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
