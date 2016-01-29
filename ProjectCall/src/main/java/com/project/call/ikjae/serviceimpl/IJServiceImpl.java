package com.project.call.ikjae.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.call.domain.FightBoard;
import com.project.call.domain.Member;
import com.project.call.ikjae.dao.IJDao;
import com.project.call.ikjae.service.IJService;

@Service
public class IJServiceImpl implements IJService {

	@Autowired
	private IJDao jBDao;
	
	public void setjBDao(IJDao jBDao) {
		this.jBDao = jBDao;
	}

	@Override
	public Member getMember(String loginUser) {
		return jBDao.getMember(loginUser);
	}

	@Override
	public List<FightBoard> getFight(String loginUser) {
		return jBDao.getFight(loginUser);
	}
	
	
}
