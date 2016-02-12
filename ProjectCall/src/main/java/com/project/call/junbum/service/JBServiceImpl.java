package com.project.call.junbum.service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.AskBoard;
import com.project.call.domain.Comment;
import com.project.call.domain.FreeBoard;
import com.project.call.domain.Member;
import com.project.call.domain.PointProduct;
import com.project.call.hyunsu.email.Email;
import com.project.call.hyunsu.email.EmailFileSender;
import com.project.call.hyunsu.supprot.ScriptHandling;
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

	@Autowired
	private ScriptHandling scriptHandling;

	@Autowired
	private EmailFileSender emailFileSender;

	@Override
	public Boolean loginResult(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {

		boolean result = true;
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");

		Member m = jBDao.getloginResult(email);

		if (m.getPass().equals(pass)) {
			session.setAttribute("loginUser", m);
		} else {
			result = false;
			scriptHandling.historyBack(response, "정보가 일치 하지 않습니다");
		}
		return result;
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
		int quantity = Integer.valueOf(request.getParameter("quantity"));

		PointProduct prod = jBDao.productContent(pNo);

		int check = 0;
		prod.setpQuantity(quantity);
		if (pList.size() == 0) {
			pList.add(prod);
		} else {
			for (int i = 0; i < pList.size(); i++) {
				if (prod.getpProductCode() == pList.get(i).getpProductCode()) {
					check += 1;
					pList.get(i).setpQuantity(quantity);
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
	public void orderPrduct(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
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
			m.setUsepoint(m.getUsepoint() + p.getpPrice() * quantity);
			jBDao.orderProduct(p, m);
			p.setpQuantity(quantity);
			prodList.add(p);
			for (int j = 0; j < quantity; j++) {
				if (p.getpProductCode() == 5) {
					Email email = new Email();
					email.setReciver(m.getEmail());
					email.setSubject("ProjectCall에서 구매하신 상품입니다");
					email.setContent("기프티콘 이미지로 발송하였으니 확인 부탁드립니다");
					System.out.println(m.getEmail() + "상품 발송 하였습니다" + p.getpName() + "..." + (j + 1) + "회");
					emailFileSender.sendEmail(email, "C:\\mun\\mun1000.jpg");
				}
			}
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
	public void aggroBoardWriteResult(MultipartHttpServletRequest request, HttpServletResponse response, HttpSession session, String path)
			throws Exception {
		MultipartFile multipartFile = request.getFile("image");
		FreeBoard fb = new FreeBoard();
		
		if(request.getParameter("title").equals("") || request.getParameter("title") == null
			||	request.getParameter("content").equals("") || request.getParameter("content") == null){
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
			jBDao.aggroBoardWritephoto(fb);
		} else {
			fb.setFrbArea(request.getParameter("area"));
			fb.setFrbWriter(request.getParameter("writer"));
			fb.setFrbEmail(request.getParameter("email"));
			fb.setFrbTitle(request.getParameter("title"));
			fb.setFrbContent(request.getParameter("content"));
			Timestamp time = new Timestamp(System.currentTimeMillis());
			fb.setFrbWriteDate(time);
			jBDao.aggroBoardWrite(fb);
		}
	}

	@Override
	public void aggroContent(HttpServletRequest request) {
		int frbNo = Integer.valueOf(request.getParameter("frbNo"));
		int frbHit = Integer.valueOf(request.getParameter("frbHit"));
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		jBDao.aggroHitUpdate(frbHit + 1, frbNo);
		FreeBoard frb = jBDao.aggroContent(frbNo);
		request.setAttribute("frb", frb);
		request.setAttribute("pageNum", pageNum);
	}

	@Override
	public void aggroPreContent(HttpServletRequest request) {
		int frbNo = Integer.valueOf(request.getParameter("frbNo"));
		Integer preNo = jBDao.aggroPreNo(frbNo);
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		if (preNo == null) {

		} else {
			FreeBoard frb = jBDao.aggroContent(preNo);
			jBDao.aggroHitUpdate(frb.getFrbHit() + 1, preNo);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("frb", frb);
		}
	}

	@Override
	public void aggroNextContent(HttpServletRequest request) {
		int frbNo = Integer.valueOf(request.getParameter("frbNo"));
		Integer nextNo = jBDao.aggroNextNo(frbNo);
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		if (nextNo == null) {

		} else {
			FreeBoard frb = jBDao.aggroContent(nextNo);
			jBDao.aggroHitUpdate(frb.getFrbHit() + 1, nextNo);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("frb", frb);
		}
	}

	@Override
	public void aggroUpdateForm(HttpServletRequest request) {
		int frbNo = Integer.valueOf(request.getParameter("frbNo"));
		FreeBoard frb = jBDao.aggroContent(frbNo);

		request.setAttribute("frb", frb);
	}

	@Override
	public void agrroUpdateResult(MultipartHttpServletRequest request, String path) throws IOException {
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
			jBDao.aggroBoardUpdatePhoto(fb);
		} else {
			fb.setFrbNo(Integer.valueOf(request.getParameter("frbNo")));
			fb.setFrbArea(request.getParameter("area"));
			fb.setFrbWriter(request.getParameter("writer"));
			fb.setFrbEmail(request.getParameter("email"));
			fb.setFrbTitle(request.getParameter("title"));
			fb.setFrbContent(request.getParameter("content"));
			Timestamp time = new Timestamp(System.currentTimeMillis());
			fb.setFrbWriteDate(time);
			jBDao.aggroBoardUpdate(fb);
		}
	}

	@Override
	public void aggroDelete(HttpServletRequest request) {
		int frbNo = Integer.valueOf(request.getParameter("frbNo"));
		jBDao.aggroDelete(frbNo);
	}

	@Override
	public void aggroSearch(HttpServletRequest request) {
		String search = request.getParameter("search");

		String pageNum = "1";
		int currentPage = Integer.valueOf(pageNum);

		int startRow = currentPage * PAGE_SIZE - PAGE_SIZE;

		int listCount = jBDao.aggroSearchCount(search);

		if (listCount > 0) {
			List<FreeBoard> aggroList = jBDao.aggroSearch(search, startRow, PAGE_SIZE);
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
	public void getComment(String No, String pageNum, HttpServletRequest request) {
		int frbNo = Integer.valueOf(No);

		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.valueOf(pageNum);

		int startRow = currentPage * PAGE_SIZE - PAGE_SIZE;
		int listCount = jBDao.getCommentCount(frbNo);

		if (listCount > 0) {
			List<Comment> cList = jBDao.getComment(frbNo, startRow, PAGE_SIZE);
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
	public void aggroCommentWrite(String frbNo, String content, String email) {

		Comment c = new Comment();
		c.setbNo(Integer.valueOf(frbNo));
		c.setcContent(content);
		c.setcEmail(email);

		jBDao.aggroCommentWrite(c);
	}

	@Override
	public void aggroCommentUpdate(String cNo, String content) {
		Comment c = new Comment();
		c.setcNo(Integer.valueOf(cNo));
		c.setcContent(content);

		jBDao.aggroCommentUpdate(c);
	}

	@Override
	public void aggroCommentDelete(String No) {
		int cNo = Integer.valueOf(No);

		jBDao.aggroCommentDelete(cNo);
	}

	@Override
	public void askResultList(HttpServletRequest request, HttpSession session) {
		Member m = (Member) session.getAttribute("loginUser");
		String email = m.getEmail();
		List<AskBoard> aList = jBDao.askResultList(email);

		request.setAttribute("aList", aList);
	}

	@Override
	public void askResultUpdate(HttpServletRequest request, HttpSession session) {
		int abNo = Integer.valueOf(request.getParameter("abNo"));

		AskBoard ab = jBDao.getAskResult(abNo);

		request.setAttribute("ab", ab);
	}

	@Override
	public void askResultUpdateResult(HttpServletRequest request) {
		AskBoard ab = new AskBoard();
		ab.setAbNo(Integer.valueOf(request.getParameter("abNo")));
		ab.setAbToid(request.getParameter("toId"));
		ab.setAbFightDate(Date.valueOf(request.getParameter("fightDate")));
		ab.setAbPlace(request.getParameter("place"));
		ab.setAbTell(request.getParameter("tell"));

		jBDao.askResultUpdateResult(ab);

	}

	@Override
	public void askResultDelete(HttpServletRequest request) {
		int abNo = Integer.valueOf(request.getParameter("abNo"));
		jBDao.askResultDelete(abNo);
	}

	@Override
	public void askReceveList(HttpServletRequest request, HttpSession session) {
		Member m = (Member) session.getAttribute("loginUser");
		String nickName = m.getNickName();
		List<AskBoard> aList = jBDao.askReceveList(nickName);
		if (aList != null) {
			request.setAttribute("aList", aList);
		} else {
			request.setAttribute("aList", null);
		}

	}

	@Override
	public void askApproval(HttpServletRequest request) {
		int abNo = Integer.valueOf(request.getParameter("abNo"));
		AskBoard ab = jBDao.getAskBoard(abNo);
		jBDao.addFight(ab);
		jBDao.askApproval(abNo);
	}

	@Override
	public void askCancel(HttpServletRequest request) {
		int abNo = Integer.valueOf(request.getParameter("abNo"));

		jBDao.askCancel(abNo);
	}
}
