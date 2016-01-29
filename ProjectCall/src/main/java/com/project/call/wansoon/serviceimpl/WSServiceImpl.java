package com.project.call.wansoon.serviceimpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.call.domain.FreeBoard;
import com.project.call.wansoon.dao.WSDao;
import com.project.call.wansoon.service.WSService;

@Service
public class WSServiceImpl implements WSService {

	@Autowired
	private WSDao jBDao;
	
	public void setjBDao(WSDao jBDao) {
		this.jBDao = jBDao;
	}

	@Override
	public List<FreeBoard> getFreeBoardAll(HttpServletRequest req) {
		
		return jBDao.getFreeBoardAll();
	}

	@Override
	public FreeBoard getFreeBoard(int frbNo) {
		
		return jBDao.getFreeBoard(frbNo);
	}



	
	
}
