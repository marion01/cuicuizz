package fr.isima.cuicuizz.front.mode;

import java.io.IOException;
import java.util.List;

import fr.isima.cuicuizz.front.Application;
import fr.isima.cuicuizz.front.Game;
import fr.isima.cuicuizz.front.Question;

public class Speed implements IMode{
	
	private static Speed instance;

	@Override
	public void execute(List<Question> questions) {
		System.out.println("Speed mode");	
		try {
			long start = System.currentTimeMillis();
			int nbTrue = Application.game.getQuestionManagement().answerQuestions(questions);
			long end = System.currentTimeMillis();
			double time = (end - start)/1000;
			System.out.println(time + "s");
			System.out.println("You have "+ nbTrue + " valid response");
			Application.game.getQuestionManagement().visualizeCorrectResponse(questions);
			Application.game.menu();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static IMode getInstance() {
		if (instance == null) {
			instance = new Speed();
		} 
		return instance;
	}

}
