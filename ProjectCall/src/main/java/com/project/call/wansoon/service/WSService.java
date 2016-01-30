package com.project.call.wansoon.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.FreeBoard;

public interface WSService {

	public List<FreeBoard> getFreeBoardAll();
	
	public FreeBoard getFreeBoard(int frbNo);
	
	public List<FreeBoard> insertBoard(FreeBoard freeboard);

	public void addWrite(
			MultipartHttpServletRequest request, String filePath)
					throws IOException;
	
	
	
}


