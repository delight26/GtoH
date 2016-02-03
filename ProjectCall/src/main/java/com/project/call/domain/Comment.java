package com.project.call.domain;

public class Comment {
	private int cNo;
	private String cEmial;
	private String cWriter;
	private String cContent;
	private int bNo;
	
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
	public String getcEmial() {
		return cEmial;
	}
	public void setcEmial(String cEmial) {
		this.cEmial = cEmial;
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
