package fr.isima.cuicuizz.users.dbaccess.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import fr.isima.cuicuizz.users.model.Score;

/**
 * MyBatis mapper for scores.
 *
 */
public interface ScoreMapper {
	
	/**
	 * Get the list of score of a specified user
	 * @param userid the id of the user
	 * @return the list of the user scores
	 */
	@Select("SELECT * FROM SCORE WHERE UserId=#{userid}")
	List<Score> getUserScores(@Param("userid") int userid);
	
	/**
	 * Add a new score into the data base
	 * @param score the score to add
	 */
	@Insert("INSERT INTO SCORE (UserId, Mode, Theme, NbQuestions, NbSuccess, Value) VALUES (#{score.userId}, #{score.mode}, #{score.theme}, #{score.nbQuestions}, #{score.nbSuccess}, #{score.value})")
	void addScore(@Param("score") Score score);
	
	/**
	 * Update a score in the data base
	 * @param score the score updated
	 */
	@Update("UPDATE SCORE SET NbQuestions=#{score.nbQuestions}, NbSuccess=#{score.nbSuccess}, Value=#{score.value} WHERE Id=#{score.id}")
	void updateScore(@Param("score") Score score);
	
	/**
	 * Delete a score from data base
	 * @param score the score to delete
	 */
	@Delete("DELETE FROM SCORE WHERE UserId=#{score.userId} AND Mode=#{score.mode} AND Theme=#{score.theme}")
	void deleteScore(@Param("score") Score score);
	
	/**
	 * Get a score of a specified user for a specified mode and theme
	 * @param userId the id of the user
	 * @param mode the mode of the score
	 * @param theme the theme of the score
	 * @return the score find corresponding to the user, the mode and the theme
	 */
	@Select("SELECT * FROM SCORE WHERE UserId=#{userId} AND Mode=#{mode} AND Theme=#{theme}")
	Score getUserScore(@Param("userId") int userId, @Param("mode") String mode, @Param("theme") String theme);
	
	/**
	 * Get all score of a specified user and for a specified mode
	 * @param userId the id of the user
	 * @param mode the mode of the score
	 * @return the list of scores corresponding to the specified user and mode
	 */
	@Select("SELECT * FROM SCORE WHERE UserId=#{userId} AND Mode=#{mode}")
	List<Score> getUserModeScores(@Param("userId") int userId, @Param("mode") String mode);
	
	/**
	 * Get all score of a specified user and for a specified theme
	 * @param userId the id of the user
	 * @param theme the theme of the score
	 * @return the list of scores corresponding to the specified user and theme
	 */
	@Select("SELECT * FROM SCORE WHERE UserId=#{userId} AND Theme=#{theme}")
	List<Score> getUserThemeScores(@Param("userId") int userId, @Param("theme") String theme);
	
	/**
	 * Get all scores
	 * @return the list of all scores
	 */
	@Select("SELECT * FROM SCORE")
	List<Score> getAllScores();
	
	/**
	 * Get all scores of a specified mode
	 * @param mode the mode of the score
	 * @return the list of scores corresponding to the specified mode
	 */
	@Select("SELECT * FROM SCORE WHERE Mode=#{mode}")
	List<Score> getAllModeScores(@Param("mode") String mode);
	
	/**
	 * Get all scores of a specified theme
	 * @param theme the theme of the score
	 * @return the list of scores corresponding to the specified theme
	 */
	@Select("SELECT * FROM SCORE WHERE Theme=#{theme}")
	List<Score> getAllThemeScores(@Param("theme") String theme);
}
