package fr.isima.cuicuizz.front.mode;

import java.io.IOException;
import java.util.List;

import fr.isima.cuicuizz.front.Application;
import fr.isima.cuicuizz.front.Game;
import fr.isima.cuicuizz.front.Question;

public class Normal implements IMode {
	
	private static Normal instance;
	
	private List<Question> questions;

	@Override
	public void execute(List<Question> pQuestions) {
		questions = pQuestions;
		System.out.println("Normal mode");
		try {
			int nbTrue = Application.game.getQuestionManagement().answerQuestions(questions);
			System.out.println("You have "+ nbTrue + " valid response");
			Application.game.getQuestionManagement().visualizeCorrectResponse(questions);
			Application.game.menu();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static IMode getInstance() {
		if (instance == null) {
			instance = new Normal();
		} 
		return instance;
	}

}
