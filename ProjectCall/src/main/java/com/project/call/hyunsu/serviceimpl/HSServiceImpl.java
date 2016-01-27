package com.project.call.hyunsu.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.call.hyunsu.dao.HSDao;
import com.project.call.hyunsu.service.HSService;

@Service
public class HSServiceImpl implements HSService {

	@Autowired
	private HSDao jBDao;
	
	public void setjBDao(HSDao jBDao) {
		this.jBDao = jBDao;
	}
}
