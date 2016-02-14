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
import com.project.call.hyunsu.supprot.ScriptHandling;
import com.project.call.yoonseok.service.YSService;

@Controller
public class YSController {

	@Autowired
	private YSService jBService;

	public void setjBService(YSService jBService) {
		this.jBService = jBService;
	}

	@Autowired
	private ScriptHandling scriptHandling;

	@RequestMapping("YSRanking")
	public ModelAndView getMemberLanking() {
		List<Member> rankingList = jBService.ranking();
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("rankingList", rankingList);
		modelAndView.addAllObjects(model);
		modelAndView.setViewName("index.jsp?body=ys/rankingList");
		return modelAndView;

	}

	@RequestMapping("YSaddNote")
	public String addNote(HttpServletRequest request, HttpServletResponse response) throws Exception {
		NoticeBoard note = new NoticeBoard();
		note.setNbContent(request.getParameter("content"));
		note.setNbTitle(request.getParameter("title"));
		note.setNbEmail(request.getParameter("email"));
		note.setNbToid(request.getParameter("toid"));
		System.out.println(note.getNbToid());
		if (request.getParameter("content").equals("") || request.getParameter("content") == null
				|| request.getParameter("title").equals("") || request.getParameter("title") == null) {
			scriptHandling.historyBack(response);
		}

		jBService.addNote(note);
		// again <- 창닫는 값
		int again = 1;
		request.setAttribute("again", again);
		return "ys/addNote";

	}

	@RequestMapping("YSReplyNoteForm")
	public String replyNoteForm(HttpServletRequest request, Model model) {
		model.addAttribute("email", request.getParameter("email"));
		model.addAttribute("nickName", request.getParameter("nickName"));
		return "ys/replayNote";

	}

	@RequestMapping("YSAddNoteForm")
	public String addNoteForm(HttpServletRequest request, Model model) {
		int again = 0;
		request.setAttribute("again", again);
		return "ys/addNote";

	}
	
	
	@RequestMapping("YSSendNote")
	public String sendNote(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		int maxPage = 0;
		String email = request.getParameter("email");
		System.out.println(email);
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		List<NoticeBoard> noteList = jBService.sendNote(email, pageNum);

		if (noteList.isEmpty()) {
			maxPage = 0;
		} else {
			System.out.println("maxpage: "+noteList.get(0).getNbMaxPage());
			maxPage = noteList.get(0).getNbMaxPage();
		}
System.out.println("리스트 사이즈"+noteList.size());
		model.addAttribute("noteList", noteList);
		model.addAttribute("size", noteList.size());
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("maxPage", maxPage);
		/*
		 * //현수 수정 jBService.getNote(request, model);
		 */
		return "ys/sendNoteList";

	}
	
	
	
	@RequestMapping("YSGetNote")
	public String getNote(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		int maxPage = 0;
		String toid = request.getParameter("toid");
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		List<NoticeBoard> noteList = jBService.getNote(toid, pageNum);
		int size = 0;
		if (noteList.isEmpty()) {
			maxPage = 0;
			size = 0;
		} else {
			System.out.println("maxpage: "+noteList.get(0).getNbMaxPage());
			maxPage = noteList.get(0).getNbMaxPage();
			size = noteList.get(0).getSize();
		}
		model.addAttribute("noteList", noteList);
		model.addAttribute("size", size);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("maxPage", maxPage);
		/*
		 * //현수 수정 jBService.getNote(request, model);
		 */
		return "ys/noteList";

	}
	
	
	@RequestMapping("YSSendNoteContent")
	public ModelAndView sendNoteContent(HttpServletRequest request) {
		int nbNo = Integer.parseInt(request.getParameter("nbNo"));
		String check ="send";
		NoticeBoard note = jBService.noteContent(nbNo, check);
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("note", note);

		modelAndView.addAllObjects(model);
		modelAndView.setViewName("ys/noteContent");
		return modelAndView;

	}
	
	@RequestMapping("YSnoteContent")
	public ModelAndView noteContent(HttpServletRequest request) {
		int nbNo = Integer.parseInt(request.getParameter("nbNo"));
		String check ="content";
		NoticeBoard note = jBService.noteContent(nbNo,check);
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("note", note);

		modelAndView.addAllObjects(model);
		modelAndView.setViewName("ys/noteContent");
		return modelAndView;

	}

	@RequestMapping("YSdeleteNote")
	public String deleteNote(HttpServletRequest request, Model model) {
		String toid = request.getParameter("toid");
		int nbNo = Integer.parseInt(request.getParameter("nbNo"));
		System.out.println("번호 nbNo" + nbNo);
		jBService.deleteNote(nbNo);
		model.addAttribute("toid", toid);
		model.addAttribute("pageNum", 1);
		return "redirect:YSGetNote";

	}

	@RequestMapping(value = "noteCheck", method = RequestMethod.POST)
	public String noteCheck(Model model, HttpServletResponse response, @RequestParam("toid") String toid)
			throws IOException {
		int count = jBService.noteCheck(toid);
		PrintWriter out = response.getWriter();
		out.println(count);
		out.close();
		return null;

	}
			@RequestMapping(value = "modalRank", method = RequestMethod.POST)
			public void modalSearch(Model model, HttpServletResponse response, @RequestParam("nickName") String nickName)
					throws IOException {
				Member modal = jBService.modalSearch(nickName);
				PrintWriter out = response.getWriter();
			out.print("<td>"+modal.getRank()+"</td>"
					+ "<td>"+modal.getNickName()+"</td>"
					+ "<td>"+modal.getLevel()+"</td>"
					+ "<td>"+modal.getWin()+"승" +modal.getLose()+"패</td>"
					+ "<td>"+modal.getArea()+"</td>"
					);
			out.close();

			}

			
			
			
	@RequestMapping(value = "nickNameSearch", method = RequestMethod.POST)
	public void nickNameSearch(Model model, HttpServletResponse response, @RequestParam("nickName") String nickName)
			throws IOException {
		List<String> nickNameList = jBService.nickNameSearch(nickName);
		PrintWriter out = response.getWriter();
		System.out.println(nickNameList);
		for (int i = 0; i < nickNameList.size(); i++) {
			System.out.println(nickNameList.get(i));
			out.print("<li class='searchList'>" + nickNameList.get(i) + "</li>");
		}

		out.close();

	}

}
