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
	private IJService jBService;
	
	public void setjBService(IJService jBService) {
		this.jBService = jBService;
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
		
		Member member = jBService.getMember(loginUser);
		mav.addObject("member", member);
		
		List<FightBoard> fightList = jBService.getFight(loginUser);
		mav.addObject("fightList", fightList);
		
		float winningRate =
				100 * member.getWin() / (member.getWin() + member.getLose());
		mav.addObject("winningRate", winningRate);
		
		
		return mav;

	}
	
	@RequestMapping(value = "updateMemberInfoForm", method = RequestMethod.GET)
	public String updateMemberInfoForm(Model model,
			@RequestParam("loginUser") String loginUser) {
		
		Member member = jBService.getMember(loginUser);
		model.addAttribute("member", member);
		
		return "myPage/updateMemberInfoForm";
		
	}
	
	
	
}
