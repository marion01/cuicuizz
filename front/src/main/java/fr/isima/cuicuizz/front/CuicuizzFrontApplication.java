package fr.isima.cuicuizz.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ "fr.isima.cuicuizz.front" })
@SpringBootApplication
public class CuicuizzFrontApplication {

	@Autowired
	public Game game;

	public static void main(String[] args) {
		// disable auto restart
		System.setProperty("spring.devtools.restart.enabled", "false");
		System.setProperty("spring.main.allow-bean-definition-overriding", "true");

		SpringApplication.run(CuicuizzFrontApplication.class, args);

	}
}
