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
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpSession session) {
		
		Member m = ijService.getMember("admin@ghcall.com");
//		Member m = ijService.getMember("bb@naver.com");
		
		session.setAttribute("loginUser", m);
		
		return "index.jsp?body=myPage/startTest";
	}
	
	//마이페이지 메인
	@RequestMapping(value = { "/myPage" }, method = RequestMethod.GET)
	public ModelAndView myPage(Model model, HttpServletRequest request,
			@RequestParam("loginUser") String loginUser) {
		ModelAndView mav = new ModelAndView("index.jsp?body=myPage/myPage");
		
		Member member = ijService.getMember(loginUser);
		mav.addObject("member", member);
		
		ijService.getFightList(loginUser, request);
		float winningRate=0;
		if(member.getWin() + member.getLose()!=0){
			winningRate =
					100 * member.getWin() / (member.getWin() + member.getLose());
		}else{
			winningRate=0;
		}
		
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
			MultipartHttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		
		
		String filePath = request.getServletContext().getRealPath(path);
		
		Member member = ijService.updateMember(request, response, filePath, session);
		
		RedirectView  redirectView  =  new  RedirectView("index.jsp?body=myPage?loginUser=" + member.getEmail());
		mav  =  new ModelAndView(redirectView);
		
		return mav;
		
	}
	
	//회원정보 수정 시 별명 중복체크
	@RequestMapping(value = "/checkNickName", method = RequestMethod.POST)
	public void checkNickName(Model model,
			HttpServletResponse res,
			@RequestParam("nickName") String nickName)  throws IOException {
		
		int count = ijService.nickNameCheck(nickName);
		
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
			@RequestParam("loginUser") String loginUser) throws IllegalStateException, IOException {
		String filePath = request.getServletContext().getRealPath(path);
		
		ijService.addFightResultBoardResult(multipartFile, fightNumber, title, loginUser, content, winner, filePath);
		
		RedirectView  redirectView  =  new  RedirectView("myPage?loginUser="+loginUser);
		mav  =  new ModelAndView(redirectView);
		
		return mav;
		
	}
	
	//승부결과 게시판 리스트
	@RequestMapping(value = { "/fightResultBoardList" }, method = RequestMethod.GET)
	   public String fightResultBoardList(Model model,
	         @RequestParam("pageNum") int pageNum) {
	      
	      List<FightResultBoard> fightResultBoardList = ijService.getFightResultBoardList(pageNum);
	      model.addAttribute("fightResultBoardList", fightResultBoardList);
	      model.addAttribute("pageNum",pageNum);
	      System.out.println(fightResultBoardList.get(0).getPageSize());
	      return "index.jsp?body=fightBoard/fightResultBoardList";

	   }
	
	//승부결과 글 내용 가져오는 컨트롤러
	@RequestMapping(value = { "/fightResultBoardContent" }, method = RequestMethod.GET)
	public String fightResultBoardContent(Model model,
			@RequestParam("no") String no) {
		
		FightResultBoard frb = ijService.getFightResultBoard(Integer.parseInt(no));
		model.addAttribute("frb", frb);
		ijService.hitUp(Integer.parseInt(no));
		FightBoard fight = ijService.getFight(frb.getFightNumber());
		model.addAttribute("fight", fight);
		
		
		return "index.jsp?body=fightBoard/fightResultBoardContent";

	}
	
	//승부 결과 승인
	@RequestMapping(value = { "/confirmFightResult" }, method = RequestMethod.GET)
	public String confirmFightResult(Model model,
			@RequestParam("no") String no) {
		
		ijService.adminConfirm(Integer.parseInt(no));
		
		return "redirect:fightResultBoardList";

	}
	
	//승부 결과 반려
	@RequestMapping(value = { "/denyFightResult" }, method = RequestMethod.POST)
	public String denyFightResult(Model model,
			@RequestParam("no") String no,
			@RequestParam("message") String message,
			@RequestParam("writer") String writer) {
		
		System.out.println(writer + " 에게 " + no +"번 글에 대해 반려 쪽지보내기 실행");
		System.out.println("message 내용 : " + message);
		return "redirect:fightResultBoardList";
		
	}
	
	//회원정보 수정 폼 요청
		@RequestMapping(value = "/updateFightResultBoardForm", method = RequestMethod.GET)
		public String updateFightResultBoardForm(Model model,
				@RequestParam("no") String no,
				@RequestParam("fightNumber") String fightNumber) {
			
			FightResultBoard frb = ijService.getFightResultBoard(Integer.parseInt(no));
			model.addAttribute("frb", frb);
			
			FightBoard fight = ijService.getFight(Integer.parseInt(fightNumber));
			model.addAttribute("fight", fight);
			
			return "index.jsp?body=fightBoard/updateFightResultBoardForm";
			
		}
	
	//승부 결과 수정
	@RequestMapping(value = "/updateFightResultBoardResult", method = RequestMethod.POST)
	public ModelAndView updateFightResultBoardResult(ModelAndView mav, HttpSession session,
			HttpServletRequest request,
			@RequestParam("fightNumber") String fightNumber,
			@RequestParam("title") String title,
			@RequestParam("photo")  MultipartFile multipartFile,
			@RequestParam("winner") String winner,
			@RequestParam("content") String content,
			@RequestParam("loginUser") String loginUser) throws IllegalStateException, IOException {
		
		String filePath = request.getServletContext().getRealPath(path);
		
		ijService.updateFightResultBoardResult(multipartFile, fightNumber, title, loginUser, content, winner, filePath);
		
		RedirectView  redirectView  =  new  RedirectView("index.jsp?body=myPage?loginUser=" + (String)session.getAttribute("loginUser"));
		mav  =  new ModelAndView(redirectView);
		
		return mav;
		
	}
	
	@RequestMapping(value = "/deleteFightResultBoard", method = RequestMethod.GET)
	public ModelAndView deleteFightResultBoard(ModelAndView mav, HttpSession session,
			HttpServletRequest request,
			@RequestParam("no") String no) throws IllegalStateException, IOException {
		
		ijService.deleteFightResultBoard(Integer.parseInt(no));
		
		RedirectView  redirectView  =  new  RedirectView("fightResultBoardList");
		mav  =  new ModelAndView(redirectView);
		
		return mav;
		
	}
	
	
	
	
	
	
	
}



















