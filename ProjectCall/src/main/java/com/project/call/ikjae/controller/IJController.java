package com.project.call.ikjae.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.project.call.ikjae.service.IJService;

@Controller
public class IJController {
	
	@Autowired
	private IJService jBService;
	
	public void setjBService(IJService jBService) {
		this.jBService = jBService;
	}
}
