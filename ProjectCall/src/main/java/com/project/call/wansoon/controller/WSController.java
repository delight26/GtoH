package com.project.call.wansoon.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.NonWritableChannelException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.project.call.domain.FightBoard;
import com.project.call.domain.FreeBoard;
import com.project.call.domain.FreebComment;
import com.project.call.domain.Member;
import com.project.call.wansoon.dao.WSDao;
import com.project.call.wansoon.service.WSService;

@Controller
public class WSController {
	
	@Autowired
	private WSService wsService;
	
	private static final String filePath = "/resources/uploadimages/";
	
	public void setjBService(WSService wsService) {
		this.wsService = wsService;
	}
	
	

	@RequestMapping (value={"FreeBoardList"})
	public ModelAndView List(
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
				List<FreeBoard> frbList = wsService.getFreeBoardAll();	
				
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("frbList", frbList);		
		
				ModelAndView mav = new ModelAndView("freeB/FreeBoardList");
				mav.addAllObjects(model);	
				
				return mav;
	}
	
	@RequestMapping (value={"FreeBoardContent"})
	public ModelAndView Content(
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		int frbNo = Integer.parseInt((request.getParameter("frbNo")));
		FreeBoard frb = wsService.getFreeBoard(frbNo);
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		
		ModelAndView mav = new ModelAndView();
		
		model.put("frb", frb);
		mav.setViewName("freeB/FreeBoardContent");
		
		
		
		
		
		mav.addAllObjects(model);
		
		
		
		
		return mav;
	}
	
	@RequestMapping("/writeForm")
	public String addForm() {
		
		return "index.jsp?body=freeB/FreeBoardWrite";
	}
	
	@RequestMapping(value={"WriteFreeBoard"})
		public ModelAndView boardWrite(
				HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		FreeBoard frb = new FreeBoard();
		
		frb.setFrbEmail(request.getParameter("frbEmail"));
		frb.setFrbPass(request.getParameter("frbPass"));
		frb.setFrbTitle(request.getParameter("frbTitle"));
		frb.setFrbArea(request.getParameter("frbArea"));
		frb.setPhoto1(request.getParameter("photo1"));
	
		wsService.insertBoard(frb);
			
		RedirectView redirectView = new RedirectView("FreeBoardList");
		
		ModelAndView modelAndView = new ModelAndView(redirectView);		
		
		return modelAndView;
	}
		
	
	@RequestMapping(value="/add")
	public String addForm(String multipart) {
		
		String result = null;
		
		if(multipart.equals("request")) {
			result = "index.jsp?body=FreeBoardWrite";
		}
		return result;
	}
	
	@RequestMapping("/modifyForm")
	public String modifyForm(HttpServletRequest request,Model model) {
	
		int frbNo = Integer.parseInt((request.getParameter("frbNo")));
		FreeBoard frb = wsService.getFreeBoard(frbNo);
		model.addAttribute("frb",frb);
		
		return "index.jsp?body=freeB/FreeBoardModify";
	}

	
	
	@RequestMapping(value="FreeBoardModify", method=RequestMethod.POST)
		public String Modify(HttpSession session,
				HttpServletRequest request,
				@RequestParam("photo") MultipartFile photo1) throws IOException{
	
		
		FreeBoard frb = new FreeBoard();
		
		frb.setFrbNo(Integer.parseInt(request.getParameter("no")));
		frb.setFrbTitle(request.getParameter("title"));
		frb.setFrbContent(request.getParameter("content"));
		frb.setPhoto1(filePath+photo1.getOriginalFilename());
		frb.setFrbArea(request.getParameter("area"));

		String path = request.getServletContext().getRealPath(filePath);
		String photoPath = path + photo1.getOriginalFilename();
		
		File file = new File(photoPath);
		photo1.transferTo(file);
	
		wsService.modifyWrite(frb, path);
		
		return redirectPrefix();
	}
	
	@RequestMapping(value="deleteBoard")
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		int frbNo = Integer.parseInt(request.getParameter("frbNo"));
	
		wsService.deleteBoard(frbNo);
		
		RedirectView redirectView = new RedirectView("FreeBoardList");
		
		ModelAndView mav = new ModelAndView(redirectView);		
		
		return mav;
	
	
	
}
	
	@RequestMapping(value = { "/ListAllComment" }, method = RequestMethod.POST)
	public ModelAndView ListAllComment(Model model, HttpServletRequest request,
					HttpServletResponse response,
			@RequestParam("bno") String bno
			) throws IOException {

		ModelAndView mav= new ModelAndView();
		List<FreebComment> fbcList = wsService.commentAllList(Integer.parseInt(bno));
		mav.addObject("fbcList", fbcList);
		mav.setViewName("freeB/ajaxComment");
		return mav;

	}
	@RequestMapping(value = { "/AddComment" }, method = RequestMethod.POST)
	public ModelAndView AddComment(Model model, HttpServletRequest request,
					HttpServletResponse response,
			@RequestParam("loginUser") String loginUser,
			@RequestParam("bno") String bno,
			@RequestParam("comment") String comment) throws IOException {

		ModelAndView mav= new ModelAndView();
		FreebComment fbc = new FreebComment();
		System.out.println("bno : " + bno);
		fbc.setBno(Integer.parseInt(bno));
		fbc.setEmail(loginUser);
		fbc.setComment(comment);
		fbc.setWriteDate(new Timestamp(System.currentTimeMillis()));
		
		wsService.addComment(fbc);
		
		List<FreebComment> fbcList = wsService.commentAllList(Integer.parseInt(bno));
		
		mav.addObject("fbcList", fbcList);
		
		

		mav.setViewName("freeB/ajaxComment");
		
		
		return mav;

	}
	
	
	private String redirectPrefix() {
		return "redirect:/FreeBoardList";
	}
	
}
