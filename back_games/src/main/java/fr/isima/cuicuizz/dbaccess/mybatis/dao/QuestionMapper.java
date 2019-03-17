package fr.isima.cuicuizz.dbaccess.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fr.isima.cuicuizz.model.QuestionModel;
import fr.isima.cuicuizz.model.ThemeModel;

/**
 * MyBatis mapper for questions. Sql requests are defined into
 * QuestionMapper.xml in resources
 * 
 *
 */
public interface QuestionMapper {
	/**
	 * Returns all questions from a specific theme
	 * 
	 * @param themeId
	 * @return
	 */
	List<QuestionModel> selectAllQuestions(@Param("themeId") Integer themeId);

	/**
	 * returns the maximum nuber of questions associated to a given theme
	 * 
	 * @param themeId
	 * @return
	 */
	Integer getNbQuestionFromTheme(Integer themeId);

	/**
	 * returns all the available themes
	 * 
	 * @return
	 */
	List<ThemeModel> getThemes();
}
