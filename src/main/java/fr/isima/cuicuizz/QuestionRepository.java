package fr.isima.cuicuizz;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import fr.isima.cuicuizz.converters.QuestionConverter;
import fr.isima.cuicuizz.model.QuestionModel;
import fr.isima.db.QuestionDbAccess;
import io.spring.guides.gs_producing_web_service.Question;

@Component
public class QuestionRepository {
	private static Map<Integer, QuestionModel> questions = new HashMap<>();
	private static QuestionDbAccess questionDbAccess = new QuestionDbAccess();

	@PostConstruct
	public void initData() {
		questions = questionDbAccess.selectQuestionFromTheme(0);
	}

	public Question findQuestion(Integer themeId) {
		Assert.notNull(themeId, "The theme id must not be null");

		questions = questionDbAccess.selectQuestionFromTheme(themeId);
		System.out.println(questions.size());
		return QuestionConverter.convert(questions.get(0));
	}
}