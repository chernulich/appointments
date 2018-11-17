package appointer.net.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import appointer.net.dto.AppointmentCreation;
import appointer.util.date.DateAdapter;
import biweekly.component.VEvent;

/**
 *Calls the server to create an appointment; 
 */
public class CreateRESTClient {

	static RestTemplate restTemplate = new RestTemplate();

	public static void postAppEvent(VEvent event) throws URISyntaxException {

		AppointmentCreation appEvent = doAppointmentCreation(event);
		
		System.out.println(appEvent.toString());
		
		final String url = "http://localhost:8080/attendee/create";

		HttpHeaders headers = new HttpHeaders();

		RequestEntity<AppointmentCreation> requestEntity = new RequestEntity<AppointmentCreation>(appEvent, headers,
				HttpMethod.POST, new URI(url));

		ResponseEntity<Boolean> response = restTemplate.exchange(requestEntity, Boolean.class); // printing // without

		Boolean body = response.getBody();

		System.out.println("Printing event creation result on server: " + body);

	}

	private static AppointmentCreation doAppointmentCreation(VEvent event) {
		
		AppointmentCreation appEvent = new AppointmentCreation();
		appEvent.setOrganizer(event.getOrganizer().getCommonName());
		appEvent.setAttendee(event.getAttendees().get(0).getCommonName());
		appEvent.setStart(DateAdapter.asLocalDateTime(event.getDateStart().getValue()));
		//appEvent.setEnd(DateAdapter.asLocalDateTime(event.getDateEnd().getValue()));
		//TODO: NPE ^
		return appEvent;
		
	}

	
}