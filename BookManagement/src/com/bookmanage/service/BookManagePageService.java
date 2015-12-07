package com.bookmanage.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookmanage.controller.CommandAction;
import com.bookmanage.controller.ViewInfo;
import com.bookmanage.dao.BookManageMemberDelete;

public class BookManagePageService implements CommandAction {

	@Override
	public ViewInfo requestProc(HttpServletRequest request, HttpServletResponse response, ViewInfo view)
			throws IOException, ServletException {
		switch(view.getReqURI()){
		case "index.do":
			view.setView("MainForm.jsp");
			view.setRedirect(false);
			break;
		case "memberjoin.do":
			view.setView("JoinForm.jsp");
			view.setRedirect(false);
			break;
		case "bookInsert.do":
			view.setView("BookInsert.jsp");
			view.setRedirect(false);
			break;
		case "logout.do":
			HttpSession session = request.getSession();
			session.invalidate();
			view.setView("MainForm.jsp");
			view.setRedirect(true);
			break;
		}
		return view;
	}
}
