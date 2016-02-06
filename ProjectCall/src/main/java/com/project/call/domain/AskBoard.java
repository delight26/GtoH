package com.project.call.domain;

import java.sql.Timestamp;

public class AskBoard {

	private int abNo;
	private String abEmail;
	private String abToid;
	private int abEmailRank;
	private int abToidRank;
	private String abPlace;
	private Timestamp abWriteDate;
	private Timestamp abFightDate;
	private String abTell;
	private int abApproval;

	public int getAbNo() {
		return abNo;
	}

	public void setAbNo(int abNo) {
		this.abNo = abNo;
	}

	public String getAbEmail() {
		return abEmail;
	}

	public void setAbEmail(String abEmail) {
		this.abEmail = abEmail;
	}

	public String getAbToid() {
		return abToid;
	}

	public void setAbToid(String abToid) {
		this.abToid = abToid;
	}

	public int getAbEmailRank() {
		return abEmailRank;
	}

	public void setAbEmailRank(int abEmailRank) {
		this.abEmailRank = abEmailRank;
	}

	public int getAbToidRank() {
		return abToidRank;
	}

	public void setAbToidRank(int abToidRank) {
		this.abToidRank = abToidRank;
	}

	public String getAbPlace() {
		return abPlace;
	}

	public void setAbPlace(String abPlace) {
		this.abPlace = abPlace;
	}

	public Timestamp getAbWriteDate() {
		return abWriteDate;
	}

	public void setAbWriteDate(Timestamp abWriteDate) {
		this.abWriteDate = abWriteDate;
	}

	public Timestamp getAbFightDate() {
		return abFightDate;
	}

	public void setAbFightDate(Timestamp abFightDate) {
		this.abFightDate = abFightDate;
	}

	public String getAbTell() {
		return abTell;
	}

	public void setAbTell(String abTell) {
		this.abTell = abTell;
	}

	public int getAbApproval() {
		return abApproval;
	}

	public void setAbApproval(int abApproval) {
		this.abApproval = abApproval;
	}

}
