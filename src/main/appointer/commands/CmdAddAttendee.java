package appointer.commands;

import java.util.List;

import biweekly.component.VEvent;
import biweekly.property.Attendee;

public class CmdAddAttendee extends CmdLeaf implements AppCommand {
	
	private final String user; 
	private final VEvent event;  
	private List<Attendee> currentAttendee;
 		
	public CmdAddAttendee(String LocalName, VEvent event) {
		super();
		this.user = LocalName;
		this.event = event;
	}

	@Override 
	public void execute() {
		currentAttendee = event.getAttendees();
		event.addAttendee(new Attendee(user, ""));
	}

	@Override
	public void undo() {
		//TODO: remove Attendee
	}
	// leaf node


}
