package fr.isima.cuicuizz.users.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	private int id;	
	private String pseudo;
	private Date lastActionDate;
	private String password;
	
	public User(int id, String pseudo, Date lastActionDate, String password) {
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
	public Date getLastActionDate() {
		return lastActionDate;
	}
	public void setLastActionDate(String lastActionDate) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.lastActionDate = format.parse(lastActionDate);
		} catch (ParseException e) {
			try {
				this.lastActionDate = format.parse("1900-01-01 00:00:00");
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
