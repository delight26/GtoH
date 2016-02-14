package com.project.call.seunghee.daoimpl;

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
import com.project.call.domain.FreeBoard;
import com.project.call.domain.Member;
import com.project.call.seunghee.dao.SHDao;

@Repository
public class SHDaoImpl implements SHDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private DaoMapper dm = new DaoMapper();
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public int getBoardCount() {
		SqlParameterSource namedParameters = new MapSqlParameterSource("notice", "공지");
		return namedParameterJdbcTemplate.queryForObject("SELECT count(*) FROM freeboard where area = :notice",
				namedParameters, Integer.class);
	}

	@Override
	public List<FreeBoard> getNoticeList(int startRow, int PAGE_SIZE) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("startRow", startRow).addValue("PAGE_SIZE", PAGE_SIZE).addValue("notice", "공지");
		return namedParameterJdbcTemplate.query(
				"SELECT * FROM freeboard WHERE area = :notice order by no DESC limit :startRow, :PAGE_SIZE",
				namedParameters, new NoticeBoardRowMapper());
	}
	
	@Override
	public FreeBoard getNoticeContent(int no) {
		System.out.println(no);
		SqlParameterSource namedParam = new MapSqlParameterSource("no", no);
		jdbcTemplate.update("UPDATE freeboard SET hit = hit + 1 "
				+ "where no = ?", no);
		return namedParameterJdbcTemplate.query("SELECT * FROM freeboard WHERE no = :no",
				namedParam,
				new ResultSetExtractor<FreeBoard>() {
			@Override
			public FreeBoard extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					FreeBoard f = new FreeBoard();
					f.setFrbNo(rs.getInt("no"));
					f.setFrbTitle(rs.getString("title"));
					f.setFrbPass(rs.getString("pass"));
					f.setFrbContent(rs.getString("content"));
					f.setPhoto1(rs.getString("photo"));
					f.setFrbWriteDate(rs.getTimestamp("writeDate"));
					f.setFrbHit(rs.getInt("hit"));
					f.setFrbArea(rs.getString("area"));
					f.setFrbEmail(rs.getString("email"));
					f.setFrbWriter(rs.getString("writer"));

					return f;
				}
				return null;
			}
		});
	}
	
	@Override
	public void noticeDelete(int no) {
//		SqlParameterSource namedParam = new MapSqlParameterSource("no", no);
		jdbcTemplate.update("DELETE FROM freeboard where no = ?", no);
	}
	
	@Override
	public void noticeWrite(FreeBoard noticeboard) {
//		SqlParameterSource beanProperty = new BeanPropertySqlParameterSource(noticeboard);
		
		String title = noticeboard.getFrbTitle();
		String pass = noticeboard.getFrbPass();
		String writer = noticeboard.getFrbWriter();
		String area = noticeboard.getFrbArea();
		String email = noticeboard.getFrbEmail();
		String content = noticeboard.getFrbContent();
		String photo = noticeboard.getPhoto1();
		
		jdbcTemplate.update(
				"INSERT INTO freeboard VALUES(0, ?, ?, ?, "
						+ "?, now(), 0, ?, ?, ?)",
				title, pass, content,  photo, area, email, writer);
	}
	
	@Override
	public void noticeModify(FreeBoard noticeBoard) {
		SqlParameterSource beanProperty = new BeanPropertySqlParameterSource(noticeBoard);
		namedParameterJdbcTemplate.update("UPDATE freeboard set "
				+ "content = :frbContent, writeDate = :frbWriteDate, photo = :photo1 WHERE no = :frbNo", beanProperty);
	}
	
	@Override
	public Integer noticePreNo(int frbNo) {
		SqlParameterSource frbNoparam = new MapSqlParameterSource("frbNo", frbNo);
		return namedParameterJdbcTemplate.queryForObject(
				"SELECT min(no) FROM freeboard fr WHERE no > :frbNo and area='공지'", frbNoparam, Integer.class);
	}

	@Override
	public Integer noticeNextNo(int frbNo) {
		SqlParameterSource frbNoparam = new MapSqlParameterSource("frbNo", frbNo);
		return namedParameterJdbcTemplate.queryForObject(
				"SELECT max(no) FROM freeboard fr WHERE no < :frbNo and area='공지'", frbNoparam, Integer.class);
	}
	
	@Override
	public List<Member> getSeoulRanking() {
		return namedParameterJdbcTemplate.query("SELECT * FROM member WHERE area='서울' and name not in('관리자') ORDER BY accpoint DESC Limit 10",
				dm.getMemberRowMapper());
	}
	
	@Override
	public List<Member> getGyeonggiRanking() {
		return namedParameterJdbcTemplate.query("SELECT * FROM member WHERE area='경기' and name not in('관리자') ORDER BY accpoint DESC Limit 10",
				dm.getMemberRowMapper());
	}
	
	@Override
	public List<Member> getKangwonRanking() {
		return namedParameterJdbcTemplate.query("SELECT * FROM member WHERE area='강원' and name not in('관리자') ORDER BY accpoint DESC Limit 10",
				dm.getMemberRowMapper());
	}
	
	@Override
	public List<Member> getChungcheongRanking() {
		return namedParameterJdbcTemplate.query("SELECT * FROM member WHERE area='충남' or area='충북' and name not in('관리자') ORDER BY accpoint DESC Limit 10",
				dm.getMemberRowMapper());
	}
	
	@Override
	public List<Member> getGyeongsangRanking() {
		return namedParameterJdbcTemplate.query("SELECT * FROM member WHERE area='경남' or area='경북' and name not in('관리자') ORDER BY accpoint DESC Limit 10",
				dm.getMemberRowMapper());
	}
	
	@Override
	public List<Member> getJunlaRanking() {
		return namedParameterJdbcTemplate.query("SELECT * FROM member WHERE area='전남' or area='전북' and name not in('관리자') ORDER BY accpoint DESC Limit 10",
				dm.getMemberRowMapper());
	}
	
	@Override
	public List<Member> getJejuRanking() {
		return namedParameterJdbcTemplate.query("SELECT * FROM member WHERE area='제주' and name not in('관리자') ORDER BY accpoint DESC Limit 10",
				dm.getMemberRowMapper());
	}
	
	private class NoticeBoardRowMapper implements RowMapper<FreeBoard> {

		@Override
		public FreeBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
			FreeBoard f = new FreeBoard();
			f.setFrbNo(rs.getInt("no"));
			f.setFrbTitle(rs.getString("title"));
			f.setFrbPass(rs.getString("pass"));
			f.setFrbContent(rs.getString("content"));
			f.setPhoto1(rs.getString("photo"));
			f.setFrbWriteDate(rs.getTimestamp("writeDate"));
			f.setFrbHit(rs.getInt("hit"));
			f.setFrbArea("공지");
			f.setFrbEmail(rs.getString("email"));
			f.setFrbWriter(rs.getString("writer"));

			return f;
		}
	}
}
