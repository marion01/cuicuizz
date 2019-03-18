package fr.isima.cuicuizz.front.webservices;

import fr.isima.cuicuizz.front.GetNbQuestionResponse;
import fr.isima.cuicuizz.front.GetQuestionResponse;
import fr.isima.cuicuizz.front.GetThemesResponse;

public interface IQuestionClient {
	/**
	 * Get a number of questions in question back in a specific theme
	 * @param id the theme id
	 * @param nbQuestions the number of question wanted
	 * @return the questions
	 */
	GetQuestionResponse getQuestion(Integer id, Integer nbQuestions);

	/**
	 * Get the number of question saved in database in a theme
	 * @param themeId
	 * @return the number of question
	 */
	GetNbQuestionResponse getNbQuestionsFromTheme(Integer themeId);

	/**
	 * Get the theme available
	 * @return themes
	 */
	GetThemesResponse getThemes();

}
