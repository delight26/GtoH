package com.project.call.hyunsu.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.Area;
import com.project.call.domain.Member;
import com.project.call.hyunsu.dao.HSDao;
import com.project.call.hyunsu.email.Email;
import com.project.call.hyunsu.email.EmailSender;

@Service
public class HSServiceImpl implements HSService {

	@Autowired
	private HSDao Dao;
	
	public void setjBDao(HSDao dao) {
		this.Dao = dao;
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
		out.close();
	}
	
	
	@Override
	public String emailCheck(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
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
		out.close();
		session.setAttribute("emailSendCode", sendCode);
		System.out.println("세션 저장함" + sendCode);
		return sendCode;
	}
	
	@Override
	public void getSendCodeCheck(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		
		String getCode = request.getParameter("getCode").trim();
		System.out.println("getCode : "+ getCode);
		String emailSendCode = (String)session.getAttribute("emailSendCode");
		System.out.println("emailSendCode" + emailSendCode);
		response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        String result = "<font color='red'>인증번호가 일치하지 않습니다</font>";
        if(emailSendCode.equals(getCode)){
        	result = "<font color='green'>확인 되었습니다.</font>";
        }
		out.println(result);
		out.close();
	}
	
	@Override
	public void addMember(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		Member member = new Member();
		String email = request.getParameter("email");
		String confirm = request.getParameter("getCode");
		String pswd1 = request.getParameter("pswd1");
		String pswd2 = request.getParameter("pswd2");
		String name = request.getParameter("nm");
		//0-> 남성 , 1->여성
		String gender = "남";
		String sex = request.getParameter("sex");
		String yy = request.getParameter("yy");
		String mm = request.getParameter("mm");
		String dd = request.getParameter("dd");
		String birthday = yy + "-" + mm + "-" + dd;
		String nickName = request.getParameter("nickname");
		int check = 1;
		String sendCode = (String) session.getAttribute("emailSendCode");
		if(!confirm.equals(sendCode)) check = 0;
		if(sex.equals("1")) gender = "여";
		if(!pswd1.equals(pswd2)) check = 0;
		member.setBirthday(birthday);
		member.setEmail(email);
		member.setGender(gender);
		member.setPass(pswd1);
		member.setName(name);
		member.setNickName(nickName);
		if(check == 0){
			response.setContentType("text/html;charset=UTF-8");
	        response.setHeader("Cache-Control", "no-cache");
	        PrintWriter out = response.getWriter();
	       out.println("<script>");
	       out.println("alert('잘못된 정보가 입력되었습니다');");
	       out.println("history.back();");
	       out.println("</script>");
	       out.close();
		}
		Dao.addMemberCompulsory(member);
		session.setAttribute("loginUser", member);
		
	}
	
	@Override
	public List<Area> getAreaList() {
		return Dao.getAreaList();
	}
	
	@Override
	public void additionalInformationMember(MultipartHttpServletRequest request, 
			HttpServletResponse response,	HttpSession session, String path) throws Exception {
		int tempPhoneState = 1;
		Member member = (Member) session.getAttribute("loginUser");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String phone = phone1 + "-" + phone2 + "-" + phone3;
		if(phone1.equals("") || phone1 == null) tempPhoneState = 0;
		if(phone2.equals("") || phone2 == null) tempPhoneState = 0;
		if(phone3.equals("") || phone3 == null) tempPhoneState = 0;
		if(tempPhoneState == 1){
			Dao.updatePhone(phone, member);
		}
		
		String address = request.getParameter("address");
		if(!address.equals("")) Dao.updateAddress(address, member);
		String area = request.getParameter("area");
		if(!area.equals("")) Dao.updateArea(area, member);
		String word = request.getParameter("word");
		if(!word.equals("")) Dao.updateWord(word, member);
		MultipartFile multipartFile = request.getFile("photo");
		if(!multipartFile.isEmpty()) {
			File file = new File(path, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);
			String photo = multipartFile.getOriginalFilename();			
			Dao.updatePhoto(photo, member);
		}
		member = Dao.getMember(member);
		session.setAttribute("loginUser", member);
		
	}
	
	
}












