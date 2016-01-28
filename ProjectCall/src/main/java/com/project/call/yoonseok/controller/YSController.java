package com.project.call.yoonseok.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.call.domain.Member;
import com.project.call.yoonseok.service.YSService;

@Controller
public class YSController {
	
	@Autowired
	private YSService jBService;
	
	public void setjBService(YSService jBService) {
		this.jBService = jBService;
	}
	
	@RequestMapping("YSLanking")
	public ModelAndView getMemberLanking(){
		List<Member> lankingList = jBService.ranking();
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> model = new HashMap<String, Object>();
		model.put("lankingList", lankingList);
		modelAndView.addAllObjects(model);
		modelAndView.setViewName("ys/lankingList");
		return modelAndView;
		
	}
	
	
	
	
	
	
	
	
}
