package fr.isima.cuicuizz.front;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.isima.cuicuiz.front.services.IQuestionService;

@Component
public class Game {

	@Autowired
	IQuestionService questionService;

	@Autowired
	ModeManagement modeManagement;

	@Autowired
	QuestionManagement questionManagement;

	@Autowired
	ThemeManagement themeManagement;

	public IQuestionService getQuestionService() {
		return questionService;
	}

	public ModeManagement getModeManagement() {
		return modeManagement;
	}

	public ThemeManagement getThemeManagement() {
		return themeManagement;
	}

	public QuestionManagement getQuestionManagement() {
		return questionManagement;
	}

	private Game() {
	}

	private BufferedReader entry = new BufferedReader(new InputStreamReader(System.in));

	@PostConstruct
	public void launch() throws IOException {
		//modeManagement = new ModeManagement();
		//questionManagement = new QuestionManagement();
		//themeManagement = new ThemeManagement();

		System.out.println("************* Welcome to cuicuizz !! :) *************\n");
		System.out.println("Enter your pseudo:");
		final String pseudo = readEntry();
		final User user = User.getInstance();
		user.setPseudo(pseudo);
		System.out.println();

		menu();
	}

	public String readEntry() throws IOException {
		final String string = entry.readLine();
		if ("exit".equals(string)) {
			launch();
			return null;
		} else {
			return string;
		}
	}

	public void menu() throws IOException {
		System.out.println("**** Menu ****");
		System.out.println("0.Show history");
		System.out.println("1.New game");

		final String i = readEntry();
		switch ((i != null) ? i : "-1") {
		case ("0"):
			System.out.println("history");
			break;
		case ("1"):
			modeManagement.modeChoose();
			break;
		default:
			System.out.println("incorrect entry");
			menu();
		}
		System.out.println();
	}

	public List<Question> chooseThemeAndNumberQuestion() throws IOException {

		final int themeId = themeManagement.chooseTheme();
		final int nb = questionManagement.getNumberQuestions();
		final GetQuestionResponse response = questionService.getQuestion(themeId, nb);
		// final GetQuestionResponse response = questionService.getQuestion(themeId,
		// nb);

		return response.getQuestions();
	}

}
