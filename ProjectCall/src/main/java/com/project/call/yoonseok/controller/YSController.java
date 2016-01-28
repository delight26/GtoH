package com.project.call.yoonseok.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.call.domain.Member;
import com.project.call.domain.NoticeBoard;
import com.project.call.yoonseok.service.YSService;

@Controller
public class YSController {
	
	@Autowired
	private YSService jBService;
	
	public void setjBService(YSService jBService) {
		this.jBService = jBService;
	}
	
	@RequestMapping("YSLanking")
	public ModelAndView getMemberLanking(){
		List<Member> lankingList = jBService.ranking();
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> model = new HashMap<String, Object>();
		model.put("lankingList", lankingList);
		modelAndView.addAllObjects(model);
		modelAndView.setViewName("ys/lankingList");
		return modelAndView;
		
	}
	
	@RequestMapping("YSaddNote")
	public String addNote(HttpServletRequest request){
		NoticeBoard note = new NoticeBoard();
		note.setNbContent(request.getParameter("content"));
		note.setNbTitle(request.getParameter("title"));
		note.setNbEmail(request.getParameter("email"));
		note.setNbToid(request.getParameter("toid"));
		jBService.addNote(note);
		//again <- 창닫는 값
		int again = 1;
		request.setAttribute("again",again );
		return "ys/addNote";
		
	}
	
	
	@RequestMapping("YSAddNoteForm")
	public String addNoteForm(HttpServletRequest request){
		int again = 0;
		request.setAttribute("again",again );
		return "ys/addNote";
		
	}
	
	
	@RequestMapping("YSGetNote")
	public ModelAndView getNote(HttpServletRequest request){
		String toid = request.getParameter("toid");
		List<NoticeBoard> noteList = jBService.getNote(toid);
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> model = new HashMap<String, Object>();
		model.put("noteList", noteList);
		modelAndView.addAllObjects(model);
		modelAndView.setViewName("ys/noteList");
		return modelAndView;
		
	}
	
	
	
}
