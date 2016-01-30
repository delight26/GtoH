package com.project.call.yoonseok.service;

import java.util.List;

import com.project.call.domain.Member;
import com.project.call.domain.NoticeBoard;

public interface YSService {
	public List<Member> ranking();
	public void addNote(NoticeBoard note);
	public List<NoticeBoard> getNote(String toid, int pageNum);
	public NoticeBoard noteContent(int nbNo);
	public void deleteNote(int nbNo);
	
	
}
