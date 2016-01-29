package com.project.call.wansoon.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.project.call.domain.FreeBoard;

public interface WSService {

	public List<FreeBoard> getFreeBoardAll(HttpServletRequest req);
	
	public FreeBoard getFreeBoard(int frbNo);
	

	
	
	
	
	
}


