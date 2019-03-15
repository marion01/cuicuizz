package fr.isima.cuicuizz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CuicuizzQuestionsApplication {

	public static void main(String[] args) {
		// disable auto restart
		System.setProperty("spring.devtools.restart.enabled", "true");

		SpringApplication.run(CuicuizzQuestionsApplication.class, args);
	}

}
