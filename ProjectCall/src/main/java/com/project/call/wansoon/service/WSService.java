package com.project.call.wansoon.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.FreeBoard;
import com.project.call.domain.FreebComment;

public interface WSService {

	public void getFreeBoardList(HttpServletRequest request);

	public FreeBoard getFreeBoard(int frbNo);

	public void insertWrite(FreeBoard freeboard, String filePath);
	
	public void modifyWrite(FreeBoard freeboard, String filePath);

	public void deleteBoard(int frbNo);

	public void addComment(FreebComment freebComment);

	public List<FreebComment> commentAllList(int bno);

	public void modifyComment(FreebComment freebcomment);

}
