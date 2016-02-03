package com.project.call.ikjae.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.project.call.daomapper.DaoMapper;
import com.project.call.domain.FightBoard;
import com.project.call.domain.Member;
import com.project.call.domain.FightResultBoard;
import com.project.call.ikjae.dao.IJDao;

@Repository
public class IJDaoImpl implements IJDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private DaoMapper mapper = new DaoMapper();

	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	
	@Override
	public Member getMember(String loginUser) {
		
		return jdbcTemplate.queryForObject("SELECT * FROM member WHERE email = ?",
				mapper.getMemberRowMapper(), loginUser);
		
	}
	@Override
	public List<FightBoard> getFightList(String loginUser) {
		
		return jdbcTemplate.query(
				"select f2.*, m2.nickname as user2nickname from"
				+ " (SELECT f.*, m.nickname as user1nickname FROM"
				+ " fight f INNER JOIN member m ON f.player1 = m.email) f2"
				+ " inner join member m2 on f2.player2 = m2.email"
				+ " WHERE player1 = ? OR player2 = ?",
				new RowMapper<FightBoard>() {
					public FightBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						FightBoard f = new FightBoard();
						
						f.setFbNo(rs.getInt("fightNumber"));
						f.setFbCallDate(rs.getTimestamp("callDate"));
						f.setFbResultDate(rs.getTimestamp("resultDate"));
						f.setFbP1(rs.getString("user1nickname"));
						f.setFbP2(rs.getString("user2nickname"));
						f.setFbresult(rs.getString("result"));
						
						return f;
						
					}
				},loginUser, loginUser);
			
		
	}
	@Override
	public int passwordCheck(String loginUser, String password) {
		
		int result = 0;
		String getPassword = jdbcTemplate.queryForObject(
						"SELECT  pass  FROM  member WHERE  email = ?", 
						String.class, loginUser);
		if(getPassword.equals(password)) {
			result = 1;
		} else {
			result = 0;
		}
		
		return result;
	}
	@Override
	public int nickNameCheck(String loginUser, String nickName) {
		
		return  jdbcTemplate.queryForObject(
				"SELECT  COUNT(*)  FROM  member WHERE  nickName = ?  AND email != ?;", 
				Integer.class,  nickName,  loginUser);
		
	}
	@Override
	public void updateMember(Member m) {
		
		SqlParameterSource beanParam = 
				new BeanPropertySqlParameterSource(m);
		
		if(m.getPass() == null) {
			
			if(m.getProfilPhoto() == null) {
				
				namedParameterJdbcTemplate.update(
						"UPDATE  member  SET nickname = :nickName,  "
						+  " gender = :gender, phone = :phone, word = :word,"
						+ " WHERE email = :email",
						beanParam);
				
			} else if (m.getProfilPhoto() != null) {
				
				namedParameterJdbcTemplate.update(
						"UPDATE  member  SET nickname = :nickName,  "
						+  " gender = :gender, phone = :phone, word = :word, photo = :profilPhoto"
						+ " WHERE email = :email",
						beanParam);
				
			}
			
		} else if(m.getPass() != null){
			
			if(m.getProfilPhoto() == null) {
				
				namedParameterJdbcTemplate.update(
						"UPDATE  member  SET pass = :pass, nickname = :nickName,  "
						+  " gender = :gender, phone = :phone, word = :word,"
						+ " WHERE email = :email",
						beanParam);
				
			} else if (m.getProfilPhoto() != null) {
				
				namedParameterJdbcTemplate.update(
						"UPDATE  member  SET pass = :pass, nickname = :nickName,  "
						+  " gender = :gender, phone = :phone, word = :word, photo = :profilPhoto"
						+ " WHERE email = :email",
						beanParam);
				
			}
			
		}
		
	}
	
	@Override
	public void deleteMember(String loginUser) {
		
		jdbcTemplate.update(
				"DELETE  FROM  member WHERE  email  =  ?",  loginUser);
		
	}
	
	@Override
	public FightBoard getFight(int fightNumber) {
		
		return jdbcTemplate.queryForObject("SELECT * FROM fight WHERE fightNumber = ?",
				new RowMapper<FightBoard>() {

			public FightBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				FightBoard f = new FightBoard();
				
				f.setFbNo(rs.getInt("fightNumber"));
				f.setFbCallDate(rs.getTimestamp("callDate"));
				f.setFbResultDate(rs.getTimestamp("resultDate"));
				f.setFbP1(rs.getString("player1"));
				f.setFbP2(rs.getString("player2"));
				f.setFbresult(rs.getString("result"));
				
				return f;
				
			}
		}, fightNumber);
		
	}
	@Override
	public void addFightResultBoardResult(FightResultBoard frb) {
		
		SqlParameterSource beanParam = 
				new BeanPropertySqlParameterSource(frb);
		
		namedParameterJdbcTemplate.update(
				"INSERT INTO fightResultBoard(fightNumber, title, writer, content, photo, writeDate,"
				+ " hit, isAdminCheck, winner) VALUES(:fightNumber, :title, :writer, :content, :photo, :writeDate,"
				+ " :hit, :isAdminCheck, :winner)",
				beanParam);
		
		jdbcTemplate.update(
				"UPDATE  fight  SET  result  =  ? where fightNumber = ?",
				"심사중", frb.getFightNumber());
		
	}
	@Override
	public List<FightResultBoard> getFightResultBoardList() {
		
		return jdbcTemplate.query(
				"SELECT * FROM fightResultBoard",
				new RowMapper<FightResultBoard>() {
					public FightResultBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						FightResultBoard frb = new FightResultBoard();
						
						frb.setNo(rs.getInt("no"));
						frb.setContent(rs.getString("content"));
						frb.setHit(rs.getInt("hit"));
						frb.setIsAdminCheck(rs.getInt("isAdminCheck"));
						frb.setPhoto(rs.getString("photo"));
						frb.setTitle(rs.getString("title"));
						frb.setWriteDate(rs.getTimestamp("writeDate"));
						frb.setWriter(rs.getString("writer"));
						frb.setWinner(rs.getString("winner"));
						
						return frb;
						
					}
				});
		
	}
	@Override
	public FightResultBoard getFightResultBoard(int no) {
		
		return jdbcTemplate.queryForObject("SELECT * FROM fightResultBoard WHERE no = ?",
				new RowMapper<FightResultBoard>() {

			public FightResultBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				FightResultBoard frb = new FightResultBoard();
				
				frb.setNo(rs.getInt("no"));
				frb.setContent(rs.getString("content"));
				frb.setHit(rs.getInt("hit"));
				frb.setIsAdminCheck(rs.getInt("isAdminCheck"));
				frb.setPhoto(rs.getString("photo"));
				frb.setTitle(rs.getString("title"));
				frb.setWriteDate(rs.getTimestamp("writeDate"));
				frb.setWriter(rs.getString("writer"));
				frb.setFightNumber(rs.getInt("fightNumber"));
				frb.setWinner(rs.getString("winner"));
				
				return frb;
				
			}
		}, no);
		
	}
	@Override
	public void adminConfirm(int no) {
		
		jdbcTemplate.update(
				"UPDATE  fightResultBoard  SET  isAdminCheck = 1 where no = ?" , no);
		
		int fightNumber = jdbcTemplate.queryForObject(
				"SELECT  fightNumber  FROM  fightResultBoard WHERE  no = ?",
				Integer.class, no);
		String winner = jdbcTemplate.queryForObject(
				"SELECT  winner  FROM  fightResultBoard WHERE  no = ?",
				String.class, no);
		
		jdbcTemplate.update(
				"UPDATE  fight  SET  result = ? where fightNumber = ?" , 
				winner, fightNumber);
		
	}
	@Override
	public void updateFightResultBoardResult(FightResultBoard frb) {
		
		SqlParameterSource beanParam = 
				new BeanPropertySqlParameterSource(frb);
		
		namedParameterJdbcTemplate.update(
				"UPDATE fightResultBoard SET fightNumber = :fightNumber, title = :title,"
				+ " writer = :writer, content = :content, photo = :photo, writeDate = :writeDate,"
				+ " hit = :hit, isAdminCheck = :isAdminCheck, winner =  :winner"
				+ " WHERE no = :no ",
				beanParam);
		
	}
	@Override
	public void deleteFightResultBoard(int no) {
		
		jdbcTemplate.update(
				"DELETE  FROM  fightResultBoard WHERE no =  ?", no);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}






















