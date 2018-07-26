package pg.ical;

import java.util.Iterator;
import java.util.TreeSet;

public class NormalCalendar implements Calendar {
	// abstraction function: a timespan of events
	// rep invariant: events are unique and sortedl
	private TreeSet<Event> events;
	private final User owner;

	{
		events = new TreeSet<Event>();
	}

	public NormalCalendar(User owner) {
		this.owner = owner;
	}

	@Override
	public User owner() {
		return owner;
	}

	@Override
	public boolean add(Event event) {
		return events.add(event);
	}

	@Override
	public boolean contains(Event event) {
		return events.contains(event);
	}

	@Override
	public boolean remove(Event event) {
		return events.remove(event);
	}

	@Override
	public Iterator<Event> iterator() {
		return events.iterator();
	}

}
