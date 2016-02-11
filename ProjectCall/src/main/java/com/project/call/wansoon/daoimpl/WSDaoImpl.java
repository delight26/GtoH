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

import com.project.call.domain.FightBoard;
import com.project.call.domain.FreeBoard;
import com.project.call.domain.FreebComment;
import com.project.call.domain.Member;
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
	public List<FreeBoard> getFreeBoardList(int startRow, int PAGE_SIZE) {
		
		SqlParameterSource namedParameters = new MapSqlParameterSource("startRow", startRow).addValue("PAGE_SIZE",
				PAGE_SIZE);
		
		return namedParameterJdbcTemplate.query(
				"select fb.*, (select count(*) from comment where comment.bno = frb.no) as comm "
						+ "from freeboard frb where  frb.area='freeboard' order by writedate desc limit :startRow, :PAGE_SIZE "
						, namedParameters, new FreeBoardMapper());
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
	public void insertWrite(FreeBoard freeboard, String filePath)  {
		
		namedParameterJdbcTemplate.update(
				"INSERT INTO freeboard(title, pass, content, photo, writeDate, hit, area, email, writer)"
				+ " VALUES(:title, :pass, :content, :photo, now(), 0, :area, :email, :writer);", 
				
				new MapSqlParameterSource()
				.addValue("title", freeboard.getFrbTitle())
				.addValue("pass", freeboard.getFrbPass())
				.addValue("content", freeboard.getFrbContent())
				.addValue("photo", freeboard.getPhoto1())
				.addValue("area", freeboard.getFrbArea())
				.addValue("email", freeboard.getFrbEmail())
				.addValue("writer", freeboard.getFrbWriter())
				);
			
	}
	
	@Override
	public void modifyWrite(FreeBoard freeboard, String filePath) {
				
			namedParameterJdbcTemplate.update(
				"update freeboard set title=:title , content=:content, photo=:photo, area=:area where no=:no",
						
				new MapSqlParameterSource()
				.addValue("no", freeboard.getFrbNo())
				.addValue("title", freeboard.getFrbTitle())
				.addValue("area", freeboard.getFrbArea())
				.addValue("content", freeboard.getFrbContent())
				.addValue("photo", freeboard.getPhoto1())				
			);
			
	}
	
	@Override
	public void deleteBoard(int frbNo) {
		
		SqlParameterSource namedParam = new MapSqlParameterSource("no", frbNo);
		
		namedParameterJdbcTemplate.update(
				"DELETE FROM freeboard WHERE no = :no", namedParam);	
		
	}
	
	@Override
	public void addComment(FreebComment freebComment) {
		
		SqlParameterSource beanProperty =
				new BeanPropertySqlParameterSource(freebComment);
		
		namedParameterJdbcTemplate.update(
				"INSERT INTO freebcomment(comment, email, bno, writeDate) VALUES(:comment, :email, :bno, :writeDate)", beanProperty);
		
	}
	
	@Override
	public List<FreebComment> commentAllList(int bno) {
	
		return jdbcTemplate.query(
				"select * from freebcomment where bno = ?",
				new RowMapper<FreebComment>() {
					public FreebComment mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						FreebComment fbc = new FreebComment();
						
						fbc.setNo(rs.getInt("no"));
						fbc.setComment(rs.getString("comment"));
						fbc.setEmail(rs.getString("email"));
						fbc.setBno(rs.getInt("bno"));
						fbc.setWriteDate(rs.getTimestamp("writeDate"));

						
						return fbc;
						
					}
				},bno);
		
	}
	@Override
	public void modifyComment(FreebComment freebcomment) {
		
		SqlParameterSource beanProperty  = new  BeanPropertySqlParameterSource(freebcomment);
		
		namedParameterJdbcTemplate.update(
				"UPDATE freeboard SET comment =:comment WHERE  no=:no", beanProperty);
		
	}

	
	
	
	
}
