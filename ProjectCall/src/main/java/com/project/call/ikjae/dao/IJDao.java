package com.project.call.ikjae.dao;

import java.util.List;

import com.project.call.domain.FightBoard;
import com.project.call.domain.Member;
import com.project.call.domain.FightResultBoard;

public interface IJDao {
	
	public Member getMember(String loginUser);
	public int getFightCount(String loginUser);
//	public List<FightBoard> getFightList(int startRow, int PAGE_SIZE);
	public int passwordCheck(String loginUser, String password);
	public int nickNameCheck(String nickName);
	public void updateMember(Member m);
	public void deleteMember(String loginUser);
	public FightBoard getFight(int fightNumber);
	public void addFightResultBoardResult(FightResultBoard frb);
	public List<FightResultBoard> getFightResultBoardList(int pageNum);
	public FightResultBoard getFightResultBoard(int no);
	public void adminConfirm(int no);
	public void updateFightResultBoardResult(FightResultBoard frb);
	public void deleteFightResultBoard(int no);
	public void hitUp(int parseInt);
	//닉네임 리스트를 가져온다
	public List<String> getNickNameList();
	public void updateResult(String fightNumber);
	public List<FightResultBoard> getAdminFightResultBoardList(); 
	public List<FightBoard> getFightList(String loginUser ,int startRow, int PAGE_SIZE);

}
