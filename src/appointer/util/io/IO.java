package appointer.util.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import appointer.ui.text.commands.IPromptData;
import appointer.ui.text.commands.StringQuestionData;

public class IO {
	
	/**
	 * Prompts user to enter phone number
	 */
	public static String phoneFromUser() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		IPromptData phoneData = new StringQuestionData(br, "Insert telephone number", "quit");
		return readOneLine(phoneData);
	}
	
	/**
	 * @return line from input
	 * @param data
	 *            IPromptData object Adapter over BufferedReader that takes coupled
	 *            Command IPromptData;
	 */
	public static String readOneLine(IPromptData data) {
		String line = null;
		while (true) {
			System.out.println(data.getHint());
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
