package fr.isima.cuicuizz.users.services.implementations;

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
		if(pseudo.isEmpty() || password.isEmpty()) {
			return false;	
		}
		User user = userMapper.login(pseudo,password);
		if( user != null) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = new Date();
			userMapper.updateLastActionDate(df.format(now), user.getId());
			return true;
		}
		return false;
	}

	@Override
	public UserDto addUser(UserDto user) {
		if(userMapper.isPseudoExisting(user.getPseudo()) != 0) {
			return null;
		}
		return UserConverter.convertEntityToDto(userMapper.addUser(UserConverter.convertDtoToEntity(user)));
	}

	@Override
	public boolean isConnected(int id) {
		User user = userMapper.getUser(id);
		if(user == null) {
			return false;
		}
		Date now = new Date();
		Date lastAction = user.getLastActionDate();
		return lastAction.getTime() >= now.getTime()-10*60 && lastAction.getTime()<=now.getTime();
	}
	
	@Override
	public void disconnect(int id) {
		userMapper.updateLastActionDate(null, id);
	}
}
