package com.project.call.wansoon.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.NonWritableChannelException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.project.call.domain.FightBoard;
import com.project.call.domain.FreeBoard;
import com.project.call.domain.FreebComment;
import com.project.call.domain.Member;
import com.project.call.wansoon.dao.WSDao;
import com.project.call.wansoon.service.WSService;

@Controller
public class WSController {
	
	@Autowired
	private WSService wsService;
	
	private static final String filePath = "/resources/uploadimages/";
	
	public void setjBService(WSService wsService) {
		this.wsService = wsService;
	}
	
	

	@RequestMapping (value={"FreeBoardList"})
	public ModelAndView List(
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
	
		// Service 클래스를 이용해 전체 상품 리스트를 가져온다.
				List<FreeBoard> frbList = wsService.getFreeBoardAll();	
				
				/* 모델을 생성하여 상품 리스트를 저장한다.
				 * 모델에는 도메인 객체나 비즈니스 로직을 처리한 결과를 저장한다. 
				 **/
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("frbList", frbList);		
				
				/* ModelAndView를 생성하고 모델과 뷰 정보를 저장한다.
				 * 컨트롤러는 모델과 뷰 정보를 저장한 ModelAndView를 반환해야 한다. 
				 **/	
				ModelAndView mav = new ModelAndView("freeB/FreeBoardList");
				mav.addAllObjects(model);	
				
				return mav;
	}
	
	@RequestMapping (value={"FreeBoardContent"})
	public ModelAndView Content(
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		int frbNo = Integer.parseInt((request.getParameter("frbNo")));
		FreeBoard frb = wsService.getFreeBoard(frbNo);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("frb", frb);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("freeB/FreeBoardContent");
		mav.addAllObjects(model);
		
		return mav;
	}
	
	@RequestMapping(value={"WriteFreeBoard"})
		public ModelAndView boardWrite(
				HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		FreeBoard frb = new FreeBoard();
		
		frb.setFrbEmail(request.getParameter("frbEmail"));
		frb.setFrbPass(request.getParameter("frbPass"));
		frb.setFrbTitle(request.getParameter("frbTitle"));
		frb.setFrbArea(request.getParameter("frbArea"));
		frb.setPhoto1(request.getParameter("photo1"));
	
		wsService.insertBoard(frb);
			
		RedirectView redirectView = new RedirectView("FreeBoardList");
		
		ModelAndView modelAndView = new ModelAndView(redirectView);		
		
		return modelAndView;
	}
		
	@RequestMapping(value = { "/AddComment" }, method = RequestMethod.POST)
	public ModelAndView AddComment(Model model, HttpServletRequest request,
					HttpServletResponse response,
			@RequestParam("loginUser") String loginUser,
			@RequestParam("bno") String bno,
			@RequestParam("comment") String comment) throws IOException {
		System.out.println("bNo : " + bno);
		
		ModelAndView mav= new ModelAndView();
		
		FreebComment fbc = new FreebComment();
		
		fbc.setBno(Integer.parseInt(bno));
		fbc.setEmail(loginUser);
		fbc.setComment(comment);
		fbc.setWriteDate(new Timestamp(System.currentTimeMillis()));
		
		wsService.addComment(fbc);
		
		List<FreebComment> fbcList = wsService.commentAllList(Integer.parseInt(bno));
		
		mav.addObject("fbcList", fbcList);
		
		
		System.out.println("qfds:" + fbcList.size());
		
		mav.setViewName("freeB/ajaxComment");
		
		
		return mav;

	}
}
