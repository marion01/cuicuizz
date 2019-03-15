package fr.isima.cuicuizz.front.management;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import fr.isima.cuicuizz.front.Utils;

@Controller
@Qualifier("ThemeManagement")
public class ThemeManagement implements IManagement{

	private List<String> theme;
	
	@PostConstruct
	public void getTheme() {
		theme = new ArrayList<String>();
		// to recover in db
		theme.add("General");
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
			theme = Utils.readEntry();
			themeId = Integer.parseInt(theme);
		} catch (final IOException e) {
			e.printStackTrace();
		}

		// recup vrai theme
		themeId = 1;
		return themeId;
	}
	
	@Override
	public boolean handlingEntry(int idTheme) throws IOException {
		boolean correctEntry = false;
		if (idTheme <= theme.size()) {
			correctEntry = true;
		} else {
			System.out.println("incorrect entry");
		}
		return correctEntry;
	}

}
