package com.bookmanage.service;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmanage.controller.CommandAction;
import com.bookmanage.controller.ViewInfo;
import com.bookmanage.dao.BookDao;
import com.bookmanage.member.Book;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BookInsertService implements CommandAction {
	

	@Override
	public ViewInfo requestProc(HttpServletRequest request, HttpServletResponse response, ViewInfo view)
			throws IOException, ServletException {
		
		String pImages;
		ServletContext application = request.getServletContext();
		pImages = application.getInitParameter("pImages");
		
		String realPath = application.getRealPath(pImages);

		int maxFileSize = 10 * 1024 * 1024;

		String encoding = "UTF-8";
		MultipartRequest multi = new MultipartRequest(request, realPath, maxFileSize, encoding,
				new DefaultFileRenamePolicy());

		String bookName = multi.getParameter("bookName");
		String author = multi.getParameter("author");
		String bookCompany = multi.getParameter("bookCompany");
		String price = multi.getParameter("Price");

		Book book = new Book();
		book.setBookName(bookName);
		book.setAuthor(author);
		book.setBookCompany(bookCompany);
		book.setPrice(price);
		String fileName = multi.getFilesystemName("image");
		System.out.println("업로드 된 파일명 : " + fileName);
		System.out.println("원본 파일명 : " + multi.getOriginalFileName("image"));

		book.setPhoto(fileName.length() > 0 ? request.getContextPath()+pImages + "/" + fileName : null);

		if (book.getPhoto() == null) {
			System.out.println("파일이 업로드 되지 않았음");
		}

		BookDao dao = new BookDao();
		dao.getaddBook(book);
		view.setView("booklist.do");
		view.setRedirect(true);

		return view;
	}
}
