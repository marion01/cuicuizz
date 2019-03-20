package fr.isima.cuicuizz.front.mode;

import java.io.IOException;
import java.util.List;

import fr.isima.cuicuizz.front.Question;
import fr.isima.cuicuizz.front.ScoreDto;
import fr.isima.cuicuizz.front.ConnectedUser;
import fr.isima.cuicuizz.front.Utils;
import fr.isima.cuicuizz.front.management.QuestionManagement;

/**
 * Handle the duel mode
 */
public class Duel implements IMode {

	/**
	 * the instance of the mode
	 */
	private static Duel instance;

	int nbRightResponsePlayer1;
	int nbRightResponsePlayer2;
	ConnectedUser user;

	QuestionManagement questionManagement;

	/**
	 * Execution of the duel mode
	 */
	@Override
	public void execute(List<Question> questions, QuestionManagement qm, String theme) throws IOException {
		this.questionManagement = qm;
		System.out.println("Duel mode");
		getPseudoSecondPlayer();
		user = ConnectedUser.getInstance();
		for (int i = 0; i < 10; i++)
			System.out.println();

		// first player
		System.out.println("****** " + user.getPseudo() + ", your turn ******");
		nbRightResponsePlayer1 = questionManagement.answerQuestions(questions);
		System.out.println();
		for (int i = 0; i < 10; i++)
			System.out.println();

		// second player
		System.out.println("****** " + user.getPseudoSecondPlayer() + ", your turn ******");
		nbRightResponsePlayer2 = questionManagement.answerQuestions(questions);
		System.out.println();
		for (int i = 0; i < 10; i++)
			System.out.println();

		final int winner = displayResult(questions);

		final ScoreDto s = new ScoreDto();
		s.setMode("Duel");
		s.setTheme(theme);
		s.setNbQuestions(questions.size());
		s.setNbSuccess(nbRightResponsePlayer1);
		if (winner == 1)
			s.setValue(user.getPseudo() + " win against " + user.getPseudoSecondPlayer());
		else if (winner == 2)
			s.setValue(user.getPseudoSecondPlayer() + "against " + user.getPseudo());
		else
			s.setValue("exaequo");
		s.setUserId(ConnectedUser.getInstance().getUserDto().getId());
		ConnectedUser.getInstance().setScore(s);
	}

	/**
	 * Display the result of the duel
	 * @param questions
	 * @return
	 */
	private int displayResult(List<Question> questions) {
		int winner = 0;
		if (nbRightResponsePlayer1 > nbRightResponsePlayer2) {
			winner = 1;
			System.out.println(user.getPseudo() + " you win !!");
		} else if (nbRightResponsePlayer1 < nbRightResponsePlayer2) {
			winner = 2;
			System.out.println(user.getPseudoSecondPlayer() + " you win !!");
		} else {
			System.out.println("exaequo !");
		}

		System.out.println(user.getPseudo() + " you have " + nbRightResponsePlayer1 + " valid response");
		System.out.println(user.getPseudoSecondPlayer() + " You have " + nbRightResponsePlayer2 + " valid response");
		try {
			questionManagement.visualizeCorrectResponse(questions);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return winner;
	}

	/**
	 * get the singleton instance
	 * @return the instance
	 */
	public static IMode getInstance() {
		if (instance == null) {
			instance = new Duel();
		}
		return instance;
	}

	/**
	 * ask the user for a second pseudo
	 */
	public void getPseudoSecondPlayer() {
		System.out.println("Enter the pseudo of the second player:");
		String pseudo;
		try {
			pseudo = Utils.readEntryString();
			final ConnectedUser user = ConnectedUser.getInstance();
			user.setPseudoSecondPlayer(pseudo);
			System.out.println();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
