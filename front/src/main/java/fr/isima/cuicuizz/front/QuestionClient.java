package fr.isima.cuicuizz.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class QuestionClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(QuestionClient.class);

	public GetQuestionResponse getQuestion(Integer id, Integer nbQuestions) {

		final GetQuestionRequest request = new GetQuestionRequest();
		request.setThemeId(id);
		request.setNbQuestions(nbQuestions);

		log.info("Requesting location for " + id);

		return (GetQuestionResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:8080/ws/questions",
				request, new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/GetQuestionRequest"));
	}

}