package com.project.call.hyunsu.dao;

import java.util.List;

import com.project.call.domain.Area;
import com.project.call.domain.AskInjection;
import com.project.call.domain.Member;

public interface HSDao {

	//멤버 리스트를 가져온다
	public List<Member> getMemberIdList();
		
	//필수정보 입력
	public void addMemberCompulsory(Member member); 

	//출몰지역 리스트 가져오기
	public List<Area> getAreaList();
	
	//추가정보(전화번호)
	public void updatePhone(String phone, Member member);

	//추가정보(주소)
	public void updateAddress(String address, Member member);

	//추가정보(선호지역)
	public void updateArea(String area, Member member);

	//추가정보(한마디)
	public void updateWord(String word, Member member);

	//추가정보(사진)
	public void updatePhoto(String photo, Member member);

	//추가정보입력된 아이디의 DB값을 다시 불러온다(세션저장용)
	public Member getMember(Member member);

	//닉네임리스트를 가져온다
	public List<String> getNickNameList(String keyword);

	//대결신청 추가
	public void insertAsk(AskInjection ask);
}
