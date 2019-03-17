package fr.isima.cuicuizz.services.interfaces;

import java.util.List;

import io.spring.guides.gs_producing_web_service.Question;
import io.spring.guides.gs_producing_web_service.Theme;

public interface IQuestionService {
	/**
	 * returns a list of n questions using a given theme
	 * 
	 * @param themeId
	 * @param nb
	 * @return
	 */
	List<Question> findQuestion(Integer themeId, Integer nb);

	/**
	 * returns the number of questions for a specific theme
	 * 
	 * @param themeId
	 * @return
	 */
	Integer getNbQuestionFromTheme(Integer themeId);

	/**
	 * returns all the available themes
	 * 
	 * @return
	 */
	List<Theme> getThemes();

}
