package appointer.calendar;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import appointer.util.date.DateAdapter;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.component.VFreeBusy;
import biweekly.parameter.FreeBusyType;
import biweekly.property.Summary;
import biweekly.util.Duration;

/**
 * Proof-of concept that the iCal library is better than bicycle approach;
 * Already we have access to busy/free implementation;
 * 
 * Demonstration of concern: if we want to extend the BiWeekly iCal library for our app
 * we have to probably wrap its event and duplicate its calendar with the AppEvent collection
 * 
 */
public class Calendar {
	// adding freeBusy to the calendar
	public static void addBusy(ICalendar calendar, VEvent eventII) {
		VFreeBusy freebusy = new VFreeBusy();
		Date start = eventII.getDateStart().getValue();
		Date end = DateAdapter.asDate(DateAdapter.asLocalDateTime(start)
				.plus(eventII.getDuration().getValue().toMillis(), ChronoUnit.MILLIS));
		freebusy.addFreeBusy(FreeBusyType.BUSY, start, end);
		calendar.addFreeBusy(freebusy);
	}

	// adding data to the VEvent;
	public static VEvent fillEvent(VEvent event, LocalDateTime fillTime) {
		Summary summary = event.setSummary("Team Meeting");
		summary.setLanguage("en-us");

		Date start = DateAdapter.asDate(fillTime);
		event.setDateStart(start);

		Duration duration = new Duration.Builder().hours(1).build();
		event.setDuration(duration);
		return event;
	}
}
