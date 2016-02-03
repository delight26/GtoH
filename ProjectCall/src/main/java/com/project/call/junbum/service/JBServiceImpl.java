package com.project.call.junbum.service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.FreeBoard;
import com.project.call.domain.Member;
import com.project.call.domain.PointProduct;
import com.project.call.junbum.dao.JBDao;

@Service
public class JBServiceImpl implements JBService {

	@Autowired
	private JBDao jBDao;
	private List<PointProduct> pList = new ArrayList<PointProduct>();
	private static final int PAGE_SIZE = 10;
	private static final int PAGE_GROUP = 10;

	public void setjBDao(JBDao jBDao) {
		this.jBDao = jBDao;
	}

	@Override
	public Boolean loginResult(HttpServletRequest request, HttpSession session) {
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");

		Member m = jBDao.getloginResult(email);

		if (m.getPass().equals(pass)) {
			session.setAttribute("loginUser", m);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void getproductList(HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.valueOf(pageNum);

		int startRow = currentPage * PAGE_SIZE - PAGE_SIZE;
		int listCount = jBDao.getProductCount();

		if (listCount > 0) {
			List<PointProduct> pList = jBDao.getproductList(startRow, PAGE_SIZE);

			int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);

			int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
					- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);

			int endPage = startPage + PAGE_GROUP - 1;

			if (endPage > pageCount) {
				endPage = pageCount;
			}
			request.setAttribute("pList", pList);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("PAGE_GROUP", PAGE_GROUP);
		}
	}

	@Override
	public void addProduct(MultipartHttpServletRequest request, String path) throws IOException {
		MultipartFile multipartFile = request.getFile("image");

		if (!multipartFile.isEmpty()) {
			File file = new File(path, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);

			PointProduct p = new PointProduct();

			p.setpName(request.getParameter("pname"));
			p.setpPrice(Integer.valueOf(request.getParameter("price")));
			p.setpAmount(Integer.valueOf(request.getParameter("amount")));
			p.setpImage(multipartFile.getOriginalFilename());

			jBDao.addProduct(p);
		}
	}

	@Override
	public void productContent(HttpServletRequest request) {
		int pNo = Integer.valueOf(request.getParameter("pNo"));

		PointProduct prod = jBDao.productContent(pNo);

		request.setAttribute("prod", prod);
	}

	@Override
	public void productUpdate(HttpServletRequest request) {
		int pProductCode = Integer.valueOf(request.getParameter("pProductCode"));

		PointProduct prod = jBDao.productContent(pProductCode);

		request.setAttribute("prod", prod);
	}

	@Override
	public void productUpdateResult(MultipartHttpServletRequest request, String path) throws IOException {
		MultipartFile multipartFile = request.getFile("image");

		if (!multipartFile.isEmpty()) {
			File file = new File(path, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);

			PointProduct p = new PointProduct();
			p.setpProductCode(Integer.valueOf(request.getParameter("pProductCode")));
			p.setpName(request.getParameter("pname"));
			p.setpPrice(Integer.valueOf(request.getParameter("price")));
			p.setpAmount(Integer.valueOf(request.getParameter("amount")));
			p.setpImage(multipartFile.getOriginalFilename());

			jBDao.updateProduct(p);
		}
	}

	@Override
	public void productDelete(HttpServletRequest request) {
		int pProdcutCode = Integer.valueOf(request.getParameter("pProductCode"));

		jBDao.productDelete(pProdcutCode);
	}

	@Override
	public void addCart(HttpServletRequest request, HttpSession session) {
		int pNo = Integer.valueOf(request.getParameter("pNo"));
		int quentity = Integer.valueOf(request.getParameter("quantity"));

		PointProduct prod = jBDao.productContent(pNo);

		int check = 0;
		prod.setpQuantity(quentity);
		if (pList.size() == 0) {
			pList.add(prod);
		} else {
			for (int i = 0; i < pList.size(); i++) {
				if (prod.getpProductCode() == pList.get(i).getpProductCode()) {
					check += 1;
					pList.get(i).setpQuantity(quentity);
				}
			}
			if (check == 0) {
				pList.add(prod);
			}
		}
	}

	@Override
	public void getCart(HttpSession session) {
		session.setAttribute("pList", pList);
	}

	@Override
	public void buyCartProduct(HttpServletRequest request) {
		String[] pCodeList = request.getParameterValues("checkbox");

		ArrayList<PointProduct> prodList = new ArrayList<PointProduct>();
		for (int i = 0; i < pCodeList.length; i++) {
			int pNo = Integer.valueOf(pCodeList[i]);
			int quantity = Integer.valueOf(request.getParameter("quantity" + (i + 1)));
			PointProduct p = jBDao.productContent(pNo);
			p.setpQuantity(quantity);
			prodList.add(p);
		}
		request.setAttribute("pList", prodList);
	}

	@Override
	public void orderPrduct(HttpServletRequest request, HttpSession session) {
		String[] pCodeList = request.getParameterValues("checkbox");
		ArrayList<PointProduct> prodList = new ArrayList<PointProduct>();
		Member m = (Member) session.getAttribute("loginUser");
		
		for (int i = 0; i < pCodeList.length; i++) {
			int pNo = Integer.valueOf(pCodeList[i]);
			int quantity = Integer.valueOf(request.getParameter("quantity" + (i + 1)));
			PointProduct p = jBDao.productContent(pNo);
			int nAmount = p.getpAmount() - quantity;
			int nBuy = p.getpBuy() + 1;
			p.setpAmount(nAmount);
			p.setpBuy(nBuy);
			m.setUsepoint(m.getUsepoint()+p.getpPrice() * quantity);
			jBDao.orderProduct(p, m);
			p.setpQuantity(quantity);
			prodList.add(p);
		}
		request.setAttribute("pList", prodList);
		session.setAttribute("loginUser", m);
	}

	@Override
	public void buyProduct(HttpServletRequest request) {
		ArrayList<PointProduct> prodList = new ArrayList<PointProduct>();
		int pNo = Integer.valueOf(request.getParameter("pProductCode"));
		int quantity = Integer.valueOf(request.getParameter("quantity"));
		PointProduct p = jBDao.productContent(pNo);
		p.setpQuantity(quantity);
		prodList.add(p);
		request.setAttribute("pList", prodList);
	}

	@Override
	public void aggroBoardList(HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.valueOf(pageNum);
		
		int startRow = currentPage * PAGE_SIZE - PAGE_SIZE;
		int listCount = jBDao.getaggroCount();

		if (listCount > 0) {
			List<FreeBoard> aggroList = jBDao.getAggroList(startRow, PAGE_SIZE);
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
	public void aggroBoardWriteResult(MultipartHttpServletRequest request, HttpSession session, String path) throws IOException {
		MultipartFile multipartFile = request.getFile("image");
		FreeBoard fb = new FreeBoard();
		if (!multipartFile.isEmpty()) {
			File file = new File(path, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);

			fb.setFrbArea(request.getParameter("area"));
			fb.setFrbWriter(request.getParameter("writer"));
			fb.setFrbEmail(request.getParameter("email"));
			fb.setFrbTitle(request.getParameter("title"));
			fb.setFrbPass(request.getParameter("pass"));
			fb.setFrbContent(request.getParameter("content"));
			Timestamp time = new Timestamp(System.currentTimeMillis());
			fb.setFrbWriteDate(time);
			fb.setPhoto1(multipartFile.getOriginalFilename());
			jBDao.aggroBoardWritephoto(fb);
		}else{
			fb.setFrbArea(request.getParameter("area"));
			fb.setFrbWriter(request.getParameter("writer"));
			fb.setFrbEmail(request.getParameter("email"));
			fb.setFrbTitle(request.getParameter("title"));
			fb.setFrbPass(request.getParameter("pass"));
			fb.setFrbContent(request.getParameter("content"));
			Timestamp time = new Timestamp(System.currentTimeMillis());
			fb.setFrbWriteDate(time);
			jBDao.aggroBoardWrite(fb);
		}
	}
}
