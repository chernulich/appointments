package appointer.util.text.commands;

import java.io.BufferedReader;

/**
 * Auth data to drive AuthInput.readOneLine method
 */
public interface ITextQuestionData {

	BufferedReader getReader();

	String getQuestion();

	String getExitWord();

}