package fr.isima.cuicuizz.front.webservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class UserConfiguration {
	/**
	 * Definition of marshaller for User context
	 * @return
	 */
	@Bean
	public Jaxb2Marshaller marshaller() {
		final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("fr.isima.cuicuizz.front");
		return marshaller;
	}
	
	/**
	 * Definition of client for users based on the above marshaller
	 * @param marshaller
	 * @return
	 */
	@Bean
	public UserClient userClient(Jaxb2Marshaller marshaller) {
		final UserClient client = new UserClient();
		client.setDefaultUri("http://localhost:9000/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
