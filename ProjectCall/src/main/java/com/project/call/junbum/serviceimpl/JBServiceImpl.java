package com.project.call.junbum.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.Member;
import com.project.call.domain.PointProduct;
import com.project.call.junbum.dao.JBDao;
import com.project.call.junbum.service.JBService;

@Service
public class JBServiceImpl implements JBService {

	@Autowired
	private JBDao jBDao;

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
		List<PointProduct> pList = jBDao.getproductList();
		
		request.setAttribute("pList", pList);
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
}
