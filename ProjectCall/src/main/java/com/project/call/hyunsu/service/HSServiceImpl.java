package com.project.call.hyunsu.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.call.domain.Member;
import com.project.call.hyunsu.dao.HSDao;
import com.project.call.hyunsu.email.Email;
import com.project.call.hyunsu.email.EmailSender;

@Service
public class HSServiceImpl implements HSService {

	@Autowired
	private HSDao Dao;
	
	public void setjBDao(HSDao jBDao) {
		this.Dao = jBDao;
	}
	
	@Autowired
	private EmailSender emailSender;
	
	@Override
	public void checkMemberId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList<Member> idList = (ArrayList<Member>) Dao.getMemberIdList();
		
		response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        String getId = request.getParameter("id");
		String result = "<font color='green'>사용할 수 있는 ID 입니다.</font>";
		for(Member id : idList){
			if(getId.equals(id.getEmail())){
				result = "<font color='red'>이미 등록된 ID 입니다.</font>";
			}
		}
		out.println(result);	
		
	}
	
	
	@Override
	public String emailCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
		Email email = new Email();
		String id = request.getParameter("id");
		System.out.println(id);
		Random random = new Random();
		//0~999999 수를 받는다
		int cipher = 1000000;
		int randomInteger = random.nextInt(cipher);
		String support = "";
		for(int i = 5; i != 1 ; i--){
			if(randomInteger < Math.pow(10, i)){
				support += "0";
			}
		}
		String sendCode = (support + randomInteger).trim();
		
		System.out.println(sendCode);
		String reciver = id;
		String subject = "ProjectCall Email인증입니다";
		String content = "ProjectCall Email인증번호 :  " + sendCode ;
		
		email.setReciver(reciver);
		email.setSubject(subject);
		email.setContent(content);
		emailSender.sendEmail(email);
		out.println("인증메일을 전송하였습니다");
		return sendCode;
	}
	
	@Override
	public void getSendCodeCheck(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		
		String getCode = request.getParameter("getCode").trim();
		//System.out.println("getCode : "+ getCode);
		String emailSendCode = (String)session.getAttribute("emailSendCode");
		//System.out.println("emailSendCode" + emailSendCode);
		response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        String result = "<font color='red'>이미 등록된 ID 입니다.</font>";
        if(emailSendCode.equals(getCode)){
        	result = "<font color='green'>사용할 수 있는 ID 입니다.</font>";
        }
		out.println(result);
	}
	
	
	
}
