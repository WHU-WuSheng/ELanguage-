package com.zzz.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzz.springboot.entity.Capability;
import com.zzz.springboot.service.ICapabilityService;

@Controller
@RequestMapping("/capability")
public class CapabilityController {
	@Autowired
	private ICapabilityService iCapabilityService;

	@RequestMapping(value = "addCapability", method = RequestMethod.POST)
	public String addCapability(Capability capability) throws Exception {
		iCapabilityService.addCapability(capability);
		return "addaddCapabilitySuccess";
	}

	@RequestMapping(value = "selectCapabilityByLanguageAndLevel", method = RequestMethod.POST)
	public String selectCapabilityByLanguageAndLevel(String language, int level, ModelMap modelMap) throws Exception {
		List<Capability> capabilities = iCapabilityService.selectCapabilityByLanguageAndLevel(language, level);
		modelMap.put("capabilities", capabilities);
		return "selectCapabilityShow";
	}

	@RequestMapping(value = "modifyCapability", method = RequestMethod.POST)
	public String modifyCapability(Capability capability) throws Exception {
		iCapabilityService.modify(capability);
		return "personCapabilityShow";
	}

	@RequestMapping(value = "show/{username}")
	public String selectCapabilityByUsername(@PathVariable("username") String username, ModelMap modelMap)
			throws Exception {
		modelMap.put("capabilities", iCapabilityService.selectCapabilityByUsername(username));
		return "capability";
	}

	@RequestMapping(value = "deleteOne/{username}/{language}", method = RequestMethod.POST)
	public String deleteOneCapability(@PathVariable("username") String username,
			@PathVariable("language") String language) throws Exception {
		iCapabilityService.deleteOneCapability(username, language);
		return "redirect:/capability/show/" + username;
	}

	@RequestMapping(value = "deleteAll/{username}", method = RequestMethod.POST)
	public String deleteAllCapability(@PathVariable("username") String username) throws Exception {
		iCapabilityService.deleteAllCapability(username);
		return "redirect:/capability/show/" + username;
	}
}
