package com.project.call.hyunsu.dao;

import java.sql.Timestamp;
import java.util.List;

import com.project.call.domain.Area;
import com.project.call.domain.AskInjection;
import com.project.call.domain.Fight;
import com.project.call.domain.FightResult;
import com.project.call.domain.FightResultBoardSupprot;
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
	
	//랜덤생성 이메일 저장
	public void setPassMember(String email, String pass);
	
	//FightResult Table에 데이터가 있는지 확인한다
	public int getFightResultCount(int fightNumber);
	
	//Fight의 데이터를 가져온다
	public Fight getFight(int fightNumber);
	
	//fightResult type1
	public void insertFightResultPlayer1(int result, int fightNumber, Timestamp nowTime);
	
	//fightResult type2
	public void insertFightResultPlayer2(int result, int fightNumber, Timestamp nowTime);
	
	//fightResult type3
	public void updateFightResultPlayer1(int result, int fightNumber, Timestamp nowTime);
	
	//fightResult type4
	public void updateFightResultPlayer2(int result, int fightNumber, Timestamp nowTime);

	//fight테이블에 결과 등록했는지 입력.. 원래는 존재해서 안되지만 급하므로 컬럼추가
	public void updateFight(int state, int fightNumber);
	
	//fightResult의 카운터를 받아온다
	public int getFightResultCount();
	
	//fightResult의 리스트를 가져온다
	public List<FightResult> getFightResultList(int startRow, int PAGE_SIZE);
	
	//getFight Type2 닉네임도 가져온다
	public Fight getFightAsNickname(int fightNumber);
	
	//fightresult의 결과와 fight결과를 조인하여  support 객체로 반환한다(no 이용)
	public FightResultBoardSupprot getFigthResultContent(int no);
	
	//fightResult Table hit count update;
	public void updateFightResultHit(int hit, int no);
	
	//fightresult의 결과와 fight결과를 조인하여  support 객체로 반환한다(fightNumber 이용)
	public FightResultBoardSupprot getFigthResultContentUseFightNumber(int fightNumber);
	
	//email을 이용하여 회원의 정보를 가져온다
	public Member getMember(String email);
	
	//회원 정보를 업데이트한다(포인트, 패널티)
	public void updateMemberPenalty(String email, int point, int penalty);
	
	//회원 정보를 업데이트한다(포인트, 패배)
	public void updateMemberLose(String email, int point, int lose);
	
	//회원 정보를 업데이트한다(포인트, 승리)
	public void updateMemberWin(String email, int point, int win);
	
	
	
	
	
	
}
