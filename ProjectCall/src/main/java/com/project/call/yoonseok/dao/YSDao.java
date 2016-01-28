package com.project.call.yoonseok.dao;

import java.util.List;

import com.project.call.domain.Member;
import com.project.call.domain.NoticeBoard;

public interface YSDao {

	public List<Member> ranking();
	public void addNote(NoticeBoard note);
	public List<NoticeBoard> getNote(String toid);
	
}
