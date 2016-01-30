package com.project.call.ikjae.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.call.domain.FightBoard;
import com.project.call.domain.Member;

public interface IJService {
	
	public Member getMember(String loginUser);
	public List<FightBoard> getFight(String loginUser);
	public int passwordCheck(String loginUser, String password);
	public int nickNameCheck(String loginUser, String nickName);
	public void updateMember(MultipartFile multipartFile, String email, String password,
			String nickName, String gender, String phone, String word, String filePath)
					throws IllegalStateException, IOException;
	
	public void deleteMember(String loginUser);

}
