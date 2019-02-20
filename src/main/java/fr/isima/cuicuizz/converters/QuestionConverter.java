package fr.isima.cuicuizz.converters;

import java.util.ArrayList;
import java.util.List;

import fr.isima.cuicuizz.model.QuestionModel;
import io.spring.guides.gs_producing_web_service.Question;

public class QuestionConverter {

	public static Question convert(QuestionModel questionModel) {
		final Question question = new Question();
		question.setId(questionModel.getId());
		question.setValue(questionModel.getQuestion());
		return question;
	}

	public static List<Question> convert(List<QuestionModel> questionsModel) {
		final List<Question> questions = new ArrayList<>();
		for (final QuestionModel questionModel : questionsModel) {
			final Question question = new Question();
			question.setId(questionModel.getId());
			question.setValue(questionModel.getQuestion());
			questions.add(question);
		}
		return questions;
	}

	public QuestionModel convert(Question obj) {
		return null;
	}
}
