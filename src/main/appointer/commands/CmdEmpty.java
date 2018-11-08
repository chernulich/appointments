package appointer.commands;

/**
 * Sentinel pattern: created to get rid out of nulls;
 */
public class CmdEmpty extends CmdLeaf implements AppCommand {

	@Override
	public void execute() {
	}

	@Override
	public void undo() {
	}

}
