package com.project.call.domain;

import java.sql.Timestamp;

public class FreebComment {

	private int no;
	private String comment;
	private String email;
	private int bno;
	private Timestamp writeDate;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public Timestamp getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
	}
	
	
	
}
