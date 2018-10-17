package appointer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import appointer.calendar.AppEvent;
import appointer.calendar.AppEventCollection;
import appointer.calendar.AppUser;
import appointer.calendar.Calendar;
import appointer.util.io.IOCalendarPersistence;
import biweekly.ICalendar;
import biweekly.component.VEvent;

/**
 * Demonstration of project Calendar library
 *
 */
public class CalendarTest {
	public static void main(String[] args) {
		
		testCalendarPersistence();	
		
	}

	/**
	 * Introduction to calendar
	 */
	public static void testCalendarTour() {
		// AppEventCollection holds the wrapper class AppEvent;
		AppEventCollection col = new AppEventCollection();
		ICalendar calendar = new ICalendar();

		// AppEvent is the wrapper to the library class VEvent;
		AppEvent aEI = new AppEvent(new AppUser("Ben Bitdiddle"), new VEvent());
		VEvent eventI = aEI.getVevent();
		LocalDateTime timeI = LocalDateTime.of(2018, Month.JULY, 26, 17, 00);
		eventI = Calendar.fillEvent(eventI, timeI);
		col.events().add(aEI);
		calendar.addEvent(eventI);

		AppEvent aEII = new AppEvent(new AppUser("Alyssa P. Hacker"), new VEvent());
		VEvent eventII = aEII.getVevent();
		LocalDateTime timeII = LocalDateTime.of(2018, Month.JULY, 27, 15, 00);
		eventII = Calendar.fillEvent(eventII, timeII);
		col.events().add(aEII);
		calendar.addEvent(eventII);

		// Demonstrating the wrapper class benefit
		for (AppEvent ae : col.events()) {
			System.out.println("An event");
			System.out.println("Owner: " + ae.getOwner().getName());
			System.out.println(ae.getVevent());
		}

		// demonstrating that the iCal has the free/busy functionality
		Calendar.addBusy(calendar, eventII);
		System.out.println("Free/Busy functionality");
		System.out.println(calendar.getFreeBusies());
	}
	
	/**
	 * Writes and reads a calendar object to file;
	 */
	private static void testCalendarPersistence() {
		AppEventCollection col = new AppEventCollection();
		ICalendar calendar = new ICalendar();

		// AppEvent is the wrapper to the library class VEvent;
		AppEvent aEI = new AppEvent(new AppUser("Ben Bitdiddle"), new VEvent());
		VEvent eventI = aEI.getVevent();
		LocalDateTime timeI = LocalDateTime.of(2018, Month.JULY, 26, 17, 00);
		eventI = Calendar.fillEvent(eventI, timeI);
		col.events().add(aEI);
		calendar.addEvent(eventI);
		
		ArrayList<ICalendar> alc = new ArrayList<ICalendar>();
		alc.add(calendar);		
		try {
			IOCalendarPersistence.dumpToFile(alc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			IOCalendarPersistence.readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
