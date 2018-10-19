package appointer.calendar;

import java.time.LocalDateTime;
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
import biweekly.util.Duration;

/**
 * Static wrapper over Biweekly Calendar;
 * 
 * ToPHILL: Why calendar named "Biweekly"?
 */
public class AppCalendar {
	
	/**
	 * VEvent is the Biweekly implementation of calendar event; creating VEvent for now;
	 */
	public static VEvent createEventCurrentTime() {
		VEvent vEventOne = new VEvent();
		LocalDateTime timeNow = LocalDateTime.now();
		vEventOne = AppCalendar.setEventStart(vEventOne, timeNow);
		return vEventOne;
	}

	/**
	 * 
	 * @param event
	 * @param fillTime
	 * @return
	 */
	public static VEvent addOneHourToEvent(VEvent event) {
		Duration duration = new Duration.Builder().hours(1).build();
		event.setDuration(duration);
		return event;
	}
	
	/**
	 * 
	 * @param event
	 * @param fillTime
	 * @return
	 */
	public static VEvent setEventStart(VEvent event, LocalDateTime fillTime) {
		Date start = DateAdapter.asDate(fillTime);
		event.setDateStart(start);
		return event;
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
	 * @param calendar
	 * @param event
	 */
	public static void addBusy(ICalendar calendar, VEvent event) {
		VFreeBusy freebusy = new VFreeBusy();
		Date start = event.getDateStart().getValue();
		Date end = DateAdapter.asDate(DateAdapter.asLocalDateTime(start)
				.plus(event.getDuration().getValue().toMillis(), ChronoUnit.MILLIS));
		freebusy.addFreeBusy(FreeBusyType.BUSY, start, end);
		calendar.addFreeBusy(freebusy);
	}

	/**
	 * print a calendar
	 * @param calendar
	 */
	public static void printCalendar(ICalendar calendar) {
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

	
}