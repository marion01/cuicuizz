package fr.isima.cuicuizz.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class to launch the API of uses
 *
 */
@SpringBootApplication
public class CuicuizzUsersApplication {

	/**
	 * Run the API
	 */
	public static void main(String[] args) {
		// Disable auto restart
		System.setProperty("spring.devtools.restart.enabled", "false");

		SpringApplication.run(CuicuizzUsersApplication.class, args);
	}

}
