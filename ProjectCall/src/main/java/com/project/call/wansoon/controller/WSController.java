package com.project.call.wansoon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.project.call.wansoon.service.WSService;

@Controller
public class WSController {
	
	@Autowired
	private WSService jBService;
	
	public void setjBService(WSService jBService) {
		this.jBService = jBService;
	}
}
