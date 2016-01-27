package com.project.call.domain;

import java.sql.Timestamp;

public class Message {
	private String msSendNickName;
	private String msReceiveNickName;
	private String msTitle;
	private String msContent;
	private Timestamp msDate;
	private int msReadCheck;

	public String getMsSendNickName() {
		return msSendNickName;
	}

	public void setMsSendNickName(String msSendNickName) {
		this.msSendNickName = msSendNickName;
	}

	public String getMsReceiveNickName() {
		return msReceiveNickName;
	}

	public void setMsReceiveNickName(String msReceiveNickName) {
		this.msReceiveNickName = msReceiveNickName;
	}

	public String getMsTitle() {
		return msTitle;
	}

	public void setMsTitle(String msTitle) {
		this.msTitle = msTitle;
	}

	public String getMsContent() {
		return msContent;
	}

	public void setMsContent(String msContent) {
		this.msContent = msContent;
	}

	public Timestamp getMsDate() {
		return msDate;
	}

	public void setMsDate(Timestamp msDate) {
		this.msDate = msDate;
	}

	public int getMsReadCheck() {
		return msReadCheck;
	}

	public void setMsReadCheck(int msReadCheck) {
		this.msReadCheck = msReadCheck;
	}

}
