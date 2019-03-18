package fr.isima.cuicuizz.front.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isima.cuicuizz.front.GetNbQuestionResponse;
import fr.isima.cuicuizz.front.GetQuestionResponse;
import fr.isima.cuicuizz.front.GetThemesResponse;
import fr.isima.cuicuizz.front.webservices.IQuestionClient;

@Service
public class QuestionService implements IQuestionService {

	@Autowired
	IQuestionClient questionClient;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetQuestionResponse getQuestion(Integer id, Integer nbQuestions) {
		return questionClient.getQuestion(id, nbQuestions);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetNbQuestionResponse getNbQuestionsFromTheme(Integer themeId) {
		return questionClient.getNbQuestionsFromTheme(themeId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetThemesResponse getThemes() {
		return questionClient.getThemes();
	}
}
