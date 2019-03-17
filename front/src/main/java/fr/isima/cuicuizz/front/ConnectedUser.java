package fr.isima.cuicuizz.front;

public class ConnectedUser {
	private static ConnectedUser instance;
	
	private String pseudo;
	private String pseudoSecondPlayer;
	private UserDto userDto;
	private ScoreDto score;
	
	public UserDto getUserDto() {
		return userDto;
	}
	
	public ScoreDto getScore() {
		return score;
	}
	
	public void setUserDto(UserDto u) {
		userDto = u;
	}
	
	public void setScore(ScoreDto s) {
		score = s;
	}
	
	public static ConnectedUser getInstance() {
		if (instance == null) {
			instance = new ConnectedUser();
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
