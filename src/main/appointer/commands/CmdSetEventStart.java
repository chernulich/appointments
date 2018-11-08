package appointer.commands;

import java.time.LocalDate;

import appointer.util.date.DateAdapter;
import biweekly.component.VEvent;

public class CmdSetEventStart extends CmdLeaf implements AppCommand {
	
	private final VEvent event;
	private LocalDate currentStart;
	private LocalDate previousStart; 
	
	public CmdSetEventStart(VEvent event, LocalDate start) {
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
