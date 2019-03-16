package fr.isima.cuicuizz.users.webservices;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class ScoreServiceConfig {

	@Bean(name = "scores")
	@Qualifier(value = "scoresSchema")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema scoresSchema) {
		final DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ScorePort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
		wsdl11Definition.setSchema(scoresSchema);
		return wsdl11Definition;
	}

	@Bean(name = "scoresSchema")
	public XsdSchema scoresSchema() {
		return new SimpleXsdSchema(new ClassPathResource("scores.xsd"));
	}
}