package fr.isima.cuicuizz.front.webservices;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Component
public class UserClient extends WebServiceGatewaySupport implements IUserClient{

}
