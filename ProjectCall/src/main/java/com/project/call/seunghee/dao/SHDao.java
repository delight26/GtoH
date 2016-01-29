package com.project.call.seunghee.dao;

import java.util.List;

import com.project.call.domain.FreeBoard;


public interface SHDao {
	
	public int getBoardCount();
	
	public List<FreeBoard> getNoticeList(int startRow, int PAGE_SIZE);

	public FreeBoard getNoticeContent(int no);

	public void noticeDelete(int no);

}
