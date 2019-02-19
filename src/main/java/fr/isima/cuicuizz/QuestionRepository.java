package fr.isima.cuicuizz;

import java.util.HashMap;
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
		questions = questionDbAccess.selectAll();
	}

	public Question findQuestion(Integer id) {
		Assert.notNull(id, "The question id must not be null");
		return QuestionConverter.convert(questions.get(id));
	}
}