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
	
	
}
