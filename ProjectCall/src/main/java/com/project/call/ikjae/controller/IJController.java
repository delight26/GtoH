package com.project.call.ikjae.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.project.call.domain.FightBoard;
import com.project.call.domain.Member;
import com.project.call.ikjae.service.IJService;

@Controller
public class IJController {
	
	private static final String path = "/resources/uploadimages/";
	
	@Autowired
	private IJService ijService;
	
	public void setijService(IJService ijService) {
		this.ijService = ijService;
	}
	
	@RequestMapping(value = "/startTest", method = RequestMethod.GET)
	public String start(HttpSession session) {
		
		session.setAttribute("loginUser", "bb@naver.com");
		
		return "myPage/startTest";
	}
	
	@RequestMapping(value = { "/myPage" }, method = RequestMethod.GET)
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
	
	@RequestMapping(value = "/passwordCheck", method = RequestMethod.POST)
	public String passwordCheck(Model model,
			HttpServletResponse res,
			@RequestParam("password") String password,
			@RequestParam("loginUser") String loginUser)  throws IOException {
		
		int result = ijService.passwordCheck(loginUser, password);
		
		PrintWriter out = res.getWriter();
		out.println(result);
		out.close();
		
		return null;
		
	}
	
	@RequestMapping(value = "/updateMemberInfoForm", method = RequestMethod.POST)
	public String updateMemberInfoForm(Model model,
			@RequestParam("loginUser") String loginUser) {
		
		Member member = ijService.getMember(loginUser);
		model.addAttribute("member", member);
		
		return "myPage/updateMemberInfoForm";
		
	}
	
	@RequestMapping(value = "/updateMemberInfoResult", method = RequestMethod.POST)
	public ModelAndView updateMemberInfoResult(ModelAndView mav,
			HttpServletRequest request,
			@RequestParam("photo") MultipartFile multipartFile,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("nickName") String nickName,
			@RequestParam("gender") String gender,
			@RequestParam("phone") String phone,
			@RequestParam("word") String word) throws IllegalStateException, IOException {
		
		String filePath = request.getServletContext().getRealPath(path);
		
		ijService.updateMember(multipartFile, email, password, nickName, gender, phone, word, filePath);
		
		RedirectView  redirectView  =  new  RedirectView("myPage?loginUser=" + email);
		mav  =  new ModelAndView(redirectView);
		
		return mav;
		
	}
	
	@RequestMapping(value = "/checkNickName", method = RequestMethod.POST)
	public void checkNickName(Model model,
			HttpServletResponse res,
			@RequestParam("nickName") String nickName,
			@RequestParam("loginUser") String loginUser)  throws IOException {
		
		int count = ijService.nickNameCheck(loginUser, nickName);
		
		PrintWriter out = res.getWriter();
		out.println(count);
		out.close();
		
	}
	
	@RequestMapping(value = "/deleteMember", method = RequestMethod.GET)
	public ModelAndView deleteMember(ModelAndView mav,
			HttpSession session,
			@RequestParam("loginUser") String loginUser) {
		
		ijService.deleteMember(loginUser);
		session.invalidate(); 
		
		RedirectView  redirectView  =  new  RedirectView("index");
		mav  =  new ModelAndView(redirectView);
		
		return mav;
		
	}
	
	
	
}



















