package fr.isima.cuicuizz.front;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.isima.cuicuizz.front.mode.ModeEnum;

public class Main {
	
	private Main() {}
	
	private static BufferedReader entry = new BufferedReader(new InputStreamReader(System.in));	 
	
	public static void launch() throws IOException {
		System.out.println("************* Welcome to cuicuizz !! :) *************\n");
		System.out.println("Enter your pseudo:");
		String pseudo = readEntry();
		User user = User.getInstance();
		user.setPseudo(pseudo);
		System.out.println();
		
		menu();
	}
	
	public static String readEntry() throws IOException {
		String string = entry.readLine();
		if ("exit".equals(string)) {
			launch();
			return null;
		}else {
			return string;
		}
	}
	
	public static void modeChoose() throws IOException {
		for (int index = 0; index < ModeEnum.values().length; index++) {
			ModeEnum mode = ModeEnum.getById(index);
			System.out.println(index + "." + mode.getName());
		}
		System.out.println(ModeEnum.values().length + ".Back");
		System.out.println("Enter the number of the mode:"); 
		String i = readEntry();
		if (Integer.parseInt(i) < ModeEnum.values().length) {
			ModeEnum mode = ModeEnum.getById(Integer.parseInt(i));
			List<Question> questions = chooseThemeAndNumberQuestion();
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
			for (Question q: questions) {
				boolean result = displayQuestion(q);
				if (result) nbTrue++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nbTrue;
	}
	
	public static void menu() throws IOException {
		System.out.println("**** Menu ****");
		System.out.println("0.Show history");
		System.out.println("1.New game");
		 
		String i = readEntry();
		switch((i != null) ? i : "-1") {
			case("0"):
				System.out.println("history");
				break;
			case("1"):
				modeChoose();
				break;
			default:
				System.out.println("incorrect entry");
				menu();
		}
		System.out.println();
	}
	
	//to recover in db
	private static String[] theme = {"General"};
	
	public static List<Question> chooseThemeAndNumberQuestion() throws IOException {

		int themeId = chooseTheme();
		int nb = getNumberQuestions();
		GetQuestionResponse response = Application.questionClient.getQuestion(themeId, nb);
		
		return response.getQuestions();
	}
	
	public static int getNumberQuestions() {
		//recup nb max question
		int nbMax = 7;
		
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
					System.out.println("There are not enought questions, choose less question");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return nb;
	}
	
	public static int chooseTheme() {
		System.out.println("Choose the theme of the question:");
		for (int i=0; i<theme.length ;i++) {
			System.out.println(i+"."+theme[i]);
		}
		String theme;
		int themeId;
		try {
			theme = readEntry();
			themeId = Integer.parseInt(theme);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//recup vrai theme
		themeId = 1;
		return themeId;
	}
	
	public static boolean displayQuestion(Question q) throws IOException {
		System.out.println(q.getValue());
		List<Answer> answers = q.getAnswers();
		for (int i = 0;i<answers.size();i++) {
			System.out.println(i+"."+answers.get(i).getAnswer());
		}
		String i = null;
		boolean wrongAnswer = true;
		while(wrongAnswer) {
			i = readEntry();
			wrongAnswer = false;
			if (Integer.parseInt(i) >= answers.size()) {
				System.out.println("incorrect response");
				wrongAnswer = true;
			} 
		}
		if(answers.get(Integer.parseInt(i)).isIsCorrect()) 
			return true;
		return false;
	}
	
	public static void visualizeCorrectResponse(List<Question> questions) throws IOException {
		System.out.println();
		System.out.println("Wright answers :");
		for (Question q: questions) {
			System.out.print(q.getValue() + ":");
			for (Answer a: q.getAnswers()) {
				if (a.isIsCorrect()) {
					System.out.println(a.getAnswer());
					break;
				}
			}
		}
		System.out.println();
	}
}
