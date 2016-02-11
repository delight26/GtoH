package com.project.call.wansoon.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.project.call.domain.FreeBoard;
import com.project.call.domain.FreebComment;
import com.project.call.wansoon.dao.WSDao;
import com.project.call.wansoon.service.WSService;

@Service
public class WSServiceImpl implements WSService {

	@Autowired
	private WSDao WSDao;
	private static final int PAGE_SIZE = 10;
	private static final int PAGE_GROUP = 10;
	
	public void setjBDao(WSDao WSDao) {
		this.WSDao = WSDao;
	}

	@Override
	public void getFreeBoardList(HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.valueOf(pageNum);

		int startRow = currentPage * PAGE_SIZE - PAGE_SIZE;
		int listCount = WSDao.FreeBoardCount();
		
		if (listCount > 0) {
			List<FreeBoard> frbList = WSDao.getFreeBoardList(startRow, PAGE_SIZE);
			
			int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);

			int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
					- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);

			int endPage = startPage + PAGE_GROUP - 1;

			if (endPage > pageCount) {
				endPage = pageCount;
			}
			
			request.setAttribute("frbList", frbList);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("PAGE_GROUP", PAGE_GROUP);
			
		}
	
	}

	@Override
	public FreeBoard getFreeBoard(int frbNo) {
		
		return WSDao.getFreeBoard(frbNo);
	}

	@Override
	public void insertWrite(FreeBoard freeboard, String filePath) {
	
		WSDao.insertWrite(freeboard,filePath);
	}

	@Override
	public void modifyWrite(FreeBoard freeboard, String filePath) {
		
		WSDao.modifyWrite(freeboard, filePath);
	}
	
	@Override
	public void deleteBoard(int frbNo) {
		WSDao.deleteBoard(frbNo);
		
	}
	
	@Override
	public void addComment(FreebComment freebComment) {
		
		WSDao.addComment(freebComment);
		
	}

	@Override
	public List<FreebComment> commentAllList(int bno) {
		
		return WSDao.commentAllList(bno);
	}

	@Override
	public void modifyComment(FreebComment freebcomment) {
		
		WSDao.modifyComment(freebcomment);		
	}






	
	
	}
