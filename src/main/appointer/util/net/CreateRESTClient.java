package appointer.util.net;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import appointer.calendar.facades.EventFacade;
import appointer.http.dto.AppointmentEvent;
import appointer.user.AppUser;
import appointer.user.IUser;

/**
 * Tests connecting to the server, passes phone number to validate;
 */
public class CreateRESTClient {

	static RestTemplate restTemplate = new RestTemplate();

	public static void postAppEvent() throws URISyntaxException {

		AppointmentEvent appEvent = createDemoEvent();

		final String url = "http://localhost:8080/attendee/create";

		HttpHeaders headers = new HttpHeaders();

		RequestEntity<AppointmentEvent> requestEntity = new RequestEntity<AppointmentEvent>(appEvent, headers,
				HttpMethod.POST, new URI(url));

		ResponseEntity<Boolean> response = restTemplate.exchange(requestEntity, Boolean.class); // printing // without

		Boolean body = response.getBody();

		System.out.println(body);

	}

	private static AppointmentEvent createDemoEvent() {

		AppointmentEvent appEvent = new AppointmentEvent(EventFacade.createEventCurrentTime());

		IUser user = new AppUser("Jane Smith");

		EventFacade.addAttendee(appEvent.getEvent(), user.getName());

		EventFacade.setOrganiser(appEvent.getEvent(), "Ben Bitdiddle");

		return appEvent;

	}
}