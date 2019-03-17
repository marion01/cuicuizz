package fr.isima.cuicuizz.users.dbaccess.mybatis.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import fr.isima.cuicuizz.users.model.User;

public interface UserMapper {
	@Select("SELECT * FROM User WHERE Pseudo=#{pseudo} AND Password=#{password}")
	User login(@Param("pseudo") String pseudo, @Param("password") String password);
	
	@Update("UPDATE User SET LastActionDate = #{date} WHERE Id=#{id}")
	void updateLastActionDate(@Param("date") String date,@Param("id") int id);
	
	@Select("SELECT count(*) FROM User WHERE Pseudo=#{pseudo}")
	int isPseudoExisting(String pseudo);
	
	@Insert("INSERT INTO User (Pseudo, Password, LastActionDate) VALUES (#{u.pseudo},#{u.password},#{u.lastActionDate})")
	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
	int addUser(@Param("u") User u);
	
	@Delete("DELETE FROM User WHERE Pseudo=#{user.pseudo} AND Password=#{user.password}")
	void deleteUser(@Param("user") User user);
	
	@Select("SELECT * FROM User WHERE Pseudo=#{pseudo}")
	User getUser(@Param("pseudo") String pseudo);
	
	@Select("SELECT * FROM User WHERE Pseudo=#{pseudo}")
	User getUserByPseudo(String pseudo);
}
