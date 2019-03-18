package fr.isima.cuicuizz.users.model;

/**
 * The Model of user objects
 *
 */
public class User {
	
	/**
	 * The id of the user
	 */
	private int id;
	
	/**
	 * The pseudo of the user
	 */
	private String pseudo;
	
	/**
	 * The date of the last action the user has made
	 */
	private String lastActionDate;
	
	/**
	 * The password of the user (hashed)
	 */
	private String password;
	
	public User(int id, String pseudo, String lastActionDate, String password) {
		this.id = id;
		this.pseudo = pseudo;
		this.lastActionDate = lastActionDate;
		this.password = password;
	}
	
	public User() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getLastActionDate() {
		return lastActionDate;
	}
	public void setLastActionDate(String lastActionDate) {
		this.lastActionDate = lastActionDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
