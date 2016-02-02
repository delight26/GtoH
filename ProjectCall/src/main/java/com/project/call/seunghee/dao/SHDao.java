package com.project.call.seunghee.dao;

import java.util.List;

import com.project.call.domain.FreeBoard;
import com.project.call.domain.Member;


public interface SHDao {
	
	public int getBoardCount();
	
	public List<FreeBoard> getNoticeList(int startRow, int PAGE_SIZE);

	public FreeBoard getNoticeContent(int no);

	public void noticeDelete(int no);

	public void noticeWrite(FreeBoard noticeboard);

	public void noticeModify(FreeBoard noticeboard);

	public List<Member> getSeoulRanking();

	public List<Member> getGyeonggiRanking();

	public List<Member> getKangwonRanking();

	public List<Member> getChungcheongRanking();

	public List<Member> getGyeongsangRanking();

	public List<Member> getJunlaRanking();

	public List<Member> getJejuRanking();

}
