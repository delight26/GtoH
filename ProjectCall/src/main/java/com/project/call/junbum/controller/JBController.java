package com.project.call.junbum.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.PointProduct;
import com.project.call.junbum.service.JBService;

@Controller
public class JBController {

	@Autowired
	private JBService jBService;
	private static final String filePath = "/resources/uploadimages/";

	public void setjBService(JBService jBService) {
		this.jBService = jBService;
	}

	@RequestMapping(value = "loginform")
	public String loginForm() {
		return "member/login";
	}

	// 로그인
	@RequestMapping(value = "loginresult", method = RequestMethod.POST)
	public String loginResult(HttpServletRequest request, HttpSession session) {
		boolean logtf = jBService.loginResult(request, session);

		if (logtf) {
			return "index";
		} else {
			return "redirect:loginform";
		}

	}

	// 로그아웃
	@RequestMapping(value = "logout")
	public String logout(HttpSession session) {
		session.invalidate();

		return "redirect:index";
	}

	@RequestMapping(value = "productlist")
	public String getproductList(HttpServletRequest request) {
		jBService.getproductList(request);

		return "product/productlist";
	}

	@RequestMapping(value = "adminproductlist")
	public String getAdminproductList(HttpServletRequest request) {
		jBService.getproductList(request);

		return "product/adminproductlist";
	}

	@RequestMapping(value = "productadd")
	public String addproductform() {
		return "product/productadd";
	}

	@RequestMapping(value = "productaddresult", method = RequestMethod.POST)
	public String addProduct(MultipartHttpServletRequest request) throws IOException {
		String path = request.getServletContext().getRealPath(filePath);

		jBService.addProduct(request, path);

		return "redirect:adminproductlist";
	}

	@RequestMapping(value = "productupdate")
	public String productUpdate(HttpServletRequest request) {
		jBService.productUpdate(request);

		return "product/productupdate";
	}

	@RequestMapping(value = "productupdateresult", method = RequestMethod.POST)
	public String productUpdateResult(MultipartHttpServletRequest request) throws IOException {
		String path = request.getServletContext().getRealPath(filePath);
		jBService.productUpdateResult(request, path);

		return "redirect:adminproductlist";
	}

	@RequestMapping(value = "productcontent")
	public String productContent(HttpServletRequest request) {
		jBService.productContent(request);

		return "product/productcontent";
	}

	@RequestMapping(value = "adminproductcontent")
	public String adminproductContent(HttpServletRequest request) {
		jBService.productContent(request);

		return "product/adminproductcontent";
	}

	@RequestMapping(value="productdelete")
	public String productDelete(HttpServletRequest request){
		jBService.productDelete(request);
		
		return "redirect:adminproductlist";
	}

	@RequestMapping(value = "addcart")
	public String addCart(HttpServletRequest request, HttpSession session) {
		jBService.addCart(request, session);

		return "product/cartselect";
	}

	@RequestMapping(value = "getcartlist")
	public String getCart(HttpSession session) {
		jBService.getCart(session);

		return "product/cartlist";
	}
}
