package fr.isima.cuicuizz.converters;

import java.util.ArrayList;
import java.util.List;

import fr.isima.cuicuizz.model.AnswerModel;
import io.spring.guides.gs_producing_web_service.Answer;

/**
 * Converter class from Models to webservice response
 * 
 * @author fabie
 *
 */
public class AnswerConverter {

	public static Answer convert(AnswerModel answerModel) {
		final Answer answer = new Answer();
		answer.setId(answerModel.getId());
		answer.setAnswer(answerModel.getAnswer());
		answer.setIsCorrect(answerModel.getIsCorrect());
		return answer;
	}

	public static List<Answer> convert(List<AnswerModel> answersModel) {
		final List<Answer> answers = new ArrayList<>();
		for (final AnswerModel answerModel : answersModel) {
			answers.add(convert(answerModel));
		}
		return answers;
	}

}
