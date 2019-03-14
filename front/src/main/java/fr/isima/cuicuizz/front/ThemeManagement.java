package fr.isima.cuicuizz.front;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

@Controller
public class ThemeManagement {

	private List<String> theme;
	
	@PostConstruct
	public void getTheme() {
		theme = new ArrayList<String>();
		// to recover in db
		theme.add("General");
	}

	
	public int chooseTheme() {
		//to remove after
		theme = new ArrayList<String>();
		theme.add("General");
		
		System.out.println("Choose the theme of the question:");
		for (int i = 0; i < theme.size(); i++) {
			System.out.println(i + "." + theme.get(i));
		}
		String theme;
		int themeId;
		try {
			theme = Application.game.readEntry();
			themeId = Integer.parseInt(theme);
		} catch (final IOException e) {
			e.printStackTrace();
		}

		// recup vrai theme
		themeId = 1;
		return themeId;
	}

}
