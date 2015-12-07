package com.bookmanage.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmanage.controller.CommandAction;
import com.bookmanage.controller.ViewInfo;
import com.bookmanage.dao.BookDao;

public class BookDeleteService implements CommandAction {

	@Override
	public ViewInfo requestProc(HttpServletRequest request, HttpServletResponse response, ViewInfo view)
			throws IOException, ServletException {
		BookDao dao = BookDao.getInstance();
		String BookNumber = request.getParameter("BookNumber");
		dao.getBookDelete(BookNumber);
		view.setView("booklist.do");
		view.setRedirect(true);

		return view;

	}
}
