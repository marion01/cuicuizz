package fr.isima.cuicuizz.front.webservices;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import fr.isima.cuicuizz.front.AddScore;
import fr.isima.cuicuizz.front.AddUser;
import fr.isima.cuicuizz.front.BooleanResponse;
import fr.isima.cuicuizz.front.GetAllModesScores;
import fr.isima.cuicuizz.front.GetAllScores;
import fr.isima.cuicuizz.front.GetAllThemesScores;
import fr.isima.cuicuizz.front.GetUserModeScores;
import fr.isima.cuicuizz.front.GetUserModeThemeScore;
import fr.isima.cuicuizz.front.GetUserScores;
import fr.isima.cuicuizz.front.GetUserThemeScores;
import fr.isima.cuicuizz.front.Login;
import fr.isima.cuicuizz.front.Score;
import fr.isima.cuicuizz.front.ScoreDto;
import fr.isima.cuicuizz.front.ScoreResponse;
import fr.isima.cuicuizz.front.User;
import fr.isima.cuicuizz.front.UserDto;

@Component
public class UserClient extends WebServiceGatewaySupport implements IUserClient {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScoreResponse getUserScores(UserDto userDto) {
		final GetUserScores request = new GetUserScores();
		request.setUser(userDto);
		return (ScoreResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:9000/ws/scores",
				request, new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/getUserScores"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Score addScore(ScoreDto scoreDto) {
		final AddScore request = new AddScore();
		request.setScore(scoreDto);
		return (Score) getWebServiceTemplate().marshalSendAndReceive("http://localhost:9000/ws/scores",
				request, new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/addScore"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScoreResponse getUserModeScores(UserDto userDto, String mode) {
		final GetUserModeScores request = new GetUserModeScores();
		request.setUser(userDto);
		request.setMode(mode);
		return (ScoreResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:9000/ws/scores",
				request, new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/getUserModeScores"));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScoreResponse getUserThemeScores(UserDto userDto, String theme) {
		final GetUserThemeScores request = new GetUserThemeScores();
		request.setUser(userDto);
		request.setTheme(theme);
		return (ScoreResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:9000/ws/scores",
				request, new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/getUserThemeScores"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScoreResponse getUserScore(UserDto userDto, String theme, String mode) {
		final GetUserModeThemeScore request = new GetUserModeThemeScore();
		request.setUser(userDto);
		request.setMode(mode);
		request.setTheme(theme);
		return (ScoreResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:9000/ws/scores",
				request,
				new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/getUserModeThemeScore"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScoreResponse getAllScores() {
		final GetAllScores request = new GetAllScores();
		return (ScoreResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:9000/ws/scores",
				request, new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/getAllScores"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScoreResponse getAllModeScores(String mode) {
		final GetAllModesScores request = new GetAllModesScores();
		request.setMode(mode);
		return (ScoreResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:9000/ws/scores",
				request, new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/getAllModesScores"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScoreResponse getAllThemeScores(String theme) {
		final GetAllThemesScores request = new GetAllThemesScores();
		request.setTheme(theme);
		return (ScoreResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:9000/ws/scores",
				request, new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/getAllThemesScores"));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public User  addUser(UserDto userDto) throws Exception{
		final AddUser  request = new AddUser();
		request.setUser(userDto);
		return (User) getWebServiceTemplate().marshalSendAndReceive("http://localhost:9000/ws/users",
				request, new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/addUser"));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public User login(UserDto userDto) {
		final Login request = new Login();
		request.setUser(userDto);
		return (User) getWebServiceTemplate().marshalSendAndReceive("http://localhost:9000/ws/users",
				request, new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/login"));
	}
}
