package fr.isima.cuicuizz.front.mode;

import java.io.IOException;
import java.util.List;

import fr.isima.cuicuizz.front.Question;
import fr.isima.cuicuizz.front.management.QuestionManagement;

public interface IMode {

	/**
	 * Execute the correct mode
	 * @throws IOException 
	 */
	public void execute(List<Question> questions, QuestionManagement qm, String theme) throws IOException;

	/**
	 * Get the instance of the mode
	 * @return
	 */
	public static IMode getInstance() {
		return null;}
}
