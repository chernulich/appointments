package appointer.http.dto;

import biweekly.component.VEvent;

public class AppointmentEvent {
	VEvent event;
	
	//rep invariant: event must contain attendee and organiser; 
	
	private void repCheck() {
		//TODO: ensure rep invariant; 
	}

	public AppointmentEvent() {
		super();
	}
	
	public AppointmentEvent(VEvent event) {
		super();
		this.event = event;
	}
	
	public VEvent getEvent() {
		return event;
	}

	public void setEvent(VEvent event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "AppointmentEvent [event=" + event.toString() + "]";
	}


}
