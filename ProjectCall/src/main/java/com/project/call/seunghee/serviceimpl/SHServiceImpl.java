package com.project.call.seunghee.serviceimpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.call.domain.FreeBoard;
import com.project.call.seunghee.dao.SHDao;
import com.project.call.seunghee.service.SHService;

@Service
public class SHServiceImpl implements SHService {

	@Autowired
	private SHDao shDao;
	
	private static final int PAGE_SIZE = 10;
	private static final int PAGE_GROUP = 10;
	
	public void setshDao(SHDao shDao) {
		this.shDao = shDao;
	}
	
	@Override
	public void getNoticeList(HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");
		
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.valueOf(pageNum);

		int startRow = currentPage * PAGE_SIZE - PAGE_SIZE;
		int listCount = shDao.getBoardCount();
		List<FreeBoard> noticeList = null;
		
		if (listCount > 0) {

			noticeList = shDao.getNoticeList(startRow, PAGE_SIZE);

			int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);

			int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
					- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);

			int endPage = startPage + PAGE_GROUP - 1;

			if (endPage > pageCount) {
				endPage = pageCount;
			}

			request.setAttribute("noticeList", noticeList);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("PAGE_GROUP", PAGE_GROUP);
		}
		
	}
	
	@Override
	public FreeBoard getNoticeContent(HttpServletRequest request) {
		int no = Integer.valueOf(request.getParameter("no"));
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		System.out.println(no);
		FreeBoard notice = shDao.getNoticeContent(no);

		request.setAttribute("notice", notice);
		request.setAttribute("no", no);
		request.setAttribute("pageNum", pageNum);
		
		return shDao.getNoticeContent(no);
	}
	
	@Override
	public void noticeDelete(HttpServletRequest request) {
		int no = Integer.valueOf(request.getParameter("no"));
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		
		shDao.noticeDelete(no);
		request.setAttribute("no", no);
		request.setAttribute("pageNum", pageNum);
		
	}
}
