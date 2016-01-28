package com.project.call.yoonseok.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.call.domain.Member;
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
						m.setNikname(rs.getString("nickname"));
						m.setPass(rs.getString("pass"));
						m.setPhone(rs.getString("phone"));
						m.setPoint(rs.getInt("point"));
						m.setProfilphoto(rs.getString("photo"));
						m.setRank(rs.getString("rank"));
						return m;
					}
				});
	} 
}
