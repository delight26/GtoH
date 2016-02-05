package com.project.call.hyunsu.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.Area;
import com.project.call.domain.AskInjection;
import com.project.call.domain.Member;
import com.project.call.hyunsu.dao.HSDao;
import com.project.call.hyunsu.email.Email;
import com.project.call.hyunsu.email.EmailFileSender;
import com.project.call.hyunsu.email.EmailSender;
import com.project.call.hyunsu.supprot.ScriptHandling;
import com.project.call.hyunsu.supprot.TimestampHandling;

@Service
public class HSServiceImpl implements HSService {

	@Autowired
	private HSDao Dao;
	
	public void setjBDao(HSDao dao) {
		this.Dao = dao;
	}
	
	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private EmailFileSender emailFileSender;
	
	@Autowired
	private ScriptHandling scriptHandling;
	
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
		session.setAttribute("emailSendCode", sendCode);
		System.out.println("(세션 저장함)sendCode : " + sendCode);
		String reciver = id;
		String subject = "ProjectCall Email인증입니다";
		String content = "ProjectCall Email인증번호 :  " + sendCode ;
		
		email.setReciver(reciver);
		email.setSubject(subject);
		email.setContent(content);
		emailSender.sendEmail(email);
		out.println("인증메일을 전송하였습니다");
		out.close();
		
	
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
		int check = 1;
		
		String email = request.getParameter("email");
		String confirm = request.getParameter("getCode");
		String pswd1 = request.getParameter("pswd1");
		String pswd2 = request.getParameter("pswd2");
		String name = request.getParameter("nm");
		//0-> 남성 , 1->여성
		String gender = "남";
		String sex = request.getParameter("sex");
		System.out.println(sex);
		String yy = request.getParameter("yy");
		String mm = request.getParameter("mm");
		String dd = request.getParameter("dd");
		String birthday = yy + "-" + mm + "-" + dd;
		String nickName = request.getParameter("nickname");

		String sendCode = (String) session.getAttribute("emailSendCode");
		if(sendCode.equals("") || sendCode == null) check = 0;
		if(!confirm.equals(sendCode)) check = 0;
		if(sex.equals("1")) gender = "여";
		if(!pswd1.equals(pswd2)) check = 0;
		List<Member> memberList = Dao.getMemberIdList();
		for(Member m : memberList){
			if(m.getEmail().equals(email)) check = 0;
			if(m.getNickName().equals(nickName)) check= 0;
		}
		if(nickName.equals("")) check = 0;
		member.setBirthday(birthday);
		member.setEmail(email);
		member.setGender(gender);
		member.setPass(pswd1);
		member.setName(name);
		member.setNickName(nickName);
		if(check == 0){
			scriptHandling.historyBack(response);
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
		Random random = new Random();
		session.setAttribute("loginUser", member);
		session.setAttribute("emailSendCode", random.nextInt(1000000));
	}
	
	@Override
	public void nickNameCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String nickname = request.getParameter("nickname");
		System.out.println(nickname);
		List<Member> memberList = Dao.getMemberIdList();
		int state = 1;
		for(Member member : memberList){
			if(member.getNickName().equals(nickname)) state = 0;
		}
		response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        String result = "<font color='red'>사용불가능한 닉네임입니다</font>";
        if(state == 1 ){
        	result = "<font color='green'>멋진 닉네임이네요!</font>";
        }
        out.println(result);
        out.close();
		
	}
	
	@Override
	public void searchNickName(HttpServletRequest request, HttpServletResponse response, 
			Model model) throws Exception {
		String keyword = request.getParameter("keyword");
		List<String> nickNameList = Dao.getNickNameList(keyword);
		
		List<String> result = new ArrayList<String>();
		
		for(int i = 0; i < nickNameList.size(); i++) {
			// DB에서 검색된 리스트와 입력된 검색어의 시작 문자열을 대문자로 비교
			if(nickNameList.get(i).toUpperCase().startsWith(keyword.toUpperCase())) {
				result.add(nickNameList.get(i));
			}
		}
		Collections.sort(result);
		response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        for(int i = 0; i < result.size(); i++) {
    		String key = (String)result.get(i);
    		out.print("<li class='searchList'>" + key + "</li>");
    		// 자동완성 리스트를 15개로 한정
    		if(i >= 14) {
    			break;
    		}		
    	}
        out.close();
	}
	
	
	@Override
	public void addAsk(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		int check = 1;
		int temp = 0;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		List<String> nickNameList = Dao.getNickNameList("");
		String toid = request.getParameter("toId");
		if(toid.equals("")) check = 0;
		for(String nickName : nickNameList){
			if(toid.equals(nickName)) temp = 1;
		}
		if(temp == 0) check = 0;
		System.out.println("check : " + check);
		
		String fightDate = request.getParameter("fightDate");
		if(fightDate.equals("")) check = 0;
		String place = request.getParameter("place");
		if(place.equals("")) check = 0;
		String tell = request.getParameter("tell");
		if(tell.equals("")) check = 0;
		Member member = null;
		try {
			member = (Member) session.getAttribute("loginUser");
		}catch(Exception e) {
			scriptHandling.historyBack(response);
		}
		TimestampHandling handling = new TimestampHandling();
		if(!handling.isDate(fightDate, timestamp)) scriptHandling.historyBack(response, "저희는 타임머신 기능이 없어요 T.T");
		
		AskInjection ask = new AskInjection(toid, fightDate, 0, place, 
				timestamp, tell, member.getEmail());
		System.out.println("check"+ ++temp + " : "+check);
		if(check == 0) scriptHandling.historyBack(response);
		Dao.insertAsk(ask);		
	}
	
	@Override
	public void findIdPassAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int state = 1;
		response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        String result = "<font color='red'>정보가 일치하지 않습니다</font>";
        Email email = new Email();
		String name = request.getParameter("name");
		String birthDay = request.getParameter("birthday");
		Member member = new Member();
		state = 10;		
		List<Member> memberList = Dao.getMemberIdList();
		for(Member m : memberList){
			if(m.getName().equals(name)){
				String bir = String.valueOf(m.getBirthday()).substring(0,10);
				if(bir.equals(birthDay)) {
					state = 1;
					member = m;				
				}
			}
		}
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
		String pass = (support + randomInteger).trim();
		member.setPass(pass);
		Dao.setPassMember(member.getEmail(), member.getPass());
		String reciver = member.getEmail();
		String subject = "ProjectCall 비밀번호 인증입니다";
		String content = "ProjectCall 비밀번호 :  " + pass ;
		
		email.setReciver(reciver);
		email.setSubject(subject);
		email.setContent(content);
		
	    if(state == 1 ){
        	result = "<font color='green'>"+ member.getEmail() + "으로 비밀번호를 발송하였습니다"+"</font>";
        	System.out.println(member.getEmail() + "비밀번호 찾기 발송완료 비밀번호 : " + member.getPass());
        	emailSender.sendEmail(email);
	    }
        out.println(result);
        out.close();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//테스트용 
	@Override
	public void testService(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Object object = session.getAttribute("loginUser");
		System.out.println((object == null));
		System.out.println(object.toString());
	}
	
	
	
}












