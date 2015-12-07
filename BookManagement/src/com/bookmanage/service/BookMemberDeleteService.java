package com.bookmanage.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmanage.controller.CommandAction;
import com.bookmanage.controller.ViewInfo;
import com.bookmanage.dao.BookManageMemberDelete;

public class BookMemberDeleteService implements CommandAction {

	@Override
	public ViewInfo requestProc(HttpServletRequest request, HttpServletResponse response, ViewInfo view)
			throws IOException, ServletException {
		BookManageMemberDelete dao = BookManageMemberDelete.getInstance();
		String id = request.getParameter("id");
		 dao.getMemberDelete(id);
		 view.setView("memberlist.do");
		view.setRedirect(true);
		
		return view;
	}

}
