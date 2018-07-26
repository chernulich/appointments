package appointer;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import appointer.component.AppEvent;
import appointer.component.AppEventCollection;
import appointer.component.AppUser;
import appointer.util.DateAdapter;
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
public class Main {
	public static void main(String[] args) {
		
		//AppEventCollection holds the wrapper class AppEvent;
		AppEventCollection col = new AppEventCollection();
		ICalendar calendar = new ICalendar();
		
		//AppEvent is the wrapper to the library class VEvent;
		AppEvent aEI = new AppEvent(new AppUser("Ben Bitdiddle"), new VEvent());
		VEvent eventI = aEI.getVevent();
		LocalDateTime timeI = LocalDateTime.of(2018, Month.JULY, 26, 17, 00);
		eventI = fillEvent(eventI, timeI);
		col.events().add(aEI);
		calendar.addEvent(eventI);

		AppEvent aEII = new AppEvent(new AppUser("Alyssa P. Hacker"), new VEvent());
		VEvent eventII = aEII.getVevent();
		LocalDateTime timeII = LocalDateTime.of(2018, Month.JULY, 27, 15, 00);
		eventII = fillEvent(eventII, timeII);
		col.events().add(aEII);
		calendar.addEvent(eventII);
	
		//Demonstrating the wrapper class benefit
		for (AppEvent ae : col.events()) {
			System.out.println("An event");
			System.out.println("Owner: "+ae.getOwner().getName());
			System.out.println(ae.getVevent());
		}
		
		
		//demonstrating that the iCal has the free/busy functionality
		addBusy(calendar, eventII);
		System.out.println("Free/Busy functionality");
		System.out.println(calendar.getFreeBusies());
	}

	//adding freeBusy to the calendar
	private static void addBusy(ICalendar calendar, VEvent eventII) {
		VFreeBusy freebusy = new VFreeBusy();
		Date start = eventII.getDateStart().getValue();
		Date end = DateAdapter.asDate(DateAdapter.asLocalDateTime(start)
				.plus(eventII.getDuration().getValue().toMillis(), ChronoUnit.MILLIS));
		freebusy.addFreeBusy(FreeBusyType.BUSY, start, end);
		calendar.addFreeBusy(freebusy);
	}

	//adding data to the VEvent; 
	private static VEvent fillEvent(VEvent event, LocalDateTime fillTime) {
		Summary summary = event.setSummary("Team Meeting");
		summary.setLanguage("en-us");

		Date start = DateAdapter.asDate(fillTime);
		event.setDateStart(start);

		Duration duration = new Duration.Builder().hours(1).build();
		event.setDuration(duration);
		return event;
	}
}
