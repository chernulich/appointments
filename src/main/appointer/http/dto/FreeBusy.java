package appointer.http.dto;

import biweekly.component.VFreeBusy;

public class FreeBusy {
	private VFreeBusy freeBusy;

	public FreeBusy(VFreeBusy freeBusy) {
		super();
		this.freeBusy = freeBusy;
	}

	public FreeBusy() {
		super();
	}

	public VFreeBusy getFreeBusy() {
		return freeBusy;
	}

	public void setFreeBusy(VFreeBusy freeBusy) {
		this.freeBusy = freeBusy;
	}
	
}
