package appointer.calendar;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Stream;

import appointer.util.date.DateAdapter;
import biweekly.ICalendar;
import biweekly.component.ICalComponent;
import biweekly.component.VEvent;
import biweekly.component.VFreeBusy;
import biweekly.parameter.FreeBusyType;
import biweekly.property.ICalProperty;
import biweekly.property.Organizer;
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
	public static VEvent createEvent(VEvent event, LocalDateTime fillTime) {
		Summary summary = event.setSummary("Team Meeting");
		summary.setLanguage("en-us");

		Date start = DateAdapter.asDate(fillTime);
		event.setDateStart(start);

		Duration duration = new Duration.Builder().hours(1).build();
		event.setDuration(duration);
		return event;
	}
	/**
	 * print a calendar
	 * @param calendar
	 */
	private static void printCalendar(ICalendar calendar) {
		Stream.of(calendar.getEvents()).forEach(System.out::println);
	}
	
	/**
	 * Prints properties of BiWeekly calendar component;
	 * @param vEventOne
	 */
	public static <T extends ICalComponent> void printComponentProperties(T vEventOne) {
		for (Entry<Class<? extends ICalProperty>, List<ICalProperty>> property : vEventOne.getProperties()) {
			System.out.println(property.getKey().toString());
		    property.getValue()
		    .forEach(System.out::println);
		    property.getValue().forEach(p -> p.getParameters().forEach(System.out::println));
		}
	}
	
	/**
	 * Prints extra components of BiWeekly calendar component;
	 * @param vEventOne
	 */
	public static <T extends ICalComponent> void printComponentComponents(T vEventOne) {
		for (Entry<Class<? extends ICalComponent>, List<ICalComponent>> component : vEventOne.getComponents()) {
			System.out.println(component.getKey().toString());
		}
	}

	/**
	 * @param event
	 * @param organiserName name of user
	 * @return
	 */
	public static VEvent setOrganiser(VEvent event, String organiserName) {
		event.setOrganizer(new Organizer(organiserName, ""));
		return event;
	}

	/**
	 * VEvent is the Biweekly implementation of calendar event; creating VEvent for now;
	 */
	public static VEvent createEventCurrentTime() {
		VEvent vEventOne = new VEvent();
		LocalDateTime timeNow = LocalDateTime.now();
		vEventOne = Calendar.createEvent(vEventOne, timeNow);
		return vEventOne;
	}
}