package com.project.call.wansoon.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.project.call.domain.Comment;
import com.project.call.domain.FreeBoard;
import com.project.call.wansoon.service.WSService;


@Controller
public class WSController {

	@Autowired
	private WSService service;

	private static final String filePath = "/resources/uploadimages/";

	public void setservice(WSService service) {
		this.service = service;
	}
//
//	// 도발 게시판리스트
//		@RequestMapping(value = "freeboard")
//		public String freeBoardList(HttpServletRequest request, HttpSession session) {
//			service.freeBoardList(request);
//
//			return "index.jsp?body=free/freeList";
//		}
//
//		// 도발 게시판 글쓰기 폼
//		@RequestMapping(value = "freewrite")
//		public String freeBoardWrite(HttpServletRequest request, HttpSession session) {
//			if (session.getAttribute("loginUser") == null) {
//				return "redirect:loginform?page=free";
//			} else {
//				service.freeBoardList(request);
//
//				return "free/freewrite";
//			}
//		}
//
//		// 도발 게시판 글쓰기 결과
//		@RequestMapping(value = "freewriteresult")
//		public String freeBoardWriteResult(MultipartHttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
//			String path = request.getServletContext().getRealPath(filePath);
//			service.freeBoardWriteResult(request, response, session, path);
//
//			return "redirect:freeboard";
//		}
//
//		// 도발 게시판 내용
//		@RequestMapping(value = "freecontent")
//		public String freeContent(HttpServletRequest request) {
//			service.freeContent(request);
//
//			return "index.jsp?body=free/freecontent";
//		}
//
//		// 도발 게시판 이전글
//		@RequestMapping(value = "freepre")
//		public String freePreContent(HttpServletRequest request) {
//			service.freePreContent(request);
//			System.out.println(request.getAttribute("frb"));
//			if (request.getAttribute("frb") == null) {
//				request.setAttribute("message", "최신글 입니다.");
//				request.setAttribute("returnUrl", "javascript:history.back()");
//				return "alertAndRedirect";
//			}
//			return "index.jsp?body=free/freecontent";
//		}
//
//		// 도발 게시판 다음글
//		@RequestMapping(value = "freenext")
//		public String freeNextContent(HttpServletRequest request) {
//			service.freeNextContent(request);
//			System.out.println("frb=" + request.getAttribute("frb"));
//			if (request.getAttribute("frb") == null) {
//				request.setAttribute("message", "마지막글 입니다.");
//				request.setAttribute("returnUrl", "javascript:history.back()");
//				return "alertAndRedirect";
//			}
//			return "index.jsp?body=free/freecontent";
//		}
//
//		// 도발 게시판 수정
//		@RequestMapping(value = "freeupdate")
//		public String freeUpdateForm(HttpServletRequest request) {
//			service.freeUpdateForm(request);
//
//			return "free/freeupdate";
//		}
//
//		// 도발 게시판 수정 결과
//		@RequestMapping(value = "freeupdateresult")
//		public String freeUpdateResult(MultipartHttpServletRequest request) throws IOException {
//			String path = request.getServletContext().getRealPath(filePath);
//			service.freeUpdateResult(request, path);
//
//			return "redirect:freeboard";
//		}
//
//		// 도발 게시판 삭제
//		@RequestMapping(value = "freedelete")
//		public String freeDelete(HttpServletRequest request) {
//			service.freeDelete(request);
//
//			return "redirect:freeboard";
//		}
//
//		//도발 게시판 검색
//		@RequestMapping(value="freesearch")
//		public String freeSearch(HttpServletRequest request){
//			service.freeSearch(request);
//			
//			return "index.jsp?body=free/freeList";
//		}
//		
//		// 도발 게시판 댓글
//		@RequestMapping(value = "freecomment")
//		public String freeComment(@RequestParam("frbNo") String frbNo, @RequestParam("pageNum") String pageNum,
//				HttpServletRequest request) {
//
//			service.getComment(frbNo, pageNum, request);
//
//			return "free/freecomment";
//		}
//
//		// 도발 게시판 댓글 달기
//		@RequestMapping(value = "freecommentwrite", method = RequestMethod.POST)
//		public String freeCommentWrite(@RequestParam("frbNo") String frbNo, @RequestParam("content") String content,
//				@RequestParam("email") String email, HttpServletRequest request) {
//			service.freeCommentWrite(frbNo, content, email);
//			service.getComment(frbNo, "1", request);
//			return "free/freecomment";
//		}
//
//		// 도발게시판 댓글 수정
//		@RequestMapping(value = "freecommentupdate", method = RequestMethod.POST)
//		public String freeCommentUpdate(@RequestParam("cNo") String cNo, @RequestParam("content") String content,
//				@RequestParam("frbNo") String frbNo, HttpServletRequest request) {
//			service.freeCommentUpdate(cNo, content);
//			service.getComment(frbNo, "1", request);
//			return "free/freecomment";
//		}
//
//		// 도발게시판 댓글 삭제
//		@RequestMapping(value = "freecommentdelete", method = RequestMethod.POST)
//		public String freeCommentDelete(@RequestParam("cNo") String cNo, @RequestParam("frbNo") String frbNo,
//				HttpServletRequest request) {
//			service.freeCommentDelete(cNo);
//			service.getComment(frbNo, "1", request);
//			System.out.println();
//			return "free/freecomment";
//		}

}
