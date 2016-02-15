package com.project.call.hyunsu.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.Area;

public interface HSService {

	//테스트용 
	public void testService(HttpServletRequest request, HttpServletResponse response, HttpSession session);
	
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

	//닉네임 검색 자동완성
	public void searchNickName(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception;

	//대결신청요청 처리 
	public void addAsk(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception;

	//아이디 비밀번호 찾기 로직1
	public void findIdPassAjax(HttpServletRequest request, HttpServletResponse response) throws Exception;

	//대결결과 입력
	public void fightResultMySelf(HttpServletRequest request, HttpSession session);
	
	//승부결과 게시판 리스트를 가져온다
	public void fightResultBoardList(HttpServletRequest request, HttpSession session, Model model);

	//승부결과 게시판 상세정보를 가져온다
	public void getFigthResultContent(HttpServletRequest request, Model model);
	
	//myInfo에서 회원 정보 수정을 요구 받아 요청 처리를 완료하고 로그인유저의 eMail(PK)를 반납한다.
	public String updateMemberMyInfo(MultipartHttpServletRequest request, HttpServletResponse response, HttpSession session, String path) throws Exception;
	
	
	
	
	
	
}
