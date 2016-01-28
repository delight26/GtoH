package com.project.call.ikjae.dao;

import java.util.List;

import com.project.call.domain.FightBoard;
import com.project.call.domain.Member;

public interface IJDao {
	
	public Member getMember(String loginUser);
	public List<FightBoard> getFight(String loginUser);

}
