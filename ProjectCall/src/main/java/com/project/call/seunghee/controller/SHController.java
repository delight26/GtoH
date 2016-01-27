package com.project.call.seunghee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.project.call.seunghee.service.SHService;

@Controller
public class SHController {
	
	@Autowired
	private SHService jBService;
	
	public void setjBService(SHService jBService) {
		this.jBService = jBService;
	}
}
