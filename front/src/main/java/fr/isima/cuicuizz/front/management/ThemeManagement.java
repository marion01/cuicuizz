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
import fr.isima.cuicuizz.front.services.IQuestionService;

@Controller
@Qualifier("ThemeManagement")
public class ThemeManagement implements IManagement{

	private List<Theme> themes;
	
	@Autowired
	private IQuestionService questionService;
	
	@PostConstruct
	public void getTheme() {
		GetThemesResponse tr = questionService.getThemes();
		themes = tr.getQuestions();
	}
	
	@Override
	public int handling() throws IOException {
		boolean falseEntry = true;
		int idMode = -1;
		while (falseEntry) {
			idMode = choose();
			falseEntry = !handlingEntry(idMode);
		}
		return idMode;
	}

	@Override
	public int choose() {		
		System.out.println("Choose the theme of the question:");
		for (int i = 0; i < themes.size(); i++) {
			System.out.println(i + "." + themes.get(i).getName());
		}
		String theme;
		int themeId = -1;
		try {
			theme = Utils.readEntry();
			themeId = Integer.parseInt(theme);
		} catch (final IOException e) {
			e.printStackTrace();
		}

		// recup vrai theme
	//	themeId = 1;
		return themeId + 1;
	}
	
	@Override
	public boolean handlingEntry(int idTheme) throws IOException {
		boolean correctEntry = false;
		if (idTheme <= themes.size()) {
			correctEntry = true;
		} else {
			System.out.println("incorrect entry");
		}
		return correctEntry;
	}

}
