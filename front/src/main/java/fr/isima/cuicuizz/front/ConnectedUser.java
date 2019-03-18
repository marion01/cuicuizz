package fr.isima.cuicuizz.front;

public class ConnectedUser {
	/**
	 * Instance of the connected user
	 */
	private static ConnectedUser instance;
	
	/**
	 * pseudo of the connected user
	 */
	private String pseudo;
	
	/**
	 * pseudo of the second player if the duel mode is run
	 */
	private String pseudoSecondPlayer;
	
	/**
	 * userDto with the password and the pseudo of the user
	 */
	private UserDto userDto;
	
	/**
	 * last score saved during the session
	 */
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
	
	/**
	 * @return the instance of connected user
	 */
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
