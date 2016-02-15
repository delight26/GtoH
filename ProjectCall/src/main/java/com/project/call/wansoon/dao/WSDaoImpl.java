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
	private DaoMapper mapper = new DaoMapper();
	@Override
	public List<FreeBoard> getFreeBoardList(int startRow, int PAGE_SIZE) {
/*		String sql = "SELECT * FROM projectcall.freeboard where area = 'free' order by writedate desc";
		SqlParameterSource namedParameters = new MapSqlParameterSource("startRow", startRow).addValue("PAGE_SIZE",
				PAGE_SIZE);
		
		return namedParameterJdbcTemplate.query(
				sql, new RowMapper<FreeBoard>() {
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
						f.setFrbArea(rs.getString("area"));
						f.setFrbEmail(rs.getString("email"));
						f.setFrbWriter(rs.getString("writer"));
						return f;
					}
				});
*/	
		SqlParameterSource productparam = new MapSqlParameterSource("startRow", startRow).addValue("PAGE_SIZE",
				PAGE_SIZE);
		return namedParameterJdbcTemplate.query(
				"select fb.*, (select count(*) from comment where comment.bno = fb.no) as comm "
						+ "from freeboard fb where  fb.area='free' order by writedate desc limit :startRow, :PAGE_SIZE ",
				productparam, mapper.getFreeBoardRowMapper());
	
	
	
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
	public int getFreeBoardCount() {
		String sql = "SELECT count(*) FROM projectcall.freeboard where area = 'free'";
		return namedParameterJdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				Integer count = 0;
				if(rs.next()){
					count = rs.getInt(1);					
				}
				return count;
			}
		});
	}

	@Override
	public void insertWrite(FreeBoard freeboard)  {
		
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
		
		String sql = "UPDATE `projectcall`.`freeboard` SET `title`='삭제된 게시글 입니다.', `content`='삭제된 게시글 입니다.', "
				+ "`photo`='null' WHERE `no`= :no ";
		SqlParameterSource namedParam = new MapSqlParameterSource("no", frbNo);
		
		namedParameterJdbcTemplate.update(sql, namedParam);
	}
	
	@Override
	public void addComment(Comment freebComment) {
		
		SqlParameterSource beanProperty =
				new BeanPropertySqlParameterSource(freebComment);
		
		namedParameterJdbcTemplate.update(
				"INSERT INTO comment(comment, email, bno, writeDate) VALUES(:comment, :email, :bno, :writeDate)", beanProperty);
		
	}
	
	@Override
	public List<Comment> commentAllList(int bno) {
	
		return jdbcTemplate.query(
				"select c.*, m.nickname from (SELECT * FROM comment) c "
				+ "inner join member m where m.email = c.email and bno =?",
				mapper.getCommentRowMapper()
				,bno);
	}
	
	@Override
	public void modifyComment(Comment freebcomment) {
		
		SqlParameterSource beanProperty  = new  BeanPropertySqlParameterSource(freebcomment);
		
		namedParameterJdbcTemplate.update(
				"UPDATE freeboard SET comment =:comment WHERE  no=:no", beanProperty);		
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
				searchParam, mapper.getFreeBoardRowMapper());

	}
	
	@Override
	public FreeBoard freeContent(int frbNo) {
		SqlParameterSource frbNoparam = new MapSqlParameterSource("frbNo", frbNo);
		return namedParameterJdbcTemplate
				.query("select fb.*, (select count(*) from comment where comment.bno = fb.no) as comm "
						+ "from freeboard fb where  fb.no=:frbNo", frbNoparam, mapper.getFreeBoardResultSetExtractor());
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
	public Integer freePreNo(int frbNo) {
		SqlParameterSource frbNoparam = new MapSqlParameterSource("frbNo", frbNo);
		return namedParameterJdbcTemplate.queryForObject(
				"SELECT min(no) FROM freeboard fr WHERE no > :frbNo and area='free'", frbNoparam, Integer.class);
	}
	
	@Override
	public Integer freeNextNo(int frbNo) {
		SqlParameterSource frbNoparam = new MapSqlParameterSource("frbNo", frbNo);
		return namedParameterJdbcTemplate.queryForObject(
				"SELECT max(no) FROM freeboard fr WHERE no < :frbNo and area='free';", frbNoparam, Integer.class);
	}
	
	
	
	
	
}
