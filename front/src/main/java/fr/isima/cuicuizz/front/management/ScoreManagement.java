package fr.isima.cuicuizz.front.management;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import fr.isima.cuicuizz.front.ConnectedUser;
import fr.isima.cuicuizz.front.ScoreDto;
import fr.isima.cuicuizz.front.ScoreResponse;
import fr.isima.cuicuizz.front.UserDto;
import fr.isima.cuicuizz.front.Utils;
import fr.isima.cuicuizz.front.mode.ModeEnum;
import fr.isima.cuicuizz.front.services.IQuestionService;
import fr.isima.cuicuizz.front.services.IUserService;

@Controller
public class ScoreManagement {

	@Autowired
	IUserService userService;

	@Autowired
	IQuestionService questionService;

	@Autowired
	@Qualifier("ModeManagement")
	private IManagement modeManagement;

	@Autowired
	@Qualifier("ThemeManagement")
	private IManagement themeManagement;

	/**
	 * Display all the choice of historic and recover the choice of the user
	 * @return the choice of the user
	 * @throws IOException
	 */
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

	/**
	 * Ask what historic the user want and handle the choice
	 * 
	 * @param menuId
	 * @throws IOException
	 */
	public void handleChoice(int menuId) throws IOException {
		int themeId, modeId;
		String mode;
		String theme;
		ScoreResponse sc = null;
		final UserDto userDto = ConnectedUser.getInstance().getUserDto();
		switch (menuId) {
		case (0): //all scores of the connected user
			sc = userService.getUserScores(userDto);
			break;
		case (1): //all scores of the connected user in a specific mode
			modeId = modeManagement.handling();
			mode = modeIdToName(modeId);
			sc = userService.getUserModeScores(userDto, mode);
			break;
		case (2): //all scores of the connected user in a specific theme
			themeId = themeManagement.handling();
			theme = themeIdToName(themeId);
			sc = userService.getUserThemeScores(userDto, theme);
			break;
		case (3): //all scores of the connected user in a specific mode and theme
			modeId = modeManagement.handling();
			themeId = themeManagement.handling();
			theme = themeIdToName(themeId);
			mode = modeIdToName(modeId);
			sc = userService.getUserScore(userDto, theme, mode);
			break;
		case (4): //all scores of the application
			sc = userService.getAllScores();
			break;
		case (5): //all scores of the application in a specific mode
			modeId = modeManagement.handling();
			mode = modeIdToName(modeId);
			sc = userService.getAllModeScores(mode);
			break;
		case (6): //all scores of the application in a specific theme
			themeId = themeManagement.handling();
			theme = themeIdToName(themeId);
			sc = userService.getAllThemeScores(theme);
			break;
		}

		displayScores(sc);
	}

	/**
	 * Display scores 
	 * @param sc
	 */
	public void displayScores(ScoreResponse sc) {
		if (sc != null && sc.getScores() != null && !sc.getScores().isEmpty()) {
			final List<ScoreDto> scores = sc.getScores();
			for (final ScoreDto s : scores) {
				System.out.println("user " + s.getUserId() + " on mode " + s.getMode() + " and theme " + s.getTheme());
				System.out.println(s.getNbSuccess() + " right answers on " + s.getNbQuestions());
				System.out.println("score: " + s.getValue());
				System.out.println();
			}
		} else {
			System.out.println("There are no scores saved");
		}

	}

	/**
	 * convert a theme id in its name
	 * @param themeId the theme id
	 * @return
	 */
	public String themeIdToName(int themeId) {
		return questionService.getThemes().getThemes().get(themeId - 1).getName();
	}

	/**
	 * convert a mode id in its name
	 * @param modeId the mode id
	 * @return
	 */
	public String modeIdToName(int modeId) {
		return ModeEnum.getById(modeId).getName();
	}
}
