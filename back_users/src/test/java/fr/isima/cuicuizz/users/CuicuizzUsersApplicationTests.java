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
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/beans.xml" })
@SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager")
@EntityScan(basePackages = "fr.isima.cuicuizz.users.dbaccess.mybatis.dao")
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
	public void scoreConvertNullDtoToEntityTest() {
		assertNull(ScoreConverter.convertDtoToEntity(null));
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
	
	@Test
	public void scoreConvertNullEntityToDtoTest() {
		assertNull(ScoreConverter.convertEntityToDto(null));
	}

	/*
	 * UserConverter tests
	 */
	
	@Test
	public void userConvertDtoToEntityTest() {
		UserDto ud = new UserDto();
		User user;
		
		ud.setId(0);
		ud.setPassword("password");
		ud.setPseudo("pseudo");
		
		user = UserConverter.convertDtoToEntity(ud);

		assertEquals(ud.getId(), user.getId());
		assertEquals(ud.getPseudo(), user.getPseudo());
		assertEquals(getHash(ud.getPassword()), user.getPassword());
	}
	
	@Test
	public void userConvertNullDtoToEntityTest() {
		assertNull(UserConverter.convertDtoToEntity(null));
	}
	
	@Test
	public void userConvertNoPasswordDtoToEntityTest() {
		UserDto ud = new UserDto();
		User user;
		
		ud.setId(0);
		ud.setPassword(null);
		ud.setPseudo("pseudo");
		
		user = UserConverter.convertDtoToEntity(ud);

		assertEquals(ud.getPassword(), user.getPassword());
	}
	
	@Test
	public void userConvertEmptyPasswordDtoToEntityTest() {
		UserDto ud = new UserDto();
		User user;
		
		ud.setId(0);
		ud.setPassword("");
		ud.setPseudo("pseudo");
		
		user = UserConverter.convertDtoToEntity(ud);
		
		assertNull(user.getPassword());
	}
	
	@Test
	public void userConvertEntityToDtoTest() {
		User user = new User();
		UserDto ud;
		
		user.setId(0);
		user.setPseudo("pseudo");
		user.setPassword("password");
		
		ud = UserConverter.convertEntityToDto(user);

		assertEquals(user.getId(), ud.getId());
		assertEquals(user.getPseudo(), ud.getPseudo());
		assertEquals(user.getPassword(), ud.getPassword());
	}
	
	@Test
	public void userConvertNullEntityToDtoTest() {
		assertNull(UserConverter.convertEntityToDto(null));
	}

	/*
	 * ScoreMapper tests
	 */

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void updateNonExistentScoreTest() {
		Score score = new Score();
		
		score.setUserId(1);
		score.setMode("mode");
		score.setTheme("theme");
		score.setNbQuestions(2);
		score.setNbSuccess(3);
		score.setValue("value");
		
		scoreMapper.updateScore(score);
		
		assertNull(scoreMapper.getUserScore(1, "mode", "theme"));
	}

	/*
	 * UserMapper tests
	 */

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void loginTest() {
		User user = userMapper.login("pseudo1", getHash("password1"));
		
		assertNotNull(user);
		assertEquals("pseudo1", user.getPseudo());
		assertEquals(getHash("password1"), user.getPassword());
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void loginIncorrectTest() {
		assertNull(userMapper.login("wrong pseudo", "password"));
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void updateLastActionDateTest() {
		userMapper.updateLastActionDate("2019-03-20 15:00:00", userMapper.getUser("pseudo1").getId());
		
		assertEquals("2019-03-20 15:00:00", userMapper.getUser("pseudo1").getLastActionDate());
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void isPseudoExistingTest() {
		assertEquals(1, userMapper.isPseudoExisting("pseudo1"));
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void isNonExistingPseudoExistingTest() {
		assertEquals(0, userMapper.isPseudoExisting("pseudo"));
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void addUserTest() {
		User user = new User(), userDb;
		
		user.setPseudo("pseudo3");
		user.setPassword("password3");
		user.setLastActionDate("2019-03-20 15:00:00");
		
		assertEquals(1, userMapper.addUser(user));
				
		userDb = userMapper.getUser("pseudo3");
		
		assertEquals(user.getPseudo(), userDb.getPseudo());
		assertEquals(user.getPassword(), userDb.getPassword());
		assertEquals("2019-03-20 15:00:00", userDb.getLastActionDate());
	}

	@Test(expected=DuplicateKeyException.class)
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void addExistingUserTest() throws DuplicateKeyException {
		User user = new User();
		
		user.setPseudo("pseudo4");
		user.setPassword("password4");
		user.setLastActionDate("2019-03-20 15:00:00");
		userMapper.addUser(user);
		
		userMapper.addUser(user);
	}

	/*
	 * IScoreService tests
	 */
	
	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void getUserScoresTest() {
		List<ScoreDto> scores = scoreService.getUserScores(1);
		
		assertEquals(3, scores.size());
		for(ScoreDto score : scores) assertEquals(1, score.getUserId());
	}
	
	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void getNonExistingUserScoresTest() {
		assertEquals(0, scoreService.getUserScores(0).size());
	}
	
	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void getUserScoreTest() {
		ScoreDto score = scoreService.getUserScore(1, "mode1", "theme1");

		assertEquals(1, score.getUserId());
		assertEquals("mode1", score.getMode());
		assertEquals("theme1", score.getTheme());
	}
	
	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void getNonExistingUserScoreTest() {
		assertNull(scoreService.getUserScore(0, "mode", "theme"));
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void getUserModeScoresTest() {
		List<ScoreDto> scores = scoreService.getUserModeScores(1, "mode1");
		
		assertEquals(2, scores.size());
		for(ScoreDto score : scores) assertEquals(1, score.getUserId());
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void getNonExistingUserModeScoresTest() {
		assertEquals(0, scoreService.getUserModeScores(0, "mode").size());
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void getUserThemeScoresTest() {
		List<ScoreDto> scores = scoreService.getUserThemeScores(1, "theme1");
		
		assertEquals(2, scores.size());
		for(ScoreDto score : scores) assertEquals(1, score.getUserId());
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void getNonExistingUserThemeScoresTest() {
		assertEquals(0, scoreService.getUserThemeScores(0, "theme1").size());
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void getAllScoresTest() {
		assertEquals(5, scoreService.getAllScores().size());
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void getAllModeScoresTest() {
		assertEquals(3, scoreService.getAllModeScores("mode1").size());
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void getAllThemeScoresTest() {
		assertEquals(3, scoreService.getAllThemeScores("theme1").size());
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void addScoreTest() {
		ScoreDto sd = new ScoreDto(), scoreDb;
		
		sd.setUserId(1);
		sd.setMode("mode");
		sd.setTheme("theme");
		sd.setNbQuestions(2);
		sd.setNbSuccess(3);
		sd.setValue("value");
		
		scoreDb = scoreService.addScore(sd);
		
		assertEquals(sd.getUserId(), scoreDb.getUserId());
		assertEquals(sd.getMode(), scoreDb.getMode());
		assertEquals(sd.getTheme(), scoreDb.getTheme());
		assertEquals(sd.getNbQuestions(), scoreDb.getNbQuestions());
		assertEquals(sd.getNbSuccess(), scoreDb.getNbSuccess());
		assertEquals(sd.getValue(), scoreDb.getValue());
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void addExistingScoreTest() {
		Score score = new Score();
		ScoreDto sd, scoreDb;

		score.setUserId(1);
		score.setMode("mode");
		score.setTheme("theme");
		score.setNbQuestions(2);
		score.setNbSuccess(3);
		score.setValue("value");
		
		scoreMapper.addScore(score);
		
		sd = ScoreConverter.convertEntityToDto(score);
		sd.setId(scoreMapper.getUserScore(1, "mode", "theme").getId());
		sd.setNbQuestions(4);
		sd.setNbSuccess(5);
		sd.setValue("new value");
		
		scoreDb = scoreService.addScore(sd);
		
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
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void serviceLoginTest() {
		assertTrue(userService.login("pseudo1", "password1"));
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void serviceWrongLoginTest() {
		assertFalse(userService.login("pseudo1", "wrong password"));
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void serviceNoLoginTest() {		
		
		assertFalse(userService.login(null, "password"));
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void serviceAddUserTest() {
		UserDto user = new UserDto(), userDb = new UserDto();
		
		user.setPseudo("pseudo5");
		user.setPassword("password5");
		try { userDb = userService.addUser(user); }
		catch (Exception e) { fail(e.getMessage()); }
		
		assertEquals(user.getPseudo(), userDb.getPseudo());
		assertEquals(getHash(user.getPassword()), userDb.getPassword());
	}

	@Test(expected=Exception.class)
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void serviceAddExistingUserTest() throws Exception {
		User user = new User();
		
		user.setPseudo("pseudo6");
		user.setPassword("password6");
		user.setLastActionDate("2019-03-20 15:00:00");
		userMapper.addUser(user);
		
		userService.addUser(UserConverter.convertEntityToDto(user));
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void isConnectedTest() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		userMapper.updateLastActionDate(sdf.format(new Date()), userMapper.getUser("pseudo1").getId());
		
		assertTrue(userService.isConnected("pseudo1"));
		
		userService.disconnect(userMapper.getUser("pseudo1").getId());
		
		assertFalse(userService.isConnected("pseudo1"));
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void isNonExistingUserConnectedTest() {
		assertFalse(userService.isConnected("unknown pseudo"));
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void disconnectTest() {
		userService.login("pseudo1", "password");
		userService.disconnect(userMapper.getUser("pseudo1").getId());
		
		assertFalse(userService.isConnected("pseudo1"));
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void disconnectDisconnectedUserTest() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		userMapper.updateLastActionDate(sdf.format(new Date(0)), userMapper.getUser("pseudo1").getId());
		
		userService.disconnect(userMapper.getUser("pseudo1").getId());
		
		assertFalse(userService.isConnected("pseudo1"));
	}

	@Test
	@Sql(scripts = { "classpath:clean.sql", "classpath:insert.sql" },
	 	 config = @SqlConfig(dataSource = "testDataSource", transactionManager = "testTransactionManager"))
	public void disconnectNonExistingUserTest() {
		userService.disconnect(0);
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

