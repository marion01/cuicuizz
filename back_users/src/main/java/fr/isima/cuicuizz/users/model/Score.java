package fr.isima.cuicuizz.users.model;

public class Score {
	
	private int id;
	private int userId;
	private String mode;
	private String theme;
	private int nbQuestions;
	private int nbSuccess;
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
