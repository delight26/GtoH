package com.project.call.hyunsu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.project.call.hyunsu.service.HSService;

@Controller
public class HSController {
	
	@Autowired
	private HSService jBService;
	
	public void setjBService(HSService jBService) {
		this.jBService = jBService;
	}
}
