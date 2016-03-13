package com.project.call.domain;

import java.sql.Timestamp;

public class FightResultBoard {
	
	private int no;
	private int fightNumber;
	private String title;
	private String content;
	private String writer;
	private String photo;
	private int isAdminCheck;
	private Timestamp writeDate;
	private int hit;
	private String winner;
	private int pageSize;
	
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getIsAdminCheck() {
		return isAdminCheck;
	}
	public void setIsAdminCheck(int isAdminCheck) {
		this.isAdminCheck = isAdminCheck;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getFightNumber() {
		return fightNumber;
	}
	public void setFightNumber(int fightNumber) {
		this.fightNumber = fightNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public Timestamp getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
}
