package appointer.http;

import java.time.LocalDate;

import appointer.http.dto.AppointmentEvent;
import biweekly.property.FreeBusy;
import biweekly.util.Duration;

/**
 * Design whiteboard
 *
 */
public class MockHTTPClient {


	// /freebusy GET()
	public FreeBusy freebusy() {
		return null;
	}

	// /addappointment PUT(LocalDate start, Duration Duration)
	public AppointmentEvent addAppointment(LocalDate start, Duration duration) {
		return new AppointmentEvent();
	} 
	
	// /checkAppointment PUT(AppointmentStatusObject statusObject)
	public AppointmentEvent checkAppointment(AppointmentEvent statusObject) {
		return  statusObject;
	}
	
	// /change PUT(LocalDate start, Duration Duration)
	public AppointmentEvent changeAppointment(AppointmentEvent statusObject, LocalDate start, Duration duration) {
		return new AppointmentEvent();
	}

	//	day4  -  designing web api;
	//	get /{provider}/timeavailable/time/{}/duration/{}
	//	"free time";
	//	post /{provider}/appointments/time/{}/duration/{}
	//	"notification created";
	//	//callback
	//	post /{provider}/appointments/time/{}/duration/{}
	//	"confirmed";
	//	post /{provider}/appointments/time(?)/newtime/{}
	
}
