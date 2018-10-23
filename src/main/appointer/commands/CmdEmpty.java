package appointer.commands;

/**
 * Sentinel pattern: created to get rid out of nulls;
 */
public class CmdEmpty implements AppCommand {

	@Override
	public void execute() {
	}

	@Override
	public void undo() {
	}

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
