package appointer.util.io.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import appointer.util.text.commands.ITextQuestionData;
import appointer.util.text.commands.QuestionData;

public class IOTextQuestions {
	
	/**
	 * @return line from input
	 * @param data
	 *            IPromptData object Adapter over BufferedReader that takes coupled
	 *            Command IPromptData;
	 */
	public static String readOneLine(ITextQuestionData data) {
		String line = null;
		while (true) {
			System.out.println(data.getQuestion());
			try {
				line = data.getReader().readLine();
			} catch (IOException e) {
			}
			if (line.equals(data.getExitWord()))
				return null;
			return line;
		}
	}

}
