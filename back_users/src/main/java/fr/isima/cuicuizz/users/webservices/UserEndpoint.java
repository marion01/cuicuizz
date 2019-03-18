package fr.isima.cuicuizz.users.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.isima.cuicuizz.users.services.interfaces.IUserService;
import io.spring.guides.gs_producing_web_service.AddUser;
import io.spring.guides.gs_producing_web_service.BooleanResponse;
import io.spring.guides.gs_producing_web_service.Disconnect;
import io.spring.guides.gs_producing_web_service.IsConnected;
import io.spring.guides.gs_producing_web_service.Login;
import io.spring.guides.gs_producing_web_service.User;
import io.spring.guides.gs_producing_web_service.UserDto;

/**
 * Endpoint exposed by Spring webservices. WSDLs are accessible on
 * localhost:9000/ws/localPart.wsdl once server is booted
 * 
 *
 */
@Endpoint
public class UserEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	/**
	 * The user service
	 */
	@Autowired
	private IUserService userService;

	/**
	 * Add a user
	 * @param dto the user to add
	 * @return the added user
	 * @throws Exception
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUser")
	@ResponsePayload
	public User addUser(@RequestPayload AddUser dto) throws Exception {
		final User ur = new User();
		ur.setUserDto(userService.addUser(dto.getUser()));
		return ur;
	}

	/**
	 * Login a user
	 * @param dto the user to login
	 * @return true if the user has been login and false otherwise
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "login")
	@ResponsePayload
	public BooleanResponse login(@RequestPayload Login dto) {
		final UserDto uDto = dto.getUser();
		final BooleanResponse br = new BooleanResponse();
		br.setValue(userService.login(uDto.getPseudo(), uDto.getPassword()));
		return br;
	}

	/**
	 * Get connection of a user
	 * @param isConnected the isconnected
	 * @return true if the user is connected and false otherwise
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "isConnected")
	@ResponsePayload
	public BooleanResponse isConnected(@RequestPayload IsConnected isConnected) {
		final BooleanResponse br = new BooleanResponse();
		br.setValue(userService.isConnected(isConnected.getUser().getPseudo()));
		return br;
	}

	/**
	 * Disconnect a user
	 * @param disconnect the disconnect
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "disconnect")
	public void disconnect(@RequestPayload Disconnect disconnect) {
		userService.disconnect(disconnect.getUser().getId());
	}
}
