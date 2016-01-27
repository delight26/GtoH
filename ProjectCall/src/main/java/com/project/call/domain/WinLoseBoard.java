package com.project.call.domain;

import java.sql.Timestamp;

public class WinLoseBoard {
	private int wlNo;
	private String wlTitle;
	private String wlContent;
	private String wlImage1;
	private String wlImage2;
	private String wlImage3;
	private String wlWinNickName;
	private String wlLoseNickName;
	private Timestamp wlDate;
	private Timestamp wlFightDate;
	private int wlClick;
	private int wlComment;

	public int getWlNo() {
		return wlNo;
	}

	public void setWlNo(int wlNo) {
		this.wlNo = wlNo;
	}

	public String getWlTitle() {
		return wlTitle;
	}

	public void setWlTitle(String wlTitle) {
		this.wlTitle = wlTitle;
	}

	public String getWlContent() {
		return wlContent;
	}

	public void setWlContent(String wlContent) {
		this.wlContent = wlContent;
	}

	public String getWlImage1() {
		return wlImage1;
	}

	public void setWlImage1(String wlImage1) {
		this.wlImage1 = wlImage1;
	}

	public String getWlImage2() {
		return wlImage2;
	}

	public void setWlImage2(String wlImage2) {
		this.wlImage2 = wlImage2;
	}

	public String getWlImage3() {
		return wlImage3;
	}

	public void setWlImage3(String wlImage3) {
		this.wlImage3 = wlImage3;
	}

	public String getWlWinNickName() {
		return wlWinNickName;
	}

	public void setWlWinNickName(String wlWinNickName) {
		this.wlWinNickName = wlWinNickName;
	}

	public String getWlLoseNickName() {
		return wlLoseNickName;
	}

	public void setWlLoseNickName(String wlLoseNickName) {
		this.wlLoseNickName = wlLoseNickName;
	}

	public Timestamp getWlDate() {
		return wlDate;
	}

	public void setWlDate(Timestamp wlDate) {
		this.wlDate = wlDate;
	}

	public Timestamp getWlFightDate() {
		return wlFightDate;
	}

	public void setWlFightDate(Timestamp wlFightDate) {
		this.wlFightDate = wlFightDate;
	}

	public int getWlClick() {
		return wlClick;
	}

	public void setWlClick(int wlClick) {
		this.wlClick = wlClick;
	}

	public int getWlComment() {
		return wlComment;
	}

	public void setWlComment(int wlComment) {
		this.wlComment = wlComment;
	}

}
