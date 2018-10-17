package appointer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import appointer.calendar.AppEvent;
import appointer.calendar.AppEventCollection;
import appointer.calendar.AppUser;
import appointer.calendar.Calendar;
import appointer.util.io.IOCalendarPersistence;
import biweekly.ICalendar;
import biweekly.component.ICalComponent;
import biweekly.component.VEvent;
import biweekly.property.ICalProperty;
/**
 * Demonstration of project Calendar library
 *
 */
public class CalendarTest {
	public static void main(String[] args) {
		
		//testCalendarPersistence();	
		VEvent vEventOne = createOneTimeEvent();
		
		Calendar.setOrganiser(vEventOne, new AppUser("John Doe").getName());
		
		printComponentProperties(vEventOne);
		
		printComponentComponents(vEventOne);
		
		System.out.println("Finished");
	}


	private static <T extends ICalComponent> void printComponentProperties(T vEventOne) {
		for (Entry<Class<? extends ICalProperty>, List<ICalProperty>> property : vEventOne.getProperties()) {
			System.out.println(property.getKey().toString());
		    property.getValue()
		    .forEach(System.out::println);
		    property.getValue().forEach(p -> p.getParameters().forEach(System.out::println));
		}
	}
	
	private static <T extends ICalComponent> void printComponentComponents(T vEventOne) {
		for (Entry<Class<? extends ICalComponent>, List<ICalComponent>> component : vEventOne.getComponents()) {
			System.out.println(component.getKey().toString());
		}
	}


	private static VEvent createOneTimeEvent() {
		VEvent vEventOne = new VEvent();
		LocalDateTime timeEventOne = LocalDateTime.of(2018, Month.JULY, 26, 17, 00);
		vEventOne = Calendar.createEvent(vEventOne, timeEventOne);
		return vEventOne;
	}
	
	

	/**
	 * Introduction to calendar
	 */
	public static void testCalendarTour() {
		// AppEventCollection holds the wrapper class AppEvent;
		AppEventCollection col = new AppEventCollection();
		ICalendar calendar = new ICalendar();

		// AppEvent is the wrapper to the library class VEvent;
		AppEvent appEventOne = new AppEvent(new AppUser("Ben Bitdiddle"), new VEvent());
		VEvent vEventOne = appEventOne.getVevent();
		LocalDateTime timeEventOne = LocalDateTime.of(2018, Month.JULY, 26, 17, 00);
		
		vEventOne = Calendar.createEvent(vEventOne, timeEventOne);
		col.events().add(appEventOne);
		calendar.addEvent(vEventOne);

		AppEvent appEventTwo = new AppEvent(new AppUser("Alyssa P. Hacker"), new VEvent());
		VEvent vEventTwo = appEventTwo.getVevent();
		LocalDateTime timeII = LocalDateTime.of(2018, Month.JULY, 27, 15, 00);
		vEventTwo = Calendar.createEvent(vEventTwo, timeII);
		col.events().add(appEventTwo);
		calendar.addEvent(vEventTwo);

		// Demonstrating the wrapper class benefit
		for (AppEvent ae : col.events()) {
			System.out.println("An event");
			System.out.println("Owner: " + ae.getOwner().getName());
			System.out.println(ae.getVevent());
		}

		// demonstrating that the iCal has the free/busy functionality
		Calendar.addBusy(calendar, vEventTwo);
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
		eventI = Calendar.createEvent(eventI, timeI);
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
