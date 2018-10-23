package appointer.ui.text.dialogs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import appointer.util.io.console.IOTextQuestions;
import appointer.util.text.commands.ITextQuestionData;
import appointer.util.text.commands.QuestionData;

public class UserDialogPhone {
	
	// Can possibly refactor that;
	/**
	 * Prompts user to enter phone number
	 */
	public static String phoneFromUser() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ITextQuestionData phoneData = new QuestionData(br, "Insert telephone number", "quit");
		return IOTextQuestions.readOneLine(phoneData);
	}
	
}
