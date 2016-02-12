package com.project.call.yoonseok.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.project.call.domain.Member;
import com.project.call.domain.NoticeBoard;

public interface YSService {
   public List<Member> ranking();
   public void addNote(NoticeBoard note);
   public List<NoticeBoard> getNote(String toid, int pageNum);
   //현수 보정들어감
   public void getNote(HttpServletRequest request, Model model) throws Exception;
   public Member modalSearch(String nickName);
   public NoticeBoard noteContent(int nbNo, String check);
   public void deleteNote(int nbNo);
   public int noteCheck(String toid);
   public List<String> nickNameSearch(String nickName);
   public List<NoticeBoard> sendNote(String email, int pageNum);
}
