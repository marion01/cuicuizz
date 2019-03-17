package fr.isima.cuicuizz.converters;

import java.util.ArrayList;
import java.util.List;

import fr.isima.cuicuizz.model.QuestionModel;
import io.spring.guides.gs_producing_web_service.Question;

/**
 * Converter class from Questions to webservice response
 * 
 * @author fabie
 *
 */
public interface QuestionConverter {

	public static Question convert(QuestionModel questionModel) {
		final Question question = new Question();
		question.setId(questionModel.getId());
		question.setValue(questionModel.getQuestion());
		question.getAnswers().addAll(AnswerConverter.convert(questionModel.getAnswers()));
		question.setTheme(questionModel.getThemeId());
		return question;
	}

	public static List<Question> convert(List<QuestionModel> questionsModel) {
		final List<Question> questions = new ArrayList<>();
		for (final QuestionModel questionModel : questionsModel) {
			questions.add(convert(questionModel));
		}
		return questions;
	}
}
