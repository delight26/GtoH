package com.project.call.junbum.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface JBService {
	public Boolean loginResult(HttpServletRequest request, HttpSession session);
	
	public void getproductList(HttpServletRequest request);
	
	public void addProduct(MultipartHttpServletRequest request, String path) throws IOException;
	
	public void productContent(HttpServletRequest request);
	
	public void addCart(HttpServletRequest request, HttpSession session);
}
