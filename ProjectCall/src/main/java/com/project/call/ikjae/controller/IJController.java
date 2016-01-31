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
import com.project.call.domain.FightResultBoard;
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
	
	//테스트 페이지
	@RequestMapping(value = "/startTest", method = RequestMethod.GET)
	public String start(HttpSession session) {
		
//		Member m = ijService.getMember("admin@ghcall.com");
//		
//		session.setAttribute("loginUser", m);
		
		return "myPage/startTest";
	}
	
	//마이페이지 메인
	@RequestMapping(value = { "/myPage" }, method = RequestMethod.GET)
	public ModelAndView myPage(Model model,
			@RequestParam("loginUser") String loginUser) {
		
		ModelAndView mav = new ModelAndView("myPage/myPage");
		
		Member member = ijService.getMember(loginUser);
		mav.addObject("member", member);
		
		List<FightBoard> fightList = ijService.getFightList(loginUser);
		mav.addObject("fightList", fightList);
		
		float winningRate =
				100 * member.getWin() / (member.getWin() + member.getLose());
		mav.addObject("winningRate", winningRate);
		
		
		return mav;

	}
	
	//회원정보 수정을 위한 비밀번호 확인
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
	
	//회원정보 수정 폼 요청
	@RequestMapping(value = "/updateMemberInfoForm", method = RequestMethod.POST)
	public String updateMemberInfoForm(Model model,
			@RequestParam("loginUser") String loginUser) {
		
		Member member = ijService.getMember(loginUser);
		model.addAttribute("member", member);
		
		return "myPage/updateMemberInfoForm";
		
	}
	
	//회원정보 수정 실행
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
	
	//회원정보 수정 시 별명 중복체크
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
	
	//회원 탈퇴
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
	
	@RequestMapping(value = "/addFightResultBoardForm", method = RequestMethod.GET)
	public String addResultForm(Model model,
			@RequestParam("fightNumber") String fightNumber) {
		
		FightBoard fight = ijService.getFight(Integer.parseInt(fightNumber));
		model.addAttribute("fight", fight);
		
		return "fightBoard/addFightResultBoardForm";
		
	}
	
	//승부결과게시판 글 등록
	@RequestMapping(value = "/addFightResultBoardResult", method = RequestMethod.POST)
	public ModelAndView updateMemberInfoResult(ModelAndView mav, HttpSession session,
			HttpServletRequest request,
			@RequestParam("fightNumber") String fightNumber,
			@RequestParam("title") String title,
			@RequestParam("photo")  MultipartFile multipartFile,
			@RequestParam("winner") String winner,
			@RequestParam("content") String content,
			@RequestParam("loginUser") String loginUser ) throws IllegalStateException, IOException {
		
		String filePath = request.getServletContext().getRealPath(path);
		
		ijService.addFightResultBoardResult(multipartFile, fightNumber, title, loginUser, content, filePath);
		
		RedirectView  redirectView  =  new  RedirectView("myPage?loginUser=" + (String)session.getAttribute("loginUser"));
		mav  =  new ModelAndView(redirectView);
		
		return mav;
		
	}
	
	//승부결과 게시판 리스트
	@RequestMapping(value = { "/fightResultBoardList" }, method = RequestMethod.GET)
	public String fightResultBoardList(Model model) {
		
		List<FightResultBoard> fightResultBoardList = ijService.getFightResultBoardList();
		model.addAttribute("fightResultBoardList", fightResultBoardList);
		
		return "fightBoard/fightResultBoardList";

	}
	
	//승부결과 글 내용
	@RequestMapping(value = { "/fightResultBoardContent" }, method = RequestMethod.GET)
	public String fightResultBoardContent(Model model,
			@RequestParam("loginUser") String loginUser) {
		
		ModelAndView mav = new ModelAndView("myPage/myPage");
		
		Member member = ijService.getMember(loginUser);
		mav.addObject("member", member);
		
		List<FightBoard> fightList = ijService.getFightList(loginUser);
		mav.addObject("fightList", fightList);
		
		float winningRate =
				100 * member.getWin() / (member.getWin() + member.getLose());
		mav.addObject("winningRate", winningRate);
		
		
		return "";

	}
	
	
}



















