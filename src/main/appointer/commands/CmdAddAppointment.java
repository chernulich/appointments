package appointer.commands;

import appointer.user.SingletonAppUser;
import biweekly.ICalendar;
import biweekly.component.VEvent;

public class CmdAddAppointment extends CmdComposite implements AppCommand {
	
	private final ICalendar appCalendar;
	private final VEvent event;
 	private final String orgname;

 	
	public CmdAddAppointment(ICalendar appCalendar, VEvent event, String organiser) {
		super();
		this.appCalendar = appCalendar;
		this.event = event;
		this.orgname = organiser;
		addSubCommands();
		//TODO: async approve in remote;		
		//TODO: command for CalendarStorage.getValueByName(orgname).addEvent(event);
	}

	private void addSubCommands() {
		add(new CmdAddEvent(appCalendar, event));
		add(new CmdAddAttendee(SingletonAppUser.lazyGet().getName(), event));
		add(new CmdSetOrganiser(event, orgname));
	}
}
