package com.project.call.yoonseok.service;

import java.util.List;

import com.project.call.domain.Member;
import com.project.call.domain.NoticeBoard;

public interface YSService {
	public List<Member> ranking();
	public void addNote(NoticeBoard note);

}
