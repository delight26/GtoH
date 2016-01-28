package com.project.call.domain;

import java.sql.Timestamp;

public class FightBoard {
	private int fbNo;
	private Timestamp fbCallDate;
	private Timestamp fbResultDate;
	private String fbP1;
	private String fbP2;
	private String fbresult;

	public int getFbNo() {
		return fbNo;
	}

	public void setFbNo(int fbNo) {
		this.fbNo = fbNo;
	}

	public Timestamp getFbCallDate() {
		return fbCallDate;
	}

	public void setFbCallDate(Timestamp fbCallDate) {
		this.fbCallDate = fbCallDate;
	}

	public Timestamp getFbResultDate() {
		return fbResultDate;
	}

	public void setFbResultDate(Timestamp fbResultDate) {
		this.fbResultDate = fbResultDate;
	}

	public String getFbP1() {
		return fbP1;
	}

	public void setFbP1(String fbP1) {
		this.fbP1 = fbP1;
	}

	public String getFbP2() {
		return fbP2;
	}

	public void setFbP2(String fbP2) {
		this.fbP2 = fbP2;
	}

	public String getFbresult() {
		return fbresult;
	}

	public void setFbresult(String fbresult) {
		this.fbresult = fbresult;
	}

}
