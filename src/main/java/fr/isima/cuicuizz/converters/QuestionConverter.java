package fr.isima.cuicuizz.converters;

import fr.isima.cuicuizz.model.QuestionModel;
import io.spring.guides.gs_producing_web_service.Question;

public class QuestionConverter {

	public static Question convert(QuestionModel questionModel) {
		final Question question = new Question();
		question.setId(questionModel.getId());
		question.setValue(questionModel.getQuestion());
		return question;
	}

	public QuestionModel convert(Question obj) {
		return null;
	}
}
