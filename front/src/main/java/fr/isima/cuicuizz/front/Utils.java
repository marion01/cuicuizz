package fr.isima.cuicuizz.front;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

	private static BufferedReader entry = new BufferedReader(new InputStreamReader(System.in));

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

	public static String readEntryString() throws IOException {
		final String string = entry.readLine();
		if (string.equals("exit"))
			System.exit(1);
		return string;
	}
}
