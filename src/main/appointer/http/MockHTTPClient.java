package appointer.http;

import java.time.LocalDate;

import appointer.http.dto.AppointmentStatusObject;
import biweekly.property.FreeBusy;
import biweekly.util.Duration;

/**
 * Design whiteboard
 * @author shtirlitz
 *
 */
public class MockHTTPClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub;
	}

	// /freebusy GET()
	public FreeBusy freebusy() {
		return null;
	}

	// /addappointment PUT(LocalDate start, Duration Duration)
	public AppointmentStatusObject addAppointment(LocalDate start, Duration duration) {
		return new AppointmentStatusObject();
	} 
	
	// /checkAppointment PUT(AppointmentStatusObject statusObject)
	public AppointmentStatusObject checkAppointment(AppointmentStatusObject statusObject) {
		return  statusObject;
	}
	
	// /change PUT(LocalDate start, Duration Duration)
	public AppointmentStatusObject changeAppointment(AppointmentStatusObject statusObject, LocalDate start, Duration duration) {
		return new AppointmentStatusObject();
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
