package fr.isima.cuicuizz.front.mode;

import java.io.IOException;
import java.util.List;

import fr.isima.cuicuizz.front.Application;
import fr.isima.cuicuizz.front.Game;
import fr.isima.cuicuizz.front.Question;
import fr.isima.cuicuizz.front.User;
import fr.isima.cuicuizz.front.Utils;
import fr.isima.cuicuizz.front.management.QuestionManagement;

public class Duel implements IMode{
	
	private static Duel instance;
	
	int nbRightResponsePlayer1;
	int nbRightResponsePlayer2;
	User user;
	
	QuestionManagement questionManagement;

	@Override
	public void execute(List<Question> questions, QuestionManagement qm) throws IOException {
		this.questionManagement = qm;
		System.out.println("Duel mode");
		getPseudoSecondPlayer();
		user = User.getInstance();
		for (int i=0;i<10;i++) System.out.println();
		
		//first player
		System.out.println("****** " + user.getPseudo() + ", your turn ******");
		nbRightResponsePlayer1 = questionManagement.answerQuestions(questions);
		System.out.println();
		for (int i=0;i<10;i++) System.out.println();
		
		//second player
		System.out.println("****** " + user.getPseudoSecondPlayer() + ", your turn ******");
		nbRightResponsePlayer2 = questionManagement.answerQuestions(questions);
		System.out.println();
		for (int i=0;i<10;i++) System.out.println();
		
		displayResult(questions);		
	}
	
	
	
	private void displayResult(List<Question> questions) {
		if (nbRightResponsePlayer1 > nbRightResponsePlayer2) {
			System.out.println(user.getPseudo() + " you win !!");
		} else if (nbRightResponsePlayer1 < nbRightResponsePlayer2){
			System.out.println(user.getPseudoSecondPlayer() + " you win !!");
		} else {
			System.out.println("exaequo !");
		}
		
		
		System.out.println(user.getPseudo() + " you have "+ nbRightResponsePlayer1 + " valid response");
		System.out.println(user.getPseudoSecondPlayer() + " You have "+ nbRightResponsePlayer2 + " valid response");
		try {
			questionManagement.visualizeCorrectResponse(questions);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static IMode getInstance() {
		if (instance == null) {
			instance = new Duel();
		} 
		return instance;
	}

	public void getPseudoSecondPlayer() {
		System.out.println("Enter the pseudo the second player:");
		String pseudo;
		try {
			pseudo = Utils.readEntryString();
			User user = User.getInstance();
			user.setPseudoSecondPlayer(pseudo);
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
