package appointer.commands;

/**
 * Commands for the calendar;
 * uses Compoiste pattern
 */
public interface AppCommand {
	public void execute();

	public void undo();

	public void add(AppCommand appCommand);

	public void remove(AppCommand appCommand);

	public AppCommand getChild(int i);
}
