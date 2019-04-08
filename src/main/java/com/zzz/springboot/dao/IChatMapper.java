package com.zzz.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zzz.springboot.entity.Chat;

@Mapper
public interface IChatMapper {
	@Select("select * from chat where sender=#{sender} and receiver=#{receiver} or sender=#{receiver} and receiver=#{sender} order by id asc")
	List<Chat> selectChat(@Param("sender") String sender, @Param("receiver") String receiver) throws Exception;

	@Select("select distinct contact from (select contact from (select receiver as contact,id from chat where sender=#{username} union select sender as contact,id from chat where receiver=#{username}) as contactlist order by id desc) as norepeat")
	List<String> selectContact(String username) throws Exception;

	@Insert("insert into chat(sender,receiver,time,content)\r\n"
			+ "		values(#{sender},#{receiver},#{time},#{content})")
	void insertChat(Chat chat) throws Exception;
}
