package com.project.call.seunghee.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.call.domain.FreeBoard;
import com.project.call.seunghee.service.SHService;

@Controller
public class SHController {
	
	@Autowired
	private SHService shService;
	
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
	
}
