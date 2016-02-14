package com.project.call.domain;

import java.sql.Timestamp;

public class Fight {

	private int fightNumber;
	private Timestamp callDate;
	private Timestamp resultDate;
	private String player1;
	private String player2;
	private int result;
	private int player1Result;
	private int player2Result;
	
	public Fight() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Fight(int fightNumber, Timestamp callDate, Timestamp resultDate, String player1, String player2, int result,
			int player1Result, int player2Result) {
		super();
		this.fightNumber = fightNumber;
		this.callDate = callDate;
		this.resultDate = resultDate;
		this.player1 = player1;
		this.player2 = player2;
		this.result = result;
		this.player1Result = player1Result;
		this.player2Result = player2Result;
	}



	public int getFightNumber() {
		return fightNumber;
	}

	public void setFightNumber(int fightNumber) {
		this.fightNumber = fightNumber;
	}

	public Timestamp getCallDate() {
		return callDate;
	}

	public void setCallDate(Timestamp callDate) {
		this.callDate = callDate;
	}

	public Timestamp getResultDate() {
		return resultDate;
	}

	public void setResultDate(Timestamp resultDate) {
		this.resultDate = resultDate;
	}

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getPlayer1Result() {
		return player1Result;
	}

	public void setPlayer1Result(int player1Result) {
		this.player1Result = player1Result;
	}

	public int getPlayer2Result() {
		return player2Result;
	}

	public void setPlayer2Result(int player2Result) {
		this.player2Result = player2Result;
	}
	
	
	
}
