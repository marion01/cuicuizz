package fr.isima.cuicuizz.front.mode;

import java.io.IOException;
import java.util.List;

import fr.isima.cuicuizz.front.Application;
import fr.isima.cuicuizz.front.Game;
import fr.isima.cuicuizz.front.Question;
import fr.isima.cuicuizz.front.management.QuestionManagement;

public class Normal implements IMode {
	
	private static Normal instance;
	
	private List<Question> questions;
	
	QuestionManagement questionManagement;

	@Override
	public void execute(List<Question> pQuestions, QuestionManagement qm) {
		questionManagement = qm;
		questions = pQuestions;
		System.out.println("Normal mode");
		try {
			int nbTrue = questionManagement.answerQuestions(questions);
			System.out.println("You have "+ nbTrue + " valid response");
			questionManagement.visualizeCorrectResponse(questions);
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
