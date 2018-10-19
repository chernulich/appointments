package appointer.calendar;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.Period;
import java.util.Date;

import appointer.util.date.DateAdapter;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.component.VFreeBusy;
import biweekly.parameter.FreeBusyType;
import biweekly.property.Organizer;
import biweekly.util.Duration;

/**
 * Static wrapper over biweekly calendar; 
 * https://github.com/mangstadt/biweekly
 * biweekly is an iCalendar library written in Java. 
 * The project aims to provide a well documented, easy to use API for reading and writing iCalendar data.
 */
public class AppCalendar {
	
	

	/**
	 * Reads time of start and end of event and then creates and adds to calendar an instance of VFreeBusy class; 
	 * @param calendar 
	 * @param event
	 */
	// does too much, has to refactor
	// event transparency exist;
	public static void addBusy(ICalendar calendar, VEvent event) {
		VFreeBusy freebusy = new VFreeBusy();
		Date start = event.getDateStart().getValue();
		Date end = DateAdapter.asDate(DateAdapter.asLocalDateTime(start)
				.plus(event.getDuration().getValue().toMillis(), ChronoUnit.MILLIS));
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
