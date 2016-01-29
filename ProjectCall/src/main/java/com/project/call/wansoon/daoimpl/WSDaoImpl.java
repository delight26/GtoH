package com.project.call.wansoon.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.project.call.domain.FreeBoard;
import com.project.call.wansoon.dao.WSDao;

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
	
	private class FreeBoardMapper implements RowMapper<FreeBoard> {
		
		public FreeBoard mapRow(ResultSet rs, int rowNum) throws SQLException{
			
			FreeBoard frb = new FreeBoard();
			
			frb.setFrbNo(rs.getInt("No"));
			frb.setFrbTitle(rs.getString("Title"));
			frb.setFrbPass(rs.getString("Pass"));
			frb.setFrbContent(rs.getString("Content"));
			frb.setPhoto1(rs.getString("photo"));
			frb.setFrbWriteDate(rs.getTimestamp("WriteDate"));
			frb.setFrbHit(rs.getInt("Hit"));
			frb.setFrbArea(rs.getString("Area"));
			frb.setFrbEmail(rs.getString("Email"));
			frb.setFrbWriter(rs.getString("Writer"));
			
			return frb;
		
		}
		
	}
	
	
	@Override
	public List<FreeBoard> getFreeBoardAll() {
		
		return namedParameterJdbcTemplate.query(
				"SELECT * FROM FreeBoard", new FreeBoardMapper());
	}
	
	@Override
	public FreeBoard getFreeBoard(int frbNo) {
		
		SqlParameterSource namedParameters = new MapSqlParameterSource("no",frbNo);
		namedParameterJdbcTemplate.update("UPDATE freeboard SET hit = hit +1 WHERE  no = :no",
				new MapSqlParameterSource().addValue("no", frbNo));
		
		return namedParameterJdbcTemplate.query("SELECT * FROM freeboard WHERE no=:no",
				namedParameters,
			new ResultSetExtractor<FreeBoard>() {

				@Override
				public FreeBoard extractData(ResultSet rs) throws SQLException, DataAccessException {
					if(rs.next()){
						FreeBoard frb = new FreeBoard();
						
						frb.setFrbNo(rs.getInt("No"));
						frb.setFrbTitle(rs.getString("Title"));
						frb.setFrbPass(rs.getString("Pass"));
						frb.setFrbContent(rs.getString("Content"));
						frb.setPhoto1(rs.getString("photo"));
						frb.setFrbWriteDate(rs.getTimestamp("WriteDate"));
						frb.setFrbHit(rs.getInt("Hit"));
						frb.setFrbArea(rs.getString("Area"));
						frb.setFrbEmail(rs.getString("Email"));
						frb.setFrbWriter(rs.getString("Writer"));
						
						return frb;	
					}
					
					return null;
				}
			});

	}
	
	
	@Override
	public int FreeBoardCount() {
		SqlParameterSource namedParameters = new MapSqlParameterSource("no", "no");
		return namedParameterJdbcTemplate.queryForObject("SELECT count(*) FROM freeboard WHERE no", namedParameters,
				Integer.class);
		
	}
	@Override
	public List<FreeBoard> insertBoard(FreeBoard freeboard) {
		
		return namedParameterJdbcTemplate.query(
				"INSERT INTO freeboard VALUES(0, :title, :pass, :content, :photo, now(), 0, :email, :writer)"
				, new FreeBoardMapper());
	}
	@Override
	public void addWrite(FreeBoard freeboard) {
		
		SqlParameterSource beanProperty =
				new BeanPropertySqlParameterSource(freeboard);
		
		namedParameterJdbcTemplate.update(
				"INSERT INTO freeboard VALUES(:no, :title, :pass, :content, :photo, :now(), :hit, :area, :email, :writer)", beanProperty);
		
	}
	
	
	
	
	
	
}
