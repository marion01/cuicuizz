package fr.isima.cuicuizz.front.webservices;

import fr.isima.cuicuizz.front.GetNbQuestionResponse;
import fr.isima.cuicuizz.front.GetQuestionResponse;
import fr.isima.cuicuizz.front.GetThemesResponse;

public interface IQuestionClient {

	GetQuestionResponse getQuestion(Integer id, Integer nbQuestions);

	GetNbQuestionResponse getNbQuestionsFromTheme(Integer themeId);

	GetThemesResponse getThemes();

}
