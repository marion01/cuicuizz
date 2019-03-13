package fr.isima.cuicuizz.dbaccess.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fr.isima.cuicuizz.model.QuestionModel;

public interface QuestionMapper {

	List<QuestionModel> selectAllQuestions(@Param("themeId") Integer themeId);

	Integer getNbQuestionFromTheme(Integer themeId);
}
