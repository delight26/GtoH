package com.project.call.ikjae.controller;

import java.sql.Timestamp;

public class Test {

	public static void main(String[] args) {
		long currentTime = System.currentTimeMillis();
		Timestamp t = new Timestamp(currentTime);
		System.out.println(t);

	}

}
