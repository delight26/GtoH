package com.project.call.domain;

import java.sql.Timestamp;

public class Comment {
	private int cNo;
	private String cEmail;
	private String cWriter;
	private String cContent;
	private Timestamp writeDate;
	private String photo;
	private int bNo;
	
	

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getcEmail() {
		return cEmail;
	}
	
	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}
	
	public Timestamp getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
	}

	public String getcWriter() {
		return cWriter;
	}

	public void setcWriter(String cWriter) {
		this.cWriter = cWriter;
	}

	public int getcNo() {
		return cNo;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
	}

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

}
