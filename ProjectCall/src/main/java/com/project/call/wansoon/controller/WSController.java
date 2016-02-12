package com.project.call.wansoon.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.project.call.domain.Comment;
import com.project.call.domain.FreeBoard;
import com.project.call.wansoon.service.WSService;


@Controller
public class WSController {

	@Autowired
	private WSService service;

	private static final String filePath = "/resources/uploadimages/";

	public void setjBService(WSService service) {
		this.service = service;
	}

	@RequestMapping(value ="FreeBoardList")
	public String freeboardList(HttpServletRequest request, HttpSession session) {
		service.getFreeBoardList(request);
		
		return "index.jsp?body=freeB/FreeBoardList";

	}

	@RequestMapping(value = { "FreeBoardContent" })
	public ModelAndView Content(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int frbNo = Integer.parseInt((request.getParameter("frbNo")));
		FreeBoard frb = service.getFreeBoard(frbNo);

		Map<String, Object> model = new HashMap<String, Object>();

		ModelAndView mav = new ModelAndView();

		model.put("frb", frb);
		mav.setViewName("freeB/FreeBoardContent");
		mav.addAllObjects(model);

		return mav;
	}

	@RequestMapping("/freewrite")
	public String addForm(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("loginUser") == null) {
			return "redirect:loginform?page=aggro";
		}else{ 
			service.getFreeBoardList(request);
			return "freeB/FreeBoardWrite";
		}
	}

	@RequestMapping(value = "/addWrite")
	public String addForm(String multipart) {

		String result = null;

		if (multipart.equals("request")) {
			result = "index.jsp?body=FreeBoardWrite";
		}
		return result;
	}

	@RequestMapping(value = "FreeBoardWrite", method = RequestMethod.POST)
	public String addResult(HttpSession session, HttpServletRequest request,
			@RequestParam("photo") MultipartFile photo1) throws IOException {

		FreeBoard frb = new FreeBoard();

		frb.setFrbTitle(request.getParameter("title"));
		frb.setFrbPass(request.getParameter("pass"));
		frb.setFrbContent(request.getParameter("content"));
		frb.setFrbArea("free");
		frb.setFrbEmail(request.getParameter("email"));
		frb.setFrbWriter(request.getParameter("writer"));
		
		if(!photo1.isEmpty()){
				
			frb.setPhoto1(photo1.getOriginalFilename());
			String path = request.getServletContext().getRealPath(filePath);
			String photoPath = photo1.getOriginalFilename();
			File file = new File(photoPath);
			photo1.transferTo(file);
	
		}else{
			frb.setPhoto1("");
		}
		service.insertWrite(frb);
		return redirectPrefix();
	}

	@RequestMapping("/modifyForm")
	public String modifyForm(HttpServletRequest request, Model model) {

		int frbNo = Integer.parseInt((request.getParameter("frbNo")));
		FreeBoard frb = service.getFreeBoard(frbNo);
		model.addAttribute("frb", frb);

		return "index.jsp?body=freeB/FreeBoardModify";
	}

	@RequestMapping(value = "FreeBoardModify", method = RequestMethod.POST)
	public String Modify(HttpSession session, HttpServletRequest request, @RequestParam("photo") MultipartFile photo1)
			throws IOException {

		FreeBoard frb = new FreeBoard();

		frb.setFrbNo(Integer.parseInt(request.getParameter("no")));
		frb.setFrbTitle(request.getParameter("title"));
		frb.setFrbContent(request.getParameter("content"));
		frb.setPhoto1(filePath + photo1.getOriginalFilename());
		frb.setFrbArea(request.getParameter("area"));

		String path = request.getServletContext().getRealPath(filePath);
		String photoPath = path + photo1.getOriginalFilename();

		File file = new File(photoPath);
		photo1.transferTo(file);

		service.modifyWrite(frb, path);

		return redirectPrefix();
	}

	@RequestMapping(value = "deleteBoard")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int frbNo = Integer.parseInt(request.getParameter("frbNo"));
		service.deleteBoard(frbNo);
		RedirectView redirectView = new RedirectView("FreeBoardList");
		ModelAndView mav = new ModelAndView(redirectView);
		return mav;
	}

	@RequestMapping(value = { "/ListAllComment" }, method = RequestMethod.POST)
	public ModelAndView ListAllComment(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("bno") String bno) throws IOException {

		ModelAndView mav = new ModelAndView();
		List<Comment> fbcList = service.commentAllList(Integer.parseInt(bno));
		mav.addObject("fbcList", fbcList);
		mav.setViewName("freeB/ajaxComment");
		return mav;

	}

	@RequestMapping(value = { "/AddComment" }, method = RequestMethod.POST)
	public ModelAndView AddComment(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("loginUser") String loginUser, @RequestParam("bno") String bno,
			@RequestParam("comment") String comment) throws IOException {

		ModelAndView mav = new ModelAndView();
		Comment fbc = new Comment();
		System.out.println("bno : " + bno);
		fbc.setbNo(Integer.parseInt(bno));
		fbc.setcEmail(loginUser);
		fbc.setcContent(comment);
		fbc.setWriteDate(new Timestamp(System.currentTimeMillis()));

		service.addComment(fbc);

		List<Comment> fbcList = service.commentAllList(Integer.parseInt(bno));

		mav.addObject("fbcList", fbcList);

		mav.setViewName("freeB/ajaxComment");

		return mav;

	}

	@RequestMapping(value = { "/modifyComment" }, method = RequestMethod.POST)
	public void ModifyComment(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		service.modifyComment(request, response, session);
	}

	private String redirectPrefix() {
		return "redirect:/FreeBoardList";
	}
	
	@RequestMapping(value = "freedelete")
	public String aggroDelete(HttpServletRequest request) {
		service.freeDelete(request);
		return "redirect:FreeBoardList";
	}
	
	//자유 게시판 검색
	@RequestMapping(value="freesearch")
	public String aggroSearch(HttpServletRequest request){
		service.freeSearch(request);
		
		return "index.jsp?body=freeB/FreeList";
	}
	
	//자유게시판 수정
	@RequestMapping(value = "freeupdate")
	public String aggroUpdateForm(HttpServletRequest request) {
		service.freeUpdateForm(request);

		return "freeB/freeupdate";
	}
	
	// 도발 게시판 수정 결과
	@RequestMapping(value = "freeupdateresult")
	public String agrroUpdateResult(MultipartHttpServletRequest request) throws IOException {
		String path = request.getServletContext().getRealPath(filePath);
		service.freeUpdateResult(request, path);

		return "redirect:FreeBoardList";
	}
	
	// 자유 게시판 내용
	@RequestMapping(value = "freecontent")
	public String freeContent(HttpServletRequest request) {
		service.freeContent(request);

		return "index.jsp?body=freeB/freecontent";
	}

	// 자유 게시판 이전글
	@RequestMapping(value = "freepre")
	public String aggroPreContent(HttpServletRequest request) {
		service.freePreContent(request);
		System.out.println(request.getAttribute("frb"));
		if (request.getAttribute("frb") == null) {
			request.setAttribute("message", "최신글 입니다.");
			request.setAttribute("returnUrl", "javascript:history.back()");
			return "alertAndRedirect";
		}
		return "index.jsp?body=freeB/freecontent";
	}
	
	// 도발 게시판 다음글
	@RequestMapping(value = "freenext")
	public String aggroNextContent(HttpServletRequest request) {
		service.freeNextContent(request);
		System.out.println(request.getAttribute("frb"));
		if (request.getAttribute("frb") == null) {
			request.setAttribute("message", "마지막 글 입니다.");
			request.setAttribute("returnUrl", "javascript:history.back()");
			return "alertAndRedirect";
		}
		return "index.jsp?body=freeB/freecontent";
	}

	

}
