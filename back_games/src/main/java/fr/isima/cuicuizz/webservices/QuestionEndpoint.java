package fr.isima.cuicuizz.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.isima.cuicuizz.services.interfaces.IQuestionService;
import io.spring.guides.gs_producing_web_service.GetQuestionRequest;
import io.spring.guides.gs_producing_web_service.GetQuestionResponse;

@Endpoint
public class QuestionEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	@Autowired
	private IQuestionService questionService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getQuestionRequest")
	@ResponsePayload
	public GetQuestionResponse getQuestion(@RequestPayload GetQuestionRequest request) {
		final GetQuestionResponse response = new GetQuestionResponse();
		response.getQuestions().addAll(questionService.findQuestion(request.getThemeId(), request.getNbQuestions()));

		return response;
	}
}