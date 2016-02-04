package com.project.call.yoonseok.serviceimpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
	public void getNote(HttpServletRequest request, Model model) throws Exception {
		int maxPage = 0;
		String toId = request.getParameter("toid");
		System.out.println(toId);
		Member member = jBDao.getMember(toId);
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		//int listCount = jBDao.getCount(toId);
		int start =0;
		int end = 5;
		if(pageNum == 1){
			start = 0;
		}else if(pageNum == 2){
			start = 5;
		}else{
			start = pageNum*5-5;			
		}
		System.out.println(start+""+ end);
		System.out.println(member.getNickName());
		List<NoticeBoard> noticeBoardList = jBDao.getNoticeBoard(member.getNickName(), start, end);
		if(noticeBoardList.isEmpty()) {
			maxPage = 0;
		} else {
			System.out.println(noticeBoardList.get(0).getNbMaxPage());
			maxPage =noticeBoardList.get(0).getNbMaxPage();
		}
		
		model.addAttribute("noteList",noticeBoardList);
		model.addAttribute("size",  noticeBoardList.size());
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("maxPage", maxPage);
		
		
	}

	@Override
	public List<String> nickNameSearch(String nickName) {
		
		return jBDao.nickNameSearch(nickName);
	}

	
}






















