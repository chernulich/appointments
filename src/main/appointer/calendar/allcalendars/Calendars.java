package appointer.calendar.allcalendars;

import appointer.calendar.single.ICalendarsLocal;
import appointer.calendar.single.SingleCalendar;
import appointer.user.AppUser;
import appointer.user.IUser;
import biweekly.component.VEvent;
import biweekly.property.Attendee;
import biweekly.property.Organizer;

// * localCalendar can be refactored off to oblivion
/**
 * Represents user, local calendar and all remote calendars; 
 * Responsible for creating and approving events across the network of app users;
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
		CalendarStorage.getValueByName(name);
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
		localCalendar.getValue().addEvent(event);
		event.setOrganizer(new Organizer(orgname, ""));
		
	}
	
	public void removeEvent(VEvent event) {
		localCalendar.getValue().removeComponent(event);
		String orgname = event.getOrganizer().getCommonName();
		//TODO: async approve in remote;
		CalendarStorage.getValueByName(orgname).addEvent(event);
	}
	
	public String toString() {
		return localCalendar.toString() + "\n" + 
			CalendarStorage.toStaticString();
	}
	
}
