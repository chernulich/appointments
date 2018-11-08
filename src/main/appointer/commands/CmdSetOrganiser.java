package appointer.commands;

import biweekly.component.VEvent;
import biweekly.property.Organizer;

public class CmdSetOrganiser extends CmdLeaf implements AppCommand {
	
	private final VEvent event;  
	private final String currentOrgName;
	private String previousOrgName;
    
	public CmdSetOrganiser(VEvent event, String organiser) {
		this.event = event;
		this.currentOrgName = organiser;
	}

	@Override ()
	public void execute() {
		previousOrgName = event.getOrganizer().getCommonName();
		event.setOrganizer(new Organizer(currentOrgName, ""));
	}

	@Override
	public void undo() {
		event.setOrganizer(new Organizer(previousOrgName, ""));
	}
}
