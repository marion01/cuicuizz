package fr.isima.cuicuizz.users.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.isima.cuicuizz.users.services.interfaces.IUserService;
import io.spring.guides.gs_producing_web_service.UserDto;

@Endpoint
public class UserEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	@Autowired
	private IUserService userService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUser")
	@ResponsePayload
	public UserDto addUser(@RequestPayload UserDto dto) {
		return userService.addUser(dto);
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "login")
	@ResponsePayload
	public boolean login(@RequestPayload UserDto dto) {
		return userService.login(dto.getPseudo(), dto.getPassword());
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "isConnected")
	@ResponsePayload
	public boolean isConnected(@RequestPayload int id) {
		return userService.isConnected(id);
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "disconnect")
	@ResponsePayload
	public void disconnect(@RequestPayload int id) {
		userService.disconnect(id);
	}
}
