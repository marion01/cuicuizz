package fr.isima.cuicuizz.front.management;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.isima.cuicuizz.front.Answer;
import fr.isima.cuicuizz.front.Question;
import fr.isima.cuicuizz.front.Utils;

/**
 * Class which handle the interaction with the questions
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class QuestionManagement {
		
	/**
	 * Display the question and the answers and recover the user response
	 * @param q the question
	 * @return if the response of the user is correct
	 * @throws IOException
	 */
	public boolean displayQuestion(Question q) throws IOException {
		//display question and answers
		System.out.println(q.getValue());
		final List<Answer> answers = q.getAnswers();
		for (int i = 0; i < answers.size(); i++) {
			System.out.println(i + "." + answers.get(i).getAnswer());
		}
		
		//recover the user response
		int i = 0;
		boolean wrongAnswer = true;
		while (wrongAnswer) {
			i = Utils.readEntryNumber();
			wrongAnswer = false;
			if (i >= answers.size()) {
				System.out.println("incorrect response");
				wrongAnswer = true;
			}
		}
		if (answers.get(i).isIsCorrect())
			return true;
		return false;
	}
	
	/**
	 * Visualize the rights answers of the questions of the game
	 * @param questions of the game
	 * @throws IOException
	 */
	public void visualizeCorrectResponse(List<Question> questions) throws IOException {
		System.out.println();
		System.out.println("Wright answers :");
		for (final Question q : questions) {
			System.out.print(q.getValue() + ":");
			for (final Answer a : q.getAnswers()) {
				if (a.isIsCorrect()) {
					System.out.println(a.getAnswer());
					break;
				}
			}
		}
		System.out.println();
	}
	
	/**
	 * Let the user respond to all the questions
	 * 
	 * @return the number of valid response of a player
	 */
	public int answerQuestions(List<Question> questions) {
		int nbTrue = 0;
		try {
			for (final Question q : questions) {
				final boolean result = displayQuestion(q);
				if (result)
					nbTrue++;
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return nbTrue;
	}
	
	/**
	 * Recover from the user the number of question wanted for the game
	 * 
	 * @param nbMax number max of question available
	 * @return the number of question chosen
	 */
	public int getNumberQuestions(int nbMax) {
		boolean tooManyQuestion = true;
		System.out.println("Choose the number of questions:");
		int nb = 0;
		while (tooManyQuestion) {
			try {
				nb = Utils.readEntryNumber();
				tooManyQuestion = false;
				if (nb > nbMax) {
					tooManyQuestion = true;
					System.out.println("There are not enough questions, choose less question");
				}
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		return nb;
	}

}
