package com.project.call.wansoon.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.project.call.domain.FreeBoard;
import com.project.call.domain.FreebComment;
import com.project.call.wansoon.dao.WSDao;
import com.project.call.wansoon.service.WSService;

@Service
public class WSServiceImpl implements WSService {

	@Autowired
	private WSDao WSDao;
	
	public void setjBDao(WSDao WSDao) {
		this.WSDao = WSDao;
	}

	@Override
	public List<FreeBoard> getFreeBoardAll() {
		
		return WSDao.getFreeBoardAll();
	
	}

	@Override
	public FreeBoard getFreeBoard(int frbNo) {
		
		return WSDao.getFreeBoard(frbNo);
	}

	@Override
	public List<FreeBoard> insertBoard(FreeBoard freeboard) {
		
		return WSDao.insertBoard(freeboard);
	}

	@Override
	public void addWrite(MultipartHttpServletRequest request, String filePath) throws IOException {
	MultipartFile multipartFile = request.getFile("image");
		
		// 업로드된 파일 데이터가 존재하면
		if(!multipartFile.isEmpty()) {
			
			File file = new File(filePath, multipartFile.getOriginalFilename());
			
			// 업로드한 파일 데이터를 지정한 파일로 저장한다.
			multipartFile.transferTo(file);
			
			// 파일 업로드가 완료되면 ProductDao를 이용해 상품 정보를 DB에 저장한다.
			FreeBoard frb = new FreeBoard();
			
			frb.setFrbNo(Integer.parseInt(request.getParameter("no")));
			frb.setFrbTitle(request.getParameter("frbTitle"));
			frb.setFrbPass(request.getParameter("frbPass"));
			frb.setFrbContent(request.getParameter("frbContent"));
			frb.setPhoto1(multipartFile.getOriginalFilename());
			frb.setFrbWriteDate(new Timestamp(System.currentTimeMillis()));
			frb.setFrbHit(Integer.parseInt(request.getParameter("frbHit")));
			frb.setFrbArea(request.getParameter("frbArea"));
			frb.setFrbEmail(request.getParameter("frbEmail"));
			frb.setFrbWriter(request.getParameter("frbWriter"));
			
			WSDao.addWrite(frb);
		
		}
	}

	@Override
	public void modifyWrite(FreeBoard freeboard, String filePath) {
		
		WSDao.modifyWrite(freeboard, filePath);
	}
	
	@Override
	public void deleteBoard(int frbNo) {
		WSDao.deleteBoard(frbNo);
		
	}
	
	@Override
	public void addComment(FreebComment freebComment) {
		
		WSDao.addComment(freebComment);
		
	}

	@Override
	public List<FreebComment> commentAllList(int bno) {
		
		return WSDao.commentAllList(bno);
	}

	





	
	
	}
