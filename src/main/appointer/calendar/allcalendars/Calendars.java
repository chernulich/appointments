package appointer.calendar.allcalendars;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import appointer.calendar.single.ICalendarsLocal;
import appointer.calendar.single.SingleCalendar;
import appointer.user.AppUser;
import appointer.user.IUser;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Attendee;
import biweekly.property.Organizer;

public class Calendars {
	
	IUser user;
	
	ICalendarsLocal localCalendar;
	
	Map<String, ICalendar> remoteCalendars = new HashMap<>();
	
	public Calendars(String name) {
		user = new AppUser(name);
		localCalendar = new SingleCalendar(user);
	}
	
	public void AddRemoteUser(String name) {
		if (name == user.getName()) throw new IllegalArgumentException();
		remoteCalendars.put(name, SingleCalendar.getLocalCalendar(new AppUser(name)));
	}
	
	private ICalendar getRemoteSingleCalendar(String orgname) {
		if (remoteCalendars.containsKey(orgname))
				return remoteCalendars.get(orgname);
		
		ICalendar newRemote = SingleCalendar.getLocalCalendar(new AppUser(orgname));
		
		remoteCalendars.put(orgname, newRemote);
		return newRemote;
	}
	
	/**
	 * Puts an event into local and remote calendars 
	 * Preconditions: must 
	 * @param orgname
	 */
	public void addAppointment(String orgname) {
		VEvent event = new VEvent();
		event.addAttendee(new Attendee(user.getName(), ""));
		localCalendar.getLocalCalendar().addEvent(event);
		event.setOrganizer(new Organizer(orgname, ""));
		// async approve in remote;
		this.getRemoteSingleCalendar(orgname).addEvent(event);
	}
	
	public void removeEvent(VEvent event) {
		localCalendar.getLocalCalendar().removeComponent(event);
		String orgname = event.getOrganizer().getCommonName();
		this.getRemoteSingleCalendar(orgname).addEvent(event);
	}
	
	public String toString() {
		return localCalendar.toString() + "\n" + 
			remoteCalendars.entrySet().stream().map(entry -> entry.getKey() + " - " + entry.getValue())
	        .collect(Collectors.joining());
	}
	
}
