package com.project.call.hyunsu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.call.domain.Member;
import com.projectcall.daomapper.DaoMapper;

@Repository
public class HSDaoImpl implements HSDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;
	
	
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setNamedParamJdbcTemplate(NamedParameterJdbcTemplate namedParamJdbcTemplate) {
		this.namedParamJdbcTemplate = namedParamJdbcTemplate;
	}
	
	private DaoMapper mapper = new DaoMapper();

	
	@Override
	public List<Member> getMemberIdList() {

		String sql = "select * from member";
		return namedParamJdbcTemplate.query(sql,mapper.getMemberRowMapper());

	}
	
	
}
