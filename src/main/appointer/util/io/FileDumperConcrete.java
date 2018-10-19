package appointer.util.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FileDumperConcrete {
	
	static PrintWriter filewriter = null;
	
	
	/**
	 * @param fileName
	 * @param filew
	 * @throws IOException
	 */
	public static void startDump(String fileName, PrintReplace replaceMode) throws IOException {
		System.out.println("Guided dump");
		File file = new File(fileName);
		file.createNewFile();
		switch(replaceMode){
		case Replace:
			filewriter = new PrintWriter(new FileWriter(fileName, false));
		case noReplace:
			filewriter = new PrintWriter(new FileWriter(fileName, true));
		}
	}
	
	/**
	 * @param fileName
	 * @param filew
	 * @throws IOException
	 */
	public static void startDump(String fileName, PrintWriter filew) throws IOException {
		System.out.println("Guided dump");
		File file = new File(fileName);
		file.createNewFile();
		filewriter = filew;
	}
	
	/**
	 * default replacing
	 * @param fileName
	 * @throws IOException
	 */
	public static void startDump(String fileName) throws IOException {
		System.out.println("Writing to file...");
		File file = new File(fileName);
		file.createNewFile();
		filewriter = new PrintWriter(new FileWriter(fileName, false));
	}
	
	public static void doDump(String string) {
		filewriter.println(string);
	}
	
	public static void endDump() {
		filewriter.flush();
		filewriter.close();
	}
}