package com.project.call.hyunsu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.project.call.daomapper.DaoMapper;
import com.project.call.domain.Area;
import com.project.call.domain.AskInjection;
import com.project.call.domain.Fight;
import com.project.call.domain.FightResult;
import com.project.call.domain.FightResultBoardSupprot;
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
	
	@Override
		public int getFightResultCount(int fightNumber) {
			String sql = "SELECT count(*) FROM projectcall.fightresult where fightNumber = :fightNumber ";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("fightNumber", fightNumber);			
			return namedParamJdbcTemplate.query(sql, namedParam, new ResultSetExtractor<Integer>() {
				@Override
				public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
					int count = 0;
					if(rs.next()) count = rs.getInt(1);					
					return count;
				}
			});
		}
	
	@Override
		public Fight getFight(int fightNumber) {
			String sql = "select * from fight where fightNumber = :fightNumber ";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("fightNumber", fightNumber);
			return namedParamJdbcTemplate.query(sql, namedParam, new ResultSetExtractor<Fight>() {
				@Override
				public Fight extractData(ResultSet rs) throws SQLException, DataAccessException {
					Fight fight = new Fight();
					if(rs.next()){
						fight.setFightNumber(rs.getInt("fightNumber"));
						fight.setCallDate(rs.getTimestamp("callDate"));
						fight.setResultDate(rs.getTimestamp("resultDate"));
						fight.setPlayer1(rs.getString("player1"));
						fight.setPlayer2(rs.getString("player2"));
						fight.setResult(rs.getInt("result"));
						fight.setPlayer1Result(rs.getInt("player1Result"));
						fight.setPlayer2Result(rs.getInt("player2Result"));
					}
					return fight;
				}
			});
		}
	
	@Override
		public void insertFightResultPlayer1(int result, int fightNumber, Timestamp nowTime) {
			String sql = "INSERT INTO `projectcall`.`fightresult` (`fightNumber`, `player1result`, `player1writeDate`, `hit`) "
					+ "VALUES (:fightNumber, :result, :nowTime, :hit)";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("fightNumber", fightNumber)
							.addValue("result", result)
							.addValue("nowTime", nowTime)
							.addValue("hit", 0);
			namedParamJdbcTemplate.update(sql, namedParam);		
		}
	
	@Override
		public void insertFightResultPlayer2(int result, int fightNumber, Timestamp nowTime) {
			String sql = "INSERT INTO `projectcall`.`fightresult` (`fightNumber`, `player2result`, `player2writeDate`, `hit`) "
					+ "VALUES (:fightNumber, :result, :nowTime, :hit)";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("fightNumber", fightNumber)
							.addValue("result", result)
							.addValue("nowTime", nowTime)
							.addValue("hit", 0);
			namedParamJdbcTemplate.update(sql, namedParam);			
		}
	
	@Override
		public void updateFightResultPlayer1(int result, int fightNumber, Timestamp nowTime) {
			String sql = "UPDATE `projectcall`.`fightresult` SET `player1result`= :result, "
					+ "`player1writeDate`= :nowTime WHERE `fightNumber`= :fightNumber ";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("fightNumber", fightNumber)
							.addValue("result", result)
							.addValue("nowTime", nowTime);
			namedParamJdbcTemplate.update(sql, namedParam);		
		}
	
	@Override
		public void updateFightResultPlayer2(int result, int fightNumber, Timestamp nowTime) {
			String sql = "UPDATE `projectcall`.`fightresult` SET `player2result`= :result, "
					+ "`player2writeDate`= :nowTime WHERE `fightNumber`= :fightNumber ";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("fightNumber", fightNumber)
							.addValue("result", result)
							.addValue("nowTime", nowTime);
			namedParamJdbcTemplate.update(sql, namedParam);	
		}
	
	@Override
		public void updateFight(int state, int fightNumber) {
			String sql = "UPDATE `projectcall`.`fight` "
					+ "SET `player" + state+ "Result`='1' WHERE `fightNumber`= :fightNumber";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("fightNumber", fightNumber);
			namedParamJdbcTemplate.update(sql, namedParam);		
		}
	
	@Override
		public int getFightResultCount() {
			String sql = "select count(*) from projectcall.fightresult";
			return namedParamJdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {
				@Override
				public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
					Integer count = 0;
					if(rs.next()) count = rs.getInt(1);
					return count;
				}
			});
		}
	
	@Override
		public List<FightResult> getFightResultList(int startRow, int PAGE_SIZE) {
			String sql = "select * from projectcall.fightresult order by no desc limit :startRow, :PAGE_SIZE";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("startRow", startRow)
							.addValue("PAGE_SIZE", PAGE_SIZE);
			return namedParamJdbcTemplate.query(sql, namedParam, new RowMapper<FightResult>() {
				@Override
				public FightResult mapRow(ResultSet rs, int rowNum) throws SQLException {
					FightResult fightResult = new FightResult();
					fightResult.setNo(rs.getInt("no"));
					fightResult.setFightNumber(rs.getInt("fightNumber"));
					fightResult.setPlayer1result(rs.getInt("player1result"));
					fightResult.setPlayer1writeDate(rs.getTimestamp("player1writeDate"));
					fightResult.setPlayer2result(rs.getInt("player2result"));
					fightResult.setPlayer2writeDate(rs.getTimestamp("player2writeDate"));
					fightResult.setHit(rs.getInt("hit"));
					return fightResult;
				}
			});
		}
	
	@Override
		public Fight getFightAsNickname(int fightNumber) {
		String sql = "select f.*, m1.nickname as player1Nickname, "
				+ "m2.nickname as player2nickName from fight f "
				+ "inner join member m1 inner join member m2 "
				+ "where fightNumber = :fightNumber "
				+ "and m1.email = f.player1 and m2.email = f.player2";
		SqlParameterSource namedParam = 
				new MapSqlParameterSource("fightNumber", fightNumber);
		return namedParamJdbcTemplate.query(sql, namedParam, new ResultSetExtractor<Fight>() {
			@Override
			public Fight extractData(ResultSet rs) throws SQLException, DataAccessException {
				Fight fight = new Fight();
				if(rs.next()){
					fight.setFightNumber(rs.getInt("fightNumber"));
					fight.setCallDate(rs.getTimestamp("callDate"));
					fight.setResultDate(rs.getTimestamp("resultDate"));
					fight.setPlayer1(rs.getString("player1nickName"));
					fight.setPlayer2(rs.getString("player2nickName"));
					fight.setResult(rs.getInt("result"));
					fight.setPlayer1Result(rs.getInt("player1Result"));
					fight.setPlayer2Result(rs.getInt("player2Result"));
				}
				return fight;
			}
		});
	}
	
	@Override
		public FightResultBoardSupprot getFigthResultContent(int no) {
			String sql = "select rs.*, m1.nickname as player1NickName, "
					+ "m2.nickname as player2NickName from "
					+ "(select fs.*, f.callDate, f.resultDate, f.player1, "
					+ "f.player2 from projectcall.fightresult fs "
					+ "inner join fight f where fs.fightNumber = f.fightNumber "
					+ "and fs.no = :no) rs inner join member m1 "
					+ "inner join member m2 where m1.email = rs.player1 "
					+ "and m2.email = rs.player2";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("no", no);
			return namedParamJdbcTemplate.query(sql, namedParam, 
					new ResultSetExtractor<FightResultBoardSupprot>() {

						@Override
						public FightResultBoardSupprot extractData(ResultSet rs)
								throws SQLException, DataAccessException {
							FightResultBoardSupprot supprot = new FightResultBoardSupprot();
							if(rs.next()){
								supprot.setNo(rs.getInt("no"));
								supprot.setFightNumber(rs.getInt("fightNumber"));
								supprot.setPlayer1(rs.getString("player1NickName"));
								supprot.setPlayer2(rs.getString("player2NickName"));
								supprot.setPlayer1result(rs.getInt("player1result"));
								supprot.setPlayer2result(rs.getInt("player2result"));
								supprot.setPlayer1writeDate(rs.getTimestamp("player1writeDate"));
								supprot.setPlayer2writeDate(rs.getTimestamp("player2writeDate"));
								supprot.setHit(rs.getInt("hit"));
								supprot.setCallDate(rs.getTimestamp("callDate"));
								supprot.setResultDate(rs.getTimestamp("resultDate"));						
							}
							return supprot;
						}
					});
			
		}
	
	@Override
		public void updateFightResultHit(int hit, int no) {
			String sql = "UPDATE `projectcall`.`fightresult` SET `hit`= :hit WHERE `no`= :no";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("no", no)
							.addValue("hit", hit);
			namedParamJdbcTemplate.update(sql, namedParam);		
		}
	
	@Override
		public FightResultBoardSupprot getFigthResultContentUseFightNumber(int fightNumber) {

		String sql = "select rs.*, m1.nickname as player1NickName, "
				+ "m2.nickname as player2NickName from "
				+ "(select fs.*, f.callDate, f.resultDate, f.player1, "
				+ "f.player2 from projectcall.fightresult fs "
				+ "inner join fight f where fs.fightNumber = f.fightNumber "
				+ "and fs.fightNumber = :fightNumber) rs inner join member m1 "
				+ "inner join member m2 where m1.email = rs.player1 "
				+ "and m2.email = rs.player2";
		SqlParameterSource namedParam = 
				new MapSqlParameterSource("fightNumber", fightNumber);
		return namedParamJdbcTemplate.query(sql, namedParam, 
				new ResultSetExtractor<FightResultBoardSupprot>() {

					@Override
					public FightResultBoardSupprot extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						FightResultBoardSupprot supprot = new FightResultBoardSupprot();
						if(rs.next()){
							supprot.setNo(rs.getInt("no"));
							supprot.setFightNumber(rs.getInt("fightNumber"));
							supprot.setPlayer1(rs.getString("player1"));
							supprot.setPlayer2(rs.getString("player2"));
							supprot.setPlayer1result(rs.getInt("player1result"));
							supprot.setPlayer2result(rs.getInt("player2result"));
							supprot.setPlayer1writeDate(rs.getTimestamp("player1writeDate"));
							supprot.setPlayer2writeDate(rs.getTimestamp("player2writeDate"));
							supprot.setHit(rs.getInt("hit"));
							supprot.setCallDate(rs.getTimestamp("callDate"));
							supprot.setResultDate(rs.getTimestamp("resultDate"));						
						}
						return supprot;
					}
				});	
		}
	
	@Override
		public Member getMember(String email) {
			String sql = "select * from member where email = :email";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("email", email);			
			return namedParamJdbcTemplate.query(sql, namedParam, mapper.getMemberResultSetExtractor());
		}
	
	@Override
		public void updateMemberPenalty(String email, int point, int penalty) {
			String sql = "UPDATE `projectcall`.`member` "
					+ "SET `accpoint`= :point, `accpenalty`= :penalty "
					+ "WHERE `email`= :email";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("email", email)
								.addValue("point", point)
								.addValue("penalty", penalty);		
			namedParamJdbcTemplate.update(sql, namedParam);
		}
	
	@Override
		public void updateMemberLose(String email, int point, int lose) {
			String sql = "UPDATE `projectcall`.`member` "
					+ "SET `accpoint`= :point, `acclose`= :lose "
					+ "WHERE `email`= :email";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("email", email)
								.addValue("point", point)
								.addValue("lose", lose);		
			namedParamJdbcTemplate.update(sql, namedParam);	
		}
	
	@Override
		public void updateMemberWin(String email, int point, int win) {
			String sql = "UPDATE `projectcall`.`member` "
					+ "SET `accpoint`= :point, `accwin`= :win "
					+ "WHERE `email`= :email";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("email", email)
								.addValue("point", point)
								.addValue("win", win);		
			namedParamJdbcTemplate.update(sql, namedParam);				
		}
	
	@Override
		public void updateMemberPass(String email, String password) {
			String sql = "UPDATE `projectcall`.`member` SET `pass`= :password "
					+ "WHERE `email`= :email";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("email", email)
							.addValue("password", password);
			namedParamJdbcTemplate.update(sql, namedParam);
		}
	
	@Override
		public int checkNickName(String email, String nickName) {
			String sql = "select count(*) from member where nickname = :nickName "
					+ "and email != :email";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("email", email)
							.addValue("nickName", nickName);
			return namedParamJdbcTemplate.query(sql, namedParam, new ResultSetExtractor<Integer>() {
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
		public void updateMemberNickName(String email, String nickName) {
			String sql = "UPDATE `projectcall`.`member` SET `nickname`= :nickName "
					+ "WHERE `email`= :email ";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("email", email)
							.addValue("nickName", nickName);
			namedParamJdbcTemplate.update(sql, namedParam);
	}
	
	@Override
		public void updateMemberGender(String email, String gender) {
			String sql = "UPDATE `projectcall`.`member` SET `gender`= :gender WHERE `email`= :email ";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("email", email)
							.addValue("gender", gender);
			namedParamJdbcTemplate.update(sql, namedParam);
	}
	
	@Override
		public void deleteMember(Member member, String email, String nickName) {
			String sql = "UPDATE `projectcall`.`member` "
					+ "SET `email`=:id , "
					+ "`pass`=:pass , `gender`= null, `address`= null, "
					+ "`birthday`= :birthday, `area`= null, `word`= :word, `name`= null, "
					+ "`nickname`= :nickName WHERE `email`= :email";
			SqlParameterSource namedParam = 
					new MapSqlParameterSource("email", email)
							.addValue("nickName", member.getNickName())
							.addValue("pass", member.getPass())
							.addValue("id", member.getEmail())
							.addValue("birthday", new Timestamp(System.currentTimeMillis()))
							.addValue("work", "( " + email + ", " + nickName + " )");
							
			namedParamJdbcTemplate.update(sql, namedParam);
		}
	
	
}
