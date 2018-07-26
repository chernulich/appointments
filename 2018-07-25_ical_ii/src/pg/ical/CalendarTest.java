package pg.ical;
import java.util.Date;

import biweekly.component.VEvent;

public class CalendarTest {
	public static void main(String[] args) {
		VEvent event = new VEvent();
		Date start = new Date();
		event.setDateStart(start, false);
		System.out.println(event.toString());
	 }
}
	