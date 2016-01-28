package com.project.call.junbum.serviceimpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.call.domain.Member;
import com.project.call.junbum.dao.JBDao;
import com.project.call.junbum.service.JBService;

@Service
public class JBServiceImpl implements JBService {

	@Autowired
	private JBDao jBDao;

	public void setjBDao(JBDao jBDao) {
		this.jBDao = jBDao;
	}

	@Override
	public Boolean loginResult(HttpServletRequest request, HttpSession session) {
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");

		Member m = jBDao.getloginResult(email);

		if (m.getPass().equals(pass)) {
			session.setAttribute("loginUser", m);
			return true;
		} else {
			return false;
		}
	}
}
