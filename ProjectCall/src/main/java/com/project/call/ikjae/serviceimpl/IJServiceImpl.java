package com.project.call.ikjae.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.call.ikjae.dao.IJDao;
import com.project.call.ikjae.service.IJService;

@Service
public class IJServiceImpl implements IJService {

	@Autowired
	private IJDao jBDao;
	
	public void setjBDao(IJDao jBDao) {
		this.jBDao = jBDao;
	}
}
