package com.project.call.ikjae.service;

import java.util.List;

import com.project.call.domain.FightBoard;
import com.project.call.domain.Member;

public interface IJService {
	
	public Member getMember(String loginUser);
	public List<FightBoard> getFight(String loginUser);
	public int passwordCheck(String loginUser, String password);

}
