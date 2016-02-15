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
import com.project.call.domain.Fight;
import com.project.call.domain.FightResult;
import com.project.call.domain.FightResultBoard;
import com.project.call.domain.FightResultBoardSupprot;
import com.project.call.domain.FreeBoard;
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
	
	private static final int PAGE_SIZE = 10;
	private static final int PAGE_GROUP = 10;	
	
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
		String subject = "[ProjectCall] 회원 가입 인증 메일입니다";
		String content = "안녕하세요, ProjectCall 입니다. \n"
				+ "본인 확인을 위하여 아래의 ID 및 인증 번호를 확인하신 후, 회원 가입 창에 입력하여 주십시오. \n\n"
				+ "*ProjectCall ID : " + id
				+ "\n*인증 번호 : " + sendCode 
				+ "\n\n감사합니다.";
		
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
		try{
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
		}catch(NullPointerException e){
			scriptHandling.historyBack(response, "필수정보가 입력되지 않았습니다");
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
        String id = request.getParameter("id");
        String result="<font color='red'>정보가 일치하지 않습니다</font>";
        if(id.equals("") || id==null){
        	state=0;
        }
        Email email = new Email();
		Member member = new Member();
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
		Dao.setPassMember(id, member.getPass());
		String reciver = id;
		String subject = "ProjectCall 비밀번호 인증입니다";
		String content = "ProjectCall 비밀번호 :  " + pass ;
		
		email.setReciver(reciver);
		email.setSubject(subject);
		email.setContent(content);
		
	    if(state == 1 ){
        	result = "<font color='green'>"+ id + "으로 비밀번호를 발송하였습니다"+"</font>";
        	System.out.println(id + "비밀번호 찾기 발송완료 비밀번호 : " + member.getPass());
        	emailSender.sendEmail(email);
	    }
        out.println(result);
        out.close();		
	}
	
	@Override
	public void fightResultMySelf(HttpServletRequest request, HttpSession session) {
		int result = Integer.parseInt(request.getParameter("result"));
		int fightNumber = Integer.parseInt(request.getParameter("fightNumber"));
		Member member = (Member)session.getAttribute("loginUser");
		int state = 0;
		int exist = Dao.getFightResultCount(fightNumber);
		Fight fight = Dao.getFight(fightNumber);
		if(member.getEmail().equals(fight.getPlayer1())) state = 1;
		if(member.getEmail().equals(fight.getPlayer2())) state = 2;
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		System.out.println("exist : " + exist + "\tstate : " + state);
		if(exist == 0 ){
			if(state == 1) Dao.insertFightResultPlayer1(result, fightNumber, nowTime);
			if(state == 2) Dao.insertFightResultPlayer2(result, fightNumber, nowTime);
		}else if(exist == 1){
			if(state == 1) Dao.updateFightResultPlayer1(result, fightNumber, nowTime);	
			if(state == 2) Dao.updateFightResultPlayer2(result, fightNumber, nowTime);			
			FightResultBoardSupprot supprot = Dao.getFigthResultContentUseFightNumber(fightNumber);
			pointGive(supprot);
			System.out.println("포인트 변경내역이 존재합니다 : < " + supprot.getPlayer1() + ", " + supprot.getPlayer2() + ">" );
		}
		
		Dao.updateFight(state, fightNumber);
		
	}
	
	@Override
	public void fightResultBoardList(HttpServletRequest request, HttpSession session, Model model) {
		String pageNum = request.getParameter("pageNum");
		//Member member = (Member)session.getAttribute("loginUser");
		if (pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.valueOf(pageNum);

		int startRow = currentPage * PAGE_SIZE - PAGE_SIZE;
		int listCount = Dao.getFightResultCount();
		System.out.println("listCount : " + listCount); 
		if(listCount > 0) {
			List<FightResult> fightResultList = Dao.getFightResultList(startRow, PAGE_SIZE);
			int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);
			int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
					- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
			int endPage = startPage + PAGE_GROUP - 1;
			if (endPage > pageCount) {
				endPage = pageCount;
			}
			List<FightResultBoard> fightResultBoardList = new ArrayList<FightResultBoard>();
			for(FightResult fightResult : fightResultList){
				Fight fight = Dao.getFightAsNickname(fightResult.getFightNumber());
				FightResultBoardSupprot supprot = new FightResultBoardSupprot();
				supprot.setNo(fightResult.getNo());
				supprot.setFightNumber(fightResult.getFightNumber());

				supprot.setPlayer1result(fightResult.getPlayer1result());
				supprot.setPlayer1writeDate(fightResult.getPlayer1writeDate());
				
				supprot.setPlayer2result(fightResult.getPlayer2result());
				supprot.setPlayer2writeDate(fightResult.getPlayer2writeDate());
				
				supprot.setHit(fightResult.getHit());
				supprot.setCallDate(fight.getCallDate());
				supprot.setResultDate(fight.getResultDate());
				supprot.setPlayer1(fight.getPlayer1());
				System.out.println(supprot.getPlayer1());
				supprot.setPlayer2(fight.getPlayer2());
				
				FightResultBoard board = new FightResultBoard();
				board.setNo(supprot.getNo());
				board.setHit(supprot.getHit());
				board.setWriter("관리자");
				TimestampHandling handling = new TimestampHandling();
				boolean result = handling.isDate(supprot.getPlayer1writeDate(), supprot.getPlayer2writeDate());
				
				if(result){
					board.setWriteDate(supprot.getPlayer1writeDate());
				}else{
					board.setWriteDate(supprot.getPlayer2writeDate());
				}
				String title = supprot.getFightNumber() + "번 대결 결과 입니다" + "( "
						+ supprot.getPlayer1() + " VS " + supprot.getPlayer2() + " )";
				board.setTitle(title);
				fightResultBoardList.add(board);
			}
			
			request.setAttribute("fightResultBoardList", fightResultBoardList);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("PAGE_GROUP", PAGE_GROUP);
		}
	}
	
	@Override
	public void getFigthResultContent(HttpServletRequest request, Model model) {
		int no = Integer.parseInt(request.getParameter("no"));
		FightResultBoardSupprot supprot = Dao.getFigthResultContent(no);
		FreeBoard board = new FreeBoard();
		TimestampHandling handling = new TimestampHandling();
		boolean result = handling.isDate(supprot.getPlayer1writeDate(), supprot.getPlayer2writeDate());		
		if(result){
			board.setFrbWriteDate(supprot.getPlayer1writeDate());
		}else{
			board.setFrbWriteDate(supprot.getPlayer2writeDate());
		}
		String title = supprot.getFightNumber() + "번 대결 결과 입니다" + "( "
				+ supprot.getPlayer1() + " VS " + supprot.getPlayer2() + " )";
		board.setFrbTitle(title);
		String content = "대결번호 : " + supprot.getFightNumber() 
				+	"</br>Player : " + supprot.getPlayer1() + "님, " + supprot.getPlayer2()
				+ "님 </br>"  + "대결 신청일 : " + String.valueOf(supprot.getCallDate()) 
				+ "</br> 대결 날짜 : " + supprot.getResultDate();
		String player1 = "";
		String player2 = "";
		try{
			if(supprot.getPlayer1result() == 1){
				player1 = supprot.getPlayer1() + "님의 데이터 입력 : </br> 승리하셨다고 입력하셨습니다 </br>"
						+ "데이터 입력일 : " + String.valueOf(supprot.getPlayer1writeDate()); 
			}else if(supprot.getPlayer1result() == 0  && supprot.getPlayer1writeDate() != null){
				player1 = supprot.getPlayer1() + "님의 데이터 입력 : </br> 패배하셨다고 입력하셨습니다 </br>"
						+ "데이터 입력일 : " + String.valueOf(supprot.getPlayer1writeDate()); 
			}else{
				player1 = supprot.getPlayer1() + "님은 아직 데이터를 입력하지 않으셨습니다.";
			}
		}catch(NullPointerException e){
			player1 = supprot.getPlayer1() + "님은 아직 데이터를 입력하지 않으셨습니다.";
		}
		
		try{
			if(supprot.getPlayer2result() == 1){
				player2 = supprot.getPlayer2() + "님의 데이터 입력 : </br> 승리하셨다고 입력하셨습니다 </br>"
						+ "데이터 입력일 : " + String.valueOf(supprot.getPlayer2writeDate()); 
			}else if(supprot.getPlayer2result() == 0 && supprot.getPlayer2writeDate() != null){
				player2 = supprot.getPlayer2() + "님의 데이터 입력 : </br> 패배하셨다고 입력하셨습니다 </br>"
						+ "데이터 입력일 : " + String.valueOf(supprot.getPlayer2writeDate()); 
			}else{
				player2 = supprot.getPlayer2() + "님은 아직 데이터를 입력하지 않으셨습니다.";
			}
		}catch(NullPointerException e){
			player2 = supprot.getPlayer2() + "님은 아직 데이터를 입력하지 않으셨습니다.";
		}
		board.setFrbContent(content + "</br></br>" + player1 + "</br></br>" + player2);		
		board.setFrbHit(supprot.getHit());
		Dao.updateFightResultHit((supprot.getHit()+1), supprot.getNo());
		model.addAttribute("frb", board);		
	}
	
	@Override
	public String updateMemberMyInfo(MultipartHttpServletRequest request, HttpServletResponse response,
			HttpSession session, String path) throws Exception{
		Member member = (Member) session.getAttribute("loginUser");
		String email = member.getEmail();
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		//패스워드 변경
		if(!(password.equals("") || password == null)){
			if(password2.equals("") || password2 == null){
				scriptHandling.historyBack(response, "Pass Check가 입력되지 않았습니다");
			}else if(!(password.equals(password2))){
				scriptHandling.historyBack(response, "Password와 Pass Check가 일치 하지 않습니다");
			}else{
				Dao.updateMemberPass(email, password);
			}			
		}
		//닉네임변경
		String nickName = request.getParameter("nickName");
		if(nickName.equals("") || nickName == null){
			scriptHandling.historyBack(response, "닉네임을 입력해주세요");
		}else{
			int checkNickName = Dao.checkNickName(email, nickName);
			if(checkNickName >= 1){
				scriptHandling.historyBack(response, "중복된 닉네임이 존재합니다");
			}else if(checkNickName == 0){
				Dao.updateMemberNickName(email, nickName);
				System.out.println(email + "회원의 닉네임을 " + nickName + "으로 변경 하였습니다.");
			}
		}
		//성별수정
		String gender = request.getParameter("gender");
		if(gender.equals("") || gender == null){
			scriptHandling.historyBack(response, "잘못된 입력을 시도하셨습니다.");
		}else{
			Dao.updateMemberGender(email, gender);
		}
		//전화번호 수정
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		if( !(phone1.equals("") || phone1 == null || phone2.equals("") 
				|| phone2 == null || phone3.equals("") || phone3 == null) ){
			String phone = phone1 + "-" + phone2 + "-" + phone3;
			Dao.updatePhone(phone, member);
		}
		//한마디 수정
		String word = request.getParameter("word");
		if(!(word.equals("") || word == null)){
			Dao.updateWord(word, member);
		}
		
		//사진 수정
		MultipartFile multipartFile = request.getFile("photo");
		if(!multipartFile.isEmpty()) {
			File file = new File(path, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);
			String photo = multipartFile.getOriginalFilename();			
			Dao.updatePhoto(photo, member);
		}		
		return email;
	}
	
	@Override
	public void deleteMember(HttpServletRequest request, HttpSession session) {
		Member member = (Member) session.getAttribute("loginUser");
		String deleteId = "삭제된계정입니다";
		List<Member> memberList = Dao.getMemberIdList();
		int maxDeleteMemberNumber = 0;
 		for(Member m : memberList){
			if(m.getEmail().substring(0, 8).equals(deleteId)){
				int deleteMemberNumber = Integer.parseInt(m.getEmail().substring(8, 14));
				if(maxDeleteMemberNumber < deleteMemberNumber) maxDeleteMemberNumber = deleteMemberNumber;
			}
		}
 		int deleteNumber = maxDeleteMemberNumber + 1;
 		String support = "";
		for(int i = 6; i != 1 ; i--){
			if(deleteNumber < Math.pow(10, i)){
				support += "0";
			}
		}
		String valueOfDeleteNumber = (support + deleteNumber).trim();
		System.out.println("valueOfDeleteNumber : " + valueOfDeleteNumber);
		String email = member.getEmail();
		String nickName = member.getNickName();
		int cipher = 1000000;
		Random random = new Random();
		int randomInteger = random.nextInt(cipher);
		support = "";
		for(int i = 6; i != 1 ; i--){
			if(randomInteger < Math.pow(10, i)){
				support += "0";
			}
		}
		String pass = (support + randomInteger).trim();
		member.setEmail(deleteId + valueOfDeleteNumber);
		member.setNickName(deleteId + valueOfDeleteNumber + "( " + email + ", " + nickName + " )");
		member.setPass(pass);
		Dao.deleteMember(member, email);
	}
	
	
	
	
	
	//포인트부여 부분이 너무 방대해져서 따로 빼둠 
	private void pointGive(FightResultBoardSupprot supprot){
		Member player1 = Dao.getMember(supprot.getPlayer1());
		Member player2 = Dao.getMember(supprot.getPlayer2());
		int givePointPenalty = -1000;
		int givePointLose = 50;
		int givePointWin = 500;
		int point = 0;
		int penalty = 0;
		int win = 0;
		int lose = 0;
		if(supprot.getPlayer1result() == supprot.getPlayer2result()){
			point = player1.getPoint() + givePointPenalty;
			penalty = player1.getPenalty() + 1;
			Dao.updateMemberPenalty(player1.getEmail(), point, penalty);
			point = player2.getPoint() + givePointPenalty;
			penalty = player2.getPenalty() + 1;
			Dao.updateMemberPenalty(player2.getEmail(), point, penalty);
		}else{
			if(supprot.getPlayer1result() == 0){
				point = player1.getPoint() + givePointLose;
				lose = player1.getLose() + 1;
				Dao.updateMemberLose(player1.getEmail(), point, lose);
				point = player2.getPoint() + givePointWin;
				win = player2.getWin() + 1;
				Dao.updateMemberWin(player2.getEmail(), point, win);
			}else{
				point = player1.getPoint() + givePointWin;
				win = player1.getWin() + 1;
				Dao.updateMemberWin(player1.getEmail(), point, win);
				point = player2.getPoint() + givePointLose;
				lose = player2.getLose() + 1;
				Dao.updateMemberLose(player2.getEmail(), point, lose);				
			}
		}		
	}
	
	
	//테스트용 
	@Override
	public void testService(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Object object = session.getAttribute("loginUser");
		System.out.println((object == null));
		System.out.println(object.toString());
	}
	
	
	
}












