package com.project.call.hyunsu.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		return "member/terms";
	}
	
	@RequestMapping(value="/addMember/step2")
	public String addMemberSetp2(){
		return "member/join";
	}
	
	@RequestMapping(value="/addMember/idCheck",method=RequestMethod.POST)
	public void addMemberCheckId(HttpServletRequest request, HttpServletResponse response) throws IOException{
		service.checkMemberId(request, response);
	}
	
	
	
	@RequestMapping("/addMember/emailCheck")
	public void emailCheck(HttpServletRequest request, HttpSession session) throws Exception{
		String emailSendCode = service.emailCheck(request);
		
		session.setAttribute("emailSendCode", emailSendCode);
		
	}
	
	
}
