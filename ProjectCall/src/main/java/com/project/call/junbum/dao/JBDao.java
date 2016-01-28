package com.project.call.junbum.dao;

import java.util.List;

import com.project.call.domain.Member;
import com.project.call.domain.PointProduct;

public interface JBDao {
	public Member getloginResult(String email);
	
	public List<PointProduct> getproductList();
	
	public void addProduct(PointProduct p);
	
	public PointProduct productContent(int pNo);
}
