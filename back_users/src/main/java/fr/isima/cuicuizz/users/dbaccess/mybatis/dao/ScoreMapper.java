package fr.isima.cuicuizz.users.dbaccess.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import fr.isima.cuicuizz.users.model.Score;

public interface ScoreMapper {
	@Select("SELECT * FROM SCORE WHERE UserId=#{userid}")
	List<Score> getUserScores(@Param("userid") int userid);
	
	@Insert("INSERT INTO SCORE (UserId, Mode, Theme, NbQuestions, NbSuccess, Value) VALUES (#{score.userId}, #{score.mode}, #{score.theme}, #{score.nbQuestions}, #{score.nbSuccess}, #{score.value})")
	void addScore(@Param("score") Score score);
	
	@Update("UPDATE SCORE SET NbQuestions=#{score.nbQuestions}, NbSuccess=#{score.nbSuccess}, Value=#{score.value} WHERE Id=#{score.id}")
	void updateScore(@Param("score") Score score);
	
	@Delete("DELETE FROM SCORE WHERE UserId=#{score.userId} AND Mode=#{score.mode} AND Theme=#{score.theme}")
	void deleteScore(@Param("score") Score score);
	
	@Select("SELECT * FROM SCORE WHERE UserId=#{userId} AND Mode=#{mode} AND Theme=#{theme}")
	Score getUserScore(@Param("userId") int userId, @Param("mode") String mode, @Param("theme") String theme);
	
	@Select("SELECT * FROM SCORE WHERE UserId=#{userId} AND Mode=#{mode}")
	List<Score> getUserModeScores(@Param("userId") int userId, @Param("mode") String mode);
	
	@Select("SELECT * FROM SCORE WHERE UserId=#{userId} AND Theme=#{theme}")
	List<Score> getUserThemeScores(@Param("userId") int userId, @Param("theme") String theme);
	
	@Select("SELECT * FROM SCORE")
	List<Score> getAllScores();
	
	@Select("SELECT * FROM SCORE WHERE Mode=#{mode}")
	List<Score> getAllModeScores(@Param("mode") String mode);
	
	@Select("SELECT * FROM SCORE WHERE Theme=#{theme}")
	List<Score> getAllThemeScores(@Param("theme") String theme);
}
