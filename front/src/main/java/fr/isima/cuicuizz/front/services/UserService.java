package fr.isima.cuicuizz.front.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isima.cuicuizz.front.BooleanResponse;
import fr.isima.cuicuizz.front.Score;
import fr.isima.cuicuizz.front.ScoreDto;
import fr.isima.cuicuizz.front.ScoreResponse;
import fr.isima.cuicuizz.front.User;
import fr.isima.cuicuizz.front.UserDto;
import fr.isima.cuicuizz.front.webservices.IUserClient;

@Service
public class UserService implements IUserService{
	
	@Autowired
	IUserClient userClient;
	
	@Override
	public ScoreResponse getUserScores(UserDto userDto) {
		return userClient.getUserScores(userDto);
	}

	@Override
	public Score addScore(ScoreDto scoreDto) {
		return userClient.addScore(scoreDto);
	}

	@Override
	public ScoreResponse getUserModeScores(UserDto userDto, String mode) {
		return userClient.getUserModeScores(userDto, mode);
	}

	@Override
	public ScoreResponse getUserThemeScores(UserDto userDto, String theme) {
		return userClient.getUserThemeScores(userDto, theme);
	}

	@Override
	public ScoreResponse getUserScore(UserDto userDto, String theme, String mode) {
		return userClient.getUserScore(userDto, theme, mode);
	}

	@Override
	public ScoreResponse getAllScores() {
		return userClient.getAllScores();
	}

	@Override
	public ScoreResponse getAllModeScores(String mode) {
		return userClient.getAllModeScores(mode);
	}

	@Override
	public ScoreResponse getAllThemeScores(String theme) {
		return userClient.getAllThemeScores(theme);
	}

	@Override
	public User addUser(UserDto userDto) {
		return userClient.addUser(userDto);
	}

	@Override
	public BooleanResponse login(UserDto userDto) {
		return userClient.login(userDto);
	}

}
