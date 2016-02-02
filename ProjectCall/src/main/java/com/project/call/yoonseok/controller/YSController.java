package com.project.call.yoonseok.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping("YSRanking")
	public ModelAndView getMemberLanking(){
		List<Member> rankingList = jBService.ranking();
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> model = new HashMap<String, Object>();
		model.put("rankingList", rankingList);
		modelAndView.addAllObjects(model);
		modelAndView.setViewName("index.jsp?body=ys/rankingList");
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
	public String getNote(HttpServletRequest request, Model model){
		int maxPage = 0;
		 String toid = request.getParameter("toid");
		 int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		List<NoticeBoard> noteList = jBService.getNote(toid, pageNum);
		if(noteList.isEmpty()) {
			maxPage = 0;
		} else {
			System.out.println(noteList.get(0).getNbMaxPage());
			maxPage =noteList.get(0).getNbMaxPage();
		}
		
		model.addAttribute("noteList",noteList);
		model.addAttribute("size",  noteList.size());
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("maxPage", maxPage);
		return "ys/noteList";
		
	}
	@RequestMapping("YSnoteContent")
	public ModelAndView noteContent(HttpServletRequest request){
		int nbNo = Integer.parseInt(request.getParameter("nbNo"));
		NoticeBoard note = jBService.noteContent(nbNo);
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> model = new HashMap<String, Object>();
		model.put("note", note);
		
		modelAndView.addAllObjects(model);
		modelAndView.setViewName("ys/noteContent");
		return modelAndView;
		
	}
	@RequestMapping("YSdeleteNote")
	public String deleteNote(HttpServletRequest request, 
			Model model){
		String toid = request.getParameter("toid");
		int nbNo = Integer.parseInt(request.getParameter("nbNo"));
		System.out.println("번호 nbNo"+nbNo);
		jBService.deleteNote(nbNo);
		model.addAttribute("toid", toid);
		model.addAttribute("pageNum",1);
		return "redirect:YSGetNote";
		
	}
	@RequestMapping(value="noteCheck",method=RequestMethod.POST)
	public String noteCheck(Model model,HttpServletResponse response, 
			@RequestParam("toid") String toid) throws IOException{
		int count = jBService.noteCheck(toid);
		PrintWriter out = response.getWriter();
		out.println(count);
		out.close();
		return null;
		
	}
	
	
}
