package fr.isima.cuicuizz.front;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// disable auto restart
		System.setProperty("spring.devtools.restart.enabled", "false");

		SpringApplication.run(Application.class, args);

		try {
			Main.launch();
		} catch (final IOException e) {
			e.printStackTrace();
		}
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
