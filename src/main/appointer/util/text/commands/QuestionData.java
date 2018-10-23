package appointer.util.text.commands;

import java.io.BufferedReader;

/**
 * Stores question prompt and reader;
 */
public class QuestionData extends PromptUntilExitData implements ITextQuestionData {
	final String question;

	public QuestionData(BufferedReader reader, String question, String exitWord) {
		super(reader, exitWord);
		this.question = question;
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
	public String getQuestion() {
		return question;
	}


}
