package com.zzz.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzz.springboot.dao.IUserMapper;
import com.zzz.springboot.entity.User;
import com.zzz.springboot.service.IUserService;

/**
 * <p>Title: UserService</p>
 * <p>Description: </p>
 * <p>Company: com.zzz</p>
 * @author 张志铮
 * @date 2019年3月30日 下午3:29:49
 */
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserMapper iUserMapper;

	@Override
	public User login(String user, String password) throws Exception {
		// TODO 自动生成的方法存根
		User user2 = this.iUserMapper.selectUserByUsernameAndPassword(user, password);
		if (user2 == null)
			user2 = this.iUserMapper.selectUserByPhoneAndPassword(user, password);
		if (user2 == null)
			user2 = this.iUserMapper.selectUserByEmailAndPassword(user, password);
		return user2;
	}

	@Override
	public void modify(User user) throws Exception {
		// TODO 自动生成的方法存根
		this.iUserMapper.updateUser(user);
	}

	@Override
	public List<User> selectUserByCredit(int credit) throws Exception {
		// TODO 自动生成的方法存根
		return this.iUserMapper.selectUserByCredit(credit);
	}

	@Override
	public void register(User user) throws Exception {
		// TODO 自动生成的方法存根
		this.iUserMapper.insertUser(user);
	}

	@Override
	public User selectUserByEmail(String email) throws Exception {
		// TODO 自动生成的方法存根
		return this.iUserMapper.selectUserByEmail(email);
	}

	@Override
	public User selectUserByPhone(String phone) throws Exception {
		// TODO 自动生成的方法存根
		return this.iUserMapper.selectUserByPhone(phone);
	}

	@Override
	public User selectUserByUsername(String username) throws Exception {
		// TODO 自动生成的方法存根
		return this.iUserMapper.selectUserByUsername(username);
	}

}
