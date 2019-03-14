package fr.isima.cuicuizz.front;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import fr.isima.cuicuizz.front.mode.ModeEnum;
import fr.isima.services.IQuestionService;

public class Main {

	@Autowired
	static IQuestionService questionService;

	private Main() {
	}

	private static BufferedReader entry = new BufferedReader(new InputStreamReader(System.in));

	@PostConstruct
	public static void launch() throws IOException {
		System.out.println("************* Welcome to cuicuizz !! :) *************\n");
		System.out.println("Enter your pseudo:");
		final String pseudo = readEntry();
		final User user = User.getInstance();
		user.setPseudo(pseudo);
		System.out.println();

		menu();
	}

	public static String readEntry() throws IOException {
		final String string = entry.readLine();
		if ("exit".equals(string)) {
			launch();
			return null;
		} else {
			return string;
		}
	}

	public static void modeChoose() throws IOException {
		for (int index = 0; index < ModeEnum.values().length; index++) {
			final ModeEnum mode = ModeEnum.getById(index);
			System.out.println(index + "." + mode.getName());
		}
		System.out.println(ModeEnum.values().length + ".Back");
		System.out.println("Enter the number of the mode:");
		final String i = readEntry();
		if (Integer.parseInt(i) < ModeEnum.values().length) {
			final ModeEnum mode = ModeEnum.getById(Integer.parseInt(i));
			final List<Question> questions = chooseThemeAndNumberQuestion();
			try {
				mode.getInstance().execute(questions);
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			if (Integer.parseInt(i) == ModeEnum.values().length) {
				menu();
			} else {
				System.out.println("incorrect entry");
				modeChoose();
			}
		}
		System.out.println();
	}

	/**
	 * 
	 * @return the number of valid response of a player
	 */
	public static int answerQuestions(List<Question> questions) {
		int nbTrue = 0;
		try {
			for (final Question q : questions) {
				final boolean result = displayQuestion(q);
				if (result)
					nbTrue++;
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return nbTrue;
	}

	public static void menu() throws IOException {
		System.out.println("**** Menu ****");
		System.out.println("0.Show history");
		System.out.println("1.New game");

		final String i = readEntry();
		switch ((i != null) ? i : "-1") {
		case ("0"):
			System.out.println("history");
			break;
		case ("1"):
			modeChoose();
			break;
		default:
			System.out.println("incorrect entry");
			menu();
		}
		System.out.println();
	}

	// to recover in db
	private static String[] theme = { "General" };

	public static List<Question> chooseThemeAndNumberQuestion() throws IOException {

		final int themeId = chooseTheme();
		final int nb = getNumberQuestions();
		final GetQuestionResponse response = Main.questionService.getQuestion(themeId, nb);
		// final GetQuestionResponse response = questionService.getQuestion(themeId,
		// nb);

		return response.getQuestions();
	}

	public static int getNumberQuestions() {
		// final int nbMax =
		// questionService.getNbQuestionsFromTheme(1).getNbQuestions();
		final int nbMax = Main.questionService.getNbQuestionsFromTheme(1).getNbQuestions();

		boolean tooManyQuestion = true;
		System.out.println("Choose the number of questions:");
		String nbQuestion;
		int nb = 0;
		while (tooManyQuestion) {
			try {
				nbQuestion = readEntry();
				nb = Integer.parseInt(nbQuestion);
				tooManyQuestion = false;
				if (nb > nbMax) {
					tooManyQuestion = true;
					System.out.println("There are not enough questions, choose less question");
				}
			} catch (final IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return nb;
	}

	public static int chooseTheme() {
		System.out.println("Choose the theme of the question:");
		for (int i = 0; i < theme.length; i++) {
			System.out.println(i + "." + theme[i]);
		}
		String theme;
		int themeId;
		try {
			theme = readEntry();
			themeId = Integer.parseInt(theme);
		} catch (final IOException e) {
			e.printStackTrace();
		}

		// recup vrai theme
		themeId = 1;
		return themeId;
	}

	public static boolean displayQuestion(Question q) throws IOException {
		System.out.println(q.getValue());
		final List<Answer> answers = q.getAnswers();
		System.out.println(answers.size());
		for (int i = 0; i < answers.size(); i++) {
			System.out.println(i + "." + answers.get(i).getAnswer());
		}
		String i = null;
		boolean wrongAnswer = true;
		while (wrongAnswer) {
			i = readEntry();
			wrongAnswer = false;
			if (Integer.parseInt(i) >= answers.size()) {
				System.out.println("incorrect response");
				wrongAnswer = true;
			}
		}
		if (answers.get(Integer.parseInt(i)).isIsCorrect())
			return true;
		return false;
	}

	public static void visualizeCorrectResponse(List<Question> questions) throws IOException {
		System.out.println();
		System.out.println("Wright answers :");
		for (final Question q : questions) {
			System.out.print(q.getValue() + ":");
			for (final Answer a : q.getAnswers()) {
				if (a.isIsCorrect()) {
					System.out.println(a.getAnswer());
					break;
				}
			}
		}
		System.out.println();
	}
}
