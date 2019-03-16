package fr.isima.cuicuizz.users.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.isima.cuicuizz.users.services.interfaces.IScoreService;
import io.spring.guides.gs_producing_web_service.AddScore;
import io.spring.guides.gs_producing_web_service.BooleanResponse;
import io.spring.guides.gs_producing_web_service.GetAllModesScores;
import io.spring.guides.gs_producing_web_service.GetAllScores;
import io.spring.guides.gs_producing_web_service.GetAllThemesScores;
import io.spring.guides.gs_producing_web_service.GetUserModeScores;
import io.spring.guides.gs_producing_web_service.GetUserModeThemeScore;
import io.spring.guides.gs_producing_web_service.GetUserScores;
import io.spring.guides.gs_producing_web_service.GetUserThemeScores;
import io.spring.guides.gs_producing_web_service.ScoreDto;
import io.spring.guides.gs_producing_web_service.ScoreRequest;
import io.spring.guides.gs_producing_web_service.ScoreResponse;

@Endpoint
public class ScoreEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	@Autowired
	private IScoreService scoreService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserScores")
	@ResponsePayload
	public ScoreResponse getUserScores(@RequestPayload GetUserScores us) {
		final ScoreResponse sr = new ScoreResponse();
		sr.getScores().addAll(scoreService.getUserScores(us.getUser().getId()));
		return sr;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addScore")
	@ResponsePayload
	public ScoreRequest addScore(@RequestPayload AddScore dto) {
		final ScoreRequest br = new ScoreRequest();
		ScoreDto result = scoreService.addScore(dto.getScore());
		br.setScore(result);
		return br;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserModeScores")
	@ResponsePayload
	public ScoreResponse getUserModeScores(@RequestPayload GetUserModeScores ums) {
		final ScoreResponse sr = new ScoreResponse();
		sr.getScores().addAll(scoreService.getUserModeScores(ums.getUser().getId(), ums.getMode()));
		return sr;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserThemeScores")
	@ResponsePayload
	public ScoreResponse getUserThemeScores(@RequestPayload GetUserThemeScores uts) {
		final ScoreResponse sr = new ScoreResponse();
		sr.getScores().addAll(scoreService.getUserThemeScores(uts.getUser().getId(), uts.getTheme()));
		return sr;
	}


	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserModeThemeScore")
	@ResponsePayload
	public ScoreResponse getUserScore(@RequestPayload GetUserModeThemeScore umts) {
		final ScoreResponse sr = new ScoreResponse();
		sr.getScores().add(scoreService.getUserScore(umts.getUser().getId(), umts.getMode(), umts.getTheme()));
		return sr;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllScores")
	@ResponsePayload
	public ScoreResponse getAllScores(@RequestPayload GetAllScores as) {
		final ScoreResponse sr = new ScoreResponse();
		sr.getScores().addAll(scoreService.getAllScores());
		return sr;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllModesScores")
	@ResponsePayload
	public ScoreResponse getAllModeScores(@RequestPayload GetAllModesScores ams) {
		final ScoreResponse sr = new ScoreResponse();
		sr.getScores().addAll(scoreService.getAllModeScores(ams.getMode()));
		return sr;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllThemesScores")
	@ResponsePayload
	public ScoreResponse getAllThemeScores(@RequestPayload GetAllThemesScores ats) {
		final ScoreResponse sr = new ScoreResponse();
		sr.getScores().addAll(scoreService.getAllThemeScores(ats.getTheme()));
		return sr;
	}
}