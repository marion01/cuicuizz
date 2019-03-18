package fr.isima.cuicuizz.users.services.interfaces;

import java.util.List;

import io.spring.guides.gs_producing_web_service.ScoreDto;

/**
 * The service interface for scores
 *
 */
public interface IScoreService {
	
	/**
	 * Get user scores by its id
	 */
	List<ScoreDto> getUserScores(int userId);
	
	/**
	 * Add a new score
	 */
	ScoreDto addScore(ScoreDto score);
	
	/**
	 * Get all scores of a user for a specified mode
	 */
	List<ScoreDto> getUserModeScores(int userId, String mode);
	
	/**
	 * Get all scores of a user for a specified theme
	 */
	List<ScoreDto> getUserThemeScores(int userId, String theme);
	
	/**
	 * Get a user score for specified mode and theme
	 */
	ScoreDto getUserScore(int userId, String mode, String theme);
	
	/**
	 * Get all scores
	 */
	List<ScoreDto> getAllScores();
	
	/**
	 * Get all scores for the specified mode
	 */
	List<ScoreDto> getAllModeScores( String mode);
	
	/**
	 * Get all scores for the specified theme
	 */
	List<ScoreDto> getAllThemeScores(String theme);
}
