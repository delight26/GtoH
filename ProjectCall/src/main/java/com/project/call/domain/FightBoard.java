package com.project.call.domain;

import java.sql.Timestamp;

public class FightBoard {

	private int fbNo;
	private String fbTitle;
	private String fbContent;
	private String fbP1NickName;
	private String fbP2NickName;
	private String fbP1Rank;
	private String fbP2Rank;
	private Timestamp fbDate;
	private Timestamp fbFightDate;
	private int fbClick;
	private int fbComment;

	public int getFbNo() {
		return fbNo;
	}

	public void setFbNo(int fbNo) {
		this.fbNo = fbNo;
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

	public String getFbP1NickName() {
		return fbP1NickName;
	}

	public void setFbP1NickName(String fbP1NickName) {
		this.fbP1NickName = fbP1NickName;
	}

	public String getFbP2NickName() {
		return fbP2NickName;
	}

	public void setFbP2NickName(String fbP2NickName) {
		this.fbP2NickName = fbP2NickName;
	}

	public String getFbP1Rank() {
		return fbP1Rank;
	}

	public void setFbP1Rank(String fbP1Rank) {
		this.fbP1Rank = fbP1Rank;
	}

	public String getFbP2Rank() {
		return fbP2Rank;
	}

	public void setFbP2Rank(String fbP2Rank) {
		this.fbP2Rank = fbP2Rank;
	}

	public Timestamp getFbDate() {
		return fbDate;
	}

	public void setFbDate(Timestamp fbDate) {
		this.fbDate = fbDate;
	}

	public Timestamp getFbFightDate() {
		return fbFightDate;
	}

	public void setFbFightDate(Timestamp fbFightDate) {
		this.fbFightDate = fbFightDate;
	}

	public int getFbClick() {
		return fbClick;
	}

	public void setFbClick(int fbClick) {
		this.fbClick = fbClick;
	}

	public int getFbComment() {
		return fbComment;
	}

	public void setFbComment(int fbComment) {
		this.fbComment = fbComment;
	}

}
