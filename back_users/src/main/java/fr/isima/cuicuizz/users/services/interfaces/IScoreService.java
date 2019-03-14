package fr.isima.cuicuizz.users.services.interfaces;

import java.util.List;

import io.spring.guides.gs_producing_web_service.ScoreDto;

public interface IScoreService {
	List<ScoreDto> getUserScores(int userId);
	ScoreDto addScore(ScoreDto score);
	List<ScoreDto> getUserModeScores(int userId, String mode);
	List<ScoreDto> getUserThemeScores(int userId, String theme);
	ScoreDto getUserScore(int userId, String mode, String theme);
	
	List<ScoreDto> getAllScores();
	List<ScoreDto> getAllModeScores( String mode);
	List<ScoreDto> getAllThemeScores(String theme);
}
