package appointer.calendar.allcalendars;

import appointer.user.AppUser;
import appointer.user.IUser;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Attendee;
import biweekly.property.Organizer;


/**
 * Represents user, local calendar and all remote calendars; 
 * Responsible for creating and approving events across the network of app users;
 */
public class Calendars {
	
	IUser user;
	ICalendar localCalendar; 
	
	public Calendars(String name) {
		user = new AppUser(name);
		CalendarStorage.addCalendar(name);
		localCalendar = CalendarStorage.getCalendar(name);
	}
	
	public void AddRemoteUser(String name) {
		if (name == user.getName()) throw new IllegalArgumentException();
		CalendarStorage.getCalendar(name);
	}
	
	// being refactored into command;
	/**
	 * Puts an event into local and remote calendars 
	 * TODO: preconditions;
	 * @param orgname
	 */
	public void addAppointment(String orgname) {
		VEvent event = new VEvent();
		event.addAttendee(new Attendee(user.getName(), ""));
		localCalendar.addEvent(event);
		event.setOrganizer(new Organizer(orgname, ""));
		
	}
	
	public void removeEvent(VEvent event) {
		localCalendar.removeComponent(event);
		String orgname = event.getOrganizer().getCommonName();
		//TODO: async approve in remote;
		CalendarStorage.getCalendarLazy(orgname).addEvent(event);
	}
	
	public String toString() {
		return localCalendar.toString() + "\n" + 
			CalendarStorage.toStaticString();
	}
	
}
