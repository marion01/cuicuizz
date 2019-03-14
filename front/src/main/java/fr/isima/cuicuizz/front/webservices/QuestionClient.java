package fr.isima.cuicuizz.front.webservices;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import fr.isima.cuicuizz.front.GetNbQuestionRequest;
import fr.isima.cuicuizz.front.GetNbQuestionResponse;
import fr.isima.cuicuizz.front.GetQuestionRequest;
import fr.isima.cuicuizz.front.GetQuestionResponse;
import fr.isima.cuicuizz.front.GetThemesRequest;
import fr.isima.cuicuizz.front.GetThemesResponse;

@Component
public class QuestionClient extends WebServiceGatewaySupport implements IQuestionClient {

	@Override
	public GetQuestionResponse getQuestion(Integer id, Integer nbQuestions) {
		final GetQuestionRequest request = new GetQuestionRequest();
		request.setThemeId(id);
		request.setNbQuestions(nbQuestions);
		return (GetQuestionResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:8080/ws/questions",
				request, new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/GetQuestionRequest"));
	}

	@Override
	public GetNbQuestionResponse getNbQuestionsFromTheme(Integer themeId) {
		final GetNbQuestionRequest request = new GetNbQuestionRequest();
		request.setThemeId(themeId);
		return (GetNbQuestionResponse) getWebServiceTemplate().marshalSendAndReceive(
				"http://localhost:8080/ws/nbQuestions", request,
				new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/GetNbQuestionRequest"));
	}

	@Override
	public GetThemesResponse getThemes() {
		final GetThemesRequest request = new GetThemesRequest();
		return (GetThemesResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:8080/ws/themes",
				request, new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/GetThemesRequest"));
	}

}