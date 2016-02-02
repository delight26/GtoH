package com.project.call.seunghee.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.FreeBoard;
import com.project.call.domain.Member;
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
		
		shDao.noticeDelete(no);
		request.setAttribute("no", no);
	}
	
	@Override
	public void noticeWriteForm(HttpServletRequest request) {
		int no = 0, pageNum = 0;
		
		if (request.getParameter("no") != null) {
			no = Integer.valueOf(request.getParameter("no"));
			pageNum = Integer.valueOf(request.getParameter("pageNum"));
		}

		request.setAttribute("no", no);
		request.setAttribute("pageNum", pageNum);
	}

	@Override
	public void noticeWrite(MultipartHttpServletRequest request, HttpServletResponse response, String filePath) 
			throws IOException {
		MultipartFile multipartFile = request.getFile("photo1");
		
		if(request.getParameter("no") == null || request.getParameter("pageNum") == null) {

			response.setContentType("text/html; charset=utf-8");

			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>");
			out.println("	alert('잘못된 접근입니다.');");
			out.println("	window.history.back();");
			out.println("</script>");
		}
		
		if(! multipartFile.isEmpty()) {
			
			File file = new File(filePath, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);
			
			FreeBoard noticeboard = new FreeBoard();
			noticeboard.setFrbNo(Integer.valueOf(request.getParameter("no")));
			noticeboard.setFrbWriter(request.getParameter("writer"));
			noticeboard.setFrbTitle(request.getParameter("title"));
			noticeboard.setFrbPass("1234");
			noticeboard.setFrbContent(request.getParameter("content"));
			noticeboard.setFrbEmail(request.getParameter("email"));
			noticeboard.setFrbArea("공지");
			noticeboard.setFrbWriteDate(new Timestamp(System.currentTimeMillis()));
			noticeboard.setPhoto1(multipartFile.getOriginalFilename());

			shDao.noticeWrite(noticeboard);

			String pageNum = request.getParameter("pageNum");
			if (pageNum.equals("0")) {
				pageNum = "1";
			} 
			
		} else if(multipartFile.isEmpty()) {
			
			FreeBoard noticeboard = new FreeBoard();
			noticeboard.setFrbNo(Integer.valueOf(request.getParameter("no")));
			noticeboard.setFrbWriter(request.getParameter("writer"));
			noticeboard.setFrbTitle(request.getParameter("title"));
			noticeboard.setFrbPass("1234");
			noticeboard.setFrbContent(request.getParameter("content"));
			noticeboard.setFrbEmail(request.getParameter("email"));
			noticeboard.setFrbArea("공지");
			noticeboard.setFrbWriteDate(new Timestamp(System.currentTimeMillis()));
			noticeboard.setPhoto1(null);

			shDao.noticeWrite(noticeboard);

			String pageNum = request.getParameter("pageNum");
			if (pageNum.equals("0")) {
				pageNum = "1";
			} 
			
		}
	}
	
	@Override
	public FreeBoard noticeModifyForm(HttpServletRequest request) {
		int no = Integer.valueOf(request.getParameter("no"));
		
		return shDao.getNoticeContent(no);
	}

	@Override
	public void noticeModify(MultipartHttpServletRequest request, HttpServletResponse response, String filePath) 
			throws IOException {
		MultipartFile multipartFile = request.getFile("photo1");
		
		if(request.getParameter("no") == null || request.getParameter("pageNum") == null) {

			response.setContentType("text/html; charset=utf-8");

			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>");
			out.println("	alert('잘못된 접근입니다.');");
			out.println("	window.history.back();");
			out.println("</script>");
		}
		
		if(! multipartFile.isEmpty()) {
			
			File file = new File(filePath, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);
			
			FreeBoard noticeboard = new FreeBoard();
			noticeboard.setFrbNo(Integer.valueOf(request.getParameter("no")));
			noticeboard.setFrbWriter(request.getParameter("writer"));
			noticeboard.setFrbTitle(request.getParameter("title"));
			noticeboard.setFrbPass("1234");
			noticeboard.setFrbContent(request.getParameter("content"));
			noticeboard.setFrbEmail(request.getParameter("email"));
			noticeboard.setFrbArea("공지");
			noticeboard.setFrbWriteDate(new Timestamp(System.currentTimeMillis()));
			noticeboard.setPhoto1(multipartFile.getOriginalFilename());

			shDao.noticeModify(noticeboard);

			String pageNum = request.getParameter("pageNum");
			if (pageNum.equals("0")) {
				pageNum = "1";
			} 
			
		} else if(multipartFile.isEmpty()) {
			
			FreeBoard noticeboard = new FreeBoard();
			noticeboard.setFrbNo(Integer.valueOf(request.getParameter("no")));
			noticeboard.setFrbWriter(request.getParameter("writer"));
			noticeboard.setFrbTitle(request.getParameter("title"));
			noticeboard.setFrbPass("1234");
			noticeboard.setFrbContent(request.getParameter("content"));
			noticeboard.setFrbEmail(request.getParameter("email"));
			noticeboard.setFrbArea("공지");
			noticeboard.setFrbWriteDate(new Timestamp(System.currentTimeMillis()));
			noticeboard.setPhoto1(null);

			shDao.noticeModify(noticeboard);

			String pageNum = request.getParameter("pageNum");
			if (pageNum.equals("0")) {
				pageNum = "1";
			} 
		}
		
	}
	
	@Override
	public void getSeoulRanking(HttpServletRequest request) {
		List<Member> seoul = shDao.getSeoulRanking();
		request.setAttribute("seoul", seoul);
	}
	
	@Override
	public void getGyeonggiRanking(HttpServletRequest request) {
		List<Member> gyeonggi = shDao.getGyeonggiRanking();
		request.setAttribute("gyeonggi", gyeonggi);
	}
	
	@Override
	public void getKangwonRanking(HttpServletRequest request) {
		List<Member> kangwon = shDao.getKangwonRanking();
		request.setAttribute("kangwon", kangwon);
	}
	
	@Override
	public void getChungcheongRanking(HttpServletRequest request) {
		List<Member> chungcheong = shDao.getChungcheongRanking();
		request.setAttribute("chungcheong", chungcheong);
	}
	
	@Override
	public void getGyeongsangRanking(HttpServletRequest request) {
		List<Member> gyeongsang = shDao.getGyeongsangRanking();
		request.setAttribute("gyeongsang", gyeongsang);
	}
	
	@Override
	public void getJunlaRanking(HttpServletRequest request) {
		List<Member> junla = shDao.getJunlaRanking();
		request.setAttribute("junla", junla);
	}
	
	@Override
	public void getJejuRanking(HttpServletRequest request) {
		List<Member> jeju = shDao.getJejuRanking();
		request.setAttribute("jeju", jeju);
	}
	

	
}
