package com.project.call.seunghee.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.FreeBoard;
import com.project.call.seunghee.service.SHService;

@Controller
public class SHController {
	
	@Autowired
	private SHService shService;
	
	private final static String path = "/resources/images/photo1";
	
	public void setshService(SHService shService) {
		this.shService = shService;
	}
	
	@RequestMapping(value={"/", "/index"})
	public String main() {
		return "index";
	}

	@RequestMapping(value="/getNoticeList")
	public String getNoticeList(HttpServletRequest request) {
		shService.getNoticeList(request);
		return "index.jsp?body=noticeboard/noticeList";
	}
	
	@RequestMapping(value="/getNoticeContent", method=RequestMethod.GET)
	public String getNoticeContent(HttpServletRequest request, Model model) throws IOException {
		FreeBoard notice = shService.getNoticeContent(request);
		model.addAttribute("notice", notice);
		return "index.jsp?body=noticeboard/noticeContent";
	}
	
	@RequestMapping("/noticeDelete")
	public String noticeDelete(HttpServletRequest request) {
		shService.noticeDelete(request);
		return "redirect:getNoticeList";
	}
	
	@RequestMapping("/noticeWriteForm")
	public String noticeWriteForm(HttpServletRequest request) {
		shService.noticeWriteForm(request);
		return "index.jsp?body=noticeboard/noticeWriteForm";
	}
	
	@RequestMapping(value="/noticeWrite", method=RequestMethod.POST)
	public String noticeWrite(MultipartHttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String filePath = request.getServletContext().getRealPath(path);
		shService.noticeWrite(request, response, filePath);
		return "redirect:getNoticeList";
	}
	
	@RequestMapping(value="/noticeModifyForm", method=RequestMethod.GET)
	public String noticeModifyForm(HttpServletRequest request, Model model) throws IOException {
		FreeBoard modify = shService.noticeModifyForm(request);
		model.addAttribute("modify", modify);
		return "index.jsp?body=noticeboard/noticeModifyForm";
	}
	
	@RequestMapping(value="/noticeModify", method=RequestMethod.POST)
	public String noticeModify(MultipartHttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		String filePath = request.getServletContext().getRealPath(path);
		shService.noticeModify(request, response, filePath);
		return "redirect:getNoticeList";
	}
	
	@RequestMapping("/localRanking")
	public String localRanking() {
		return "index.jsp?body=ranking/localRanking";
	}
	
	@RequestMapping("/seoulRanking")
	public String seoulRanking(HttpServletRequest request) {
		shService.getSeoulRanking(request);
		return "ranking/seoul";
	}
	
	@RequestMapping("/gyeonggiRanking")
	public String gyeonggiRanking(HttpServletRequest request) {
		shService.getGyeonggiRanking(request);
		return "ranking/gyeonggi";
	}
	
	@RequestMapping("/kangwonRanking")
	public String kangwonRanking(HttpServletRequest request) {
		shService.getKangwonRanking(request);
		return "ranking/kangwon";
	}
	
	@RequestMapping("/chungcheongRanking")
	public String chungcheongRanking(HttpServletRequest request) {
		shService.getSeoulRanking(request);
		return "ranking/chungcheong";
	}
	
	@RequestMapping("/gyeongsangRanking")
	public String gyeongsangRanking(HttpServletRequest request) {
		shService.getGyeongsangRanking(request);
		return "ranking/gyeongsang";
	}
	
	@RequestMapping("/junlaRanking")
	public String junlaRanking(HttpServletRequest request) {
		shService.getJunlaRanking(request);
		return "ranking/junla";
	}
	
	@RequestMapping("/jejuRanking")
	public String jejuRanking(HttpServletRequest request) {
		shService.getJejuRanking(request);
		return "ranking/jeju";
	}
	
	@RequestMapping()
	public String aa() {
		return null;
	}
}
