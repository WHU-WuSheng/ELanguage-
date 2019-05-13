package com.zzz.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zzz.springboot.entity.Chat;
import com.zzz.springboot.entity.NewInfo;

@Mapper
public interface IChatMapper {
	@Select("select * from chat where sender=#{sender} and receiver=#{receiver} or sender=#{receiver} and receiver=#{sender} order by id asc")
	List<Chat> selectChat(@Param("sender") String sender, @Param("receiver") String receiver) throws Exception;

//	@Select("select distinct contact from (select contact from (select receiver as contact,id from chat where sender=#{username} union select sender as contact,id from chat where receiver=#{username}) as contactlist order by id desc) as norepeat")
//	@Select("select teacher as contact from record where state=2 and student=#{username} union select student as contact from record where state=2 and teacher=#{username}")
	@Select("select sender as username,count(*) as count from chat where sender in (select teacher as contact from record where state=2 and student=#{username} union select student as contact from record where state=2 and teacher=#{username}) and state='1' group by sender")
	List<NewInfo> selectNewinfos(String username) throws Exception;

	@Select("select teacher as contact from record where state=2 and student=#{username} union select student as contact from record where state=2 and teacher=#{username}")
	List<String> selectContact(String username) throws Exception;
	
	@Insert("insert into chat(sender,receiver,time,content,state)\r\n"
			+ "		values(#{sender},#{receiver},#{time},#{content},#{state})")
	void insertChat(Chat chat) throws Exception;
	
	@Update("update chat set state='0' where sender=#{0} and receiver=#{1}")
	void hasRead(String from, String to) throws Exception;
}
