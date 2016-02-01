package com.project.call.junbum.dao;

import java.util.List;

import com.project.call.domain.FreeBoard;
import com.project.call.domain.Member;
import com.project.call.domain.PointProduct;

public interface JBDao {
	public Member getloginResult(String email);
	
	public Integer getProductCount();
	
	public List<PointProduct> getproductList(int startRow, int PAGE_SIZE);
	
	public void addProduct(PointProduct p);
	
	public PointProduct productContent(int pNo);
	
	public void updateProduct(PointProduct p);
	
	public void productDelete(int pProductCode);
	
	public void orderProduct(PointProduct p, Member m);
	
	public Integer getaggroCount();
	
	public List<FreeBoard> getAggroList(int startRow, int PAGE_SIZE);
}
