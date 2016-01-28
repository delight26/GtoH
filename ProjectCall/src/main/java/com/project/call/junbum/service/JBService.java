package com.project.call.junbum.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface JBService {
	public Boolean loginResult(HttpServletRequest request, HttpSession session);
}
