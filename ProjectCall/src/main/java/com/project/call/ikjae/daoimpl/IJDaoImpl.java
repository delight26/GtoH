package com.project.call.ikjae.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.project.call.domain.FightBoard;
import com.project.call.domain.Member;
import com.project.call.ikjae.dao.IJDao;

@Repository
public class IJDaoImpl implements IJDao{
	
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
	public Member getMember(String loginUser) {
		
		return jdbcTemplate.queryForObject("SELECT * FROM member WHERE email = ?",
				new RowMapper<Member>() {

			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Member m = new Member();
				
				m.setAddr(rs.getString("address"));
                m.setEmail(rs.getString("email"));
                m.setLevel(rs.getString("level"));
                m.setName(rs.getString("name"));
                m.setNikname(rs.getString("nickname"));
                m.setPass(rs.getString("pass"));
                m.setPhone(rs.getString("phone"));
                m.setPoint(rs.getInt("accpoint"));
                m.setProfilphoto(rs.getString("photo"));
                m.setRank(rs.getString("level"));
                m.setArea(rs.getString("area"));
                m.setLose(rs.getInt("acclose"));
                m.setGender(rs.getString("gender"));
                m.setWin(rs.getInt("accwin"));
                m.setUsepoint(rs.getInt("usepoint"));
                m.setPenalty(rs.getInt("accpenalty"));
                m.setWord(rs.getString("word"));
                m.setLevel(rs.getString("level"));
				
				return m;
				
			}
		}, loginUser);
		
	}
	@Override
	public List<FightBoard> getFight(String loginUser) {
		
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
		String getPassword;
			getPassword = jdbcTemplate.queryForObject(
						"SELECT  pass  FROM  member WHERE  email = ?", 
						String.class, loginUser);
		if(getPassword.equals(password)) {
			result = 1;
		} else {
			result = 0;
		}
		
		return result;
	}
		
		
}






















