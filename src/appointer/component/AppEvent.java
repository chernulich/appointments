package appointer.component;

import java.util.Date;

import biweekly.component.VEvent;

/**
 * Wrapper around the VEvent library class
 */
public class AppEvent implements Comparable<AppEvent>{
	User owner;
	VEvent vevent;
	
	public AppEvent(User owner, VEvent vevent) {
		this.owner = owner;
		this.vevent = vevent;
	}
	
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public VEvent getVevent() {
		return vevent;
	}
	public void setVevent(VEvent vevent) {
		this.vevent = vevent;
	}
	@Override
	public int compareTo(AppEvent other) {
		if (this == other) return 0;
		Date startThis = this.getVevent().getDateStart().getValue();
		Date startOther = other.getVevent().getDateStart().getValue();
		return startThis.compareTo(startOther);
	}

}
