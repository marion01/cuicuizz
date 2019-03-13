package fr.isima.cuicuizz.front.mode;

import java.io.IOException;
import java.util.List;

import fr.isima.cuicuizz.front.Question;

public interface IMode {

	/**
	 * Execute the correct mode
	 * @throws IOException 
	 */
	public void execute(List<Question> questions) throws IOException;

	public static IMode getInstance() {
		return null;}
}
