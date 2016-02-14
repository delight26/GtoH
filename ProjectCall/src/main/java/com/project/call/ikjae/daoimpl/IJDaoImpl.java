package com.project.call.ikjae.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.project.call.daomapper.DaoMapper;
import com.project.call.domain.Fight;
import com.project.call.domain.FightBoard;
import com.project.call.domain.Member;
import com.project.call.domain.FightResultBoard;
import com.project.call.ikjae.dao.IJDao;

@Repository
public class IJDaoImpl implements IJDao {

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

		return jdbcTemplate.queryForObject("select m.* from"
				+ " (SELECT @RNUM:=@RNUM + 1 AS rank, t.*"
				+ " FROM (SELECT * FROM member ORDER BY accpoint desc ) t,"
				+ " (SELECT @RNUM := 0) R) m  where email= ? ",
				new MemberRowMapperIJ(),
				loginUser);

	}

	@Override
	public void updateResult(String fightNumber) {
		SqlParameterSource fightnumParam = new MapSqlParameterSource("fightNumber", fightNumber);
		namedParameterJdbcTemplate.update("update fight set result = 1 where fightnumber = :fightNumber",
				fightnumParam);
	}

//	@Override
//	public List<FightBoard> getFightList(int startRow, int PAGE_SIZE) {
//		
//		SqlParameterSource fightparam = new MapSqlParameterSource("startRow", startRow).addValue("PAGE_SIZE",
//				PAGE_SIZE);
//		
//		return null;
//
//	}
	
	@Override
	public int getFightCount(String loginUser) {
		
		return  jdbcTemplate.queryForObject(
				"SELECT  COUNT(*)  FROM  fight WHERE  player1 = ? or player2 = ?;", 
				Integer.class, loginUser, loginUser);
		
	}

	@Override
	public int passwordCheck(String loginUser, String password) {

		int result = 0;
		String getPassword = jdbcTemplate.queryForObject("SELECT  pass  FROM  member WHERE  email = ?", String.class,
				loginUser);
		if (getPassword.equals(password)) {
			result = 1;
		} else {
			result = 0;
		}

		return result;
	}

	@Override
	public int nickNameCheck(String nickName, String email) {

		return jdbcTemplate.queryForObject("SELECT  COUNT(*)  FROM  member WHERE  nickName = ? and email != ?;", Integer.class,
				nickName, email);

	}

	@Override
	public void updateMember(Member m) {

		SqlParameterSource beanParam = new BeanPropertySqlParameterSource(m);

		if (m.getPass() == null) {

			if (m.getProfilPhoto() == null) {

				namedParameterJdbcTemplate.update(
						"UPDATE  member  SET nickname = :nickName,  "
								+ " gender = :gender, phone = :phone, word = :word," + " WHERE email = :email",
						beanParam);

			} else if (m.getProfilPhoto() != null) {

				namedParameterJdbcTemplate.update("UPDATE  member  SET nickname = :nickName,  "
						+ " gender = :gender, phone = :phone, word = :word, photo = :profilPhoto"
						+ " WHERE email = :email", beanParam);

			}

		} else if (m.getPass() != null) {

			if (m.getProfilPhoto() == null) {

				namedParameterJdbcTemplate.update(
						"UPDATE  member  SET pass = :pass, nickname = :nickName,  "
								+ " gender = :gender, phone = :phone, word = :word," + " WHERE email = :email",
						beanParam);

			} else if (m.getProfilPhoto() != null) {

				namedParameterJdbcTemplate.update("UPDATE  member  SET pass = :pass, nickname = :nickName,  "
						+ " gender = :gender, phone = :phone, word = :word, photo = :profilPhoto"
						+ " WHERE email = :email", beanParam);

			}

		}

	}

	@Override
	public void deleteMember(String loginUser) {

		jdbcTemplate.update("DELETE  FROM  member WHERE  email  =  ?", loginUser);

	}

	@Override
	public FightBoard getFight(int fightNumber) {

		return jdbcTemplate.queryForObject(
				"SELECT f.*, m.nickname p1, (select e.nickname from fight f1 "
						+ "inner join member e on f1.player2 = e.email where f1.fightNumber =?)"
						+ " p2 FROM fight f inner join member m on f.player1 = m.email " + "WHERE f.fightNumber = ?",
				new RowMapper<FightBoard>() {

					public FightBoard mapRow(ResultSet rs, int rowNum) throws SQLException {

						FightBoard f = new FightBoard();

						f.setFbNo(rs.getInt("fightNumber"));
						f.setFbCallDate(rs.getTimestamp("callDate"));
						f.setFbResultDate(rs.getTimestamp("resultDate"));
						f.setFbP1(rs.getString("p1"));
						f.setFbP2(rs.getString("p2"));

						return f;

					}
				}, fightNumber, fightNumber);

	}

	@Override
	public void addFightResultBoardResult(FightResultBoard frb) {

		SqlParameterSource beanParam = new BeanPropertySqlParameterSource(frb);

		namedParameterJdbcTemplate
				.update("INSERT INTO fightResultBoard(fightNumber, title, writer, content, photo, writeDate,"
						+ " hit, isAdminCheck, winner) VALUES(:fightNumber, :title, :writer, :content, :photo, :writeDate,"
						+ " :hit, :isAdminCheck, :winner)", beanParam);

	}

	@Override
	public List<FightResultBoard> getAdminFightResultBoardList() {
		return namedParameterJdbcTemplate.query("select f2.*, m2.nickname mWin from "
				+ "(select f.*, m.nickname mWr from fightresultboard f "
				+ "inner join member m on f.writer = m.email) f2" + " inner join member m2 on f2.winner = m2.nickname where isadmincheck=0",
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
						frb.setWriter(rs.getString("mWr"));
						frb.setWinner(rs.getString("mWin"));

						return frb;

					}
				});
	}

	@Override
	public FightResultBoard getFightResultBoard(int no) {

		return jdbcTemplate.queryForObject(
				"select f.*, m.nickname nickname,(select e.nickname from fightresultboard"
						+ " f1 inner join member e on f1.winner = e.email where no =?) win"
						+ " from fightresultboard f inner join member m on f.writer = m.email where f.no =?",
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
						frb.setWriter(rs.getString("nickname"));
						frb.setFightNumber(rs.getInt("fightNumber"));
						frb.setWinner(rs.getString("winner"));

						return frb;

					}
				}, no, no);

	}

	@Override
	public void adminConfirm(int no) {

		jdbcTemplate.update("UPDATE  fightResultBoard  SET  isAdminCheck = 1 where no = ?", no);

		int fightNumber = jdbcTemplate.queryForObject("SELECT  fightNumber  FROM  fightResultBoard WHERE  no = ?",
				Integer.class, no);
		String winner = jdbcTemplate.queryForObject("SELECT  winner  FROM  fightResultBoard WHERE  no = ?",
				String.class, no);

	}

	@Override
	public void updateFightResultBoardResult(FightResultBoard frb) {

		SqlParameterSource beanParam = new BeanPropertySqlParameterSource(frb);

		namedParameterJdbcTemplate.update(
				"UPDATE fightResultBoard SET fightNumber = :fightNumber, title = :title,"
						+ " writer = :writer, content = :content, photo = :photo, writeDate = :writeDate,"
						+ " hit = :hit, isAdminCheck = :isAdminCheck, winner =  :winner" + " WHERE no = :no ",
				beanParam);

	}

	@Override
	public void deleteFightResultBoard(int no) {

		jdbcTemplate.update("DELETE  FROM  fightResultBoard WHERE no =  ?", no);

	}

	@Override
	public void hitUp(int parseInt) {
		jdbcTemplate.update("update fightResultBoard set hit = hit+1 where no=?", parseInt);

	}

	@Override
	public List<String> getNickNameList() {
		String sql = "select nickname from member";
		return namedParameterJdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
	}
	@Override
	   public List<FightResultBoard> getFightResultBoardList(int pageNum) {
	      int start =0;
	         int end = 10;
	         if(pageNum == 1){
	            start = 0;
	         }else{
	            start = pageNum*10-10;
	         }
	         int pageSize =
	               jdbcTemplate.queryForObject(
	                     "select Ceil(count(*)/10) from fightresultboard", Integer.class);
	      return jdbcTemplate.query(
	            "select f2.*, m2.nickname mWin from "
	            + "(select f.*, m.nickname mWr from fightresultboard f "
	            + "inner join member m on f.writer = m.email ) f2 inner "
	            + "join member m2 on f2.winner = m2.nickname limit ?, ?",
	            
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
	                  frb.setWriter(rs.getString("mWr"));
	                  frb.setWinner(rs.getString("mWin"));
	                  frb.setPageSize(pageSize);
	                  return frb;
	                  
	               }
	            }, start, end);
	   }

	@Override
	   public List<Fight> getFightList(String loginUser, int startRow, int PAGE_SIZE) {
	      String sql = "select f2.*, m2.nickname as user2nickname from"
	            + " (SELECT f.*, m.nickname as user1nickname"
	            + " FROM fight f INNER JOIN member m ON f.player1 = m.email)"
	            + " f2 inner join member m2 on f2.player2 = m2.email"
	            + " WHERE player1 = :loginUser OR player2 = :loginUser"
	            + " order by resultDate desc limit :startRow, :PAGE_SIZE";
	      SqlParameterSource namedParam = 
	               new MapSqlParameterSource("loginUser", loginUser)
	                  .addValue("startRow", startRow)
	                  .addValue("PAGE_SIZE", PAGE_SIZE);
	      
	      return namedParameterJdbcTemplate.query(sql, namedParam, new RowMapper <Fight>() {

	         @Override
	         public Fight mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Fight fight = new Fight();
				fight.setFightNumber(rs.getInt("fightNumber"));
				fight.setCallDate(rs.getTimestamp("callDate"));
				fight.setResultDate(rs.getTimestamp("resultDate"));
				fight.setPlayer1(rs.getString("player1"));
				fight.setPlayer2(rs.getString("player2"));
				fight.setResult(rs.getInt("result"));
				fight.setPlayer1Result(rs.getInt("player1Result"));
				fight.setPlayer2Result(rs.getInt("player2Result"));
	            return fight;
	         }
	      });
	   }

	 
	public class MemberRowMapperIJ implements RowMapper<Member> {
	      @Override
	      public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
	         Member m = new Member();
	         
	         m.setRank(rs.getString("rank"));
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
