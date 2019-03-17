package fr.isima.cuicuizz.converters;

import java.util.ArrayList;
import java.util.List;

import fr.isima.cuicuizz.model.ThemeModel;
import io.spring.guides.gs_producing_web_service.Theme;

/**
 * Converter class from Themes to webservice response
 * 
 * @author fabie
 *
 */
public class ThemeConverter {

	public static Theme convert(ThemeModel themeModel) {
		final Theme theme = new Theme();
		theme.setId(themeModel.getId());
		theme.setName(themeModel.getName());
		return theme;
	}

	public static List<Theme> convert(List<ThemeModel> themesModel) {
		final List<Theme> themes = new ArrayList<>();
		for (final ThemeModel themeModel : themesModel) {
			themes.add(convert(themeModel));
		}
		return themes;
	}

}
