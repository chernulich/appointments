package appointer.calendar;

import java.util.TreeSet;

public class AppEventCollection {
	private TreeSet<AppEvent> events;
	public TreeSet<AppEvent> events() {
		return events;
	}
	public AppEventCollection() {
		this.events = new TreeSet<AppEvent>();
	}
}
