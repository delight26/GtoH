package com.project.call.hyunsu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.call.hyunsu.service.HSService;

@Controller
@RequestMapping(value="/hyunsu")
public class HSController {
	
	@Autowired
	private HSService service;
	
	public void setService(HSService service) {
		this.service = service;
	}
	
	@RequestMapping(value="/addMember/step1")
	public String addMemberStep1(){
		return "member/test2";
	}
	
	@RequestMapping(value="/addMember/step2")
	public String addMemberSetp2(){
		return "home";
	}
}
