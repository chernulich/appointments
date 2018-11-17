package appointer;

import java.net.URISyntaxException;

import appointer.calendar.facades.EventFacade;
import appointer.net.client.CreateRESTClient;
import appointer.user.AppUser;
import appointer.user.IUser;
import biweekly.component.VEvent;

/**
 * Tests the connection from attendee to the server when we need to create event
 */
public class CreateAppointmentTest {

	public static void main(String[] args) throws URISyntaxException {

		CreateRESTClient.postAppEvent(createDemoEvent());
	}

	private static VEvent createDemoEvent() {

		VEvent event = EventFacade.createEventCurrentTime();

		IUser user = new AppUser("Jane Smith");

		EventFacade.addAttendee(event, user.getName());

		EventFacade.setOrganiser(event, "Ben Bitdiddle");

		return event;

	}
}
