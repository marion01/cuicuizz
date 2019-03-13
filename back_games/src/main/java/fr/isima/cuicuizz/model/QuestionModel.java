package fr.isima.cuicuizz.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionModel {

	private int id;
	private String question;
	private int themeId;
	private List<AnswerModel> answers = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getThemeId() {
		return themeId;
	}

	public void setThemeId(int themeId) {
		this.themeId = themeId;
	}

	public List<AnswerModel> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerModel> answers) {
		this.answers = answers;
	}

}
