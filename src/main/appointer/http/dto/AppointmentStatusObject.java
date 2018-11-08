package appointer.http.dto;

import biweekly.component.VEvent;

public class AppointmentStatusObject {
	VEvent event;

	public VEvent getEvent() {
		return event;
	}

	public void setEvent(VEvent event) {
		this.event = event;
	}

	public AppointmentStatusObject() {
		super();
	}
	
	public AppointmentStatusObject(VEvent event) {
		super();
		this.event = event;
	}
}
