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

/**
 * Endpoint exposed by Spring webservices. WSDLs are accessible on
 * localhost:9000/ws/localPart.wsdl once server is booted
 * 
 *
 */
@Endpoint
public class QuestionEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	@Autowired
	private IQuestionService questionService;

	/**
	 * Returns list of questions based on theme Id and number of questions needed
	 * 
	 * @param request
	 * @return
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getQuestionRequest")
	@ResponsePayload
	public GetQuestionResponse getQuestions(@RequestPayload GetQuestionRequest request) {
		final GetQuestionResponse response = new GetQuestionResponse();
		response.getQuestions().addAll(questionService.findQuestion(request.getThemeId(), request.getNbQuestions()));
		return response;
	}

	/**
	 * Returns the number of questions existing in database for a given theme
	 * 
	 * @param request
	 * @return
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getNbQuestionRequest")
	@ResponsePayload
	public GetNbQuestionResponse getNbQuestionFromTheme(@RequestPayload GetNbQuestionRequest request) {
		final GetNbQuestionResponse response = new GetNbQuestionResponse();
		response.setNbQuestions(questionService.getNbQuestionFromTheme(request.getThemeId()));
		return response;
	}

	/**
	 * returns all the question themes available in database
	 * 
	 * @param request
	 * @return
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getThemesRequest")
	@ResponsePayload
	public GetThemesResponse getThemes(@RequestPayload GetThemesRequest request) {
		final GetThemesResponse response = new GetThemesResponse();
		response.getThemes().addAll(questionService.getThemes());
		return response;
	}

}