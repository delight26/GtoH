package com.bookmanage.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmanage.controller.CommandAction;
import com.bookmanage.controller.ViewInfo;
import com.bookmanage.dao.BookDao;
import com.bookmanage.member.Book;

public class BookSearchService implements CommandAction {

	@Override
	public ViewInfo requestProc(HttpServletRequest request, HttpServletResponse response, ViewInfo view)
			throws IOException, ServletException {
		String searchName = request.getParameter("searchName");
		ArrayList<Book> bookList = null;
		BookDao dao = BookDao.getInstance();
		bookList = dao.getBookSearch(searchName);
		request.setAttribute("bookList", bookList);
		view.setView("BookList.jsp");
		view.setRedirect(false);

		return view;
		
	}
}
