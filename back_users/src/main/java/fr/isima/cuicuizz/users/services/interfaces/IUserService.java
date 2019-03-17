package fr.isima.cuicuizz.users.services.interfaces;

import io.spring.guides.gs_producing_web_service.UserDto;

public interface IUserService {
	
	boolean login(String pseudo, String password);
	
	UserDto addUser(UserDto user) throws Exception;
	
	boolean isConnected(String pseudo);
	
	void disconnect(int id);

}
