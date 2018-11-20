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

	public static void postAppEvent(VEvent event) throws URISyntaxException {

		AppointmentCreation appEvent = DTOAdapter.toAppointmentCreation(event);
				
		final String url = "http://localhost:8080/attendee/create/";

		HttpHeaders headers = new HttpHeaders();

		RequestEntity<AppointmentCreation> requestEntity = new RequestEntity<AppointmentCreation>(appEvent, headers,
				HttpMethod.POST, new URI(url));

		ResponseEntity<Boolean> response = restTemplate.exchange(requestEntity, Boolean.class); // printing // without

		Boolean body = response.getBody();

		System.out.println("Printing event creation result on server: " + body);

	}
	
	public static void getAppEvent(String organiserName) throws URISyntaxException {
			
		final String url = "http://localhost:8080/organiser/create/";

		
		HttpHeaders headers = new HttpHeaders();

		RequestEntity<AppointmentCreation> requestEntity = new RequestEntity<AppointmentCreation>(headers,
				HttpMethod.GET, new URI(url + "?orgname=" + organiserName));

		System.out.println(requestEntity);
		
		ResponseEntity<AppointmentCreation> response = restTemplate.exchange(requestEntity, AppointmentCreation.class); // printing // without

		AppointmentCreation body = response.getBody();

		System.out.println("Printing pulled event from server: " + body);

	}

	

	
}