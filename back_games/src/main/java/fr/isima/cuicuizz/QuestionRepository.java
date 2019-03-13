package fr.isima.cuicuizz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import fr.isima.cuicuizz.converters.QuestionConverter;
import fr.isima.cuicuizz.model.QuestionModel;
import io.spring.guides.gs_producing_web_service.Question;

@Component
public class QuestionRepository {
	private static Map<Integer, QuestionModel> questions = new HashMap<>();

	@PostConstruct
	public void initData() {
		final QuestionDbAccess questionDbAccess = new QuestionDbAccess();
		// questions = questionDbAccess.selectAll();
		QuestionConverter.convert(questionDbAccess.selectQuestionsFromTheme(1, 10));
	}

	public List<Question> findQuestion(Integer themeId, Integer nb) {
		final QuestionDbAccess questionDbAccess = new QuestionDbAccess();
		System.out.println("coucou");
		Assert.notNull(themeId, "The question id must not be null");
		final List<Question> questions = QuestionConverter
				.convert(questionDbAccess.selectQuestionsFromTheme(themeId, nb));
		System.out.println(questions.size());
		System.out.println(questions.get(0).getValue());
		return questions;
	}
}