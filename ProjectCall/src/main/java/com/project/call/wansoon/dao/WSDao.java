package com.project.call.wansoon.dao;

import java.util.List;

import com.project.call.domain.Comment;
import com.project.call.domain.FreeBoard;



public interface WSDao {

	public Integer getfreeCount();

	public List<FreeBoard> getfreeList(int startRow, int PAGE_SIZE);

	public void freeBoardWrite(FreeBoard fb);

	public void freeBoardWritephoto(FreeBoard fb);

	public void freeHitUpdate(int frbHit, int frbNo);

	public FreeBoard freeContent(int frbNo);

	public Integer freePreNo(int frbNo);

	public Integer freeNextNo(int frbNo);

	public void freeBoardUpdatePhoto(FreeBoard frb);
	
	public void freeBoardUpdate(FreeBoard frb);
	
	public void freeDelete(int frbNo);
	
	public Integer freeSearchCount(String search);
	
	public List<FreeBoard> freeSearch(String search, int startRow, int PAGE_SIZE);

	public Integer getCommentCount(int frbNo);
	
	public List<Comment> getComment(int frbNo, int startRow, int PAGE_SIZE);
	
	public void freeCommentWrite(Comment c);
	
	public void freeCommentUpdate(Comment c);
	
	public void freeCommentDelete(int cNo);
	
}
