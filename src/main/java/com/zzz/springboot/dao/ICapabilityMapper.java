package com.zzz.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zzz.springboot.entity.Capability;

@Mapper
public interface ICapabilityMapper {
	@Select("select * from capability where username=#{username} order\r\n" + "		by level desc")
	List<Capability> selectCapabilityByUsername(String username) throws Exception;

	@Select("select * from capability where language=#{language} and\r\n"
			+ "		level>=#{level} order by level desc")
	List<Capability> selectCapabilityByLanguageAndLevel(@Param("language") String language, @Param("level") int level)
			throws Exception;

	@Insert("insert into\r\n" + "		capability(username,language,level)\r\n"
			+ "		values(#{username},#{language},#{level})")
	void insertCapability(Capability capability) throws Exception;

	@Update("update capability\r\n" + "		set level=#{level} where\r\n"
			+ "		username=#{username} and language=#{language}")
	void updateCapability(Capability capability) throws Exception;

	@Delete("delete from capability where username=#{username} and\r\n" + "		language=#{language}")
	void deleteCapabilityByUsernameAndLanguage(@Param("username") String username, @Param("language") String language)
			throws Exception;

	@Delete("delete from\r\n" + "		capability where username=#{username}")
	void deleteAllCapability(String username) throws Exception;

	@Select("select * from capability where username=#{username} and\r\n" + "		language=#{language}")
	Capability selectCapabilityByUsernameAndLanguage(@Param("username") String username,
			@Param("language") String language) throws Exception;
}
