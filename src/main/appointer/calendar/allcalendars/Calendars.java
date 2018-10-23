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
 * Class that brings together calendars. 
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
	

	/**
	 * Puts an event into local and remote calendars 
	 * Preconditions: must 
	 * @param orgname
	 */
	public void addAppointment(String orgname) {
		VEvent event = new VEvent();
		event.addAttendee(new Attendee(user.getName(), ""));
		localCalendar.getValue().addEvent(event);
		event.setOrganizer(new Organizer(orgname, ""));
		//TODO: async approve in remote;
		CalendarStorage.getValueByName(orgname).addEvent(event);
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
