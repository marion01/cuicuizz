package fr.isima.cuicuizz.front.webservices;

import fr.isima.cuicuizz.front.BooleanResponse;
import fr.isima.cuicuizz.front.Score;
import fr.isima.cuicuizz.front.ScoreDto;
import fr.isima.cuicuizz.front.ScoreResponse;
import fr.isima.cuicuizz.front.User;
import fr.isima.cuicuizz.front.UserDto;

public interface IUserClient {
	/**
	 * Get all the scores of a user
	 * @param userDto
	 * @return scores
	 */
	public ScoreResponse getUserScores(UserDto userDto);

	/**
	 * Add a score in database
	 * @param scoreDto
	 * @return the score added
	 */
	public Score addScore(ScoreDto scoreDto);

	/**
	 * Get all the scores of a user in a specific mode
	 * @param userDto
	 * @param mode
	 * @return scores
	 */
	public ScoreResponse getUserModeScores(UserDto userDto, String mode);

	/**
	 * Get all the scores of a user in a specific theme
	 * @param userDto
	 * @param theme
	 * @return scores
	 */
	public ScoreResponse getUserThemeScores(UserDto userDto, String theme);

	/**
	 * Get all the scores of a user in a specific mode and theme
	 * @param userDto
	 * @param theme
	 * @param mode
	 * @return scores
	 */
	public ScoreResponse getUserScore(UserDto userDto, String theme, String mode);

	/**
	 * Get all the scores 
	 * @return scores
	 */
	public ScoreResponse getAllScores();

	/**
	 * Get all the scores in a specific mode
	 * @param mode
	 * @return scores
	 */
	public ScoreResponse getAllModeScores(String mode);

	/**
	 * Get all the scores in a specific theme
	 * @param theme
	 * @return scores
	 */
	public ScoreResponse getAllThemeScores(String theme);

	/**
	 * Add a user in database
	 * @param userDto
	 * @return the user added
	 */
	public User addUser(UserDto userDto);

	/**
	 * log in a user
	 * @param userDto
	 * @return if the connection is succesful
	 */
	public BooleanResponse login(UserDto userDto);

}
