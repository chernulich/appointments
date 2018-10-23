package appointer.calendar.facades;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import appointer.util.date.DateAdapter;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.component.VFreeBusy;
import biweekly.parameter.FreeBusyType;

/**
 * Static facade methods for Biweekly;
 */
public class CalendarFacade {
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
