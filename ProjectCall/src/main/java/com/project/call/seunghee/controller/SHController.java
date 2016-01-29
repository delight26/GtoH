package com.project.call.seunghee.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
}
