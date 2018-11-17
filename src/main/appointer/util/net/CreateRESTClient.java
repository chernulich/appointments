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
import appointer.util.date.DateAdapter;
import biweekly.component.VEvent;

/**
 * Tests connecting to the server, passes phone number to validate;
 */
public class CreateRESTClient {

	static RestTemplate restTemplate = new RestTemplate();

	public static void postAppEvent() throws URISyntaxException {

		VEvent event = createDemoEvent();
		
		AppointmentEvent appEvent = new AppointmentEvent();
		appEvent.setOrganiser(event.getOrganizer().getCommonName());
		appEvent.setAttendee(event.getAttendees().get(0).getCommonName());
		appEvent.setStart(DateAdapter.asLocalDateTime(event.getDateStart().getValue()));
		//appEvent.setEnd(DateAdapter.asLocalDateTime(event.getDateEnd().getValue()));
		
		System.out.println(appEvent.toString());
		

		final String url = "http://localhost:8080/attendee/create";

		HttpHeaders headers = new HttpHeaders();

		RequestEntity<AppointmentEvent> requestEntity = new RequestEntity<AppointmentEvent>(appEvent, headers,
				HttpMethod.POST, new URI(url));

		ResponseEntity<Boolean> response = restTemplate.exchange(requestEntity, Boolean.class); // printing // without

		Boolean body = response.getBody();

		System.out.println(body);

	}

	private static VEvent createDemoEvent() {

		VEvent event = EventFacade.createEventCurrentTime();

		IUser user = new AppUser("Jane Smith");

		EventFacade.addAttendee(event, user.getName());

		EventFacade.setOrganiser(event, "Ben Bitdiddle");

		return event;

	}
}