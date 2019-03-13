package fr.isima.cuicuizz.webservices;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class QuestionConfig extends WsConfigurerAdapter {

	@Bean(name = "questions")
	@Qualifier(value = "questionsSchema")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema questionsSchema) {
		final DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("QuestionsPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
		wsdl11Definition.setSchema(questionsSchema);
		return wsdl11Definition;
	}

	@Bean(name = "questionsSchema")
	public XsdSchema questionsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("questions.xsd"));
	}

}