package com.project.call.wansoon.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.call.wansoon.dao.WSDao;
import com.project.call.wansoon.service.WSService;

@Service
public class WSServiceImpl implements WSService {

	@Autowired
	private WSDao jBDao;
	
	public void setjBDao(WSDao jBDao) {
		this.jBDao = jBDao;
	}
}
