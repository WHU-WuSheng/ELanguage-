package com.zzz.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zzz.springboot.entity.User;

/**
 * <p>Title: IUserService</p>
 * <p>Description: </p>
 * <p>Company: com.zzz</p>
 * @author 张志铮
 * @date 2019年3月28日 上午12:00:04
 */
public interface IUserService {
	void register(User user) throws Exception;

	User login(String user, String password) throws Exception;

	User selectUserByUsername(String username) throws Exception;

	User selectUserByEmail(String email) throws Exception;

	User selectUserByPhone(String phone) throws Exception;

	void modify(User user) throws Exception;

	List<User> selectUserByCredit(int credit) throws Exception;

}
