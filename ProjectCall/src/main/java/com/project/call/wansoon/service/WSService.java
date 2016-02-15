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
	
	public void freeBoardList(HttpServletRequest request);

	public void freeBoardWriteResult(MultipartHttpServletRequest request, HttpServletResponse response, HttpSession session, String path)
			throws Exception;

	public void freeContent(HttpServletRequest request);

	public void freePreContent(HttpServletRequest request);

	public void freeNextContent(HttpServletRequest request);

	public void freeUpdateForm(HttpServletRequest request);

	public void freeUpdateResult(MultipartHttpServletRequest request, String path) throws IOException;
	
	public void freeDelete(HttpServletRequest request);
	
	public void freeSearch(HttpServletRequest request);
	
	public void getComment(String frbNo, String pageNum, HttpServletRequest request);
	
	public void freeCommentWrite(String frbNo, String content, String email);
	
	public void freeCommentUpdate(String cNo, String content);
	
	public void freeCommentDelete(String cNo);
	
}
