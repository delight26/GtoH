package com.project.call.seunghee.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.project.call.domain.FreeBoard;
import com.project.call.seunghee.dao.SHDao;

@Repository
public class SHDaoImpl implements SHDao {

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

	@Override
	public int getBoardCount() {
		SqlParameterSource namedParameters = new MapSqlParameterSource("notice", "공지");
		return namedParameterJdbcTemplate.queryForObject("SELECT count(*) FROM freeboard where area = :notice",
				namedParameters, Integer.class);
	}

	@Override
	public List<FreeBoard> getNoticeList(int startRow, int PAGE_SIZE) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("startRow", startRow).addValue("PAGE_SIZE",
				PAGE_SIZE);
		return namedParameterJdbcTemplate.query("SELECT * FROM freeboard order by no DESC limit :startRow, :PAGE_SIZE",
				namedParameters, new NoticeBoardRowMapper());
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
