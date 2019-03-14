package fr.isima.cuicuizz.users.services.interfaces;

import io.spring.guides.gs_producing_web_service.UserDto;

public interface IUserService {
	
	boolean login(String pseudo, String password);
	
	UserDto addUser(UserDto user);
	
	boolean isConnected(int id);
	
	void disconnect(int id);

}
