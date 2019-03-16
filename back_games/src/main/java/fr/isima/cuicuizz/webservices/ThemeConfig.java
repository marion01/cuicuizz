package fr.isima.cuicuizz.webservices;

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
public class ThemeConfig {

	@Bean(name = "themes")
	@Qualifier(value = "themesSchema")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema themesSchema) {
		final DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ThemesPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
		wsdl11Definition.setSchema(themesSchema);
		return wsdl11Definition;
	}

	@Bean(name = "themesSchema")
	public XsdSchema themesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd/theme.xsd"));
	}
}
