package com.project.call.wansoon.dao;

import java.util.List;

import com.project.call.domain.Comment;
import com.project.call.domain.FreeBoard;



public interface WSDao {
	//보드 리스트를 가져온다
	public List<FreeBoard> getFreeBoardList(int startRow, int PAGE_SIZE);
	
	public FreeBoard getFreeBoard(int frbNo);
	
	public int getFreeBoardCount();
	
	public void insertWrite(FreeBoard freeboard);
	
	public void modifyWrite(FreeBoard freeboard, String filePath);
	
	public void deleteBoard(int frbNo);
	
	//댓글추가
	//02.11 테이블 통합완료
	public void addComment(Comment freebComment);
	//댓글을 가져온다
	//02.11 테이블 통합완료
	public List<Comment> commentAllList(int bno);
	
	public void modifyComment(Comment freebcomment);
	
	public Integer freeSearchCount(String search);
	
	public List<FreeBoard> freeSearch(String search, int startRow, int PAGE_SIZE);

	public FreeBoard freeContent(int frbNo);
	
	public void freeBoardUpdatePhoto(FreeBoard frb);
	
	public void freeBoardUpdate(FreeBoard frb);
	
	public Integer freePreNo(int frbNo);
	
	public Integer freeNextNo(int frbNo);
	
	
}
