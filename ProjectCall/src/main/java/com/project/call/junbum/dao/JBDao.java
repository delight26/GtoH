package com.project.call.junbum.dao;

import java.util.List;

import com.project.call.domain.Member;
import com.project.call.domain.PointProduct;

public interface JBDao {
	public Member getloginResult(String email);
	
	public List<PointProduct> getproductList();
	
	public void addProduct(PointProduct p);
	
	public PointProduct productContent(int pNo);
	
	public void updateProduct(PointProduct p);
	
	public void productDelete(int pProductCode);
	
	public void orderProduct(PointProduct p, Member m);
}
