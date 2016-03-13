package com.project.call.hyunsu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.Area;
import com.project.call.hyunsu.service.HSService;

@Controller
@RequestMapping(value="/hyunsu")
public class HSController {
	
	@Autowired
	private HSService service;
	
	public void setService(HSService service) {
		this.service = service;
	}
	
	private static final String filePath = "/resources/uploadimages/";
	
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
	
	@RequestMapping(value="/addMember/emailCheck",method=RequestMethod.POST)
	public void emailCheck(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		service.emailCheck(request, response, session);
			
	}
	
	@RequestMapping(value="/addMember/getSendCodeCheck",method=RequestMethod.POST)
	public void getSendCodeCheck(HttpServletRequest request, HttpSession session,HttpServletResponse response) throws Exception{
		service.getSendCodeCheck(request, response, session);
	}
	
	@RequestMapping(value="/addMember/step3", method=RequestMethod.POST)
	public String addMemberStep3(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Model model) throws Exception{
		service.addMember(request, response, session);
		List<Area> areaList = service.getAreaList();
		model.addAttribute("areaList",areaList);
		return "member/additional";
	}
	
	@RequestMapping(value="/addMember/step4", method=RequestMethod.POST)
	public String addMemberStep4(MultipartHttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception{
		String path = request.getServletContext().getRealPath(filePath);
		service.additionalInformationMember(request, response, session, path);
		return "redirect:/";
	}
	
	
	@RequestMapping(value="/addMember/nickNameCheck",method=RequestMethod.POST)
	public void nickNameCheck(HttpServletRequest request, HttpServletResponse response) throws Exception{
		service.nickNameCheck(request, response);			
	}
	/*
	@RequestMapping(value="/ask/new",method=RequestMethod.GET)
	public String newAsk() throws Exception{
		return "ask/newAsk";			
	}	
	
	@RequestMapping(value="/ask/searchNickName",method=RequestMethod.POST)
	public void searchNickName(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		service.searchNickName(request, response, model);
	}	
	
	@RequestMapping(value="ask/addAsk", method=RequestMethod.POST)
	public String addAsk(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		service.addAsk(request, response, session);
		return "home";
	}
	*/
	
	
	// 테스트용 소스
	@RequestMapping(value="/addMember/step10")
	public String addMemberStep10(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Model model) throws Exception{
		List<Area> areaList = service.getAreaList();
		model.addAttribute("areaList", areaList);
		return "member/additional ";
	}
	
	
	
}
