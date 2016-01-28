package com.project.call.junbum.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.call.domain.Member;
import com.project.call.junbum.service.JBService;

@Controller
public class JBController {

	@Autowired
	private JBService jBService;

	public void setjBService(JBService jBService) {
		this.jBService = jBService;
	}

	@RequestMapping(value = "loginform")
	public String loginForm() {
		return "member/login";
	}

	@RequestMapping(value = "loginresult", method = RequestMethod.POST)
	public String loginResult(HttpServletRequest request, HttpSession session) {
		boolean logtf = jBService.loginResult(request, session);

		if (logtf) {
			return "index";
		} else {
			return "redirect:loginform";
		}

		
	}
}
