package com.project.call.wansoon.dao;

import java.util.List;

import com.project.call.domain.FreeBoard;
import com.project.call.domain.FreebComment;


public interface WSDao {

	public List<FreeBoard> getFreeBoardList(int startRow, int PAGE_SIZE);
	
	public FreeBoard getFreeBoard(int frbNo);
	
	public int FreeBoardCount();
	
	public void insertWrite(FreeBoard freeboard, String filePath);
	
	public void modifyWrite(FreeBoard freeboard, String filePath);
	
	public void deleteBoard(int frbNo);
	
	public void addComment(FreebComment freebComment);
	
	public List<FreebComment> commentAllList(int bno);
	
	public void modifyComment(FreebComment freebcomment);

}
