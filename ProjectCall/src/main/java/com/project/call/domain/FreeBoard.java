package com.project.call.domain;

import java.sql.Timestamp;

public class FreeBoard {
	private int frbNo;
	private String frbTitle;
	private String frbPass;
	private String frbContent;
	private String Photo1;
	private Timestamp frbWriteDate;
	private int frbHit;
	private String frbArea;
	private String frbEmail;
	private String frbWriter;

	public int getFrbNo() {
		return frbNo;
	}

	public void setFrbNo(int frbNo) {
		this.frbNo = frbNo;
	}

	public String getFrbTitle() {
		return frbTitle;
	}

	public void setFrbTitle(String frbTitle) {
		this.frbTitle = frbTitle;
	}

	public String getFrbPass() {
		return frbPass;
	}

	public void setFrbPass(String frbPass) {
		this.frbPass = frbPass;
	}

	public String getFrbContent() {
		return frbContent;
	}

	public void setFrbContent(String frbContent) {
		this.frbContent = frbContent;
	}

	public String getPhoto1() {
		return Photo1;
	}

	public void setPhoto1(String photo1) {
		Photo1 = photo1;
	}

	public Timestamp getFrbWriteDate() {
		return frbWriteDate;
	}

	public void setFrbWriteDate(Timestamp frbWriteDate) {
		this.frbWriteDate = frbWriteDate;
	}

	public int getFrbHit() {
		return frbHit;
	}

	public void setFrbHit(int frbHit) {
		this.frbHit = frbHit;
	}

	public String getFrbArea() {
		return frbArea;
	}

	public void setFrbArea(String frbArea) {
		this.frbArea = frbArea;
	}

	public String getFrbEmail() {
		return frbEmail;
	}

	public void setFrbEmail(String frbEmail) {
		this.frbEmail = frbEmail;
	}

	public String getFrbWriter() {
		return frbWriter;
	}

	public void setFrbWriter(String frbWriter) {
		this.frbWriter = frbWriter;
	}

}
