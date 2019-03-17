package fr.isima.cuicuizz.users.model;

public class User {
	private int id;	
	private String pseudo;
	private String lastActionDate;
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
