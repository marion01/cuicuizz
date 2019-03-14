package fr.isima.cuicuizz.front;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import fr.isima.cuicuizz.front.webservices.QuestionClient;

@ComponentScan({"fr.isima.cuicuiz.front"})
@SpringBootApplication
public class Application {
	
	@Autowired
	public static Game game;

	public static void main(String[] args) {
		// disable auto restart
		System.setProperty("spring.devtools.restart.enabled", "false");

		SpringApplication.run(Application.class, args);
		
		/*try {
			game.launch();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public static QuestionClient questionClient; 

	@Bean
	CommandLineRunner lookup(QuestionClient quoteClient) {
		return args -> {
			
			questionClient = quoteClient;
			final GetQuestionResponse response = quoteClient.getQuestion(1, 3);
			for (final Question question : response.getQuestions()) {
				System.err.println(question.getValue());
				for (final Answer answer : question.getAnswers()) {
					System.err.println(answer.getAnswer());
				}
			}

			// final GetQuestionResponse response = quoteClient.getQuestion(1);
		};
	}

}
