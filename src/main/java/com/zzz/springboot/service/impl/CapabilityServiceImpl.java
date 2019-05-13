package com.zzz.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzz.springboot.dao.ICapabilityMapper;
import com.zzz.springboot.entity.Capability;
import com.zzz.springboot.exception.CustomException;
import com.zzz.springboot.service.ICapabilityService;

/**
 * <p>Title: CapabilityService</p>
 * <p>Description: </p>
 * <p>Company: com.zzz</p>
 * @author 张志铮
 * @date 2019年3月30日 下午3:29:39
 */
@Service
public class CapabilityServiceImpl implements ICapabilityService {
	@Autowired
	private ICapabilityMapper iCapabilityMapper;

	@Override
	public void addCapability(Capability capability) throws Exception {
		// TODO 自动生成的方法存根
		if (this.iCapabilityMapper.selectCapabilityByUsernameAndLanguage(capability.getUsername(),
				capability.getLanguage()) != null)
			throw new CustomException("你已发布过教学" + capability.getLanguage() + "的技能");
		this.iCapabilityMapper.insertCapability(capability);
	}

	@Override
	public List<Capability> selectCapabilityByLanguageAndLevel(String language, int level) throws Exception {
		// TODO 自动生成的方法存根
		return this.iCapabilityMapper.selectCapabilityByLanguageAndLevel(language, level);
	}

	@Override
	public List<Capability> selectCapabilityByUsername(String username) throws Exception {
		// TODO 自动生成的方法存根
		return this.iCapabilityMapper.selectCapabilityByUsername(username);
	}

	@Override
	public void modify(Capability capability) throws Exception {
		// TODO 自动生成的方法存根
		this.iCapabilityMapper.updateCapability(capability);
	}

	@Override
	public void deleteOneCapability(String username, String language) throws Exception {
		// TODO 自动生成的方法存根
		this.iCapabilityMapper.deleteCapabilityByUsernameAndLanguage(username, language);
	}

	@Override
	public void deleteAllCapability(String username) throws Exception {
		// TODO 自动生成的方法存根
		this.iCapabilityMapper.deleteAllCapability(username);
	}

	@Override
	public Capability selectCapabilityByUsernameAndLanguage(String username, String language) throws Exception {
		// TODO Auto-generated method stub
		return iCapabilityMapper.selectCapabilityByUsernameAndLanguage(username, language);
	}



}
