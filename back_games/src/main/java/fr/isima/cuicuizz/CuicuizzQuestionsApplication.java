package fr.isima.cuicuizz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Back application starting on port 8000. This server is used to get questions
 * from an SQLite database. Web service API uses spring webservices
 *
 */
@SpringBootApplication
public class CuicuizzQuestionsApplication {

	public static void main(String[] args) {
		// disable auto restart
		System.setProperty("spring.devtools.restart.enabled", "false");

		SpringApplication.run(CuicuizzQuestionsApplication.class, args);
	}

}
