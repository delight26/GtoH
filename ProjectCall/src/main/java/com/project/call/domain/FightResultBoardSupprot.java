package com.project.call.domain;

import java.sql.Timestamp;

public class FightResultBoardSupprot {

	private int no;
	private int fightNumber;
	private int player1result;
	private Timestamp player1writeDate;
	private int player2result;
	private Timestamp player2writeDate;
	private int hit;
	private Timestamp callDate;
	private Timestamp resultDate;
	private String player1;
	private String player2;
/*	private int player1Result;
	private int player2Result;*/
	
	public FightResultBoardSupprot() {
		
	}
	
	public FightResultBoardSupprot(int no, int fightNumber, int player1result, Timestamp player1writeDate,
			int player2result, Timestamp player2writeDate, int hit, Timestamp callDate, Timestamp resultDate,
			String player1, String player2, int player1Result2, int player2Result2) {
		super();
		this.no = no;
		this.fightNumber = fightNumber;
		this.player1result = player1result;
		this.player1writeDate = player1writeDate;
		this.player2result = player2result;
		this.player2writeDate = player2writeDate;
		this.hit = hit;
		this.callDate = callDate;
		this.resultDate = resultDate;
		this.player1 = player1;
		this.player2 = player2;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getFightNumber() {
		return fightNumber;
	}

	public void setFightNumber(int fightNumber) {
		this.fightNumber = fightNumber;
	}

	public int getPlayer1result() {
		return player1result;
	}

	public void setPlayer1result(int player1result) {
		this.player1result = player1result;
	}

	public Timestamp getPlayer1writeDate() {
		return player1writeDate;
	}

	public void setPlayer1writeDate(Timestamp player1writeDate) {
		this.player1writeDate = player1writeDate;
	}

	public int getPlayer2result() {
		return player2result;
	}

	public void setPlayer2result(int player2result) {
		this.player2result = player2result;
	}

	public Timestamp getPlayer2writeDate() {
		return player2writeDate;
	}

	public void setPlayer2writeDate(Timestamp player2writeDate) {
		this.player2writeDate = player2writeDate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
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

	
}
