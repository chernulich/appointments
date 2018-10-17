package appointer;

import java.io.IOException;
import java.util.ArrayList;

import appointer.calendar.AppUser;
import appointer.calendar.AppCalendar;
import appointer.util.io.IOCalendarPersistence;
import biweekly.ICalendar;
import biweekly.component.VEvent;

/**
 * Demonstration of project Calendar library
 *
 */
public class CalendarTest {
	public static void main(String[] args) {
		
		createJohnDoeEventAndPrint();
		
		
		testCalendarPersistence();	
		
	}

	/**
	 * Creates and prints an event belonging to user;
	 */
	private static void createJohnDoeEventAndPrint() {
		VEvent vEventOne = AppCalendar.createEventCurrentTime(); //we still might need an adapter;
			
		AppCalendar.setOrganiser(vEventOne, new AppUser("John Doe").getName());
		
		AppCalendar.printComponentProperties(vEventOne);
		
		AppCalendar.printComponentComponents(vEventOne);
			
		System.out.println("Finished");
	}


	/**
	 * Writes and reads a calendar object to file;
	 */
	private static void testCalendarPersistence() {
		ICalendar calendar = new ICalendar();

		// AppEvent is the wrapper to the library class VEvent;
		VEvent vEventOne = AppCalendar.createEventCurrentTime();
		AppCalendar.addOneHourToEvent(vEventOne);
		calendar.addEvent(vEventOne);
		
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
