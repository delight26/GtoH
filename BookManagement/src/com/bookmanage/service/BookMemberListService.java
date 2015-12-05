package com.bookmanage.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmanage.controller.CommandAction;
import com.bookmanage.controller.ViewInfo;
import com.bookmanage.dao.BookManageMemberList;
import com.bookmanage.member.Member;

public class BookMemberListService implements CommandAction {

	@Override
	public ViewInfo requestProc(HttpServletRequest request, HttpServletResponse response, ViewInfo view)
			throws IOException, ServletException {
		BookManageMemberList dao = BookManageMemberList.getInstance();

		ArrayList<Member> memberList = dao.getMemberList();

		request.setAttribute("memberList", memberList);
		view.setView("MemberList.jsp");
		view.setRedirect(false);

		return view;
	}
}
