package com.project.call.domain;

import java.sql.Timestamp;

public class AskBoard {

	private int abNo;
	private String abEmail;
	private String abToid;
	private String abEmailRank;
	private String abToidRank;
	private String abPlace;
	private Timestamp abWriteDate;
	private Timestamp abFightDate;
	private String abTell;
	private int abApproval;

	public int getFbNo() {
		return abNo;
	}

	public void setFbNo(int fbNo) {
		this.abNo = fbNo;
	}

	public String getFbEmail() {
		return abEmail;
	}

	public void setFbEmail(String fbEmail) {
		this.abEmail = fbEmail;
	}

	public String getFbToid() {
		return abToid;
	}

	public void setFbToid(String fbToid) {
		this.abToid = fbToid;
	}

	public String getFbP1Rank() {
		return abEmailRank;
	}

	public void setFbP1Rank(String abEmailRank) {
		this.abEmailRank = abEmailRank;
	}

	public String getFbP2Rank() {
		return abToidRank;
	}

	public void setFbP2Rank(String abToidRank) {
		this.abToidRank = abToidRank;
	}

	public String getFbPlace() {
		return abPlace;
	}

	public void setFbPlace(String abPlace) {
		this.abPlace = abPlace;
	}

	public Timestamp getFbWriteDate() {
		return abWriteDate;
	}

	public void setFbWriteDate(Timestamp abWriteDate) {
		this.abWriteDate = abWriteDate;
	}

	public Timestamp getFbFightDate() {
		return abFightDate;
	}

	public void setFbFightDate(Timestamp abFightDate) {
		this.abFightDate = abFightDate;
	}

	public String getFbTell() {
		return abTell;
	}

	public void setFbTell(String abTell) {
		this.abTell = abTell;
	}

	public int getFbApproval() {
		return abApproval;
	}

	public void setFbApproval(int abApproval) {
		this.abApproval = abApproval;
	}

}
