package com.project.call.hyunsu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.call.hyunsu.service.HSService;

@Controller
public class HsControllerOneMapping {

	@Autowired
	private HSService service;
	
	public void setService(HSService service) {
		this.service = service;
	}
	
	private static final String filePath = "/resources/uploadimages/";
	

	@RequestMapping(value="/newAsk",method=RequestMethod.GET)
	public String newAsk() throws Exception{
		return "ask/newAsk";			
	}	
	
	@RequestMapping(value="/findIdPass", method=RequestMethod.GET)
	public String findIdPass(){
		return "member/findIdPass";
	}
	
	@RequestMapping(value="/findIdPassAjax", method=RequestMethod.POST)
	public String findIdPassAjax(HttpServletRequest request, HttpServletResponse response) throws Exception{
		service.findIdPassAjax(request, response);
		return "index.jsp?body=ask/newAsk";			
	}	
	
	@RequestMapping(value="/askSearchNickName",method=RequestMethod.POST)
	public void searchNickName(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		service.searchNickName(request, response, model);
	}	
	
	@RequestMapping(value="/addAsk", method=RequestMethod.POST)
	public String addAsk(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		service.addAsk(request, response, session);
		return "home";
	}
}
