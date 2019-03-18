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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScoreResponse getUserScores(UserDto userDto) {
		return userClient.getUserScores(userDto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Score addScore(ScoreDto scoreDto) {
		return userClient.addScore(scoreDto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScoreResponse getUserModeScores(UserDto userDto, String mode) {
		return userClient.getUserModeScores(userDto, mode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScoreResponse getUserThemeScores(UserDto userDto, String theme) {
		return userClient.getUserThemeScores(userDto, theme);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScoreResponse getUserScore(UserDto userDto, String theme, String mode) {
		return userClient.getUserScore(userDto, theme, mode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScoreResponse getAllScores() {
		return userClient.getAllScores();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScoreResponse getAllModeScores(String mode) {
		return userClient.getAllModeScores(mode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScoreResponse getAllThemeScores(String theme) {
		return userClient.getAllThemeScores(theme);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User addUser(UserDto userDto) {
		return userClient.addUser(userDto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BooleanResponse login(UserDto userDto) {
		return userClient.login(userDto);
	}

}
