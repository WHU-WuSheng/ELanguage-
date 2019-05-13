package com.zzz.springboot.controller;

import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzz.springboot.entity.Capability;
import com.zzz.springboot.service.ICapabilityService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	private ICapabilityService iCapabilityService;
	
    @RequestMapping(value="/chinese", produces="text/plain;charset=UTF-8")
    public String chinese(ModelMap map) {
        map.addAttribute("subject", "韩语");
        map.addAttribute("subject_js", "chinese_script.js");
        return "ch_examination";
    }

    @RequestMapping(value="/english", produces="text/plain;charset=UTF-8")
    public String english(ModelMap map) {
        map.addAttribute("subject", "English");
        return "en_examination";
    }

    @RequestMapping(value="/submit",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody JSONObject params)  throws Exception{
        //@RequestBody只能给方法的参数注解，表明一次接收到请求参数
        //JSONObject为alibaba的fastjson包下类，推荐使用。另外也可以使用String来接收前端的json格式传参。
        Number mark = params.getAsNumber("count");
        int count = mark.intValue();
        String user=params.getAsString("user");
        int grade;
        String language = params.getAsString("language");
        if(count<60){
            grade=0;
        }else if(count<80){
            grade=1;
        }else {
            grade=2;
        }
        Capability capability=new Capability();
        capability.setLanguage(language);
        capability.setLevel(grade);
        capability.setUsername(user);
        if(iCapabilityService.selectCapabilityByUsernameAndLanguage(user, language)!=null)
        {
        iCapabilityService.modify(capability);
        }else {
        	iCapabilityService.addCapability(capability);
        }
        
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        map.put("message","提交成功！");
        return map;
    }
    
    
    @RequestMapping(value="/chooseCapability", produces="text/plain;charset=UTF-8")
    public String chooseCapability(ModelMap map) {
        return "choose_subject";
    }
}
