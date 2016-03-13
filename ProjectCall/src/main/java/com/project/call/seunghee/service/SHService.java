package com.project.call.seunghee.service;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.call.domain.FreeBoard;

public interface SHService {
	
	public void getNoticeList(HttpServletRequest request);

	public FreeBoard getNoticeContent(HttpServletRequest request);

	public void noticeDelete(HttpServletRequest request);

	public void noticeWriteForm(HttpServletRequest request);

	public void noticeWrite(MultipartHttpServletRequest request, HttpServletResponse response, String filePath)
			throws Exception;

	public FreeBoard noticeModifyForm(HttpServletRequest request);

	public void noticeModify(MultipartHttpServletRequest request, HttpServletResponse response, String filePath)
			throws Exception;

	public void getSeoulRanking(HttpServletRequest request);

	public void getGyeonggiRanking(HttpServletRequest request);

	public void getKangwonRanking(HttpServletRequest request);

	public void getChungcheongRanking(HttpServletRequest request);

	public void getGyeongsangRanking(HttpServletRequest request);

	public void getJunlaRanking(HttpServletRequest request);

	public void getJejuRanking(HttpServletRequest request);

	public void noticePreContent(HttpServletRequest request);

	public void noticeNextContent(HttpServletRequest request);

	
}
