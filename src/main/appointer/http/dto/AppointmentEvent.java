package appointer.http.dto;

import java.rmi.server.UID;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AppointmentEvent {
	
	UID uid;	
	String organiser;
	String attendee;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime start; 
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime end; 
	
	boolean created;
	boolean approved;
	
}
