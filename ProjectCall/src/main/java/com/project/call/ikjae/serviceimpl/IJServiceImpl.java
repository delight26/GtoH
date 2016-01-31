package com.project.call.ikjae.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.call.domain.FightBoard;
import com.project.call.domain.Member;
import com.project.call.domain.FightResultBoard;
import com.project.call.ikjae.dao.IJDao;
import com.project.call.ikjae.service.IJService;

@Service
public class IJServiceImpl implements IJService {

	@Autowired
	private IJDao ijDao;
	
	public void setijDao(IJDao ijDao) {
		this.ijDao = ijDao;
	}

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
	public int nickNameCheck(String loginUser, String nickName) {
		return ijDao.nickNameCheck(loginUser, nickName);
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
			String title, String loginUser, String content, String filePath)
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

	
	
}
















