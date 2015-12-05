package com.bookmanage.controller;

public class ViewInfo {
	private boolean isRedirect;
	private String view;
	private String reqURI;

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getReqURI() {
		return reqURI;
	}

	public void setReqURI(String reqURI) {
		this.reqURI = reqURI;
	}
}