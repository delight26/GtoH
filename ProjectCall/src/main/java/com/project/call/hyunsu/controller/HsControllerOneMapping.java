package com.project.call.hyunsu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
		return "redirect:ask";
	}
	
	@RequestMapping(value="/fightresultmyself", method=RequestMethod.POST)
	public String fightResultMySelf(HttpServletRequest request, HttpSession session){
		System.out.println("result : " + request.getParameter("result") + "\t fightNumber : " + request.getParameter("fightNumber"));
		service.fightResultMySelf(request, session);
		return	"redirect:/";
	}
	
	@RequestMapping(value = { "/fightResultBoardList" }, method = RequestMethod.GET)
	   public String fightResultBoardList(HttpServletRequest request, HttpSession session, Model model) {
	      /*
	      List<FightResultBoard> fightResultBoardList = ijService.getFightResultBoardList(pageNum);
	      model.addAttribute("fightResultBoardList", fightResultBoardList);
	      model.addAttribute("pageNum",pageNum);	      
	      */
			service.fightResultBoardList(request, session, model);		
		
			return "index.jsp?body=fightBoard/fightResultBoardList";
	   }
	
	@RequestMapping(value = { "/mainfightResultBoardList" }, method = RequestMethod.GET)
	   public String mainfightResultBoardList(HttpServletRequest request, HttpSession session, Model model) {
	      /*
	      List<FightResultBoard> fightResultBoardList = ijService.getFightResultBoardList(pageNum);
	      model.addAttribute("fightResultBoardList", fightResultBoardList);
	      model.addAttribute("pageNum",pageNum);	      
	      */
			service.fightResultBoardList(request, session, model);		
		
			return "fightBoard/fightResultBoardList";
	   }
	
	@RequestMapping(value = { "/fightResultBoardContent" }, method = RequestMethod.GET)
	public String fightResultBoardContent(HttpServletRequest request, Model model) {
		
/*		FightResultBoard frb = ijService.getFightResultBoard(Integer.parseInt(no));
		model.addAttribute("frb", frb);
		ijService.hitUp(Integer.parseInt(no));
		FightBoard fight = ijService.getFight(frb.getFightNumber());
		model.addAttribute("fight", fight);*/
		service.getFigthResultContent(request, model);
		
		
		return "index.jsp?body=fightBoard/fightResultBoardContent";

	}
	
	@RequestMapping(value = "/updateMemberInfoResult", method = RequestMethod.POST)
	public String updateMemberInfoResult(MultipartHttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		String path = request.getServletContext().getRealPath(filePath);
		String email = service.updateMemberMyInfo(request, response, session, path);

		return "redirect:myPage?loginUser=" + email;		
	}
	
	
	
	
	
	
}
