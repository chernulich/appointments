package appointer.commands;

import appointer.calendar.single.ICalendarsLocal;
import biweekly.component.VEvent;
import biweekly.property.Organizer;

public class CmdSetOrganiser implements AppCommand {
	
	private final ICalendarsLocal appCalendar;
	private final VEvent event;  
	private final String currentOrgName;
	private String previousOrgName;
    
	public CmdSetOrganiser(ICalendarsLocal appCalendar, VEvent event, String organiser) {
		this.appCalendar = appCalendar;
		this.event = event;
		this.currentOrgName = organiser;
	}

	@Override 
	public void execute() {
		previousOrgName = event.getOrganizer().getCommonName();
		event.setOrganizer(new Organizer(currentOrgName, ""));
	}

	@Override
	public void undo() {
		event.setOrganizer(new Organizer(previousOrgName, ""));
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
