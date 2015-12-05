package com.bookmanage.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmanage.controller.CommandAction;
import com.bookmanage.controller.ViewInfo;
import com.bookmanage.dao.BookManageJoin;
import com.bookmanage.member.Member;

public class BookMemberJoinService implements CommandAction {

	@Override
	public ViewInfo requestProc(HttpServletRequest request, HttpServletResponse response, ViewInfo view)
			throws IOException, ServletException {
		BookManageJoin dao = BookManageJoin.getInstance();
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String birthday = request.getParameter("birthday");
		String job = request.getParameter("job");
		String adress = request.getParameter("adress");
		String phone = phone1 + "-" + phone2 + "-" + phone3;
		Member m = new Member(id, pass, name, phone, birthday, job, adress);
		dao.getMemberAdd(m);
		view.setView("MainForm.jsp");
		view.setRedirect(true);

		return view;
	}

}
