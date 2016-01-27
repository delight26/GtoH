package com.project.call.domain;

import java.sql.Timestamp;

public class VSBoard {
	private String vsType;
	private int vsNo;
	private String vsNickname;
	private String vsTitle;
	private String vsContent;
	private Timestamp vsDate;
	private int vsClick;
	private int vsComment;
	private String vsImage1;
	private String vsImage2;
	private String vsImage3;

	public String getVsType() {
		return vsType;
	}

	public void setVsType(String vsType) {
		this.vsType = vsType;
	}

	public int getVsNo() {
		return vsNo;
	}

	public void setVsNo(int vsNo) {
		this.vsNo = vsNo;
	}

	public String getVsNikname() {
		return vsNickname;
	}

	public void setVsNikname(String vsNickname) {
		this.vsNickname = vsNickname;
	}

	public String getVsTitle() {
		return vsTitle;
	}

	public void setVsTitle(String vsTitle) {
		this.vsTitle = vsTitle;
	}

	public String getVsContent() {
		return vsContent;
	}

	public void setVsContent(String vsContent) {
		this.vsContent = vsContent;
	}

	public Timestamp getVsDate() {
		return vsDate;
	}

	public void setVsDate(Timestamp vsDate) {
		this.vsDate = vsDate;
	}

	public int getVsClick() {
		return vsClick;
	}

	public void setVsClick(int vsClick) {
		this.vsClick = vsClick;
	}

	public int getVsComment() {
		return vsComment;
	}

	public void setVsComment(int vsComment) {
		this.vsComment = vsComment;
	}

	public String getVsImage1() {
		return vsImage1;
	}

	public void setVsImage1(String vsImage1) {
		this.vsImage1 = vsImage1;
	}

	public String getVsImage2() {
		return vsImage2;
	}

	public void setVsImage2(String vsImage2) {
		this.vsImage2 = vsImage2;
	}

	public String getVsImage3() {
		return vsImage3;
	}

	public void setVsImage3(String vsImage3) {
		this.vsImage3 = vsImage3;
	}

}
