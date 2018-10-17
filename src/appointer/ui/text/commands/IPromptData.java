package appointer.ui.text.commands;

import java.io.BufferedReader;

/**
 * Auth data to drive AuthInput.readOneLine method
 */
public interface IPromptData {

	BufferedReader getReader();

	String getHint();

	String getExitWord();

}