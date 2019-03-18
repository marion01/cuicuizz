package fr.isima.cuicuizz.users.webservices;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * User configuration class
 *
 */
@EnableWs
@Configuration
public class UserServiceConfig extends WsConfigurerAdapter {

	@Bean(name = "users")
	@Qualifier(value = "usersSchema")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema usersSchema) {
		final DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("UserPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
		wsdl11Definition.setSchema(usersSchema);
		return wsdl11Definition;
	}

	@Bean
	@Qualifier(value = "usersSchema")
	public XsdSchema usersSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd/users.xsd"));
	}
}