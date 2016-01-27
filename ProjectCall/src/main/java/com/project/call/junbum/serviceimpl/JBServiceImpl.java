package com.project.call.junbum.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.call.junbum.dao.JBDao;
import com.project.call.junbum.service.JBService;

@Service
public class JBServiceImpl implements JBService {

	@Autowired
	private JBDao jBDao;
	
	public void setjBDao(JBDao jBDao) {
		this.jBDao = jBDao;
	}
}
