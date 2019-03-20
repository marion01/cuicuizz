package fr.isima.cuicuizz.users.services.interfaces;

import fr.isima.cuicuizz.users.model.User;
import io.spring.guides.gs_producing_web_service.UserDto;

/**
 * The service interface for users
 *
 */
public interface IUserService {
	
	/**
	 * Try to login a user with the specified pseudo and password
	 */
	User login(String pseudo, String password);

	/**
	 * Add a user to data base
	 */
	UserDto addUser(UserDto user) throws Exception;
	
	/**
	 * Get if a user is connected or not
	 */
	boolean isConnected(String pseudo);
	
	/**
	 * Disconnect a user
	 */
	void disconnect(int id);

}
