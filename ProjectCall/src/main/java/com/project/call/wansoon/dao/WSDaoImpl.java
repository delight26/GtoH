package com.project.call.wansoon.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.project.call.daomapper.DaoMapper;
import com.project.call.domain.Comment;
import com.project.call.domain.FreeBoard;


@Repository
public class WSDaoImpl implements WSDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	private DaoMapper dm = new DaoMapper();
	
	@Override
	public Integer getfreeCount() {
		SqlParameterSource freeparam = new MapSqlParameterSource("free", "free");
		return namedParameterJdbcTemplate.queryForObject("select count(*) from freeboard where area=:free", freeparam,
				Integer.class);
	}

	@Override
	public List<FreeBoard> getfreeList(int startRow, int PAGE_SIZE) {
		SqlParameterSource productparam = new MapSqlParameterSource("startRow", startRow).addValue("PAGE_SIZE",
				PAGE_SIZE);
		return namedParameterJdbcTemplate.query(
				"select fb.*, (select count(*) from comment where comment.bno = fb.no) as comm "
						+ "from freeboard fb where  fb.area='free' order by writedate desc limit :startRow, :PAGE_SIZE ",
				productparam, dm.getFreeBoardRowMapper());
	}

	@Override
	public void freeBoardWrite(FreeBoard fb) {
		SqlParameterSource fbparam = new BeanPropertySqlParameterSource(fb);
		namedParameterJdbcTemplate
				.update("insert into freeboard values(0, :frbTitle, 0, :frbContent, '', :frbWriteDate, 0, :frbArea"
						+ ", :frbEmail, :frbWriter)", fbparam);
	}

	@Override
	public void freeBoardWritephoto(FreeBoard fb) {
		SqlParameterSource fbparam = new BeanPropertySqlParameterSource(fb);
		namedParameterJdbcTemplate
				.update("insert into freeboard values(0, :frbTitle, 0, :frbContent, :Photo1, :frbWriteDate, 0, :frbArea"
						+ ", :frbEmail, :frbWriter)", fbparam);
	}

	@Override
	public FreeBoard freeContent(int frbNo) {
		SqlParameterSource frbNoparam = new MapSqlParameterSource("frbNo", frbNo);

		return namedParameterJdbcTemplate
				.query("select fb.*, (select count(*) from comment where comment.bno = fb.no) as comm "
						+ "from freeboard fb where  fb.no=:frbNo", frbNoparam, dm.getFreeBoardResultSetExtractor());
	}

	@Override
	public void freeHitUpdate(int frbHit, int frbNo) {
		SqlParameterSource frbNoparam = new MapSqlParameterSource("frbHit", frbHit).addValue("frbNo", frbNo);
		namedParameterJdbcTemplate.update("update freeboard set hit = :frbHit where no = :frbNo", frbNoparam);
	}

	@Override
	public Integer freeNextNo(int frbNo) {
		SqlParameterSource frbNoparam = new MapSqlParameterSource("frbNo", frbNo);
		return namedParameterJdbcTemplate.queryForObject(
				"SELECT max(no) FROM freeboard fr WHERE no < :frbNo and area='free';", frbNoparam, Integer.class);
	}

	@Override
	public Integer freePreNo(int frbNo) {
		SqlParameterSource frbNoparam = new MapSqlParameterSource("frbNo", frbNo);
		return namedParameterJdbcTemplate.queryForObject(
				"SELECT min(no) FROM freeboard fr WHERE no > :frbNo and area='free'", frbNoparam, Integer.class);
	}

	@Override
	public void freeBoardUpdatePhoto(FreeBoard frb) {
		SqlParameterSource frbparam = new BeanPropertySqlParameterSource(frb);
		namedParameterJdbcTemplate.update(
				"update freeboard set title = :frbTitle, content = :frbContent, photo = :Photo1 where no=:frbNo",
				frbparam);
	}

	@Override
	public void freeBoardUpdate(FreeBoard frb) {
		SqlParameterSource frbparam = new BeanPropertySqlParameterSource(frb);
		namedParameterJdbcTemplate
				.update("update freeboard set title = :frbTitle, content = :frbContent where no=:frbNo", frbparam);
	}

	@Override
	public void freeDelete(int frbNo) {
		String sql = "UPDATE `projectcall`.`freeboard` SET `title`='삭제된 게시글 입니다.', `content`='삭제된 게시글 입니다.', "
				+ "`photo`='null' WHERE `no`= :frbNo ";
		SqlParameterSource frbparam = new MapSqlParameterSource("frbNo", frbNo);
		namedParameterJdbcTemplate.update(sql, frbparam);
	}

	@Override
	public Integer freeSearchCount(String search) {
		SqlParameterSource searchParam = new MapSqlParameterSource("search", search).addValue("per", "%");
		return namedParameterJdbcTemplate.queryForObject(
				"select count(*) from freeboard where area='free' and title like :per :search :per;", searchParam,
				Integer.class);
	}

	@Override
	public List<FreeBoard> freeSearch(String search, int startRow, int PAGE_SIZE) {
		SqlParameterSource searchParam = new MapSqlParameterSource("search", search).addValue("startRow", startRow)
				.addValue("PAGE_SIZE", PAGE_SIZE).addValue("per", "%");
		return namedParameterJdbcTemplate.query(
				"select fb.*, (select count(*) from comment where comment.bno = fb.no) as comm "
						+ "from freeboard fb where  fb.area='free' and fb.title like :per :search :per "
						+ "order by writedate desc limit :startRow, :PAGE_SIZE",
				searchParam, dm.getFreeBoardRowMapper());
	}

	@Override
	public Integer getCommentCount(int frbNo) {
		SqlParameterSource freeparam = new MapSqlParameterSource("frbNo", frbNo);
		return namedParameterJdbcTemplate.queryForObject("select count(*) from comment where bno=:frbNo", freeparam,
				Integer.class);
	}

	@Override
	public List<Comment> getComment(int frbNo, int startRow, int PAGE_SIZE) {
		SqlParameterSource frbparam = new MapSqlParameterSource("frbNo", frbNo).addValue("startRow", startRow)
				.addValue("PAGE_SIZE", PAGE_SIZE);
		return namedParameterJdbcTemplate.query(
				"select com.*, mem.nickname, mem.photo  from comment com, member mem where com.email = mem.email and com.bno=:frbNo order by no desc limit :startRow, :PAGE_SIZE",
				frbparam, dm.getCommentRowMapper());
	}

	@Override
	public void freeCommentWrite(Comment c) {
		SqlParameterSource cparam = new BeanPropertySqlParameterSource(c);

		namedParameterJdbcTemplate.update("insert into comment values(0, :cContent, :cEmail, :bNo, now())", cparam);
	}

	@Override
	public void freeCommentUpdate(Comment c) {
		SqlParameterSource cparam = new BeanPropertySqlParameterSource(c);
		namedParameterJdbcTemplate.update("update comment set comment = :cContent where no = :cNo", cparam);
	}

	@Override
	public void freeCommentDelete(int cNo) {
		SqlParameterSource cNoparam = new MapSqlParameterSource("cNo", cNo);
		namedParameterJdbcTemplate.update("delete from comment where no=:cNo", cNoparam);
	}
}
