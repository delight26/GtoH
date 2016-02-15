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
import com.project.call.hyunsu.supprot.ScriptHandling;
import com.project.call.wansoon.dao.WSDao;

@Service
public class WSServiceImpl implements WSService {

	@Autowired
	private WSDao wSDao;
	
	@Autowired
	private ScriptHandling scriptHandling;
	
	
	private static final int PAGE_SIZE = 10;
	private static final int PAGE_GROUP = 10;
	
	public void setwSDao(WSDao wSDao) {
		this.wSDao = wSDao;
	}
	
	@Override
	public void freeBoardList(HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.valueOf(pageNum);

		int startRow = currentPage * PAGE_SIZE - PAGE_SIZE;
		int listCount = wSDao.getfreeCount();

		if (listCount > 0) {
			List<FreeBoard> freeList = wSDao.getfreeList(startRow, PAGE_SIZE);
			int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);

			int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
					- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);

			int endPage = startPage + PAGE_GROUP - 1;

			if (endPage > pageCount) {
				endPage = pageCount;
			}

			request.setAttribute("freeList", freeList);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("PAGE_GROUP", PAGE_GROUP);
		}
	}

	@Override
	public void freeBoardWriteResult(MultipartHttpServletRequest request, HttpServletResponse response,
			HttpSession session, String path) throws Exception {
		MultipartFile multipartFile = request.getFile("image");
		FreeBoard fb = new FreeBoard();

		if (request.getParameter("title").equals("") || request.getParameter("title") == null
				|| request.getParameter("content").equals("") || request.getParameter("content") == null) {
			scriptHandling.historyBack(response, "제목이나 내용이 비어있습니다");
		}

		if (!multipartFile.isEmpty()) {
			File file = new File(path, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);

			fb.setFrbArea(request.getParameter("area"));
			fb.setFrbWriter(request.getParameter("writer"));
			fb.setFrbEmail(request.getParameter("email"));
			fb.setFrbTitle(request.getParameter("title"));
			fb.setFrbContent(request.getParameter("content"));
			Timestamp time = new Timestamp(System.currentTimeMillis());
			fb.setFrbWriteDate(time);
			fb.setPhoto1(multipartFile.getOriginalFilename());
			wSDao.freeBoardWritephoto(fb);
		} else {
			fb.setFrbArea(request.getParameter("area"));
			fb.setFrbWriter(request.getParameter("writer"));
			fb.setFrbEmail(request.getParameter("email"));
			fb.setFrbTitle(request.getParameter("title"));
			fb.setFrbContent(request.getParameter("content"));
			Timestamp time = new Timestamp(System.currentTimeMillis());
			fb.setFrbWriteDate(time);
			wSDao.freeBoardWrite(fb);
		}
	}

	@Override
	public void freeContent(HttpServletRequest request) {
		int frbNo = Integer.valueOf(request.getParameter("frbNo"));
		int frbHit = Integer.valueOf(request.getParameter("frbHit"));
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		wSDao.freeHitUpdate(frbHit + 1, frbNo);
		FreeBoard frb = wSDao.freeContent(frbNo);
		request.setAttribute("frb", frb);
		request.setAttribute("pageNum", pageNum);
	}

	@Override
	public void freePreContent(HttpServletRequest request) {
		int frbNo = Integer.valueOf(request.getParameter("frbNo"));
		Integer preNo = wSDao.freePreNo(frbNo);
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		if (preNo == null) {

		} else {
			FreeBoard frb = wSDao.freeContent(preNo);
			wSDao.freeHitUpdate(frb.getFrbHit() + 1, preNo);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("frb", frb);
		}
	}

	@Override
	public void freeNextContent(HttpServletRequest request) {
		int frbNo = Integer.valueOf(request.getParameter("frbNo"));
		Integer nextNo = wSDao.freeNextNo(frbNo);
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		if (nextNo == null) {

		} else {
			FreeBoard frb = wSDao.freeContent(nextNo);
			wSDao.freeHitUpdate(frb.getFrbHit() + 1, nextNo);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("frb", frb);
		}
	}

	@Override
	public void freeUpdateForm(HttpServletRequest request) {
		int frbNo = Integer.valueOf(request.getParameter("frbNo"));
		FreeBoard frb = wSDao.freeContent(frbNo);

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
			wSDao.freeBoardUpdatePhoto(fb);
		} else {
			fb.setFrbNo(Integer.valueOf(request.getParameter("frbNo")));
			fb.setFrbArea(request.getParameter("area"));
			fb.setFrbWriter(request.getParameter("writer"));
			fb.setFrbEmail(request.getParameter("email"));
			fb.setFrbTitle(request.getParameter("title"));
			fb.setFrbContent(request.getParameter("content"));
			Timestamp time = new Timestamp(System.currentTimeMillis());
			fb.setFrbWriteDate(time);
			wSDao.freeBoardUpdate(fb);
		}
	}

	@Override
	public void freeDelete(HttpServletRequest request) {
		int frbNo = Integer.valueOf(request.getParameter("frbNo"));
		wSDao.freeDelete(frbNo);
	}

	@Override
	public void freeSearch(HttpServletRequest request) {
		String search = request.getParameter("search");

		String pageNum = "1";
		int currentPage = Integer.valueOf(pageNum);

		int startRow = currentPage * PAGE_SIZE - PAGE_SIZE;

		int listCount = wSDao.freeSearchCount(search);

		if (listCount > 0) {
			List<FreeBoard> freeList = wSDao.freeSearch(search, startRow, PAGE_SIZE);
			int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);

			int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
					- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);

			int endPage = startPage + PAGE_GROUP - 1;

			if (endPage > pageCount) {
				endPage = pageCount;
			}

			request.setAttribute("freeList", freeList);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("PAGE_GROUP", PAGE_GROUP);
		}

	}

	@Override
	public void getComment(String No, String pageNum, HttpServletRequest request) {
		int frbNo = Integer.valueOf(No);

		if (pageNum == "") {
			pageNum = "1";
		}
		int currentPage = Integer.valueOf(pageNum);

		int startRow = currentPage * PAGE_SIZE - PAGE_SIZE;
		int listCount = wSDao.getCommentCount(frbNo);

		if (listCount > 0) {
			List<Comment> cList = wSDao.getComment(frbNo, startRow, PAGE_SIZE);
			int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);

			int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
					- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);

			int endPage = startPage + PAGE_GROUP - 1;

			if (endPage > pageCount) {
				endPage = pageCount;
			}

			request.setAttribute("cList", cList);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("PAGE_GROUP", PAGE_GROUP);
		}

	}

	@Override
	public void freeCommentWrite(String frbNo, String content, String email) {

		Comment c = new Comment();
		c.setbNo(Integer.valueOf(frbNo));
		c.setcContent(content);
		c.setcEmail(email);

		wSDao.freeCommentWrite(c);
	}

	@Override
	public void freeCommentUpdate(String cNo, String content) {
		Comment c = new Comment();
		c.setcNo(Integer.valueOf(cNo));
		c.setcContent(content);

		wSDao.freeCommentUpdate(c);
	}

	@Override
	public void freeCommentDelete(String No) {
		int cNo = Integer.valueOf(No);

		wSDao.freeCommentDelete(cNo);
	}
	}
