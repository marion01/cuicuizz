package fr.isima.cuicuizz.users.model;

/**
 * the Model of score objects
 *
 */
public class Score {
	
	/**
	 * The id of the score
	 */
	private int id;
	
	/**
	 * The id of the user of the score
	 */
	private int userId;
	
	/**
	 * The mode name of the score
	 */
	private String mode;
	
	/**
	 * The mode theme of the score
	 */
	private String theme;
	
	/**
	 * The number of questions the user has to answer for this score
	 */
	private int nbQuestions;
	
	/**
	 * The number of successful question the user has given for this score
	 */
	private int nbSuccess;
	
	/**
	 * The value of the score
	 */
	private String value;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public int getNbQuestions() {
		return this.nbQuestions;
	}
	public void setNbQuestions(int nbQuestions) {
		this.nbQuestions = nbQuestions;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getNbSuccess() {
		return nbSuccess;
	}
	public void setNbSuccess(int nbSuccess) {
		this.nbSuccess = nbSuccess;
	}

}
