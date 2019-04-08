package com.zzz.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zzz.springboot.entity.User;

@Mapper
public interface IUserMapper {
	@Select("select username from user")
	List<String> getAllUsername() throws Exception;

	@Select("select * from user")
	List<User> getAllUser() throws Exception;

	@Select("select * from user where username=#{username}")
	User selectUserByUsername(String username) throws Exception;

	@Select("select * from user where phone=#{phone}")
	User selectUserByPhone(String phone) throws Exception;

	@Select("select * from user where email=#{email}")
	User selectUserByEmail(String email) throws Exception;

	@Select("select * from user where username=#{username} and password=#{password}")
	User selectUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password)
			throws Exception;

	@Select("select * from user where phone=#{phone} and password=#{password}")
	User selectUserByPhoneAndPassword(@Param("phone") String phone, @Param("password") String password)
			throws Exception;

	@Select("select * from user where email=#{email} and password=#{password}")
	User selectUserByEmailAndPassword(@Param("email") String email, @Param("password") String password)
			throws Exception;

	@Select("select * from user where credit>=#{credit} order by credit desc")
	List<User> selectUserByCredit(int credit) throws Exception;

	@Insert("insert into\r\n" + "		user(username,password,phone,email,profile,picture,sex,age)\r\n"
			+ "		values(#{username},#{password},#{phone},#{email},#{profile},#{picture},#{sex},#{age})")
	void insertUser(User user) throws Exception;

	@Update("update user set\r\n"
			+ "		password=#{password},phone=#{phone},email=#{email},profile=#{profile},picture=#{picture},credit=#{credit},money=#{money},sex=#{sex},age=#{age}\r\n"
			+ "		where username=#{username}")
	void updateUser(User user) throws Exception;
}
