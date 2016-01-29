package com.project.call.wansoon.dao;

import java.util.List;

import com.project.call.domain.FreeBoard;

public interface WSDao {

	public List<FreeBoard> getFreeBoardAll();
	
	public FreeBoard getFreeBoard(int frbNo);
	
	public int FreeBoardCount();
}
