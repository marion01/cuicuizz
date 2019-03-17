package fr.isima.cuicuizz.services.implementations;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isima.cuicuizz.converters.QuestionConverter;
import fr.isima.cuicuizz.converters.ThemeConverter;
import fr.isima.cuicuizz.dbaccess.mybatis.dao.QuestionMapper;
import fr.isima.cuicuizz.model.QuestionModel;
import fr.isima.cuicuizz.services.interfaces.IQuestionService;
import io.spring.guides.gs_producing_web_service.Question;
import io.spring.guides.gs_producing_web_service.Theme;

/**
 * Service used to process webservices calls
 * 
 *
 */
@Service
public class QuestionService implements IQuestionService {

	@Autowired
	private QuestionMapper questionMapper;

	@Override
	public List<Question> findQuestion(Integer themeId, Integer nb) {
		final List<QuestionModel> questions = questionMapper.selectAllQuestions(themeId);

		Collections.shuffle(questions);

		return QuestionConverter.convert((questions.size() < nb) ? questions : questions.subList(0, nb));
	}

	@Override
	public Integer getNbQuestionFromTheme(Integer themeId) {
		return questionMapper.getNbQuestionFromTheme(themeId);
	}

	@Override
	public List<Theme> getThemes() {
		return ThemeConverter.convert(questionMapper.getThemes());
	}
}
