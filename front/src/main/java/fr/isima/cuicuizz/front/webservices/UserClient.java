package fr.isima.cuicuizz.front.webservices;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Component
public class UserClient extends WebServiceGatewaySupport implements IUserClient {
	
/*	@Override
	public List<ScoreDto> getUserScores(GetUserScoreRequest id) {
		final GetUserScoreRequest request = new GetUserScoreRequest();
		request.setThemeId(id);
		request.setNbQuestions(nbQuestions);
		return (GetQuestionResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:9000/ws/scores",
				request, new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/getUserScores"));
	}
*/
}
