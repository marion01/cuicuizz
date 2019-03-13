package fr.isima.cuicuizz.dbaccess.mybatis.dao;

import java.util.List;

import fr.isima.cuicuizz.model.QuestionModel;

public interface QuestionMapper {

	List<QuestionModel> selectAllQuestions();
}
