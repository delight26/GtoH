package com.project.call.hyunsu.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HSService {

	//id의 중복값을 체크한다
	public void checkMemberId(HttpServletRequest request, HttpServletResponse response)  throws IOException;
		
}
