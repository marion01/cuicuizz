package fr.isima.cuicuizz.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.isima.cuicuizz.converters.QuestionConverter;
import fr.isima.cuicuizz.dbaccess.mybatis.dao.QuestionMapper;
import fr.isima.cuicuizz.services.interfaces.IQuestionService;
import io.spring.guides.gs_producing_web_service.Question;

@Service
public class QuestionService implements IQuestionService {

	@Autowired
	private QuestionMapper questionMapper;

	@Override
	public List<Question> findQuestion(Integer themeId, Integer nb) {
		return QuestionConverter.convert(questionMapper.selectAllQuestions());
	}
}
