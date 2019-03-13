package fr.isima.cuicuizz.services.interfaces;

import java.util.List;

import io.spring.guides.gs_producing_web_service.Question;

public interface IQuestionService {

	List<Question> findQuestion(Integer themeId, Integer nb);

}
