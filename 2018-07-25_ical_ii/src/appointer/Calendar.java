package appointer;

import java.util.Iterator;
import biweekly.component.VEvent;

/**
 * Interface for calendar. 
 */
public interface Calendar extends Iterable<VEvent>{
	public User owner();
	public void add(VEvent event);
	public boolean contains(VEvent event); 
	public void cancel(VEvent event);
	public Iterator<VEvent> iterator();
}
