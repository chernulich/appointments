package appointer.calendar.single;

import appointer.calendar.allcalendars.CalendarStorage;
import appointer.util.io.console.CalendarPrinter;
import biweekly.ICalendar;

/**
 * Represents pair user-calendar;
 */
public class SingleCalendar implements ICalendarsLocal {
	/**
	 * Abstraction function: name -> ICalendar
	 */
	/**
	 * Rep invariant: userName not null;
	 */
	private final String userName;
	
	/**
	 * @param name
	 */
	public SingleCalendar(String name) {
		if (name == null)
			throw new IllegalArgumentException();
		userName = name;
	}

	@Override
	public String getName() {
		return userName;
	}

	@Override
	/**
	 * Returns the local calendar for the default user
	 */
	public ICalendar getValue() {
		return CalendarStorage.getValueByName(userName);
	}
	
	@Override
	public String toString() {
		return "Calendar of " + userName + "\n" + CalendarPrinter.ICalendarToString(this.getValue());
	}
}
