package com.project.call.yoonseok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.project.call.yoonseok.service.YSService;

@Controller
public class YSController {
	
	@Autowired
	private YSService jBService;
	
	public void setjBService(YSService jBService) {
		this.jBService = jBService;
	}
}
