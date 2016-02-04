package com.project.call.yoonseok.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.call.domain.Member;
import com.project.call.domain.NoticeBoard;
import com.project.call.yoonseok.dao.YSDao;
import com.project.call.yoonseok.service.YSService;

@Service
public class YSServiceImpl implements YSService {

	@Autowired
	private YSDao jBDao;
	
	public void setjBDao(YSDao jBDao) {
		this.jBDao = jBDao;
	}

	@Override
	public List<Member> ranking() {
		return jBDao.ranking();
	}

	@Override
	public void addNote(NoticeBoard note) {
		jBDao.addNote(note);
	}

	@Override
	public List<NoticeBoard> getNote(String toid, int pageNum) {
		
		return jBDao.getNote(toid, pageNum);
	}

	@Override
	public NoticeBoard noteContent(int nbNo) {
		return jBDao.noteContent(nbNo);
	}

	@Override
	public void deleteNote(int nbNo) {
		jBDao.deleteNote(nbNo);
		
	}

	@Override
	public int noteCheck(String toid) {
		return jBDao.noteCheck(toid);
	}

	@Override
	public List<String> nickNameSearch(String nickName) {
		
		return jBDao.nickNameSearch(nickName);
	}

	
}
