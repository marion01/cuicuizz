package fr.isima.cuicuizz.front.mode;

import java.io.IOException;
import java.util.List;

import fr.isima.cuicuizz.front.Application;
import fr.isima.cuicuizz.front.Game;
import fr.isima.cuicuizz.front.Question;
import fr.isima.cuicuizz.front.management.QuestionManagement;

public class Speed implements IMode{
	
	private static Speed instance;
	QuestionManagement questionManagement;
	
	@Override
	public void execute(List<Question> questions, QuestionManagement qm) {
		this.questionManagement = qm;
		System.out.println("Speed mode");	
		try {
			long start = System.currentTimeMillis();
			int nbTrue = questionManagement.answerQuestions(questions);
			long end = System.currentTimeMillis();
			double time = (end - start)/1000;
			System.out.println(time + "s");
			System.out.println("You have "+ nbTrue + " valid response");
			questionManagement.visualizeCorrectResponse(questions);
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
