package fr.isima.cuicuizz.users.model;

import java.util.Date;

public class User {
	private int id;	
	private String pseudo;
	private Date lastActionDate;
	private String password;
	
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
	public Date getLastActionDate() {
		return lastActionDate;
	}
	public void setLastActionDate(Date lastActionDate) {
		this.lastActionDate = lastActionDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}