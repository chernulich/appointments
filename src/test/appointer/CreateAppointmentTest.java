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

		VEvent testEvent = createDemoEvent();
		
		CreateRESTClient.postAppEvent(testEvent);
		
		CreateRESTClient.getAppEvent(testEvent.getOrganizer().getCommonName());
		
		
	}

	private static VEvent createDemoEvent() {

		VEvent event = EventFacade.createEventCurrentTime();

		IUser user = new AppUser("Jane_Smith");

		EventFacade.addAttendee(event, user.getName());

		EventFacade.setOrganiser(event, "Ben_Bitdiddle");

		return event;

	}
}
