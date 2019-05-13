package com.zzz.springboot.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zzz.springboot.entity.Record;

@Mapper
public interface IRecordMapper {
	@Select("<script>select *\r\n" + "		from record where teacher=#{teacher} and\r\n" + "		state=#{state}\r\n"
			+ "		<if test=\"state==0 or state==1\">\r\n" + "			order by applytime desc\r\n"
			+ "		</if>\r\n" + "		<if test=\"state==2\">\r\n" + "			order by starttime desc\r\n"
			+ "		</if>\r\n" + "		<if test=\"state==3\">\r\n" + "			order by endtime desc\r\n"
			+ "		</if></script>")
	List<Record> selectRecordByTeacherAndState(@Param("teacher") String teacher, @Param("state") int state)
			throws Exception;

	@Select("<script>select * from record where student=#{student} and\r\n" + "		state=#{state}\r\n"
			+ "		<if test=\"state==0 or state==1\">\r\n" + "			order by applytime desc\r\n"
			+ "		</if>\r\n" + "		<if test=\"state==2\">\r\n" + "			order by starttime desc\r\n"
			+ "		</if>\r\n" + "		<if test=\"state==3\">\r\n" + "			order by endtime desc\r\n"
			+ "		</if></script>")
	List<Record> selectRecordByStudentAndState(@Param("student") String student, @Param("state") int state)
			throws Exception;

	@Insert("insert into\r\n"
			+ "		record(teacher,student,applytime,starttime,endtime,remark,money,star,state,language)\r\n"
			+ "		values(#{teacher},#{student},#{applyTime},#{startTime},#{endTime},#{remark},#{money},#{star},#{state},#{language})")
	void insertRecord(Record record) throws Exception;

	@Update("update record set\r\n" + "		remark=#{remark},star=#{star},state=#{state} where teacher=#{teacher}\r\n"
			+ "		and student=#{student} and DATE_FORMAT(applytime, '%Y-%m-%d\r\n"
			+ "		%H:%T:%s')=DATE_FORMAT(#{applyTime}, '%Y-%m-%d\r\n" + "		%H:%T:%s')")
	void updateRecord(Record record) throws Exception;

	@Select("select *\r\n" + "		from record where teacher=#{teacher} and\r\n"
			+ "		student=#{student} and DATE_FORMAT(applytime, '%Y-%m-%d\r\n"
			+ "		%H:%T:%s')=DATE_FORMAT(#{applyTime}, '%Y-%m-%d\r\n" + "		%H:%T:%s')")
	Record selectSingleRecord(@Param("teacher") String teacher, @Param("student") String student,
			@Param("applyTime") Date applyTime) throws Exception;
	
	
	
	@Select("select * from record where ( teacher=#{username} or student=#{username} ) and (state=1 or state=2)")
	List<Record> selectReadyUpdateRecord(@Param("username") String username) throws Exception;
	
	@Select("select * from record where student=#{username}")
	List<Record> selectRecordByStudent(@Param("username") String username) throws Exception;
}
