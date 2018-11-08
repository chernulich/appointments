package appointer.commands;

import java.util.List;

import biweekly.component.VEvent;
import biweekly.property.Attendee;

public class CmdAddAttendee implements AppCommand {
	
	private final String user; 
	private final VEvent event;  
	private List<Attendee> currentAttendee;
 		
	public CmdAddAttendee(String LocalName, VEvent event) {
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
