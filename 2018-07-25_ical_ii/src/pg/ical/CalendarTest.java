package pg.ical;
import java.net.SocketException;

import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.util.FixedUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;


public class CalendarTest {
	public static void main(String[] args) throws SocketException {
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.set(java.util.Calendar.MONTH, java.util.Calendar.DECEMBER);
		calendar.set(java.util.Calendar.DAY_OF_MONTH, 25);

		// initialise as an all-day event..
		VEvent christmas = new VEvent(new Date(calendar.getTime()), "Christmas Day");

		// Generate a UID for the event..
		UidGenerator ug = new FixedUidGenerator("1");
		christmas.getProperties().add(ug.generateUid());

		net.fortuna.ical4j.model.Calendar cal = new net.fortuna.ical4j.model.Calendar();
		cal.getComponents().add(christmas);
		System.out.println(cal.toString());
	 }
}
	