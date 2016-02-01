package com.project.call.hyunsu.dao;

import java.util.List;

import com.project.call.domain.Member;

public interface HSDao {

	//id 리스트를 가져온다
	public List<Member> getMemberIdList();
	
}
