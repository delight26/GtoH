package com.project.call.wansoon.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.project.call.domain.FreeBoard;
import com.project.call.wansoon.service.WSService;

@Controller
public class WSController {
	
	@Autowired
	private WSService jBService;
	
	private static final String filePath = "/resources/uploadimages/";
	
	public void setjBService(WSService jBService) {
		this.jBService = jBService;
	}
	
	

	@RequestMapping (value={"FreeBoardList"})
	public ModelAndView List(
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
	
		// Service 클래스를 이용해 전체 상품 리스트를 가져온다.
				List<FreeBoard> frbList = jBService.getFreeBoardAll();	
				
				/* 모델을 생성하여 상품 리스트를 저장한다.
				 * 모델에는 도메인 객체나 비즈니스 로직을 처리한 결과를 저장한다. 
				 **/
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("frbList", frbList);		
				
				/* ModelAndView를 생성하고 모델과 뷰 정보를 저장한다.
				 * 컨트롤러는 모델과 뷰 정보를 저장한 ModelAndView를 반환해야 한다. 
				 **/	
				ModelAndView mav = new ModelAndView("index.jsp?body=freeB/FreeBoardList");
				mav.addAllObjects(model);	
				
				return mav;
	}
	
	@RequestMapping (value={"FreeBoardContent"})
	public ModelAndView Content(
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		int frbNo = Integer.parseInt((request.getParameter("frbNo")));
		FreeBoard frb = jBService.getFreeBoard(frbNo);
		
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
	
		jBService.insertBoard(frb);
			
		RedirectView redirectView = new RedirectView("FreeBoardList");
		
		ModelAndView modelAndView = new ModelAndView(redirectView);		
		
		return modelAndView;
	}
		
/*	
	@RequestMapping(value={"WriteFreeBoard"})
		public String addResult(
				MultipartHttpServletRequest request) throws IOException {		
			
			String path = request.getServletContext().getRealPath(filePath);
			jBService.addWrite(request, filePath);
			
			return redirectPrefix();
		}

	
		
		
		private String redirectPrefix() {
			return "redirect:FeeBoardList";
		}
*/	
}
