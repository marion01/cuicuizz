package fr.isima.cuicuizz.front.mode;

import java.io.IOException;
import java.util.List;

import fr.isima.cuicuizz.front.ConnectedUser;
import fr.isima.cuicuizz.front.Question;
import fr.isima.cuicuizz.front.ScoreDto;
import fr.isima.cuicuizz.front.management.QuestionManagement;

public class Normal implements IMode {

	private static Normal instance;

	private List<Question> questions;

	QuestionManagement questionManagement;

	@Override
	public void execute(List<Question> pQuestions, QuestionManagement qm, String theme) {
		questionManagement = qm;
		questions = pQuestions;
		System.out.println("Normal mode");
		try {
			final int nbTrue = questionManagement.answerQuestions(questions);
			System.out.println("You have " + nbTrue + " valid response");
			questionManagement.visualizeCorrectResponse(questions);
			
			ScoreDto s = new ScoreDto();
			s.setMode("Normal");
			s.setTheme(theme);
			s.setNbQuestions(questions.size());
			s.setNbSuccess(nbTrue);
			s.setValue(nbTrue+"/"+questions.size());
			ConnectedUser.getInstance().setScore(s);
		} catch (final IOException e) {
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
