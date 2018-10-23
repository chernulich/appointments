package appointer.util.text.commands;

import java.io.BufferedReader;

public class PromptUntilExitData  {

	/**
	 * Keeps together text for reading user until exitword;
	 */
	protected final BufferedReader reader;
	protected final String exitWord;
	
	public PromptUntilExitData(BufferedReader reader, String exitWord) {
		this.reader = reader;
		this.exitWord = exitWord;
	}

	

}