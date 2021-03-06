package fr.isima.cuicuizz.front.webservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class QuestionConfiguration {

	/**
	 * Definition of marshaller for Question context
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
	 * Definition of client for questions based on the above marshaller
	 * @param marshaller
	 * @return
	 */
	@Bean
	public QuestionClient questionClient(Jaxb2Marshaller marshaller) {
		final QuestionClient client = new QuestionClient();
		client.setDefaultUri("http://localhost:8000/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}