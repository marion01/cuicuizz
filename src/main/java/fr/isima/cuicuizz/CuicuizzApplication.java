package fr.isima.cuicuizz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CuicuizzApplication {

	public static void main(String[] args) {
		//disable auto restart
		System.setProperty("spring.devtools.restart.enabled", "false");
		
		SpringApplication.run(CuicuizzApplication.class, args);
		Pas pas = new Pas();
		pas.selectAll();
	}

}

