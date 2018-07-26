package pg.ical;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.TimeZone;

/**
 * An event in the calendar
 */
public class NormalEvent implements Event, Comparable<Instant> 
	
	{

	//abstraction function: a timespan with a purpose
	//rep invariant: endTime > startTime
	
	private final User owner;
	private final EventProperties properties;
	private String name;
	private String description;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private TimeZone timeZone;

	public NormalEvent(User owner, EventProperties properties) {
		this.owner = owner;
		this.properties = properties;
	}
	
	@Override
	public User getOwner() {
		return owner;
	}
	
	@Override
	public EventProperties getProperties() {
		return properties;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public LocalDate getDate() {
		return date;
	}

	@Override
	public void setDate(LocalDate startDate) {
		this.date = startDate;
	}

	@Override
	public LocalTime getStartTime() {
		return startTime;
	}

	@Override
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	@Override
	public LocalTime getEndTime() {
		return endTime;
	}

	@Override
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	@Override
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	@Override
	public int compareTo(Instant o) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	@Override
	public void setRepetition(boolean Daily, boolean Weekly, boolean Montly) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeStartTime(int change, TemporalAmount amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeDuration(int change, TemporalAmount amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeCategory(EventCategory toCategory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EventCategory getCategory() {
		return this.properties.getCategory();
	}

	@Override
	public void setCategory(EventCategory category) {
		this.properties.setCategory(category);
	}
}
