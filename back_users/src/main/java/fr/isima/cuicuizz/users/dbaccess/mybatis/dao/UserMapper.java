package fr.isima.cuicuizz.users.dbaccess.mybatis.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import fr.isima.cuicuizz.users.model.User;

public interface UserMapper {
	@Select("SELECT * FROM USER WHERE Pseudo=#{pseudo} AND Password=#{password}")
	User login(String pseudo, String password);
	
	@Update("UPDATE USER SET LastActionDate = #{date} WHERE Id=#{id}")
	void updateLastActionDate(String date, int id);
	
	@Select("SELECT count(*) FROM USER WHERE Pseudo=#{pseudo}")
	int isPseudoExisting(String pseudo);
	
	@Insert("INSERT INTO USER (Pseudo, Password, LastActionDate) VALUES (#{u.pseudo},#{u.password},u.{lastActionDate})")
	User addUser(User u);
	
	@Select("SELECT * FROM USER WHERE Id=#{id}")
	User getUser(int id);
}
