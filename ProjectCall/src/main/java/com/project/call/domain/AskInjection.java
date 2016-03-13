package com.project.call.domain;

import java.sql.Timestamp;

public class AskInjection {

	private int askNumber;
	private String toId;
	private String fightDate;
	private int approval;
	private String place;
	private Timestamp writeDate;
	private String tell;
	private String email;
	
	public AskInjection() {
		// TODO Auto-generated constructor stub
	}

	public AskInjection(int askNumber, String toId, String fightDate, int approval, String place, Timestamp writeDate,
			String tell, String email) {
		super();
		this.askNumber = askNumber;
		this.toId = toId;
		this.fightDate = fightDate;
		this.approval = approval;
		this.place = place;
		this.writeDate = writeDate;
		this.tell = tell;
		this.email = email;
	}

	public AskInjection(String toId, String fightDate, int approval, String place, Timestamp writeDate, String tell,
			String email) {
		super();
		this.toId = toId;
		this.fightDate = fightDate;
		this.approval = approval;
		this.place = place;
		this.writeDate = writeDate;
		this.tell = tell;
		this.email = email;
	}

	public int getAskNumber() {
		return askNumber;
	}

	public void setAskNumber(int askNumber) {
		this.askNumber = askNumber;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public String getFightDate() {
		return fightDate;
	}

	public void setFightDate(String fightDate) {
		this.fightDate = fightDate;
	}

	public int getApproval() {
		return approval;
	}

	public void setApproval(int approval) {
		this.approval = approval;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Timestamp getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		
		return "toid: " + toId + "fday: " + fightDate + " app : " + approval + 
				"place : " + place + " tell : " + tell + " email : " + email;
	}
	
}

