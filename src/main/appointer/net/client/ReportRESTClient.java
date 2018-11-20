package appointer.net.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import appointer.net.dto.AppointmentCreation;

public class ReportRESTClient {

	static RestTemplate restTemplate = new RestTemplate();

	static HttpHeaders headers = new HttpHeaders();
	
	/**
	 * Idempotent event report from organiser; event must include organiser name
	 * 
	 * @param event
	 * @throws URISyntaxException
	 */
	public static void organiserReportEvent(AppointmentCreation appEvent) throws URISyntaxException {

		String organiserName = appEvent.getOrganizer();

		if (organiserName == "")
			throw new IllegalArgumentException();

		final String url = "http://localhost:8080/organiser/report/";

		

		RequestEntity<AppointmentCreation> requestEntity = new RequestEntity<AppointmentCreation>(appEvent, headers,
				HttpMethod.POST, new URI(url + "?orgname=" + organiserName));

		
		
		ResponseEntity<Boolean> response = restTemplate.exchange(requestEntity, Boolean.class); // printing // without

		Boolean body = response.getBody();

		System.out.println("Printing event report result on server: " + body + " With ID " + appEvent.getUid());

	}

	/**
	 * Idempotent event report for attendee;
	 * @param appEvent
	 * @throws URISyntaxException
	 */
	public static void attendeeReportEvent(String organiserName, UUID uid) throws URISyntaxException {
		
		final String url = "http://localhost:8080/attendee/read/";
		
		RequestEntity requestEntity = new RequestEntity(headers,
				HttpMethod.GET, new URI(url +"?orgname="+organiserName+"&uid=" + uid.toString()));
		
		ResponseEntity<AppointmentCreation> response = restTemplate.exchange(requestEntity, AppointmentCreation.class); // printing // without
		
		AppointmentCreation body = response.getBody();

		System.out.println("Printing event report result for attendee: " + body);
	}
}
