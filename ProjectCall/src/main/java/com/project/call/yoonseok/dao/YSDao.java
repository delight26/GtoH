package com.project.call.yoonseok.dao;

import java.util.List;

import com.project.call.domain.Member;
import com.project.call.domain.NoticeBoard;

public interface YSDao {

	public List<Member> ranking();

	public void addNote(NoticeBoard note);

	public List<NoticeBoard> getNote(String toid, int pageNum);

	public NoticeBoard noteContent(int nbNo);

	public void deleteNote(int nbNo);

	public int noteCheck(String toid);


	// 리스트의 카운트를 가져온다(sesson저장 아이디 기준)
	public int getCount(String toId);

	//
	public List<String> nickNameSearch(String nickName);

	// 리스트의 카운트를 가져온다(sesson저장 아이디 기준)

	//
	public List<NoticeBoard> getNoticeBoard(String toId, int startRow, int endRow);

	// 선택한 멤버의 데이터를 가져온다
	public Member getMember(String toId);
}