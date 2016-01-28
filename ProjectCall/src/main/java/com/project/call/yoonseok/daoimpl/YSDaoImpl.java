package com.project.call.yoonseok.daoimpl;

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

import com.project.call.domain.Member;
import com.project.call.domain.NoticeBoard;
import com.project.call.yoonseok.dao.YSDao;

@Repository
public class YSDaoImpl implements YSDao{
	
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
	public List<Member> ranking() {
		return namedParameterJdbcTemplate.query("select * from member order by accpoint desc", 
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member m = new Member();
						m.setAddr(rs.getString("address"));
						m.setEmail(rs.getString("email"));
						m.setLevel(rs.getString("level"));
						m.setName(rs.getString("name"));
						m.setNickName(rs.getString("nickname"));
						m.setPass(rs.getString("pass"));
						m.setPhone(rs.getString("phone"));
						m.setPoint(rs.getInt("accpoint"));
						m.setProfilPhoto(rs.getString("photo"));
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
				});
	}
	@Override
	public void addNote(NoticeBoard note) {
		//open <- 안읽음0 읽음1
		SqlParameterSource pram = new MapSqlParameterSource()
				.addValue("toid", note.getNbToid())
				.addValue("title", note.getNbTitle())
				.addValue("content", note.getNbContent())
				.addValue("email", note.getNbEmail());
		namedParameterJdbcTemplate.update("insert into note(toid, title, content, writeDate, email, open)"
				+ " value(:toid, :title, :content, now(), :email, 0)",pram  );
		
	} 
}
