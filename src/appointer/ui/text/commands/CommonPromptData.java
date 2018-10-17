package appointer.ui.text.commands;

import java.io.BufferedReader;

public class CommonPromptData  {

	/**
	 * Keeps together common AuthInput data
	 */
	protected final BufferedReader reader;
	protected final String exitWord;
	
	public CommonPromptData(BufferedReader reader, String exitWord) {
		this.reader = reader;
		this.exitWord = exitWord;
	}

	

}