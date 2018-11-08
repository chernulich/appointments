package appointer.calendar.allcalendars;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import biweekly.ICalendar;

/**
 * Static map for all ICalendars on the local system rep exposure; not
 * threadsafe;
 */
public class CalendarStorage {

	private static final Map<String, ICalendar> calendars = new HashMap<>();

	private static Map<String, ICalendar> getMap() {
		return calendars;
	}

	public static String toStaticString() {
		return getMap().entrySet().stream().map(entry -> entry.getKey() + " - " + entry.getValue())
				.collect(Collectors.joining());
	}

	// TODO: We need a method that prints only the calendars relevant to the name;
	public static String toStaticString(String name) {
		return null;
	}

	/**
	 * Returns the local calendar for any application user, if it exists;
	 * can return null; 
	 */
	public static ICalendar getCalendar(String userName) {
		return getMap().get(userName);
	}

	/**
	 * Returns the local calendar for any application user;
	 * will create calendar if it does not exist; 
	 */
	public static ICalendar getCalendarLazy(String userName) {
		if (getCalendar(userName) == null) {
			addCalendar(userName);
		}
		return getCalendar(userName);
	}

	/**
	 */
	public static boolean addCalendar(String userName) {
		ICalendar newCalendar = getMap().get(userName);
		if (newCalendar == null) {
			getMap().put(userName, new ICalendar());
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param userName
	 * @return
	 */
	public static boolean removeCalendar(String userName) {
		ICalendar oldCalendar = getMap().get(userName);
		if (oldCalendar != null) {
			getMap().remove(userName);
			return true;
		}
		return false;
	}

}
