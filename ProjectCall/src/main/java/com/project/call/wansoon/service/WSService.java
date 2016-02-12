package com.project.call.wansoon.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.Comment;
import com.project.call.domain.FreeBoard;


public interface WSService {

	public void getFreeBoardList(HttpServletRequest request);

	public FreeBoard getFreeBoard(int frbNo);

	public void insertWrite(FreeBoard freeboard, String filePath);
	
	public void modifyWrite(FreeBoard freeboard, String filePath);

	public void deleteBoard(int frbNo);

	public void addComment(Comment freebComment);

	public List<Comment> commentAllList(int bno);

	public void modifyComment(Comment freebcomment);
	
	//여기서 부터 수정 들어감 02.11
	public void modifyComment(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception;
	
	public void freeDelete(HttpServletRequest request);
	
	public void freeSearch(HttpServletRequest request);
	
	public void freeUpdateForm(HttpServletRequest request);
	
	public void freeUpdateResult(MultipartHttpServletRequest request, String path) throws IOException;
	
	public void freeContent(HttpServletRequest request); 

	public void freePreContent(HttpServletRequest request);

	public void freeNextContent(HttpServletRequest request);
}
