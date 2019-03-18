package fr.isima.cuicuizz.users.dbaccess.mybatis.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import fr.isima.cuicuizz.users.model.User;

/**
 * MyBatis mapper for users
 *
 */
public interface UserMapper {
	
	/**
	 * Get a user from its password and pseudo
	 * @param pseudo the pseudo of the user to get
	 * @param password the password of the user to get
	 * @return the user corresponding to the pseudo and the password
	 */
	@Select("SELECT * FROM User WHERE Pseudo=#{pseudo} AND Password=#{password}")
	User login(@Param("pseudo") String pseudo, @Param("password") String password);
	
	/**
	 * Update the lastActionDate
	 * @param date the lastActionDate updated
	 * @param id the id of the lastactiondate to update
	 */
	@Update("UPDATE User SET LastActionDate = #{date} WHERE Id=#{id}")
	void updateLastActionDate(@Param("date") String date,@Param("id") int id);
	
	/**
	 * Get existing of a user by its pseudo
	 * @param pseudo the pseudo of the user to find its existing
	 * @return 1 if the user exists and 0 otherwise
	 */
	@Select("SELECT count(*) FROM User WHERE Pseudo=#{pseudo}")
	int isPseudoExisting(String pseudo);
	
	/**
	 * Add a user to data base
	 * @param u the user to add
	 * @return 1 if the user has been created (number of lines created) and 0 otherwise
	 */
	@Insert("INSERT INTO User (Pseudo, Password, LastActionDate) VALUES (#{u.pseudo},#{u.password},#{u.lastActionDate})")
	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
	int addUser(@Param("u") User u);
	
	/**
	 * Delete a user from data base
	 * @param user the user to delete
	 */
	@Delete("DELETE FROM User WHERE Pseudo=#{user.pseudo} AND Password=#{user.password}")
	void deleteUser(@Param("user") User user);
	
	/**
	 * Get a user from data base by its pseudo
	 * @param pseudo the pseudo of the user to find
	 * @return the user corresponding to the input pseudo
	 */
	@Select("SELECT * FROM User WHERE Pseudo=#{pseudo}")
	User getUser(@Param("pseudo") String pseudo);
	
	@Select("SELECT * FROM User WHERE Pseudo=#{pseudo}")
	User getUserByPseudo(String pseudo);
}
