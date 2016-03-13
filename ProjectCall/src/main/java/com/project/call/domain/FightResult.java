package com.project.call.domain;

import java.sql.Timestamp;

public class FightResult {
	
	private int no;
	private int fightNumber;
	private int player1result;
	private Timestamp player1writeDate;
	private int player2result;
	private Timestamp player2writeDate;
	private int hit;

	public FightResult() {
		// TODO Auto-generated constructor stub
	}

	public FightResult(int no, int fightNumber, int player1result, Timestamp player1writeDate, int player2result,
			Timestamp player2writeDate, int hit) {
		super();
		this.no = no;
		this.fightNumber = fightNumber;
		this.player1result = player1result;
		this.player1writeDate = player1writeDate;
		this.player2result = player2result;
		this.player2writeDate = player2writeDate;
		this.hit = hit;
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
	
	
	
}
