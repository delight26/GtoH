package com.project.call.junbum.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.junbum.service.JBService;

@Controller
public class JBController {

	@Autowired
	private JBService jBService;
	private static final String filePath = "/resources/uploadimages/";

	public void setjBService(JBService jBService) {
		this.jBService = jBService;
	}

	//로그인 폼
	@RequestMapping(value = "loginform")
	public String loginForm(HttpServletRequest request) {
		String pProductCode = request.getParameter("pProductCode");
		String quantity = request.getParameter("quantity");
		String page = request.getParameter("page");
		request.setAttribute("pProductCode", pProductCode);
		request.setAttribute("quantity", quantity);
		request.setAttribute("page", page);
		return "index.jsp?body=member/login";
	}
	
	// 로그인
	@RequestMapping(value = "loginresult", method = RequestMethod.POST)
	public String loginResult(HttpServletRequest request, HttpSession session) {
		boolean logtf = jBService.loginResult(request, session);
		if (logtf) {
			switch(request.getParameter("page")){
			case "":{
				return "index";
			}
			case "cart":{
				return "redirect:getcartlist";
			}
			case "pcontent":{
				return "redirect:buyproduct?pProductCode="+ request.getParameter("pProductCode")
				+"&quantity="+request.getParameter("quantity");
			}
			case "aggro":{
				return "redirect:agrroboard";
			}
			default:{
				return "redirect:loginform";
			}
			}
		}
		return "redirect:loginform";
	}

	// 로그아웃
	@RequestMapping(value = "logout")
	public String logout(HttpSession session) {
		session.invalidate();

		return "redirect:index";
	}

	//상품 리스트
	@RequestMapping(value = "productlist")
	public String getproductList(HttpServletRequest request) {
		jBService.getproductList(request);

		return "index.jsp?body=product/productlist";
	}

	//관리자 상품 리스트
	@RequestMapping(value = "adminproductlist")
	public String getAdminproductList(HttpServletRequest request) {
		jBService.getproductList(request);

		return "index.jsp?body=product/adminproductlist";
	}

	//상품 추가 폼
	@RequestMapping(value = "productadd")
	public String addproductform() {
		return "index.jsp?body=product/productadd";
	}

	//상품 추가 결과
	@RequestMapping(value = "productaddresult", method = RequestMethod.POST)
	public String addProduct(MultipartHttpServletRequest request) throws IOException {
		String path = request.getServletContext().getRealPath(filePath);

		jBService.addProduct(request, path);

		return "redirect:adminproductlist";
	}

	//상품 수정 페이지
	@RequestMapping(value = "productupdate")
	public String productUpdate(HttpServletRequest request) {
		jBService.productUpdate(request);

		return "index.jsp?body=product/productupdate";
	}

	//상품수정 완료
	@RequestMapping(value = "productupdateresult", method = RequestMethod.POST)
	public String productUpdateResult(MultipartHttpServletRequest request) throws IOException {
		String path = request.getServletContext().getRealPath(filePath);
		jBService.productUpdateResult(request, path);

		return "redirect:adminproductlist";
	}
	
	//상품 상세보기
	@RequestMapping(value = "productcontent")
	public String productContent(HttpServletRequest request) {
		jBService.productContent(request);

		return "index.jsp?body=product/productcontent";
	}
	
	//관리자 상품 상세보기
	@RequestMapping(value = "adminproductcontent")
	public String adminproductContent(HttpServletRequest request) {
		jBService.productContent(request);

		return "index.jsp?body=product/adminproductcontent";
	}

	//상품삭제
	@RequestMapping(value="productdelete")
	public String productDelete(HttpServletRequest request){
		jBService.productDelete(request);
		
		return "redirect:adminproductlist";
	}
	
	//장바구니 담기
	@RequestMapping(value = "addcart")
	public String addCart(HttpServletRequest request, HttpSession session) {
		jBService.addCart(request, session);

		return "product/cartselect";
	}

	//장바구니 리스트
	@RequestMapping(value = "getcartlist")
	public String getCart(HttpSession session) {
		jBService.getCart(session);

		return "index.jsp?body=product/cartlist";
	}
	
	//장바구니에서 주문
	@RequestMapping(value="buycartproduct")
	public String buyCartProduct(HttpServletRequest request, HttpSession session){
		jBService.buyCartProduct(request);
		return "index.jsp?body=product/buyproduct";
	}
	
	//장바구니 주문완료
	@RequestMapping(value="cartorder")
	public String cartOrder(HttpServletRequest request, HttpSession session){
		jBService.orderPrduct(request, session);
		
		return"index.jsp?body=product/ordercomplete";
	}
	
	//상품구매페이지
	@RequestMapping(value="buyproduct")
	public String buyProduct(HttpSession session, HttpServletRequest request){
		jBService.buyProduct(request);
		return "index.jsp?body=product/buyproduct";
	}
	
	//도발 게시판리스트
	@RequestMapping(value="agrroboard")
	public String aggroBoardList(HttpServletRequest request, HttpSession session){
		if(session.getAttribute("loginUser")==null){
			return "redirect:loginform?page=aggro";
		}else{
		jBService.aggroBoardList(request);
		
		return "index.jsp?body=aggro/aggroList";
		}
	}
	//도발 게시판 글쓰기 폼
	@RequestMapping(value="aggrowrite")
	public String aggroBoardWrite(HttpServletRequest request, HttpSession session){
		if(session.getAttribute("loginUser")==null){
			return "redirect:loginform?page=aggro";
		}else{
		jBService.aggroBoardList(request);
		
		return "index.jsp?body=aggro/aggrowrite";
		}
	}
	
	//도발 게시판 글쓰기 결과
	@RequestMapping(value="aggrowriteresult")
	public String aggroBoardWriteResult(MultipartHttpServletRequest request, HttpSession session) throws IOException{
		String path = request.getServletContext().getRealPath(filePath);
		jBService.aggroBoardWriteResult(request, session, path);
		
		return "redirect:agrroboard";
	}
}
