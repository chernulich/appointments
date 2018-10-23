package appointer.calendar.allcalendars;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import biweekly.ICalendar;

/**
 * Static map for all ICalendars on the local system
 */
public class CalendarStorage {

	private static final Map<String, ICalendar> calendars = new HashMap<>();

	public static Map<String, ICalendar> getMap() {
		return calendars;
	}

	public static String toStaticString() {
		return getMap().entrySet().stream().map(entry -> entry.getKey() + " - " + entry.getValue())
        .collect(Collectors.joining());
	}
	
	//TODO: We need a method that prints only the calendars relevant to the name;
	public static String toStaticString(String name) {
		return null;
	}

	/**
	 * Returns the local calendar for any application user;
	 */
	public static ICalendar getValueByName(String userName) {
		if (getMap().get(userName) == null) {
			getMap().put(userName, new ICalendar());
		}
		return getMap().get(userName);
	}
	
	

	
}
