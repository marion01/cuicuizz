package fr.isima.cuicuizz.front;

public class User {
	private static User instance;
	
	private String pseudo;
	private String pseudoSecondPlayer;
	
	public static User getInstance() {
		if (instance == null) {
			instance = new User();
		} 
		return instance;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pPseudo) {
		pseudo = pPseudo;
	}
	public String getPseudoSecondPlayer() {
		return pseudoSecondPlayer;
	}

	public void setPseudoSecondPlayer(String pPpseudoSecondPlayer) {
		pseudoSecondPlayer = pPpseudoSecondPlayer;
	}
}
