package appointer.calendar.single;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import appointer.calendar.allcalendars.CalendarStorage;
import appointer.util.date.DateAdapter;
import appointer.util.io.console.CalendarPrinter;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.component.VFreeBusy;
import biweekly.parameter.FreeBusyType;


/**
 * Holds the root user and mutates CalendarStorage;
 */
public class SingleCalendar implements ICalendarsLocal {
	/**
	 * Abstraction function: name, calendars -> ICalendar
	 */
	/**
	 * Rep invariant: user and calendars not null;
	 */
	private final String userName;
	
	/**
	 * Returns the local calendar for any application user;
	 * 
	 * @param appUser
	 * @return
	 */
	public static ICalendar getLocalCalendar(String userName) {
		if (CalendarStorage.getMap().get(userName) == null) {
			CalendarStorage.getMap().put(userName, new ICalendar());
		}
		return CalendarStorage.getMap().get(userName);
	}
	
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
	public ICalendar getLocalCalendar() {
		return getLocalCalendar(userName);
	}
	
	@Override
	public String toString() {
		return "Calendar of " + userName + "\n" + CalendarPrinter.ICalendarToString(this.getLocalCalendar());
	}


	/**
	 * Reads time of start and end of event and then creates and adds to calendar an
	 * instance of VFreeBusy class;
	 * 
	 * @param calendar
	 * @param event
	 */
	// does too much, has to refactor;
	// event transparency exist;
	// works only in case of non-repeating event
	public static void addBusy(ICalendar calendar, VEvent event) {
		VFreeBusy freebusy = new VFreeBusy();
		Date start = event.getDateStart().getValue();
		Date end = DateAdapter.asDate( // null pointer if no duration
				DateAdapter.asLocalDateTime(start).plus(event.getDuration().getValue().toMillis(), ChronoUnit.MILLIS));
		freebusy.addFreeBusy(FreeBusyType.BUSY, start, end);
		calendar.addFreeBusy(freebusy);
	}

	public static boolean checkBusy(ICalendar calendar, LocalDateTime startTime, LocalDateTime endTime) {
		return false;
	}

	public static boolean checkBusy(ICalendar calendar, LocalDateTime time) {
		return false;
	}


}
