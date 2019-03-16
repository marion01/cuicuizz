package fr.isima.cuicuizz.users.services.implementations;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.isima.cuicuizz.users.converters.UserConverter;
import fr.isima.cuicuizz.users.dbaccess.mybatis.dao.UserMapper;
import fr.isima.cuicuizz.users.model.User;
import fr.isima.cuicuizz.users.services.interfaces.IUserService;
import io.spring.guides.gs_producing_web_service.UserDto;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean login(String pseudo, String password) {
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
			if( user != null) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date now = new Date();
				userMapper.updateLastActionDate(df.format(now), user.getId());
				return true;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public UserDto addUser(UserDto user) throws Exception {
		if(userMapper.isPseudoExisting(user.getPseudo()) != 0) {
			throw new Exception("pseudo déjà pris");
		}
		userMapper.addUser(UserConverter.convertDtoToEntity(user));
		return UserConverter.convertEntityToDto(userMapper.getUserByPseudo(user.getPseudo()));
	}

	@Override
	public boolean isConnected(String pseudo) {
		User user = userMapper.getUser(pseudo);
		if(user == null) {
			return false;
		}
		Date now = new Date();
		Date lastAction = user.getLastActionDate();
		return now.getTime()-lastAction.getTime() <= 600000;
	}
	
	@Override
	public void disconnect(int id) {
		userMapper.updateLastActionDate("1900-01-01 00:00:00", id);
	}
}
