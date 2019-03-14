package fr.isima.cuicuizz.users.dbaccess.mybatis.dao;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import fr.isima.cuicuizz.users.model.Score;

public interface ScoreMapper {
	@Select("SELECT * FROM SCORE WHERE UserId=#{userid}")
	List<Score> getUserScores(int userid);
	
	@Insert("INSERT INTO SCORE (UserId, Mode, Theme, NbQuestions, Value) VALUES (#{score.userId}, #{score.mode}, #{score.theme}, #{score.nbQuestions}, #{score.value})")
	void addScore(Score score);
	
	@Update("UPDATE SCORE SET NbQuestions=#{score.nbQuestions}, Value=#{score.value} WHERE Id=#{score.id}")
	void updateScore(Score score);
	
	@Select("SELECT * FROM SCORE WHERE UserId=#{userId} AND Mode=#{mode} AND Theme=#{theme}")
	Score getUserScore(int userId, String mode, String theme);
	
	@Select("SELECT * FROM SCORE WHERE UserId=#{userId} AND Mode=#{mode}")
	List<Score> getUserModeScores(int userId, String mode);
	
	@Select("SELECT * FROM SCORE WHERE UserId=#{userId} AND Theme=#{theme}")
	List<Score> getUserThemeScores(int userId, String theme);
	
	@Select("SELECT * FROM SCORE")
	List<Score> getAllScores();
	
	@Select("SELECT * FROM SCORE WHERE Mode=#{mode}")
	List<Score> getAllModeScores(String mode);
	
	@Select("SELECT * FROM SCORE WHERE Theme=#{theme}")
	List<Score> getAllThemeScores(String theme);
}
