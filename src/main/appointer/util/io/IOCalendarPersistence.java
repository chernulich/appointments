package appointer.util.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import biweekly.ICalendar;

public class IOCalendarPersistence {

	/**
	 * Allows to write a list of calendars into file. 
	 */
	public static void dumpToFile(List<? extends ICalendar> list) throws IOException {
		FileDumperConcrete.startDump("AppointmentClientTest.txt");
		for (ICalendar calendar : list)
			FileDumperConcrete.doDump(calendar.toString());
		FileDumperConcrete.endDump();
	}

	/**
	 * Reader of text file
	 * @throws IOException
	 */
	public static void readFromFile() throws IOException {
		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new FileReader("AppointmentClientTest.txt"));

			String l;
			while ((l = inputStream.readLine()) != null) {
				System.out.println(l);
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

}
