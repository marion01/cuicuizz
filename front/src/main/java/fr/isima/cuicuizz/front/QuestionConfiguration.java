package fr.isima.cuicuizz.front;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class QuestionConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() {
		final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml

		marshaller.setContextPath("questions.wsdl");
		marshaller.setContextPath("fr.isima.cuicuizz.front");
		return marshaller;
	}

	@Bean
	public QuestionClient questionClient(Jaxb2Marshaller marshaller) {
		final QuestionClient client = new QuestionClient();
		client.setDefaultUri("http://localhost:8080/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}