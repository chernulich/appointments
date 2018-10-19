package appointer;

import java.io.IOException;
import java.util.ArrayList;

import appointer.calendar.CalendarPrinter;
import appointer.calendar.EventFacade;
import appointer.user.AppUser;
import appointer.util.io.IOCalendarPersistence;
import biweekly.ICalendar;
import biweekly.component.VEvent;

/**
 * Welcome to biweekly, CalendarPrinter and EventFacade
 */
public class WelcomeTest {
	
	public static void main(String[] args) {
		
		createJohnDoeEventAndPrint();
		
		testCalendarPersistence();	
		
	}

	/**
	 * Creates and prints an event belonging to user;
	 */
	private static void createJohnDoeEventAndPrint() {
		VEvent vEventOne = EventFacade.createEventCurrentTime(); //we still might need an adapter;
			
		EventFacade.setOrganiser(vEventOne, new AppUser("John Doe").getName());
		
		CalendarPrinter.printComponentProperties(vEventOne);
		
		CalendarPrinter.printComponentComponents(vEventOne);
			
		System.out.println("Finished");
	}


	/**
	 * Writes and reads a calendar object to file;
	 */
	private static void testCalendarPersistence() {
		ICalendar calendar = new ICalendar();

		VEvent vEventOne = EventFacade.createEventCurrentTime();
		EventFacade.addOneHourToEvent(vEventOne);
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
