package fr.isima.cuicuizz.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CuicuizzUsersApplication {

	public static void main(String[] args) {
		// disable auto restart
		System.setProperty("spring.devtools.restart.enabled", "false");

		SpringApplication.run(CuicuizzUsersApplication.class, args);
	}

}
