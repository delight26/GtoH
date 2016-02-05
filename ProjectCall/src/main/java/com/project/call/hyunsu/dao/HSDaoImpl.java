package com.project.call.hyunsu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.project.call.daomapper.DaoMapper;
import com.project.call.domain.Area;
import com.project.call.domain.AskInjection;
import com.project.call.domain.Member;

@Repository
public class HSDaoImpl implements HSDao{
	
/*	@Autowired
	private JdbcTemplate jdbcTemplate;
*/	
	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;
	
	
	
	/*public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}*/

	public void setNamedParamJdbcTemplate(NamedParameterJdbcTemplate namedParamJdbcTemplate) {
		this.namedParamJdbcTemplate = namedParamJdbcTemplate;
	}
	
	private DaoMapper mapper = new DaoMapper();

	
	@Override
	public List<Member> getMemberIdList() {
		String sql = "select * from member";
		return namedParamJdbcTemplate.query(sql,mapper.getMemberRowMapper());
	}
	
	@Override
	public void addMemberCompulsory(Member member) {
		String sql = "INSERT INTO `projectcall`.`member` (`email`, `pass`, `gender`, `birthday`, `phone`, `level`, "
				+ "`accpoint`, `accwin`, `acclose`, `accpenalty`, `usepoint`, `name`, `nickname`) "
				+ "VALUES (:email , :pass , :gender , :birthday, :phone, '0', '0', '0', '0', '0', '0', :name, :nickname)";
		SqlParameterSource namedParam = 
				new MapSqlParameterSource("email", member.getEmail())
					.addValue("pass", member.getPass())
					.addValue("gender", member.getGender())
					.addValue("birthday", member.getBirthday())
					.addValue("phone", member.getPhone())
					.addValue("name", member.getName())
					.addValue("nickname", member.getNickName());
		
		namedParamJdbcTemplate.update(sql, namedParam);		
	}
	
	@Override
	public List<Area> getAreaList() {
		String sql = "select * from area order by areacode";
		return namedParamJdbcTemplate.query(sql, new RowMapper<Area>(){
			@Override
			public Area mapRow(ResultSet rs, int rowNum) throws SQLException {
				Area area = new Area();
				area.setArea(rs.getString("area"));
				return area;
			}
		});
	}
	
	@Override
	public void updatePhone(String phone, Member member) {
		String sql = "UPDATE `projectcall`.`member` SET `phone`= :phone WHERE `email`= :email";
		SqlParameterSource namedParam = 
				new MapSqlParameterSource("phone", phone)
					.addValue("email", member.getEmail());
		namedParamJdbcTemplate.update(sql, namedParam);
	}
	
	@Override
	public void updateAddress(String address, Member member) {
		String sql = "UPDATE `projectcall`.`member` SET `address`= :address WHERE `email`= :email";
		SqlParameterSource namedParam = 
				new MapSqlParameterSource("address", address)
					.addValue("email", member.getEmail());
		namedParamJdbcTemplate.update(sql, namedParam);
	}
	
	@Override
	public void updateArea(String area, Member member) {
		String sql = "UPDATE `projectcall`.`member` SET `area`= :area WHERE `email`= :email";
		SqlParameterSource namedParam = 
				new MapSqlParameterSource("area", area)
					.addValue("email", member.getEmail());
		namedParamJdbcTemplate.update(sql, namedParam);
	}
	
	
	@Override
	public void updateWord(String word, Member member) {
		String sql = "UPDATE `projectcall`.`member` SET `word`= :word WHERE `email`= :email";
		SqlParameterSource namedParam = 
				new MapSqlParameterSource("word", word)
					.addValue("email", member.getEmail());
		namedParamJdbcTemplate.update(sql, namedParam);
	}
	
	@Override
	public void updatePhoto(String photo, Member member) {
		String sql = "UPDATE `projectcall`.`member` SET `photo`= :photo WHERE `email`= :email";
		SqlParameterSource namedParam = 
				new MapSqlParameterSource("photo", photo)
					.addValue("email", member.getEmail());
		namedParamJdbcTemplate.update(sql, namedParam);
	}
	
	@Override
	public Member getMember(Member member) {
		String sql = "select * from member where email = :email ";
		SqlParameterSource namedParam = 
				new MapSqlParameterSource("email", member.getEmail());

		return namedParamJdbcTemplate.query(sql, namedParam, mapper.getMemberResultSetExtractor());
	}
	
	@Override
	public List<String> getNickNameList(String keyword) {
		String sql = "select nickname from member where nickname like :keyword";
		SqlParameterSource namedParam = 
				new MapSqlParameterSource("keyword", "%"+keyword+"%");
		return  namedParamJdbcTemplate.query(sql, namedParam, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});		
	}
	
	@Override
	public void insertAsk(AskInjection ask) {
		String sql = "INSERT INTO `projectcall`.`ask` (`toid`, `fightDate`, `approval`, `place`, `writeDate`, `tell`, `email`) "
				+ "VALUES(:toId, :fightDate, :approval, :place, :writeDate, :tell, :email)";
		SqlParameterSource namedParam = 
				new MapSqlParameterSource("toId",ask.getToId())
					.addValue("fightDate", ask.getFightDate())
					.addValue("approval", ask.getApproval())
					.addValue("place", ask.getPlace())
					.addValue("writeDate", ask.getWriteDate())
					.addValue("tell", ask.getTell())
					.addValue("email", ask.getEmail());
		namedParamJdbcTemplate.update(sql, namedParam);
	}
	
	@Override
		public void setPassMember(String email, String pass) {
			String sql = "UPDATE `projectcall`.`member` SET `pass`=:pass "
					+ "WHERE `email`=:email ";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("pass", pass)
								.addValue("email", email);
			namedParamJdbcTemplate.update(sql, namedParam);
		}
	
	
	
}
