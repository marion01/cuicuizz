package fr.isima.cuicuizz.front.mode;

import java.io.IOException;
import java.util.List;

import fr.isima.cuicuizz.front.Main;
import fr.isima.cuicuizz.front.Question;

public class Normal implements IMode {
	
	private static Normal instance;
	
	private List<Question> questions;

	@Override
	public void execute(List<Question> pQuestions) {
		questions = pQuestions;
		System.out.println("Normal mode");
		try {
			int nbTrue = Main.answerQuestions(questions);
			System.out.println("You have "+ nbTrue + " valid response");
			Main.visualizeCorrectResponse(questions);
			Main.menu();
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
