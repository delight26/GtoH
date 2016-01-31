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
			throws IOException;

	public FreeBoard noticeModifyForm(HttpServletRequest request);

	public void noticeModify(MultipartHttpServletRequest request, HttpServletResponse response, String filePath)
			throws IOException;

	
}
