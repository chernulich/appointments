package appointer.net.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import appointer.net.adapters.DTOAdapter;
import appointer.net.dto.AppointmentCreation;
import biweekly.component.VEvent;

/**
 *Calls the server to create an appointment; 
 */
public class CreateRESTClient {

	static RestTemplate restTemplate = new RestTemplate();

	static HttpHeaders headers = new HttpHeaders();
	
	public static void attendeeNewAppointment(AppointmentCreation appEvent) throws URISyntaxException {
				
		final String url = "http://localhost:8080/attendee/create/";

		
		RequestEntity<AppointmentCreation> requestEntity = new RequestEntity<AppointmentCreation>(appEvent, headers,
				HttpMethod.POST, new URI(url));

		ResponseEntity<Boolean> response = restTemplate.exchange(requestEntity, Boolean.class); // printing // without

		Boolean body = response.getBody();

		System.out.println("Printing event creation result on server: " + body + " with ID " + appEvent.getUid());

	}
	
	public static void organiserPendingAppoinmentPrint(String organiserName) throws URISyntaxException {
			
		final String url = "http://localhost:8080/organiser/create/";
	
		RequestEntity<AppointmentCreation> requestEntity = new RequestEntity<AppointmentCreation>(headers,
				HttpMethod.GET, new URI(url + "?orgname=" + organiserName));

		
		ResponseEntity<AppointmentCreation> response = restTemplate.exchange(requestEntity, AppointmentCreation.class); // printing // without

		AppointmentCreation body = response.getBody();

		System.out.println("Printing pulled event from server: " + body);

	}

	

	
}