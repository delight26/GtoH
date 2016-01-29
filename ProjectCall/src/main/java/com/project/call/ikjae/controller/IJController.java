package com.project.call.ikjae.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.call.domain.FightBoard;
import com.project.call.domain.Member;
import com.project.call.ikjae.service.IJService;

@Controller
public class IJController {
	
	@Autowired
	private IJService ijService;
	
	public void setijService(IJService ijService) {
		this.ijService = ijService;
	}
	
	@RequestMapping(value = "startTest", method = RequestMethod.GET)
	public String start(HttpSession session) {
		
		session.setAttribute("loginUser", "bb@naver.com");
		
		return "myPage/startTest";
	}
	
	@RequestMapping(value = { "myPage" }, method = RequestMethod.GET)
	public ModelAndView myPage(Model model,
			@RequestParam("loginUser") String loginUser) {
		
		ModelAndView mav = new ModelAndView("myPage/myPage");
		
		Member member = ijService.getMember(loginUser);
		mav.addObject("member", member);
		
		List<FightBoard> fightList = ijService.getFight(loginUser);
		mav.addObject("fightList", fightList);
		
		float winningRate =
				100 * member.getWin() / (member.getWin() + member.getLose());
		mav.addObject("winningRate", winningRate);
		
		
		return mav;

	}
	
	@RequestMapping(value = "passwordCheck", method = RequestMethod.POST)
	public String passwordCheck(Model model,
			@RequestParam("password") String password,
			@RequestParam("loginUser") String loginUser) {
		
		System.out.println("비번 확인 시작");
		System.out.println("password : " + password);
		System.out.println("loginUser : " + loginUser);
		
		int result = ijService.passwordCheck(loginUser, password);
		model.addAttribute("result", result);
		
		return "null";
		
	}
	
	@RequestMapping(value = "updateMemberInfoForm", method = RequestMethod.GET)
	public String updateMemberInfoForm(Model model,
			@RequestParam("loginUser") String loginUser) {
		
		Member member = ijService.getMember(loginUser);
		model.addAttribute("member", member);
		
		return "myPage/updateMemberInfoForm";
		
	}
	
	
	
}



















