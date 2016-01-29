package com.project.call.wansoon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.call.domain.FreeBoard;
import com.project.call.wansoon.service.WSService;
import com.sun.org.apache.xerces.internal.util.HTTPInputSource;

@Controller
public class WSController {
	
	@Autowired
	private WSService jBService;
	
	public void setjBService(WSService jBService) {
		this.jBService = jBService;
	}
	
	@RequestMapping (value={"FreeBoardList"})
	public ModelAndView List(
			HttpServletRequest req, HttpServletResponse rsp) throws Exception {
		
		List<FreeBoard> frbList = jBService.getFreeBoardAll(req);
		
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
		FreeBoard frb = jBService.getFreeBoard(frbNo);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("frb", frb);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("freeB/FreeBoardContent");
		mav.addAllObjects(model);
		
		return mav;
		
	}
	
	
	
}
