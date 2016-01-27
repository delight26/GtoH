package com.project.call.domain;

import java.sql.Timestamp;

public class FreeBoard {

	private int fbNo;
	private String fbNickName;
	private String fbTitle;
	private String fbContent;
	private Timestamp fbDate;
	private int fbClick;
	private int fbcomment;
	private String fbImage1;
	private String fbImage2;
	private String fbImage3;

	public String getFbImage1() {
		return fbImage1;
	}

	public void setFbImage1(String fbImage1) {
		this.fbImage1 = fbImage1;
	}

	public String getFbImage2() {
		return fbImage2;
	}

	public void setFbImage2(String fbImage2) {
		this.fbImage2 = fbImage2;
	}

	public String getFbImage3() {
		return fbImage3;
	}

	public void setFbImage3(String fbImage3) {
		this.fbImage3 = fbImage3;
	}

	public int getFbNo() {
		return fbNo;
	}

	public void setFbNo(int fbNo) {
		this.fbNo = fbNo;
	}

	public String getFbNikname() {
		return fbNickName;
	}

	public void setFbNikname(String fbNickName) {
		this.fbNickName = fbNickName;
	}

	public String getFbTitle() {
		return fbTitle;
	}

	public void setFbTitle(String fbTitle) {
		this.fbTitle = fbTitle;
	}

	public String getFbContent() {
		return fbContent;
	}

	public void setFbContent(String fbContent) {
		this.fbContent = fbContent;
	}

	public Timestamp getFbDate() {
		return fbDate;
	}

	public void setFbDate(Timestamp fbDate) {
		this.fbDate = fbDate;
	}

	public int getFbClick() {
		return fbClick;
	}

	public void setFbClick(int fbClick) {
		this.fbClick = fbClick;
	}

	public int getFbcomment() {
		return fbcomment;
	}

	public void setFbcomment(int fbcomment) {
		this.fbcomment = fbcomment;
	}

}
