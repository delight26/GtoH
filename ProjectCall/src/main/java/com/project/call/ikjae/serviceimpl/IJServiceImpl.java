package com.project.call.ikjae.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.FightBoard;
import com.project.call.domain.FightResultBoard;
import com.project.call.domain.Member;
import com.project.call.hyunsu.supprot.ScriptHandling;
import com.project.call.ikjae.dao.IJDao;
import com.project.call.ikjae.service.IJService;

@Service
public class IJServiceImpl implements IJService {

	@Autowired
	private IJDao ijDao;
	
	public void setijDao(IJDao ijDao) {
		this.ijDao = ijDao;
	}
	
	@Autowired
	private ScriptHandling scriptHandling;

	@Override
	public Member getMember(String loginUser) {
		return ijDao.getMember(loginUser);
	}

	@Override
	public List<FightBoard> getFightList(String loginUser) {
		return ijDao.getFightList(loginUser);
	}

	@Override
	public int passwordCheck(String loginUser, String password) {
		return ijDao.passwordCheck(loginUser, password);
	}

	@Override
	public int nickNameCheck(String nickName) {
		return ijDao.nickNameCheck(nickName);
	}

	@Override
	public void updateMember(MultipartFile multipartFile, String email, String password,
			String nickName, String gender, String phone, String word, String filePath)
					throws IllegalStateException, IOException {
	
		
		if(!multipartFile.isEmpty()) {
			
			File file = new File(filePath, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);
			
			Member member = new Member();
					
					member.setEmail(email);
					member.setPass(password);
					member.setNickName(nickName);
					member.setGender(gender);
					member.setPhone(phone);
					member.setWord(word);
					member.setProfilPhoto(multipartFile.getOriginalFilename());
			
			ijDao.updateMember(member);
			
		} else {
			
			Member member = new Member();
			
			member.setEmail(email);
			member.setPass(password);
			member.setNickName(nickName);
			member.setGender(gender);
			member.setPhone(phone);
			member.setWord(word);
			member.setProfilPhoto(null);
	
			ijDao.updateMember(member);
			
		}
	
			
	}

	@Override
	public void deleteMember(String loginUser) {
		ijDao.deleteMember(loginUser);
	}

	@Override
	public FightBoard getFight(int fightNumber) {
		return ijDao.getFight(fightNumber);
	}

	@Override
	public void addFightResultBoardResult(MultipartFile multipartFile, String fightNumber,
			String title, String loginUser, String content, String winner, String filePath)
					throws IllegalStateException, IOException {
		
		if(!multipartFile.isEmpty()) {
			
			File file = new File(filePath, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);
		
			FightResultBoard frb = new FightResultBoard();
			
			frb.setFightNumber(Integer.parseInt(fightNumber));
			frb.setTitle(title);
			frb.setWriter(loginUser);
			frb.setContent(content);
			frb.setPhoto(multipartFile.getOriginalFilename());
			frb.setIsAdminCheck(0);
			frb.setHit(0);
			frb.setWinner(winner);
			long currentTime = System.currentTimeMillis();
			frb.setWriteDate(new Timestamp(currentTime));
			
			ijDao.addFightResultBoardResult(frb);
			
		} else {
			
			FightResultBoard frb = new FightResultBoard();
			
			frb.setFightNumber(Integer.parseInt(fightNumber));
			frb.setTitle(title);
			frb.setContent(content);
			frb.setPhoto(null);
			frb.setIsAdminCheck(0);
			frb.setHit(0);
			long currentTime = System.currentTimeMillis();
			frb.setWriteDate(new Timestamp(currentTime));
			
			ijDao.addFightResultBoardResult(frb);
			
		}
	
		
	}

	@Override
	public List<FightResultBoard> getFightResultBoardList() {
		return ijDao.getFightResultBoardList();
	}

	@Override
	public FightResultBoard getFightResultBoard(int no) {
		return ijDao.getFightResultBoard(no);
	}

	@Override
	public void adminConfirm(int no) {
		ijDao.adminConfirm(no);
	}

	@Override
	public void updateFightResultBoardResult(MultipartFile multipartFile, String fightNumber, String title,
			String loginUser, String content, String winner, String filePath)
					throws IllegalStateException, IOException {
		
		if(!multipartFile.isEmpty()) {
			
			File file = new File(filePath, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);
		
			FightResultBoard frb = new FightResultBoard();
			
			frb.setFightNumber(Integer.parseInt(fightNumber));
			frb.setTitle(title);
			frb.setWriter(loginUser);
			frb.setContent(content);
			frb.setPhoto(multipartFile.getOriginalFilename());
			frb.setIsAdminCheck(0);
			frb.setHit(0);
			frb.setWinner(winner);
			long currentTime = System.currentTimeMillis();
			frb.setWriteDate(new Timestamp(currentTime));
			
			ijDao.updateFightResultBoardResult(frb);
			
		} else {
			
			FightResultBoard frb = new FightResultBoard();
			
			frb.setFightNumber(Integer.parseInt(fightNumber));
			frb.setTitle(title);
			frb.setContent(content);
			frb.setPhoto(null);
			frb.setIsAdminCheck(0);
			frb.setHit(0);
			long currentTime = System.currentTimeMillis();
			frb.setWriteDate(new Timestamp(currentTime));
			
			ijDao.updateFightResultBoardResult(frb);
			
		}
		
	}

	@Override
	public void deleteFightResultBoard(int no) {
		ijDao.deleteFightResultBoard(no);
	}

	@Override
	public void hitUp(int parseInt) {
		ijDao.hitUp(parseInt);
	}


	@Override
	public Member updateMember(MultipartHttpServletRequest request, HttpServletResponse response, String filePath)
					throws Exception {
		
		MultipartFile multipartFile = request.getFile("photo");
		int state = 1;
		int temp = 1;
		String email = request.getParameter("email");
		if(email.equals("") || email == null) state = 0;
		Member member = ijDao.getMember(email);
		String password = request.getParameter("password");
		if(password.equals("") || password == null) state = 0;
		String password2 = request.getParameter("password2");
		if(password2.equals("") || password2 == null) state = 0;
		if(!password.equals(password2)) state = 0;
		List<String> nickNameList = ijDao.getNickNameList();
		String nickName = request.getParameter("nickName");
		if(nickName.equals("") || nickName == null) state = 0;
		for(String nick : nickNameList){
			if(nick.equals(nickName) && nick.equals(member.getNickName())){
				temp = 0;
			}
		}
		if(temp == 0 ) state = 0;
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String word = request.getParameter("word");
		if(state == 0) scriptHandling.historyBack(response, "필수사항이 누락되었습니다");
		member = null;
		if(!multipartFile.isEmpty()) {
			File file = new File(filePath, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);
			member = new Member();
			member.setEmail(email);
			member.setPass(password);
			member.setNickName(nickName);
			member.setGender(gender);
			member.setPhone(phone);
			member.setWord(word);
			member.setProfilPhoto(multipartFile.getOriginalFilename());
			ijDao.updateMember(member);
			
		} else {
			member = new Member();
			member.setEmail(email);
			member.setPass(password);
			member.setNickName(nickName);
			member.setGender(gender);
			member.setPhone(phone);
			member.setWord(word);
			member.setProfilPhoto(null);
			ijDao.updateMember(member);
		}
		return member;	
	}

	
}
















