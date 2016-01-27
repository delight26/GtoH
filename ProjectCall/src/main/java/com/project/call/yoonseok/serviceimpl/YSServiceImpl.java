package com.project.call.yoonseok.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.call.yoonseok.dao.YSDao;
import com.project.call.yoonseok.service.YSService;

@Service
public class YSServiceImpl implements YSService {

	@Autowired
	private YSDao jBDao;
	
	public void setjBDao(YSDao jBDao) {
		this.jBDao = jBDao;
	}
}
