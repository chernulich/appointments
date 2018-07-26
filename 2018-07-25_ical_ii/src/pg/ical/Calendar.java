package pg.ical;

import java.util.Iterator;

public interface Calendar extends Iterable<Event>{
	public User owner();
	public boolean add(Event event);
	public boolean contains(Event event); 
	public boolean remove(Event event);
	public Iterator<Event> iterator();
}
