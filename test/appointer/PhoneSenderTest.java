package appointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import appointer.uicommands.IPromptData;
import appointer.uicommands.StringQuestionData;

public class PhoneSenderTest {
	
	public static void main(String[] args) {
		BufferedReader br = reader();
		IPromptData phoneData = new StringQuestionData(br, "Insert telephone number", "quit");
		System.out.println(readOneLine(phoneData));
	}
	
	private static BufferedReader reader() {
		return new BufferedReader(new InputStreamReader(System.in));
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
