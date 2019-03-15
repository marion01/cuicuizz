package fr.isima.cuicuizz.front.management;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.isima.cuicuizz.front.Answer;
import fr.isima.cuicuizz.front.Question;
import fr.isima.cuicuizz.front.Utils;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class QuestionManagement {
		
	public boolean displayQuestion(Question q) throws IOException {
		System.out.println(q.getValue());
		final List<Answer> answers = q.getAnswers();
		for (int i = 0; i < answers.size(); i++) {
			System.out.println(i + "." + answers.get(i).getAnswer());
		}
		String i = null;
		boolean wrongAnswer = true;
		while (wrongAnswer) {
			i = Utils.readEntry();
			wrongAnswer = false;
			if (Integer.parseInt(i) >= answers.size()) {
				System.out.println("incorrect response");
				wrongAnswer = true;
			}
		}
		if (answers.get(Integer.parseInt(i)).isIsCorrect())
			return true;
		return false;
	}
	
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
	
	public int getNumberQuestions(int nbMax) {
		boolean tooManyQuestion = true;
		System.out.println("Choose the number of questions:");
		String nbQuestion;
		int nb = 0;
		while (tooManyQuestion) {
			try {
				nbQuestion = Utils.readEntry();
				nb = Integer.parseInt(nbQuestion);
				tooManyQuestion = false;
				if (nb > nbMax) {
					tooManyQuestion = true;
					System.out.println("There are not enough questions, choose less question");
				}
			} catch (final IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return nb;
	}

}
