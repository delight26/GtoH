package com.project.call.wansoon.service;


import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.Comment;
import com.project.call.domain.FreeBoard;
import com.project.call.junbum.dao.JBDao;
import com.project.call.wansoon.dao.WSDao;

@Service
public class WSServiceImpl implements WSService {

	@Autowired
	private WSDao WSDao;
	
	@Autowired
	private JBDao dao;
	
	public void setDao(JBDao dao) {
		this.dao = dao;
	}
	
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
		int listCount = WSDao.getFreeBoardCount();

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

/*	@Override
	public List<FreeBoard> insertBoard(FreeBoard freeboard) {
		
		return WSDao.insertBoard(freeboard);
	}*/

	@Override
	public void insertWrite(FreeBoard freeboard) {
	
		WSDao.insertWrite(freeboard);
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
	public void addComment(Comment freebComment) {
		
		WSDao.addComment(freebComment);
		
	}

	@Override
	public List<Comment> commentAllList(int bno) {
		
		return WSDao.commentAllList(bno);
	}

	@Override
	public void modifyComment(Comment freebcomment) {
		
	//	WSDao.modifyComment(freebcomment);		
	}
	
	@Override
	public void modifyComment(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void freeDelete(HttpServletRequest request) {
		int boardNo = Integer.parseInt(request.getParameter("frbNo"));
		dao.aggroDelete(boardNo);		
	}

	
	@Override
	public void freeSearch(HttpServletRequest request) {
		String search = request.getParameter("search");

		String pageNum = "1";
		int currentPage = Integer.valueOf(pageNum);

		int startRow = currentPage * PAGE_SIZE - PAGE_SIZE;

		int listCount = WSDao.freeSearchCount(search);

		if (listCount > 0) {
			List<FreeBoard> aggroList = WSDao.freeSearch(search, startRow, PAGE_SIZE);
			int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);

			int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
					- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);

			int endPage = startPage + PAGE_GROUP - 1;

			if (endPage > pageCount) {
				endPage = pageCount;
			}

			request.setAttribute("aggroList", aggroList);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("PAGE_GROUP", PAGE_GROUP);
		}

		
	}
	
	
	@Override
	public void freeUpdateForm(HttpServletRequest request) {
		int frbNo = Integer.valueOf(request.getParameter("frbNo"));
		FreeBoard frb = WSDao.freeContent(frbNo);
		request.setAttribute("frb", frb);
	}

	@Override
	public void freeUpdateResult(MultipartHttpServletRequest request, String path) throws IOException {
		MultipartFile multipartFile = request.getFile("image");
		FreeBoard fb = new FreeBoard();
		if (!multipartFile.isEmpty()) {
			File file = new File(path, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);

			fb.setFrbNo(Integer.valueOf(request.getParameter("frbNo")));
			fb.setFrbArea(request.getParameter("area"));
			fb.setFrbWriter(request.getParameter("writer"));
			fb.setFrbEmail(request.getParameter("email"));
			fb.setFrbTitle(request.getParameter("title"));
			fb.setFrbContent(request.getParameter("content"));
			Timestamp time = new Timestamp(System.currentTimeMillis());
			fb.setFrbWriteDate(time);
			fb.setPhoto1(multipartFile.getOriginalFilename());
			WSDao.freeBoardUpdatePhoto(fb);
		} else {
			fb.setFrbNo(Integer.valueOf(request.getParameter("frbNo")));
			fb.setFrbArea(request.getParameter("area"));
			fb.setFrbWriter(request.getParameter("writer"));
			fb.setFrbEmail(request.getParameter("email"));
			fb.setFrbTitle(request.getParameter("title"));
			fb.setFrbContent(request.getParameter("content"));
			Timestamp time = new Timestamp(System.currentTimeMillis());
			fb.setFrbWriteDate(time);
			WSDao.freeBoardUpdate(fb);
		}
		
	}
	

	@Override
	public void freeContent(HttpServletRequest request) {
		int frbNo = Integer.valueOf(request.getParameter("frbNo"));
		int frbHit = Integer.valueOf(request.getParameter("frbHit"));
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		dao.aggroHitUpdate(frbHit + 1, frbNo);
		FreeBoard frb = dao.aggroContent(frbNo);
		request.setAttribute("frb", frb);
		request.setAttribute("pageNum", pageNum);
		
	}
	
	@Override
	public void freePreContent(HttpServletRequest request) {
		int frbNo = Integer.valueOf(request.getParameter("frbNo"));
		Integer preNo = WSDao.freePreNo(frbNo);
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		if (preNo == null) {

		} else {
			FreeBoard frb = WSDao.freeContent(preNo);
			dao.aggroHitUpdate(frb.getFrbHit() + 1, preNo);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("frb", frb);
		}		
		
	}
	
	@Override
	public void freeNextContent(HttpServletRequest request) {
		int frbNo = Integer.valueOf(request.getParameter("frbNo"));
		Integer nextNo = WSDao.freeNextNo(frbNo);
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		if (nextNo == null) {

		} else {
			FreeBoard frb = WSDao.freeContent(nextNo);
			dao.aggroHitUpdate(frb.getFrbHit() + 1, nextNo);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("frb", frb);
		}	
	}
	
	
	
	
	
	}
