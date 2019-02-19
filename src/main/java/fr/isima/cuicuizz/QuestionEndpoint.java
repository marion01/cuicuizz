package fr.isima.cuicuizz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetQuestionRequest;
import io.spring.guides.gs_producing_web_service.GetQuestionResponse;

@Endpoint
public class QuestionEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private final QuestionRepository questionRepository;

	@Autowired
	public QuestionEndpoint(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getQuestionRequest")
	@ResponsePayload
	public GetQuestionResponse getQuestion(@RequestPayload GetQuestionRequest request) {
		final GetQuestionResponse response = new GetQuestionResponse();
		response.setQuestion(questionRepository.findQuestion(request.getThemeId()));

		return response;
	}
}