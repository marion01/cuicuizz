package fr.isima.cuicuizz.front;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;

public class Utils {
	
	@Autowired
	private static Game game;
	
	private static BufferedReader entry = new BufferedReader(new InputStreamReader(System.in));
	
	public static String readEntry() throws IOException {
		final String string = entry.readLine();
		if ("exit".equals(string)) {
			game.begin();
			return null;
		} else {
			return string;
		}
	}
}
