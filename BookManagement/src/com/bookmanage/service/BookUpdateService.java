package com.bookmanage.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmanage.controller.CommandAction;
import com.bookmanage.controller.ViewInfo;
import com.bookmanage.dao.BookDao;
import com.bookmanage.member.Book;

public class BookUpdateService implements CommandAction {

	@Override
	public ViewInfo requestProc(HttpServletRequest request, HttpServletResponse response, ViewInfo view)
			throws IOException, ServletException {
		BookDao dao = BookDao.getInstance();
		int bookNumber = Integer.valueOf(request.getParameter("bookNumber"));
		Book book = dao.getBookContent(bookNumber);
		request.setAttribute("book", book);
		view.setView("BookUpdate.jsp");
		view.setRedirect(false);
		return view;
	}

}
