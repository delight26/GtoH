package com.project.call.domain;

import java.sql.Date;

public class AskBoard {

	private int abNo;
	private String abEmail;
	private String abToid;
	private String abEmailRank;
	private String abToidRank;
	private String abPlace;
	private Date abWriteDate;
	private Date abFightDate;
	private String abTell;
	private int abApproval;
   private String abNickName;
	
	
	
	public String getAbNickName() {
	return abNickName;
}

public void setAbNickName(String abNickName) {
	this.abNickName = abNickName;
}

	public String getAbEmailRank() {
		return abEmailRank;
	}

	public void setAbEmailRank(String abEmailRank) {
		this.abEmailRank = abEmailRank;
	}

	public String getAbToidRank() {
		return abToidRank;
	}

	public void setAbToidRank(String abToidRank) {
		this.abToidRank = abToidRank;
	}

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

	
	public String getAbPlace() {
		return abPlace;
	}

	public void setAbPlace(String abPlace) {
		this.abPlace = abPlace;
	}

	public Date getAbWriteDate() {
		return abWriteDate;
	}

	public void setAbWriteDate(Date abWriteDate) {
		this.abWriteDate = abWriteDate;
	}

	public Date getAbFightDate() {
		return abFightDate;
	}

	public void setAbFightDate(Date abFightDate) {
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
