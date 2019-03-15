package fr.isima.cuicuizz.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.isima.cuicuizz.services.interfaces.IQuestionService;
import io.spring.guides.gs_producing_web_service.GetNbQuestionRequest;
import io.spring.guides.gs_producing_web_service.GetNbQuestionResponse;
import io.spring.guides.gs_producing_web_service.GetQuestionRequest;
import io.spring.guides.gs_producing_web_service.GetQuestionResponse;
import io.spring.guides.gs_producing_web_service.GetThemesRequest;
import io.spring.guides.gs_producing_web_service.GetThemesResponse;

@Endpoint
public class QuestionEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	@Autowired
	private IQuestionService questionService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getQuestionRequest")
	@ResponsePayload
	public GetQuestionResponse getQuestions(@RequestPayload GetQuestionRequest request) {
		final GetQuestionResponse response = new GetQuestionResponse();
		System.out.println("test");
		response.getQuestions().addAll(questionService.findQuestion(request.getThemeId(), request.getNbQuestions()));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getNbQuestionRequest")
	@ResponsePayload
	public GetNbQuestionResponse getNbQuestionFromTheme(@RequestPayload GetNbQuestionRequest request) {
		final GetNbQuestionResponse response = new GetNbQuestionResponse();
		System.out.println("test2");
		System.out.println(request.getThemeId());
		System.out.println(questionService.getNbQuestionFromTheme(request.getThemeId()));
		response.setNbQuestions(questionService.getNbQuestionFromTheme(request.getThemeId()));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getThemesRequest")
	@ResponsePayload
	public GetThemesResponse getThemes(@RequestPayload GetThemesRequest request) {
		final GetThemesResponse response = new GetThemesResponse();
		response.getThemes().addAll(questionService.getThemes());
		return response;
	}

}