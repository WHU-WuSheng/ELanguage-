package com.zzz.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zzz.springboot.entity.Capability;

/**
 * <p>Title: ICapabilityService</p>
 * <p>Description: </p>
 * <p>Company: com.zzz</p>
 * @author 张志铮
 * @date 2019年3月27日 下午11:59:42
 */
public interface ICapabilityService {
	void addCapability(Capability capability) throws Exception;

	List<Capability> selectCapabilityByLanguageAndLevel(String language, int level) throws Exception;

	List<Capability> selectCapabilityByUsername(String username) throws Exception;
	
	Capability selectCapabilityByUsernameAndLanguage(String username,String language) throws Exception;

	void modify(Capability capability) throws Exception;

	void deleteOneCapability(String username, String language) throws Exception;

	void deleteAllCapability(String username) throws Exception;
}
