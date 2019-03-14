package fr.isima.cuicuizz.users.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.isima.cuicuizz.users.services.interfaces.IUserService;
import io.spring.guides.gs_producing_web_service.AddUser;
import io.spring.guides.gs_producing_web_service.BooleanResponse;
import io.spring.guides.gs_producing_web_service.IsConnected;
import io.spring.guides.gs_producing_web_service.Login;
import io.spring.guides.gs_producing_web_service.UserDto;
import io.spring.guides.gs_producing_web_service.UserRequest;

@Endpoint
public class UserEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	@Autowired
	private IUserService userService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUser")
	@ResponsePayload
	public UserRequest addUser(@RequestPayload AddUser dto) {
		final UserRequest ur = new UserRequest();
		ur.setUser(userService.addUser(dto.getUser()));
		return ur;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "login")
	@ResponsePayload
	public BooleanResponse login(@RequestPayload Login dto) {
		final UserDto uDto = dto.getUser();
		final BooleanResponse br = new BooleanResponse();
		br.setValue(userService.login(uDto.getPseudo(), uDto.getPassword()));
		return br;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "isConnected")
	@ResponsePayload
	public BooleanResponse isConnected(@RequestPayload IsConnected isConnected) {
		final BooleanResponse br = new BooleanResponse();
		br.setValue(userService.isConnected(isConnected.getUser().getId()));
		return br;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "disconnect")
	public void disconnect(@RequestPayload int id) {
		userService.disconnect(id);
	}
}
