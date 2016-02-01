package com.project.call.hyunsu.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.call.hyunsu.email.Email;

public interface HSService {

	//id의 중복값을 체크한다
	public void checkMemberId(HttpServletRequest request, HttpServletResponse response)  throws IOException;

	//회원가입 요청자의 이메일을 체크한다
	public String emailCheck(HttpServletRequest request, HttpServletResponse response)  throws Exception ;
	
	//인증코드를 비교한다
	public void getSendCodeCheck(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception;
}
