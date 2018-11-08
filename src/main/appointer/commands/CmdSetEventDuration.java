package appointer.commands;

import biweekly.component.VEvent;
import biweekly.property.DurationProperty;
import biweekly.util.Duration;

public class CmdSetEventDuration implements AppCommand {
	
	private final VEvent event;  
	private final Duration currentDuration;
	private Duration previousDuration;
 	
	public CmdSetEventDuration(VEvent event, Duration duration) {
		this.event = event;
		this.currentDuration = duration;
	}

	@Override 
	public void execute() {
		DurationProperty durationProperty = event.getDuration();
		previousDuration = durationProperty == null ? null : durationProperty.getValue() ;
		event.setDuration(currentDuration);
	}

	@Override
	public void undo() {
		event.setDuration(previousDuration);
	}
	
	// leaf node
	@Override
	public void add(AppCommand appCommand) {
	}

	@Override
	public void remove(AppCommand appCommand) {
	}

	@Override
	public AppCommand getChild(int i) {
		return new CmdEmpty();
	}

}
