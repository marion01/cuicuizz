package fr.isima.cuicuizz.front.services;

import fr.isima.cuicuizz.front.BooleanResponse;
import fr.isima.cuicuizz.front.Score;
import fr.isima.cuicuizz.front.ScoreDto;
import fr.isima.cuicuizz.front.ScoreResponse;
import fr.isima.cuicuizz.front.User;
import fr.isima.cuicuizz.front.UserDto;

public interface IUserService {
	public ScoreResponse getUserScores(UserDto userDto);

	public Score addScore(ScoreDto scoreDto);

	public ScoreResponse getUserModeScores(UserDto userDto, String mode);

	public ScoreResponse getUserThemeScores(UserDto userDto, String theme);

	public ScoreResponse getUserScore(UserDto userDto, String theme, String mode);

	public ScoreResponse getAllScores();

	public ScoreResponse getAllModeScores(String mode);

	public ScoreResponse getAllThemeScores(String theme);

	public User addUser(UserDto userDto);

	public BooleanResponse login(UserDto userDto);
}
