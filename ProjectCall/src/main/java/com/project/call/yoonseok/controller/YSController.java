package com.project.call.yoonseok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public List<Member> getMemberLanking(){
		jBService.ranking();
		
		
		
		return null;
		
	}
	
	
	
	
	
	
	
	
}
