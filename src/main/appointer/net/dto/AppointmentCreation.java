package appointer.net.dto;

import java.rmi.server.UID;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * dto for appointment creation; appointment is event with Organizer and Attendee; 
 *
 */
@Data
public class AppointmentCreation {
	
	UID uid;	
	String organizer;
	String attendee;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime start; 

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime end; 
	boolean created;
	boolean approved;
	
}
