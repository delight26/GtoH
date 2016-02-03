package com.project.call.ikjae.dao;

import java.util.List;

import com.project.call.domain.FightBoard;
import com.project.call.domain.Member;
import com.project.call.domain.FightResultBoard;

public interface IJDao {
	
	public Member getMember(String loginUser);
	public List<FightBoard> getFightList(String loginUser);
	public int passwordCheck(String loginUser, String password);
	public int nickNameCheck(String nickName);
	public void updateMember(Member m);
	public void deleteMember(String loginUser);
	public FightBoard getFight(int fightNumber);
	public void addFightResultBoardResult(FightResultBoard frb);
	public List<FightResultBoard> getFightResultBoardList();
	public FightResultBoard getFightResultBoard(int no);
	public void adminConfirm(int no);
	public void updateFightResultBoardResult(FightResultBoard frb);
	public void deleteFightResultBoard(int no);
	public void hitUp(int parseInt);
}
