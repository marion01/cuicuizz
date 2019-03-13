package fr.isima.cuicuizz.users.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.isima.cuicuizz.users.services.interfaces.IScoreService;
import fr.isima.cuicuizz.users.services.interfaces.IUserService;
import io.spring.guides.gs_producing_web_service.ScoreDto;
import io.spring.guides.gs_producing_web_service.UserDto;

@Endpoint
public class ScoreEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	@Autowired
	private IScoreService scoreService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserScores")
	@ResponsePayload
	public List<ScoreDto> getUserScores(@RequestPayload int userId) {
		return scoreService.getUserScores(userId);
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addScore")
	@ResponsePayload
	public ScoreDto addScore(@RequestPayload ScoreDto dto) {
		return scoreService.addScore(dto);
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserModeScores")
	@ResponsePayload
	public List<ScoreDto> getUserModeScores(@RequestPayload int userId,@RequestPayload String mode) {
		return scoreService.getUserModeScores(userId, mode);
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserThemeScores")
	@ResponsePayload
	public List<ScoreDto> getUserThemeScores(@RequestPayload int userId,@RequestPayload String theme) {
		return scoreService.getUserThemeScores(userId, theme);
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserScore")
	@ResponsePayload
	public ScoreDto getUserScore(@RequestPayload int userId,@RequestPayload String mode, @RequestPayload String theme) {
		return scoreService.getUserScore(userId, mode, theme);
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllScores")
	@ResponsePayload
	public List<ScoreDto> getAllScores() {
		return scoreService.getAllScores();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllModeScores")
	@ResponsePayload
	public List<ScoreDto> getAllModeScores(@RequestPayload String mode) {
		return scoreService.getAllModeScores(mode);
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllThemeScores")
	@ResponsePayload
	public List<ScoreDto> getAllThemeScores(@RequestPayload String theme) {
		return scoreService.getAllThemeScores(theme);
	}
}