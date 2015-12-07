package com.bookmanage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandAction {
	public ViewInfo requestProc(HttpServletRequest request, HttpServletResponse response, ViewInfo view)
			throws Throwable;
}