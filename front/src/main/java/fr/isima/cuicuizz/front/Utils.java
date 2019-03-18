package fr.isima.cuicuizz.front;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

	private static BufferedReader entry = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * read in the console an int
	 * If exit is written, the application is closed
	 * 
	 * @return the int entry
	 * @throws IOException
	 */
	public static int readEntryNumber() throws IOException {
		boolean incorrectEntry = true;
		String string = "-1";
		while (incorrectEntry) {
			string = entry.readLine();
			if (!string.matches("[0-9]+")) {
				if (string.equals("exit"))
					System.exit(1);
				else
					System.out.println("incorrect entry");
			} else {
				incorrectEntry = false;
			}
		}
		return Integer.parseInt(string);
	}

	/**
	 * Read a string in the console
	 * If exit is written, the application is closed
	 * 
	 * @return the string read
	 * @throws IOException
	 */
	public static String readEntryString() throws IOException {
		final String string = entry.readLine();
		if (string.equals("exit"))
			System.exit(1);
		return string;
	}
}
