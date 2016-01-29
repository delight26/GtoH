package com.project.call.junbum.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.project.call.domain.Member;
import com.project.call.domain.PointProduct;
import com.project.call.junbum.dao.JBDao;
import com.projectcall.daomapper.DaoMapper;

@Repository
public class JBDaoImpl implements JBDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private DaoMapper dm = new DaoMapper();
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public Member getloginResult(String email) {
		SqlParameterSource emailparam = new MapSqlParameterSource("email", email);
		return namedParameterJdbcTemplate.query("select * from member where email = :email", emailparam,
				dm.getMemberResultSetExtractor());
	}
	
	@Override
	public List<PointProduct> getproductList() {
		return namedParameterJdbcTemplate.query("select * from product where amount > 0", dm.getProductRowMapper());
	}
	
	@Override
	public void addProduct(PointProduct p) {
		SqlParameterSource prodparam = new BeanPropertySqlParameterSource(p);
		namedParameterJdbcTemplate.update("insert into product values(0, :pName, :pPrice, ' ', :pAmount, :pImage, 0)", prodparam);
	}
	
	@Override
	public PointProduct productContent(int pNo) {
		SqlParameterSource pNoparam = new MapSqlParameterSource("pNo", pNo);
		return namedParameterJdbcTemplate.query("select * from product where productcode = :pNo", pNoparam, dm.getProductResultSetExtractor());
	}
	
	@Override
	public void updateProduct(PointProduct p) {
		SqlParameterSource prodparam = new BeanPropertySqlParameterSource(p);
		namedParameterJdbcTemplate.update("update product set productname=:pName, point=:pPrice, amount=:pAmount,"
				+ "image=:pImage where productcode=:pProductCode ", prodparam);
	}
	
	@Override
	public void productDelete(int pProductCode) {
		SqlParameterSource pProductCodeparam = new MapSqlParameterSource("pProductCode", pProductCode);
		namedParameterJdbcTemplate.update("delete from product where productcode=:pProductCode ", pProductCodeparam);
	}
}
