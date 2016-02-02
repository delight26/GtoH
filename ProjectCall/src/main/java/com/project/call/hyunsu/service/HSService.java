package com.project.call.hyunsu.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.Area;
import com.project.call.hyunsu.email.Email;

public interface HSService {

	//id의 중복값을 체크한다
	public void checkMemberId(HttpServletRequest request, HttpServletResponse response)  throws IOException;

	//회원가입 요청자의 이메일을 체크한다
	public String emailCheck(HttpServletRequest request, HttpServletResponse response, HttpSession session)  throws Exception ;
	
	//인증코드를 비교한다
	public void getSendCodeCheck(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception;

	//회원가입 1차(필수정보만 해당)
	public void addMember(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception;
	
	//출몰지역 리스트 가져오기
	public List<Area> getAreaList();

	//회원가입 2차(추가정보 입력)
	public void additionalInformationMember(MultipartHttpServletRequest request, HttpServletResponse response, HttpSession session, String path) throws Exception;

	//닉네임중복을 체크한다
	public void nickNameCheck(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
