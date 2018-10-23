package appointer.calendar.allcalendars;

import appointer.calendar.single.ICalendarsLocal;
import appointer.calendar.single.SingleCalendar;
import appointer.user.AppUser;
import appointer.user.IUser;
import biweekly.component.VEvent;
import biweekly.property.Attendee;
import biweekly.property.Organizer;

/**
 * Class that brings together calendars;
 *
 */
public class Calendars {
	
	IUser user;
	
	ICalendarsLocal localCalendar; 
	
		
	public Calendars(String name) {
		user = new AppUser(name);
		localCalendar = new SingleCalendar(user.getName());
	}
	
	public void AddRemoteUser(String name) {
		if (name == user.getName()) throw new IllegalArgumentException();
		SingleCalendar.getLocalCalendar(name);
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
		//TODO: async approve in remote;
		SingleCalendar.getLocalCalendar(orgname).addEvent(event);
	}
	
	public void removeEvent(VEvent event) {
		localCalendar.getLocalCalendar().removeComponent(event);
		String orgname = event.getOrganizer().getCommonName();
		//TODO: async approve in remote;
		SingleCalendar.getLocalCalendar(orgname).addEvent(event);
	}
	
	public String toString() {
		return localCalendar.toString() + "\n" + 
			CalendarStorage.toStaticString();
	}
	
}
