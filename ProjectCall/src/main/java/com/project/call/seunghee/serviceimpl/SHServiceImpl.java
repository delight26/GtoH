package com.project.call.seunghee.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.call.seunghee.dao.SHDao;
import com.project.call.seunghee.service.SHService;

@Service
public class SHServiceImpl implements SHService {

	@Autowired
	private SHDao jBDao;
	
	public void setjBDao(SHDao jBDao) {
		this.jBDao = jBDao;
	}
}
