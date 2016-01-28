package com.projectcall.daomapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.project.call.domain.Member;

public class DaoMapper {
	private MemberResultSetExtractor memberResultSetExtractor = new MemberResultSetExtractor();

	public MemberResultSetExtractor getMemberResultSetExtractor() {
		return memberResultSetExtractor;
	}

	private class MemberResultSetExtractor implements ResultSetExtractor<Member> {
		@Override
		public Member extractData(ResultSet rs) throws SQLException, DataAccessException {

			Member m = new Member();

			if (rs.next()) {
				m.setEmail(rs.getString("email"));
				m.setPass(rs.getString("pass"));
				m.setGender(rs.getString("gender"));
				m.setAddr(rs.getString("address"));
				m.setBirthday(rs.getString("birthday"));
				m.setPhone(rs.getString("phone"));
				m.setProfilPhoto(rs.getString("photo"));
				m.setArea(rs.getString("area"));
				m.setLevel(rs.getString("level"));
				m.setPoint(rs.getInt("accpoint"));
				m.setWin(rs.getInt("accwin"));
				m.setLose(rs.getInt("acclose"));
				m.setPenalty(rs.getInt("accpenalty"));
				m.setUsepoint(rs.getInt("usepoint"));
				m.setWord(rs.getString("word"));
				m.setName(rs.getString("name"));
				m.setNickName(rs.getString("nickname"));
				return m;
			}
			return null;
		}
	}

	private MemberRowMapper memberRowMapper = new MemberRowMapper();

	public MemberRowMapper getMemberRowMapper() {
		return memberRowMapper;
	}

	private class MemberRowMapper implements RowMapper<Member> {
		@Override
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			Member m = new Member();

			m.setEmail(rs.getString("email"));
			m.setPass(rs.getString("pass"));
			m.setGender(rs.getString("gender"));
			m.setAddr(rs.getString("address"));
			m.setBirthday(rs.getString("birthday"));
			m.setPhone(rs.getString("phone"));
			m.setProfilPhoto(rs.getString("photo"));
			m.setArea(rs.getString("area"));
			m.setLevel(rs.getString("level"));
			m.setPoint(rs.getInt("accpoint"));
			m.setWin(rs.getInt("accwin"));
			m.setLose(rs.getInt("acclose"));
			m.setPenalty(rs.getInt("accpenalty"));
			m.setUsepoint(rs.getInt("usepoint"));
			m.setWord(rs.getString("word"));
			m.setName(rs.getString("name"));
			m.setNickName(rs.getString("nickname"));

			return m;
		}
	}
}
