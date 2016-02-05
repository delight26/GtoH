package com.project.call.junbum.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.Comment;

public interface JBService {
	public Boolean loginResult(HttpServletRequest request, HttpSession session);

	public void getproductList(HttpServletRequest request);

	public void addProduct(MultipartHttpServletRequest request, String path) throws IOException;

	public void productContent(HttpServletRequest request);

	public void addCart(HttpServletRequest request, HttpSession session);

	public void getCart(HttpSession session);

	public void productUpdate(HttpServletRequest request);

	public void productUpdateResult(MultipartHttpServletRequest request, String path) throws IOException;

	public void productDelete(HttpServletRequest request);

	public void buyCartProduct(HttpServletRequest request);

	public void orderPrduct(HttpServletRequest request, HttpSession session);

	public void buyProduct(HttpServletRequest request);

	public void aggroBoardList(HttpServletRequest request);

	public void aggroBoardWriteResult(MultipartHttpServletRequest request, HttpSession session, String path)
			throws IOException;

	public void aggroContent(HttpServletRequest request);

	public void aggroPreContent(HttpServletRequest request);

	public void aggroNextContent(HttpServletRequest request);

	public void aggroUpdateForm(HttpServletRequest request);

	public void agrroUpdateResult(MultipartHttpServletRequest request, String path) throws IOException;
	
	public void aggroDelete(HttpServletRequest request);
	
	public void getComment(String frbNo, HttpServletRequest request);
	
	public void aggroCommentWrite(String frbNo, String content, String email);
	
	public void aggroCommentUpdate(String cNo, String content);
	
	public void aggroCommentDelete(String cNo);
	
	public void askResultList(HttpServletRequest request, HttpSession session);
	
	public void askResultUpdate(HttpServletRequest request, HttpSession session);
	
	public void askResultUpdateResult(HttpServletRequest request);
	
	public void askResultDelete(HttpServletRequest request);
	
	public void askReceveList(HttpServletRequest request, HttpSession session);
	
	public void askApproval(HttpServletRequest request);
}
