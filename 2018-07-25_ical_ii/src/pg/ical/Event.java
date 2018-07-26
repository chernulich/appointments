package pg.ical;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.TimeZone;

/**
 * Event in our app. 
 *
 */
public interface Event {
	
	public User getOwner(); // immutable
	public String getName();
	public String getDescription();
	public LocalDate getDate(); //conversion from util.time
	public LocalTime getStartTime(); 
	public LocalTime getEndTime();
	public TimeZone getTimeZone();
	public EventProperties getProperties();
	public EventCategory getCategory();
	

	public void setName(String name);
	public void setDescription(String description);
	public void setDate(LocalDate date); //conversion from util.time
	public void setStartTime(LocalTime startTime); 
	public void setEndTime(LocalTime endTime); // duration is better
	public void setTimeZone(TimeZone timezone);
	public void setCategory(EventCategory category);
	
	
	public void setRepetition(boolean Daily, boolean Weekly, boolean Montly);
	public void changeStartTime(int change, TemporalAmount amount);
	public void changeDuration(int change, TemporalAmount amount);
	public void changeCategory(EventCategory toCategory);
}
