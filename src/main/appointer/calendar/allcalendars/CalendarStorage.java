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
		return calendars.entrySet().stream().map(entry -> entry.getKey() + " - " + entry.getValue())
        .collect(Collectors.joining());
	}
	
	//We need a method that prints only the calendars relevant to the name;
	public static String toStaticString(String name) {
		return null;
	}
	
	

	
}
