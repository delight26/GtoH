package com.project.call.junbum.dao;

import java.sql.Timestamp;
import java.util.List;

import com.project.call.domain.AskBoard;
import com.project.call.domain.Comment;
import com.project.call.domain.FreeBoard;
import com.project.call.domain.Member;
import com.project.call.domain.PointProduct;

public interface JBDao {
	public Member getloginResult(String email);

	public Integer getProductCount();

	public List<PointProduct> getproductList(int startRow, int PAGE_SIZE);

	public void addProduct(PointProduct p);

	public PointProduct productContent(int pNo);

	public void updateProduct(PointProduct p);

	public void productDelete(int pProductCode);

	public void orderProduct(PointProduct p, Member m);

	public Integer getaggroCount();

	public List<FreeBoard> getAggroList(int startRow, int PAGE_SIZE);

	public void aggroBoardWrite(FreeBoard fb);

	public void aggroBoardWritephoto(FreeBoard fb);

	public void aggroHitUpdate(int frbHit, int frbNo);

	public FreeBoard aggroContent(int frbNo);

	public Integer aggroPreNo(int frbNo);

	public Integer aggroNextNo(int frbNo);

	public void aggroBoardUpdatePhoto(FreeBoard frb);
	
	public void aggroBoardUpdate(FreeBoard frb);
	
	public void aggroDelete(int frbNo);
	
	public Integer aggroSearchCount(String search);
	
	public List<FreeBoard> aggroSearch(String search, int startRow, int PAGE_SIZE);

	public Integer getCommentCount(int frbNo);
	
	public List<Comment> getComment(int frbNo, int startRow, int PAGE_SIZE);
	
	public void aggroCommentWrite(Comment c);
	
	public void aggroCommentUpdate(Comment c);
	
	public void aggroCommentDelete(int cNo);
	
	public List<AskBoard> askResultList(String email);
	
	public AskBoard getAskResult(int abNo);
	
	public List<AskBoard> askReceveList(String nickName);
	
	public void askResultUpdateResult(AskBoard ab);
	
	public void askResultDelete(int abNo);
	
	public void askApproval(int abNo);
	
	public void askCancel(int abNo);
	
}
