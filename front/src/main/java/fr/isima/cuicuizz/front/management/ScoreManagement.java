package fr.isima.cuicuizz.front.management;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import fr.isima.cuicuizz.front.GetThemesResponse;
import fr.isima.cuicuizz.front.Theme;
import fr.isima.cuicuizz.front.Utils;
import fr.isima.cuicuizz.front.mode.ModeEnum;
import fr.isima.cuicuizz.front.services.IQuestionService;
import fr.isima.cuicuizz.front.services.UserService;

@Controller
public class ScoreManagement {
	
	private List<Theme> themes;
	
	@Autowired
	private IQuestionService questionService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	@Qualifier("ModeManagement")
	private IManagement modeManagement;
	
	@Autowired
	@Qualifier("ThemeManagement")
	private IManagement themeManagement;
	
	@PostConstruct
	public void getTheme() {
		GetThemesResponse tr = questionService.getThemes();
		themes = tr.getQuestions();
	}

	//TODO a enlever quand appelle au back user
	public void rempliScore() {
		scores = new ArrayList<String>();
		scores.add("scores1");
		scores.add("scores2");
		scores.add("scores3");
	}

	public int choose() throws IOException {
		boolean invalidEntry = true;
		int nb = -1;
		while (invalidEntry) {
			System.out.println("Choose what score(s) you want to see:");
			System.out.println("0.All of your scores");
			System.out.println("1.Your scores for a specified mode");
			System.out.println("2.Your scores for a specified theme");
			System.out.println("3.Your scores for a specified theme and mode");
			System.out.println("4.All scores");
			System.out.println("5.All scores for a specified mode");
			System.out.println("6.All scores for a specified theme");

			nb = Utils.readEntryNumber();

			if (nb < 7)
				invalidEntry = false;
			else
				System.out.println("incorrect entry");
		}
		return nb;
	}

	//TODO normalement list de scrores et non de string
	private List<String> scores;

	public void handleChoice(int menuId) throws IOException {
		//TODO appeller les bonnes fonctions du back user
		int themeId, modeId;
		switch (menuId) {
			case (0):
				rempliScore();
				//userService.getUserScores()
				break;
			case (1):
				rempliScore();
				modeId = modeManagement.handling();
				//userService.getUserModeScores(modeId)
				break;
			case (2):
				rempliScore();
				themeId = themeManagement.handling();
				//userService.getUserThemeScores(themeId)
				break;
			case (3):
				rempliScore();
				modeId = modeManagement.handling();
				themeId = themeManagement.handling();
				//userService.getUserScore(modeId, themeId)
				break;
			case (4):
				rempliScore();
				//userService.getAllScores()
				break;
			case (5):
				rempliScore();
				modeId = modeManagement.handling();
				//userService.getAllModeScores(modeId)
				break;
			case (6):
				rempliScore();
				themeId = themeManagement.handling();
				//userService.getAllThemeScores(themeId)
				break;
		}

		displayScores();
	}

	//TODO a modifier en fonction de ce qu'il y a dans l'object score
	public void displayScores() {
		for (String s : scores) {
			System.out.println(s);
		}
	}
}
