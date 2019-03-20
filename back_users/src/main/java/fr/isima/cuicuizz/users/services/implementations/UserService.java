package fr.isima.cuicuizz.users.services.implementations;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isima.cuicuizz.users.converters.UserConverter;
import fr.isima.cuicuizz.users.dbaccess.mybatis.dao.UserMapper;
import fr.isima.cuicuizz.users.model.User;
import fr.isima.cuicuizz.users.services.interfaces.IUserService;
import io.spring.guides.gs_producing_web_service.UserDto;

/**
 * The service implementation class for users
 *
 */
@Service
public class UserService implements IUserService {
	
	/**
	 * The user mapper
	 */
	@Autowired
	private UserMapper userMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User login(String pseudo, String password) {
		try {
			// Hash password
			MessageDigest md;
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			final byte byteData[] = md.digest();
			final StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			User user = userMapper.login(pseudo,sb.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if( user != null) {
				userMapper.updateLastActionDate(sdf.format(new Date()), user.getId());
				return user;
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDto addUser(UserDto user) throws Exception {
		if(userMapper.isPseudoExisting(user.getPseudo()) != 0) {
			throw new Exception("pseudo déjà pris");
		}
		userMapper.addUser(UserConverter.convertDtoToEntity(user));
		return UserConverter.convertEntityToDto(userMapper.getUserByPseudo(user.getPseudo()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isConnected(String pseudo) {
		User user = userMapper.getUser(pseudo);
		if(user == null) {
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		Date lastAction;
		try {
			lastAction = sdf.parse(user.getLastActionDate());
			return now.getTime()-lastAction.getTime() <= 600000;
		} catch (ParseException | NullPointerException e) { return false; }
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void disconnect(int id) {
		userMapper.updateLastActionDate("1900-01-01 00:00:00", id);
	}
}
