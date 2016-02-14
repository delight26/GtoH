package com.project.call.ikjae.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.FightBoard;
import com.project.call.domain.FightResultBoard;
import com.project.call.domain.Member;

public interface IJService {
	
	public Member getMember(String loginUser);
	public void getFightList(String loginUser, HttpServletRequest request);
	public int passwordCheck(String loginUser, String password);
	public int nickNameCheck(String nickName, String email);
	public void updateMember(MultipartFile multipartFile, String email, String password,
			String nickName, String gender, String phone, String word, String filePath)
					throws IllegalStateException, IOException;
	public void deleteMember(String loginUser);
	public FightBoard getFight(int fightNumber);
	public void addFightResultBoardResult(MultipartFile multipartFile, String fightNumber,
				String title, String loginUser, String content, String winner, String filePath)
						throws IllegalStateException, IOException;
	public List<FightResultBoard> getFightResultBoardList(int pageNum);
	public FightResultBoard getFightResultBoard(int no);
	public void adminConfirm(int no);
	public void updateFightResultBoardResult(MultipartFile multipartFile, String fightNumber,
			String title, String loginUser, String content, String winner, String filePath)
					throws IllegalStateException, IOException ;
	public void deleteFightResultBoard(int no);
	public void hitUp(int parseInt);
	public Member updateMember(MultipartHttpServletRequest request, HttpServletResponse response, String filePath) throws Exception;
	
}
