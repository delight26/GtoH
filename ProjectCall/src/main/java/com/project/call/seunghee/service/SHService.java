package com.project.call.seunghee.service;

import javax.servlet.http.HttpServletRequest;

import com.project.call.domain.FreeBoard;

public interface SHService {
	
	public void getNoticeList(HttpServletRequest request);

	public FreeBoard getNoticeContent(HttpServletRequest request);

	public void noticeDelete(HttpServletRequest request);

	
}
