package fr.isima.cuicuizz.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.test.context.junit4.SpringRunner;

import fr.isima.cuicuizz.users.converters.ScoreConverter;
import fr.isima.cuicuizz.users.converters.UserConverter;
import fr.isima.cuicuizz.users.dbaccess.mybatis.dao.ScoreMapper;
import fr.isima.cuicuizz.users.dbaccess.mybatis.dao.UserMapper;
import fr.isima.cuicuizz.users.model.Score;
import fr.isima.cuicuizz.users.model.User;
import fr.isima.cuicuizz.users.services.interfaces.IScoreService;
import fr.isima.cuicuizz.users.services.interfaces.IUserService;
import io.spring.guides.gs_producing_web_service.ScoreDto;
import io.spring.guides.gs_producing_web_service.UserDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuicuizzUsersApplicationTests {

	@Autowired
	private ScoreMapper scoreMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private IScoreService scoreService;

	@Autowired
	private IUserService userService;

	/*
	 * ScoreConverter tests
	 */
	
	@Test
	public void scoreConvertDtoToEntityTest() {
		ScoreDto sd = new ScoreDto();
		Score score;
		
		sd.setId(0);
		sd.setUserId(1);
		sd.setMode("mode");
		sd.setTheme("theme");
		sd.setNbQuestions(2);
		sd.setNbSuccess(3);
		sd.setValue("value");
		
		score = ScoreConverter.convertDtoToEntity(sd);

		assertEquals(sd.getId(), score.getId());
		assertEquals(sd.getUserId(), score.getUserId());
		assertEquals(sd.getMode(), score.getMode());
		assertEquals(sd.getTheme(), score.getTheme());
		assertEquals(sd.getNbQuestions(), score.getNbQuestions());
		assertEquals(sd.getNbSuccess(), score.getNbSuccess());
		assertEquals(sd.getValue(), score.getValue());
	}
	
	@Test
	public void scoreConvertEntityToDtoTest() {
		Score score = new Score();
		ScoreDto sd;
		
		score.setId(0);
		score.setUserId(1);
		score.setMode("mode");
		score.setTheme("theme");
		score.setNbQuestions(2);
		score.setNbSuccess(3);
		score.setValue("value");
		
		sd = ScoreConverter.convertEntityToDto(score);

		assertEquals(score.getId(), sd.getId());
		assertEquals(score.getUserId(), sd.getUserId());
		assertEquals(score.getMode(), sd.getMode());
		assertEquals(score.getTheme(), sd.getTheme());
		assertEquals(score.getNbQuestions(), sd.getNbQuestions());
		assertEquals(score.getNbSuccess(), sd.getNbSuccess());
		assertEquals(score.getValue(), sd.getValue());
	}

	/*
	 * UserConverter tests
	 */
	
	@Test
	public void userConvertDtoToEntityTest() {
		UserDto ud = new UserDto();
		User user;
		
		ud.setId(2);
		ud.setPassword("password");
		ud.setPseudo("pseudo");
		
		user = UserConverter.convertDtoToEntity(ud);

		assertEquals(ud.getId(), user.getId());
		assertEquals(ud.getPseudo(), user.getPseudo());
		
		assertEquals(getHash(ud.getPassword()), user.getPassword());
	}
	
	@Test
	public void userConvertEntityToDtoTest() {
		User user = new User();
		UserDto ud;
		
		user.setId(1);
		user.setPseudo("pseudo");
		user.setPassword("password");
		
		ud = UserConverter.convertEntityToDto(user);

		assertEquals(user.getId(), ud.getId());
		assertEquals(user.getPseudo(), ud.getPseudo());
		assertEquals(user.getPassword(), ud.getPassword());
	}

	/*
	 * ScoreMapper tests
	 */
	
	@Test
	public void getUserScoresTest() {
		List<Score> scores = scoreMapper.getUserScores(1);
		
		assertEquals(3, scores.size());
		for(Score score : scores) assertEquals(1, score.getUserId());
	}
	
	@Test
	public void getNonExistingUserScoresTest() {
		assertEquals(0, scoreMapper.getUserScores(0).size());
	}
	
	@Test
	public void getUserScoreTest() {
		Score score = scoreMapper.getUserScore(1, "Normal", "Culture générale");

		assertEquals(1, score.getUserId());
		assertEquals("Normal", score.getMode());
		assertEquals("Culture générale", score.getTheme());
	}
	
	@Test
	public void getNonExistingUserScoreTest() {
		assertNull(scoreMapper.getUserScore(0, "mode", "theme"));
	}
	
	@Test
	public void getUserModeScoresTest() {
		List<Score> scores = scoreMapper.getUserModeScores(1, "Normal");
		
		assertEquals(2, scores.size());
		for(Score score : scores) assertEquals(1, score.getUserId());
	}
	
	@Test
	public void getNonExistingUserModeScoresTest() {
		assertEquals(0, scoreMapper.getUserModeScores(0, "mode").size());
	}
	
	@Test
	public void getUserThemeScoresTest() {
		List<Score> scores = scoreMapper.getUserThemeScores(1, "Culture générale");
		
		assertEquals(2, scores.size());
		for(Score score : scores) assertEquals(1, score.getUserId());
	}
	
	@Test
	public void getNonExistingUserThemeScoresTest() {
		assertEquals(0, scoreMapper.getUserThemeScores(0, "Culture générale").size());
	}
	
	@Test
	public void addScoreTest() {
		Score score = new Score(), scoreDb;
		
		score.setId(5);
		score.setUserId(1);
		score.setMode("mode");
		score.setTheme("theme");
		score.setNbQuestions(2);
		score.setNbQuestions(3);
		score.setValue("value");
		
		scoreMapper.addScore(score);
		
		scoreDb = scoreMapper.getUserScore(1, "mode", "theme");
		
		scoreMapper.deleteScore(score);

		assertEquals(score.getUserId(), scoreDb.getUserId());
		assertEquals(score.getMode(), scoreDb.getMode());
		assertEquals(score.getTheme(), scoreDb.getTheme());
		assertEquals(score.getNbQuestions(), scoreDb.getNbQuestions());
		assertEquals(score.getNbSuccess(), scoreDb.getNbSuccess());
		assertEquals(score.getValue(), scoreDb.getValue());
		
	}
	
	@Test
	public void updateScoreTest() {
		Score score = new Score(), scoreDb;
		
		score.setId(5);
		score.setUserId(1);
		score.setMode("mode");
		score.setTheme("theme");
		score.setNbQuestions(2);
		score.setNbSuccess(3);
		score.setValue("value");
		
		scoreMapper.addScore(score);

		score.setNbQuestions(4);
		score.setNbSuccess(5);
		score.setValue("new value");
		
		scoreMapper.updateScore(score);
		
		scoreDb = scoreMapper.getUserScore(1, "mode", "theme");
		
		scoreMapper.deleteScore(scoreDb);

		assertEquals(score.getNbQuestions(), scoreDb.getNbQuestions());
		assertEquals(score.getNbSuccess(), scoreDb.getNbSuccess());
		assertEquals(score.getValue(), scoreDb.getValue());
		
	}
	
	@Test
	public void updateNonExistentScoreTest() {
		Score score = new Score();
		
		score.setId(5);
		score.setUserId(1);
		score.setMode("mode");
		score.setTheme("theme");
		score.setNbQuestions(2);
		score.setNbSuccess(3);
		score.setValue("value");
		
		scoreMapper.updateScore(score);
		
		assertNull(scoreMapper.getUserScore(1, "mode", "theme"));
	}
	
	@Test
	public void getAllScoresTest() {
		assertEquals(4, scoreMapper.getAllScores().size());
	}
	
	@Test
	public void getAllModeScoresTest() {
		assertEquals(3, scoreMapper.getAllModeScores("Normal").size());
	}
	
	@Test
	public void getAllThemeScoresTest() {
		assertEquals(3, scoreMapper.getAllThemeScores("Culture générale").size());
	}

	/*
	 * UserMapper tests
	 */
	
	@Test
	public void loginTest() {
		User user = userMapper.login("pseudo1", "password1");
		
		assertNotNull(user);
		assertEquals("pseudo1", user.getPseudo());
		assertEquals("password1", user.getPassword());
	}
	
	@Test
	public void loginIncorrectTest() {
		assertNull(userMapper.login("wrong pseudo", "password"));
	}
	
	@Test
	public void updateLastActionDateTest() {
		User user = new User(), userDb;
		
		user.setId(3);
		user.setPseudo("pseudo");
		user.setPassword("password");
		user.setLastActionDate("2018-02-19 14:00:00");
		userMapper.addUser(user);
		
		userMapper.updateLastActionDate("2019-03-20 15:00:00", 3);
		
		userDb = userMapper.getUser("pseudo");
		userMapper.deleteUser(user);
		
		assertEquals("2019-03-20 15:00:00", userDb.getLastActionDate());
	}
	
	@Test
	public void isPseudoExistingTest() {
		assertEquals(1, userMapper.isPseudoExisting("pseudo1"));
	}
	
	@Test
	public void isNonExistingPseudoExistingTest() {
		assertEquals(0, userMapper.isPseudoExisting("pseudo"));
	}
	
	@Test
	public void addUserTest() {
		User user = new User(), userDb;
		
		user.setId(3);
		user.setPseudo("pseudo");
		user.setPassword("password");
		user.setLastActionDate("2019-03-20 15:00:00");
		userMapper.addUser(user);
				
		userDb = userMapper.getUser("pseudo");
		
		userMapper.deleteUser(user);
		
		assertEquals(user.getId(), userDb.getId());
		assertEquals(user.getPseudo(), userDb.getPseudo());
		assertEquals(user.getPassword(), userDb.getPassword());
		assertEquals("2019-03-20 15:00:00", userDb.getLastActionDate());
	}
	
	@Test
	public void addExistingUserTest() {
		User user = new User();
		
		user.setId(3);
		user.setPseudo("pseudo");
		user.setPassword("password");
		user.setLastActionDate("2019-03-20 15:00:00");
		userMapper.addUser(user);
		
		try {
			userMapper.addUser(user);
			fail("Should throw SQLiteException");
		} catch (UncategorizedSQLException ignored) { userMapper.deleteUser(user); }
	}

	/*
	 * IScoreService tests
	 */
	
	@Test
	public void addNonExistingScoreTest() {
		ScoreDto sd = new ScoreDto(), scoreDb;
		
		sd.setUserId(1);
		sd.setMode("mode");
		sd.setTheme("theme");
		sd.setNbQuestions(2);
		sd.setNbSuccess(3);
		sd.setValue("value");
		
		scoreDb = scoreService.addScore(sd);
		scoreMapper.deleteScore(ScoreConverter.convertDtoToEntity(scoreDb));
		
		assertEquals(sd.getUserId(), scoreDb.getUserId());
		assertEquals(sd.getMode(), scoreDb.getMode());
		assertEquals(sd.getTheme(), scoreDb.getTheme());
		assertEquals(sd.getNbQuestions(), scoreDb.getNbQuestions());
		assertEquals(sd.getNbSuccess(), scoreDb.getNbSuccess());
		assertEquals(sd.getValue(), scoreDb.getValue());
		
	}
	
	@Test
	public void addExistingScoreTest() {
		Score score = new Score();
		ScoreDto sd, scoreDb;
		
		score.setId(5);
		score.setUserId(1);
		score.setMode("mode");
		score.setTheme("theme");
		score.setNbQuestions(2);
		score.setNbSuccess(3);
		score.setValue("value");
		
		scoreMapper.addScore(score);
		
		sd = ScoreConverter.convertEntityToDto(score);
		sd.setNbQuestions(4);
		sd.setNbSuccess(5);
		sd.setValue("new value");
		
		scoreDb = scoreService.addScore(sd);
		
		scoreMapper.deleteScore(ScoreConverter.convertDtoToEntity(scoreDb));
		
		assertEquals(sd.getUserId(), scoreDb.getUserId());
		assertEquals(sd.getMode(), scoreDb.getMode());
		assertEquals(sd.getTheme(), scoreDb.getTheme());
		assertEquals(sd.getNbQuestions(), scoreDb.getNbQuestions());
		assertEquals(sd.getNbSuccess(), scoreDb.getNbSuccess());
		assertEquals(sd.getValue(), scoreDb.getValue());
	}
	
	/*
	 * IUserService tests
	 */
	
	@Test
	public void serviceLoginTest() {
		boolean isConnected;
		
		userService.login("pseudo2", "password");
		isConnected = userService.isConnected("pseudo2");
		userService.disconnect(userMapper.getUser("pseudo2").getId());
		
		assertTrue(isConnected);
	}
	
	@Test
	public void serviceWrongLoginTest() {
		boolean isConnected;
		
		userService.login("pseudo2", "wrong password");
		isConnected = userService.isConnected("pseudo2");
		userService.disconnect(userMapper.getUser("pseudo2").getId());
		
		assertFalse(isConnected);
	}
	
	@Test
	public void serviceAddExistingUserTest() {
		User user = new User();
		
		user.setId(3);
		user.setPseudo("pseudo");
		user.setPassword("password");
		user.setLastActionDate("2019-03-20 15:00:00");
		userMapper.addUser(user);
		
		try {
			userService.addUser(UserConverter.convertEntityToDto(user));
			fail("Should throw Exception");
		} catch (Exception ignored) { userMapper.deleteUser(user); }
	}
	
	@Test
	public void isConnectedTest() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean isConnected;
		
		userMapper.updateLastActionDate(sdf.format(new Date()), userMapper.getUser("pseudo2").getId());
		
		isConnected = userService.isConnected("pseudo2");
		userService.disconnect(userMapper.getUser("pseudo2").getId());
		
		assertTrue(isConnected);
		assertFalse(userService.isConnected("pseudo2"));
	}
	
	@Test
	public void isNonExistingUserConnectedTest() {
		assertFalse(userService.isConnected("pseudo"));
	}
	
	@Test
	public void disconnectTest() {
		userService.login("pseudo2", "password");
		userService.disconnect(userMapper.getUser("pseudo2").getId());
		
		assertFalse(userService.isConnected("pseudo2"));
	}
	
	@Test
	public void disconnectDisconnectedUserTest() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		userMapper.updateLastActionDate(sdf.format(new Date(0)), userMapper.getUser("pseudo2").getId());
		
		try { userService.disconnect(userMapper.getUser("pseudo2").getId()); } catch (Exception e) { fail(e.getMessage()); }
		assertFalse(userService.isConnected("pseudo2"));
	}
	
	@Test
	public void disconnectNonExistingUserTest() {
		try { userService.disconnect(0); } catch (Exception e) { fail(e.getMessage()); }
	}
	
	
	/**
	 * Compute hash of given string.
	 * @param str String to hash.
	 * @return Hash of given string.
	 */
	private String getHash(String str) {
		MessageDigest md;
		final byte byteData[];
		final StringBuffer sb;
		
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
			byteData = md.digest();
			sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++)
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		} catch (NoSuchAlgorithmException e) { return str; }
	
		return sb.toString();
	}
}

