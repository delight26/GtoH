package com.project.call.ikjae.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.call.domain.FightBoard;
import com.project.call.domain.Member;
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
	public List<FightBoard> getFight(String loginUser) {
		return ijDao.getFight(loginUser);
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
			
		}
	}

	@Override
	public void deleteMember(String loginUser) {
		ijDao.deleteMember(loginUser);
	}
	
	
}
