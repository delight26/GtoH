package com.bookmanage.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookmanage.controller.CommandAction;
import com.bookmanage.controller.ViewInfo;
import com.bookmanage.dao.BookManageLogin;
import com.bookmanage.member.Member;

public class LoginService implements CommandAction {

	@Override
	public ViewInfo requestProc(HttpServletRequest request, HttpServletResponse response, ViewInfo view)
			throws IOException, ServletException {
		BookManageLogin dao = BookManageLogin.getInstance();
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		ArrayList<Member> LoginList = dao.getLogin();
		boolean logintf = false;
		for (int i = 0; i < LoginList.size(); i++) {
			if (LoginList.get(i).getId().equals(id)
					&& LoginList.get(i).getPass().equals(pass)) {
				logintf = true;
				Member member = dao.getMember(id);
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
			} 
		}
		if(logintf == true){
			if(id.equals("admin")){
				view.setView("AdminForm.jsp");
				view.setRedirect(false);
			}else{
				view.setView("/userbooklist.do");
				view.setRedirect(false);
			}
		}	else{
			view.setView("MainForm.jsp");
			view.setRedirect(false);
			request.setAttribute("isLogin", false);
		}
		return view;
		
	}
}
