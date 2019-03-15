package fr.isima.cuicuizz.front;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.isima.cuicuizz.front.management.IManagement;
import fr.isima.cuicuizz.front.management.QuestionManagement;
import fr.isima.cuicuizz.front.mode.ModeEnum;
import fr.isima.cuicuizz.front.services.IQuestionService;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Game {

	@Autowired
	private IQuestionService questionService;

	@Autowired
	@Qualifier("ModeManagement")
	private IManagement modeManagement;

	@Autowired
	private QuestionManagement questionManagement;

	@Autowired
	@Qualifier("ThemeManagement")
	private IManagement themeManagement;

	private Game() {
	}

	public void begin() throws IOException {
		System.out.println("Enter your pseudo:");
		final String pseudo = Utils.readEntry();
		final User user = User.getInstance();
		user.setPseudo(pseudo);
		System.out.println();

		menu();
	}

	@PostConstruct
	public void launch() throws IOException {
		System.out.println("************* Welcome to cuicuizz !! :) *************\n");
		begin();
	}

	public void menu() throws IOException {
		System.out.println("**** Menu ****");
		System.out.println("0.Show history");
		System.out.println("1.New game");

		final String i = Utils.readEntry();
		switch ((i != null) ? i : "-1") {
		case ("0"):
			System.out.println("history");
			break;
		case ("1"):
			int idMode = modeManagement.handling();
			if (idMode != ModeEnum.values().length) {
				final ModeEnum mode = ModeEnum.getById(idMode);
				final List<Question> questions = chooseThemeAndNumberQuestion();
				try {
					mode.getInstance().execute(questions, questionManagement);
				} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			menu();
			break;
		default:
			System.out.println("incorrect entry");
			menu();
		}
		System.out.println();
	}

	public List<Question> chooseThemeAndNumberQuestion() throws IOException {

		final int themeId = themeManagement.handling();
		final int nbMax = questionService.getNbQuestionsFromTheme(1).getNbQuestions();
		final int nb = questionManagement.getNumberQuestions(nbMax);
		final GetQuestionResponse response = questionService.getQuestion(themeId, nb);

		return response.getQuestions();
	}

}
