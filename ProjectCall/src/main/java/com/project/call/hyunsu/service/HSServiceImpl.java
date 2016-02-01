package com.project.call.hyunsu.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.call.domain.Member;
import com.project.call.hyunsu.dao.HSDao;

@Service
public class HSServiceImpl implements HSService {

	@Autowired
	private HSDao Dao;
	
	public void setjBDao(HSDao jBDao) {
		this.Dao = jBDao;
	}
	
	@Override
	public void checkMemberId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList<Member> idList = (ArrayList<Member>) Dao.getMemberIdList();
		
		response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        String getId = request.getParameter("id");
		String result = "<font color='green'>사용할 수 있는 ID 입니다.</font>";
		for(Member id : idList){
			if(getId.equals(id.getEmail())){
				result = "<font color='red'>이미 등록된 ID 입니다.</font>";
			}
		}
		out.println(result);			
	}
	
	
}
