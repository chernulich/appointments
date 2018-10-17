package appointer.ui.text.commands;

import java.io.BufferedReader;

/**
 * Stores question prompt and reader;
 */
public class StringQuestionData extends CommonPromptData implements IPromptData {
	final String question;

	public StringQuestionData(BufferedReader reader, String hint, String exitWord) {
		super(reader, exitWord);
		this.question = hint;
	}

	@Override
	public BufferedReader getReader() {
		return reader;
	}

	@Override
	public String getExitWord() {
		return exitWord;
	}

	@Override
	public String getHint() {
		return question;
	}


}
