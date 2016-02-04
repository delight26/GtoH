package com.project.call.junbum.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.project.call.daomapper.DaoMapper;
import com.project.call.domain.Comment;
import com.project.call.domain.FreeBoard;
import com.project.call.domain.Member;
import com.project.call.domain.PointProduct;

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
	public Integer getProductCount() {
		SqlParameterSource boardparam = new MapSqlParameterSource("product", "product");
		return namedParameterJdbcTemplate.queryForObject("select count(*) from product", boardparam, Integer.class);
	}

	@Override
	public List<PointProduct> getproductList(int startRow, int PAGE_SIZE) {
		SqlParameterSource productparam = new MapSqlParameterSource("startRow", startRow).addValue("PAGE_SIZE",
				PAGE_SIZE);
		return namedParameterJdbcTemplate.query("select * from product where amount > 0 limit :startRow, :PAGE_SIZE",
				productparam, dm.getProductRowMapper());
	}

	@Override
	public void addProduct(PointProduct p) {
		SqlParameterSource prodparam = new BeanPropertySqlParameterSource(p);
		namedParameterJdbcTemplate.update("insert into product values(0, :pName, :pPrice, ' ', :pAmount, :pImage, 0)",
				prodparam);
	}

	@Override
	public PointProduct productContent(int pNo) {
		SqlParameterSource pNoparam = new MapSqlParameterSource("pNo", pNo);
		return namedParameterJdbcTemplate.query("select * from product where productcode = :pNo", pNoparam,
				dm.getProductResultSetExtractor());
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

	@Override
	public void orderProduct(PointProduct p, Member m) {
		SqlParameterSource prodparam = new BeanPropertySqlParameterSource(p);
		SqlParameterSource memparam = new BeanPropertySqlParameterSource(m);
		namedParameterJdbcTemplate
				.update("update product set amount=:pAmount, buy=:pBuy where productcode=:pProductCode ", prodparam);
		namedParameterJdbcTemplate.update("update member set usepoint=:usepoint where email=:email", memparam);
	}

	@Override
	public Integer getaggroCount() {
		SqlParameterSource aggroparam = new MapSqlParameterSource("aggro", "aggro");
		return namedParameterJdbcTemplate.queryForObject("select count(*) from freeboard where area=:aggro", aggroparam,
				Integer.class);
	}

	@Override
	public List<FreeBoard> getAggroList(int startRow, int PAGE_SIZE) {
		SqlParameterSource productparam = new MapSqlParameterSource("startRow", startRow).addValue("PAGE_SIZE",
				PAGE_SIZE);
		return namedParameterJdbcTemplate.query(
				"select fb.*, (select count(*) from comment where comment.bno = fb.no) as comm "
						+ "from freeboard fb where  fb.area='aggro' order by writedate desc limit :startRow, :PAGE_SIZE ",
				productparam, dm.getFreeBoardRowMapper());
	}

	@Override
	public void aggroBoardWrite(FreeBoard fb) {
		SqlParameterSource fbparam = new BeanPropertySqlParameterSource(fb);
		namedParameterJdbcTemplate
				.update("insert into freeboard values(0, :frbTitle, 0, :frbContent, '', :frbWriteDate, 0, :frbArea"
						+ ", :frbEmail, :frbWriter)", fbparam);
	}

	@Override
	public void aggroBoardWritephoto(FreeBoard fb) {
		SqlParameterSource fbparam = new BeanPropertySqlParameterSource(fb);
		namedParameterJdbcTemplate
				.update("insert into freeboard values(0, :frbTitle, 0, :frbContent, :Photo1, :frbWriteDate, 0, :frbArea"
						+ ", :frbEmail, :frbWriter)", fbparam);
	}

	@Override
	public FreeBoard aggroContent(int frbNo) {
		SqlParameterSource frbNoparam = new MapSqlParameterSource("frbNo", frbNo);

		return namedParameterJdbcTemplate
				.query("select fb.*, (select count(*) from comment where comment.bno = fb.no) as comm "
						+ "from freeboard fb where  fb.no=:frbNo", frbNoparam, dm.getFreeBoardResultSetExtractor());
	}

	@Override
	public void aggroHitUpdate(int frbHit, int frbNo) {
		SqlParameterSource frbNoparam = new MapSqlParameterSource("frbHit", frbHit).addValue("frbNo", frbNo);
		namedParameterJdbcTemplate.update("update freeboard set hit = :frbHit where no = :frbNo", frbNoparam);
	}

	@Override
	public Integer aggroNextNo(int frbNo) {
		SqlParameterSource frbNoparam = new MapSqlParameterSource("frbNo", frbNo);
		return namedParameterJdbcTemplate.queryForObject(
				"SELECT max(no) FROM freeboard fr WHERE no < :frbNo and area='aggro';", frbNoparam, Integer.class);
	}

	@Override
	public Integer aggroPreNo(int frbNo) {
		SqlParameterSource frbNoparam = new MapSqlParameterSource("frbNo", frbNo);
		return namedParameterJdbcTemplate.queryForObject(
				"SELECT min(no) FROM freeboard fr WHERE no > :frbNo and area='aggro'", frbNoparam, Integer.class);
	}

	@Override
	public void aggroBoardUpdatePhoto(FreeBoard frb) {
		SqlParameterSource frbparam = new BeanPropertySqlParameterSource(frb);
		namedParameterJdbcTemplate.update(
				"update freeboard set title = :frbTitle, content = :frbContent, photo = :Photo1 where no=:frbNo",
				frbparam);
	}

	@Override
	public void aggroBoardUpdate(FreeBoard frb) {
		SqlParameterSource frbparam = new BeanPropertySqlParameterSource(frb);
		namedParameterJdbcTemplate
				.update("update freeboard set title = :frbTitle, content = :frbContent where no=:frbNo", frbparam);
	}

	@Override
	public void aggroDelete(int frbNo) {
		SqlParameterSource frbparam = new MapSqlParameterSource("frbNo", frbNo);
		namedParameterJdbcTemplate.update("delete from freeboard where no=:frbNo", frbparam);
	}

	@Override
	public List<Comment> getComment(int frbNo) {
		SqlParameterSource frbparam = new MapSqlParameterSource("frbNo", frbNo);
		return namedParameterJdbcTemplate.query(
				"select com.*, mem.nickname  from comment com, member mem where com.email = mem.email and com.bno=:frbNo order by no desc",
				frbparam, dm.getCommentRowMapper());
	}

	@Override
	public void aggroCommentWrite(Comment c) {
		SqlParameterSource cparam = new BeanPropertySqlParameterSource(c);

		namedParameterJdbcTemplate.update("insert into comment values(0, :cContent, :cEmail, :bNo, now())", cparam);
	}

	@Override
	public void aggroCommentUpdate(Comment c) {
		SqlParameterSource cparam = new BeanPropertySqlParameterSource(c);
		namedParameterJdbcTemplate.update("update comment set comment = :cContent where no = :cNo", cparam);
	}
	
	@Override
	public void aggroCommentDelete(int cNo) {
		SqlParameterSource cNoparam = new MapSqlParameterSource("cNo", cNo);
		namedParameterJdbcTemplate.update("delete from comment where no=:cNo", cNoparam);
	}
}
