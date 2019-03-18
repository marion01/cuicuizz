package fr.isima.cuicuizz.front.mode;

import java.io.IOException;
import java.util.List;

import fr.isima.cuicuizz.front.ConnectedUser;
import fr.isima.cuicuizz.front.Question;
import fr.isima.cuicuizz.front.ScoreDto;
import fr.isima.cuicuizz.front.management.QuestionManagement;

public class Speed implements IMode {

	private static Speed instance;
	QuestionManagement questionManagement;

	/**
	 * Execute the speed mode
	 */
	@Override
	public void execute(List<Question> questions, QuestionManagement qm, String theme) {
		this.questionManagement = qm;
		System.out.println("Speed mode");
		try {
			final long start = System.currentTimeMillis();
			final int nbTrue = questionManagement.answerQuestions(questions);
			final long end = System.currentTimeMillis();
			final double time = (end - start) / 1000;
			System.out.println(time + "s");
			System.out.println("You have " + nbTrue + " valid response");
			questionManagement.visualizeCorrectResponse(questions);
			
			ScoreDto s = new ScoreDto();
			s.setMode("Speed");
			s.setTheme(theme);
			s.setNbQuestions(questions.size());
			s.setNbSuccess(nbTrue);
			s.setValue("time: " + time);
			ConnectedUser.getInstance().setScore(s);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * get the singleton instance
	 * @return the instance
	 */
	public static IMode getInstance() {
		if (instance == null) {
			instance = new Speed();
		}
		return instance;
	}

}
