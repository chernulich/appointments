package appointer.calendar;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import appointer.user.User;
import appointer.util.date.DateAdapter;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.component.VFreeBusy;
import biweekly.parameter.FreeBusyType;

/**
 * Wrapper over biweekly calendar; https://github.com/mangstadt/biweekly
 * biweekly won by comparison to older iCal4j library;
 */
public class AppCalendar implements IAppCal {
	/**
	 * Abstraction function: name, calendar -> AppCalendar
	 */
	/**
	 * Rep invariant: user and calendar not null;
	 * 0 <= calendars.size <= 1;
	 */
	private final User user;
	private final ICalendar calendar;
	private static final List<AppCalendar> calendars = new ArrayList<AppCalendar>();

	/**
	 * @param appUser
	 */
	private AppCalendar(User appUser) {
		if (appUser == null)
			throw new IllegalArgumentException();
		user = appUser;
		calendar = new ICalendar();
	}

	/**
	 * Singleton static factory
	 * 
	 * @param appUser
	 * @return
	 */
	public static IAppCal getAppCalendar(User appUser) {
		if (calendars.isEmpty()) {
			calendars.add(new AppCalendar(appUser));
		}
		return calendars.get(0);
	}

	/* (non-Javadoc)
	 * @see appointer.calendar.IAppCal#getName()
	 */
	@Override
	public String getName() {
		return user.getName();
	}

	/* (non-Javadoc)
	 * @see appointer.calendar.IAppCal#getCalendar()
	 */
	@Override
	public ICalendar getCalendar() {
		return calendar;
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
