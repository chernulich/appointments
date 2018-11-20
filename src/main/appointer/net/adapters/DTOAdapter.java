package appointer.net.adapters;

import appointer.net.dto.AppointmentCreation;
import appointer.util.date.DateAdapter;
import biweekly.component.VEvent;

public class DTOAdapter {

	public static AppointmentCreation toAppointmentCreation(VEvent event) {

		AppointmentCreation appEvent = new AppointmentCreation();

		appEvent.setOrganizer(event.getOrganizer().getCommonName());

		appEvent.setAttendee(event.getAttendees().get(0).getCommonName());

		appEvent.setStart(DateAdapter.asLocalDateTime(event.getDateStart().getValue()));

		// appEvent.setEnd(DateAdapter.asLocalDateTime(event.getDateEnd().getValue()));
		// TODO: NPE ^

		return appEvent;

	}
}