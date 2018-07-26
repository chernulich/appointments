package pg.ical;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.TimeZone;

import biweekly.component.VEvent;
import biweekly.util.ICalDate;

public class BiWeeklyEvent implements Event, Comparable<Instant> 
{
	private final VEvent event;
	private Instant start;
	private Instant end;
	private TimeZone timezone = TimeZone.getDefault();
	
	public static void main(String[] args) {
		VEvent event = new VEvent();
		Date start = LegacyDateConverter.asDate(LocalDate.now());
		event.setDateStart(start, false);
		System.out.println();
	}
	
	{	
		event = new VEvent();
		start = event.getDateStart().getValue().toInstant(); 
		end = event.getDateEnd().getValue().toInstant(); 
	}
	
	@Override
	public int compareTo(Instant o) {
		return start.compareTo(o);
	}

	@Override
	public User getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		return event.getDescription().toString();
	}

	@Override
	public LocalDate getDate() {
		return LocalDate.from(start);
	}

	@Override
	public LocalTime getStartTime() {
		return LocalTime.from(start);
	}

	@Override
	public LocalTime getEndTime() {
		return LocalTime.from(end);
	}

	@Override
	public TimeZone getTimeZone() {
		return timezone;
	}

	@Override
	public EventProperties getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventCategory getCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDescription(String description) {
		event.setDescription(description);
	}

	@Override
	public void setDate(LocalDate date) {
		//event.setDateStart(date);		
	}

	@Override
	public void setStartTime(LocalTime startTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEndTime(LocalTime endTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTimeZone(TimeZone timezone) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCategory(EventCategory category) {
		// TODO Auto-generated method stub
		
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

}
