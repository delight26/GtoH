package com.bookmanage.member;

public class Member {
	private String id;
	private String pass;
	private String name;
	private String phone;
	private String birthday;
	private String job;
	private String adress;

	public Member(String id, String pass, String name, String phone, String birthday, String job, String adress) {
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.phone = phone;
		this.birthday = birthday;
		this.job = job;
		this.adress = adress;
	}
	public Member(String id, String pass){
		this.id = id;
		this.pass = pass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

}
