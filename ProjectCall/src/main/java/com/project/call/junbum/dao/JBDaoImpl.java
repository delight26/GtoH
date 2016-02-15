package com.project.call.junbum.dao;

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
import com.project.call.domain.AskBoard;
import com.project.call.domain.Comment;
import com.project.call.domain.FreeBoard;
import com.project.call.domain.Member;
import com.project.call.domain.PointProduct;
import com.sun.jmx.snmp.Timestamp;

@Repository
public class JBDaoImpl implements JBDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private DaoMapper dm = new DaoMapper();

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public Member getloginResult(String email) {
		SqlParameterSource emailparam = new MapSqlParameterSource("email", email);
		return namedParameterJdbcTemplate.query("select * from member where email = :email", emailparam,
				dm.getMemberResultSetExtractor());
	}

	@Override
	public Integer getProductCount() {
		SqlParameterSource boardparam = new MapSqlParameterSource("product", "product");
		return namedParameterJdbcTemplate.queryForObject("select count(*) from product", boardparam, Integer.class);
	}

	@Override
	public List<PointProduct> getproductList(int startRow, int PAGE_SIZE) {
		SqlParameterSource productparam = new MapSqlParameterSource("startRow", startRow).addValue("PAGE_SIZE",
				PAGE_SIZE);
		return namedParameterJdbcTemplate.query("select * from product where amount > 0 limit :startRow, :PAGE_SIZE",
				productparam, dm.getProductRowMapper());
	}

	@Override
	public void addProduct(PointProduct p) {
		SqlParameterSource prodparam = new BeanPropertySqlParameterSource(p);
		namedParameterJdbcTemplate.update("insert into product values(0, :pName, :pPrice, ' ', :pAmount, :pImage, 0)",
				prodparam);
	}

	@Override
	public PointProduct productContent(int pNo) {
		SqlParameterSource pNoparam = new MapSqlParameterSource("pNo", pNo);
		return namedParameterJdbcTemplate.query("select * from product where productcode = :pNo", pNoparam,
				dm.getProductResultSetExtractor());
	}

	@Override
	public void updateProduct(PointProduct p) {
		SqlParameterSource prodparam = new BeanPropertySqlParameterSource(p);
		System.out.println(p.getpAmount());
		namedParameterJdbcTemplate.update("update product set productname=:pName, point=:pPrice, amount=:pAmount,"
				+ "image=:pImage where productcode=:pProductCode ", prodparam);
	}

	@Override
	public void productDelete(int pProductCode) {
		SqlParameterSource pProductCodeparam = new MapSqlParameterSource("pProductCode", pProductCode);
		namedParameterJdbcTemplate.update("delete from product where productcode=:pProductCode ", pProductCodeparam);
	}

	@Override
	public void orderProduct(PointProduct p, Member m) {
		SqlParameterSource prodparam = new BeanPropertySqlParameterSource(p);
		SqlParameterSource memparam = new BeanPropertySqlParameterSource(m);
		namedParameterJdbcTemplate
				.update("update product set amount=:pAmount, buy=:pBuy where productcode=:pProductCode ", prodparam);
		namedParameterJdbcTemplate.update("update member set usepoint=:usepoint where email=:email", memparam);
	}

	@Override
	public Integer getaggroCount() {
		SqlParameterSource aggroparam = new MapSqlParameterSource("aggro", "aggro");
		return namedParameterJdbcTemplate.queryForObject("select count(*) from freeboard where area=:aggro", aggroparam,
				Integer.class);
	}

	@Override
	public List<FreeBoard> getAggroList(int startRow, int PAGE_SIZE) {
		SqlParameterSource productparam = new MapSqlParameterSource("startRow", startRow).addValue("PAGE_SIZE",
				PAGE_SIZE);
		return namedParameterJdbcTemplate.query(
				"select fb.*, (select count(*) from comment where comment.bno = fb.no) as comm "
						+ "from freeboard fb where  fb.area='aggro' order by writedate desc limit :startRow, :PAGE_SIZE ",
				productparam, dm.getFreeBoardRowMapper());
	}

	@Override
	public void aggroBoardWrite(FreeBoard fb) {
		SqlParameterSource fbparam = new BeanPropertySqlParameterSource(fb);
		namedParameterJdbcTemplate
				.update("insert into freeboard values(0, :frbTitle, 0, :frbContent, '', :frbWriteDate, 0, :frbArea"
						+ ", :frbEmail, :frbWriter)", fbparam);
	}

	@Override
	public void aggroBoardWritephoto(FreeBoard fb) {
		SqlParameterSource fbparam = new BeanPropertySqlParameterSource(fb);
		namedParameterJdbcTemplate
				.update("insert into freeboard values(0, :frbTitle, 0, :frbContent, :Photo1, :frbWriteDate, 0, :frbArea"
						+ ", :frbEmail, :frbWriter)", fbparam);
	}

	@Override
	public FreeBoard aggroContent(int frbNo) {
		SqlParameterSource frbNoparam = new MapSqlParameterSource("frbNo", frbNo);

		return namedParameterJdbcTemplate
				.query("select fb.*, (select count(*) from comment where comment.bno = fb.no) as comm "
						+ "from freeboard fb where  fb.no=:frbNo", frbNoparam, dm.getFreeBoardResultSetExtractor());
	}

	@Override
	public void aggroHitUpdate(int frbHit, int frbNo) {
		SqlParameterSource frbNoparam = new MapSqlParameterSource("frbHit", frbHit).addValue("frbNo", frbNo);
		namedParameterJdbcTemplate.update("update freeboard set hit = :frbHit where no = :frbNo", frbNoparam);
	}

	@Override
	public Integer aggroNextNo(int frbNo) {
		SqlParameterSource frbNoparam = new MapSqlParameterSource("frbNo", frbNo);
		return namedParameterJdbcTemplate.queryForObject(
				"SELECT max(no) FROM freeboard fr WHERE no < :frbNo and area='aggro';", frbNoparam, Integer.class);
	}

	@Override
	public Integer aggroPreNo(int frbNo) {
		SqlParameterSource frbNoparam = new MapSqlParameterSource("frbNo", frbNo);
		return namedParameterJdbcTemplate.queryForObject(
				"SELECT min(no) FROM freeboard fr WHERE no > :frbNo and area='aggro'", frbNoparam, Integer.class);
	}

	@Override
	public void aggroBoardUpdatePhoto(FreeBoard frb) {
		SqlParameterSource frbparam = new BeanPropertySqlParameterSource(frb);
		namedParameterJdbcTemplate.update(
				"update freeboard set title = :frbTitle, content = :frbContent, photo = :Photo1 where no=:frbNo",
				frbparam);
	}

	@Override
	public void aggroBoardUpdate(FreeBoard frb) {
		SqlParameterSource frbparam = new BeanPropertySqlParameterSource(frb);
		namedParameterJdbcTemplate
				.update("update freeboard set title = :frbTitle, content = :frbContent where no=:frbNo", frbparam);
	}

	@Override
	public void aggroDelete(int frbNo) {
		String sql = "UPDATE `projectcall`.`freeboard` SET `title`='삭제된 게시글 입니다.', `content`='삭제된 게시글 입니다.', "
				+ "`photo`='null' WHERE `no`= :frbNo ";
		SqlParameterSource frbparam = new MapSqlParameterSource("frbNo", frbNo);
		namedParameterJdbcTemplate.update(sql, frbparam);
	}

	@Override
	public Integer aggroSearchCount(String search) {
		SqlParameterSource searchParam = new MapSqlParameterSource("search", search).addValue("per", "%");
		return namedParameterJdbcTemplate.queryForObject(
				"select count(*) from freeboard where area='aggro' and title like :per :search :per;", searchParam,
				Integer.class);
	}

	@Override
	public List<FreeBoard> aggroSearch(String search, int startRow, int PAGE_SIZE) {
		SqlParameterSource searchParam = new MapSqlParameterSource("search", search).addValue("startRow", startRow)
				.addValue("PAGE_SIZE", PAGE_SIZE).addValue("per", "%");
		return namedParameterJdbcTemplate.query(
				"select fb.*, (select count(*) from comment where comment.bno = fb.no) as comm "
						+ "from freeboard fb where  fb.area='aggro' and fb.title like :per :search :per "
						+ "order by writedate desc limit :startRow, :PAGE_SIZE",
				searchParam, dm.getFreeBoardRowMapper());
	}

	@Override
	public Integer getCommentCount(int frbNo) {
		SqlParameterSource aggroparam = new MapSqlParameterSource("frbNo", frbNo);
		return namedParameterJdbcTemplate.queryForObject("select count(*) from comment where bno=:frbNo", aggroparam,
				Integer.class);
	}

	@Override
	public List<Comment> getComment(int frbNo, int startRow, int PAGE_SIZE) {
		SqlParameterSource frbparam = new MapSqlParameterSource("frbNo", frbNo).addValue("startRow", startRow)
				.addValue("PAGE_SIZE", PAGE_SIZE);
		return namedParameterJdbcTemplate.query(
				"select com.*, mem.nickname, mem.photo  from comment com, member mem where com.email = mem.email and com.bno=:frbNo order by no desc limit :startRow, :PAGE_SIZE",
				frbparam, dm.getCommentRowMapper());
	}

	@Override
	public void aggroCommentWrite(Comment c) {
		SqlParameterSource cparam = new BeanPropertySqlParameterSource(c);

		namedParameterJdbcTemplate.update("insert into comment values(0, :cContent, :cEmail, :bNo, now())", cparam);
	}

	@Override
	public void aggroCommentUpdate(Comment c) {
		SqlParameterSource cparam = new BeanPropertySqlParameterSource(c);
		namedParameterJdbcTemplate.update("update comment set comment = :cContent where no = :cNo", cparam);
	}

	@Override
	public void aggroCommentDelete(int cNo) {
		SqlParameterSource cNoparam = new MapSqlParameterSource("cNo", cNo);
		namedParameterJdbcTemplate.update("delete from comment where no=:cNo", cNoparam);
	}

	@Override
	public List<AskBoard> askResultList(String email) {
		SqlParameterSource cNoparam = new MapSqlParameterSource("email", email);
		return namedParameterJdbcTemplate.query(
				"select f2.*, rank2.rank as player1Rank from "
				+ "( select f.*, rank1.rank as player2Rank from "
				+ "( select a.*, m.accpoint, m.nickname from ask a, member m "
				+ "where a.email = m.email and a.email = :email and a.fightDate>now() "
				+ "order by asknumber desc) f "
				+ "inner join ((SELECT @RNUM:=@RNUM + 1 AS rank, t.* FROM "
				+ "(SELECT * FROM member where email not like '삭제된계정입니다%' ORDER BY accpoint desc  ) t, "
				+ "(SELECT @RNUM := 0) R)) rank1 where f.toid = rank1.nickname) f2 "
				+ "inner join ((SELECT @RNUM2:=@RNUM2 + 1 AS rank, t.* FROM "
				+ "(SELECT * FROM member where email not like '삭제된계정입니다%' ORDER BY accpoint desc  ) t, "
				+ "(SELECT @RNUM2 := 0) R)) rank2 where f2.email = rank2.email",
				cNoparam, dm.getAskBoardRowMapperResultSetExtractor());
	}

	@Override
	public AskBoard getAskResult(int abNo) {
		SqlParameterSource abNoParam = new MapSqlParameterSource("abNo", abNo);
		return namedParameterJdbcTemplate.query(
				"select a.*, m.accpoint, m.nickname from ask a, member m where a.email = m.email and a.asknumber = :abNo",
				abNoParam, dm.getAskBoardRowMapperResultSetExtractor());
	}

	@Override
	public void askResultUpdateResult(AskBoard ab) {
		SqlParameterSource abparam = new BeanPropertySqlParameterSource(ab);
		namedParameterJdbcTemplate.update(
				"update ask SET toid = :abToid ,fightDate = :abFightDate, place = :abPlace, tell = :abTell WHERE askNumber = :abNo;",
				abparam);
	}

	@Override
	public void askResultDelete(int abNo) {
		SqlParameterSource abNoParam = new MapSqlParameterSource("abNo", abNo);
		namedParameterJdbcTemplate.update("delete from ask where askNumber = :abNo;", abNoParam);
	}

	@Override
	public List<AskBoard> askReceveList(String nickName) {
		SqlParameterSource nickNameparam = new MapSqlParameterSource("nickName", nickName);
		List<AskBoard> abList = namedParameterJdbcTemplate.query(
				"select f.*, rank1.rank as player1Rank, rank2.rank as player2Rank from"
				+ "( select a.*, m.accpoint, m.nickname from ask a, member m where a.email = "
				+ "m.email and a.toid = :nickName and a.fightDate>now() order by asknumber desc) f "
				+ "inner join (SELECT @RNUM:=@RNUM + 1 AS rank, t.* FROM (SELECT * FROM member "
				+ "where email not like '삭제된계정입니다%' ORDER BY accpoint desc  ) t, (SELECT @RNUM := 0) R)"
				+ " rank1 inner join (SELECT @RNUM2:=@RNUM2 + 1 AS rank, t.* FROM "
				+ "(SELECT * FROM member where email not like '삭제된계정입니다%' ORDER BY accpoint desc  ) t, "
				+ "(SELECT @RNUM2 := 0) R) rank2 where f.toid = rank1.nickname and "
				+ "f.nickname = rank2.nickname;",
				nickNameparam, new RowMapper<AskBoard>() {
					@Override
					public AskBoard mapRow(ResultSet rs, int arg1) throws SQLException {
						AskBoard ab = new AskBoard();
						ab.setAbNickName(rs.getString("nickname"));
						ab.setAbNo(rs.getInt("asknumber"));
						ab.setAbToid(rs.getString("toid"));
						ab.setAbFightDate(rs.getDate("fightdate"));
						ab.setAbApproval(rs.getInt("approval"));
						ab.setAbPlace(rs.getString("place"));
						ab.setAbWriteDate(rs.getDate("writedate"));
						ab.setAbTell(rs.getString("tell"));
						ab.setAbEmail(rs.getString("nickname"));
						  if(rs.getInt("player1Rank") ==1){
							  ab.setAbEmailRank("유일신");
					            }else  if(rs.getInt("player1Rank") >1 && rs.getInt("player1Rank") <4){
					            	ab.setAbEmailRank("GOD");
					                }else  if(rs.getInt("player1Rank") >=4 && rs.getInt("player1Rank") <=10){
					                	ab.setAbEmailRank("SEMI-GOD");
					                    }else  if(rs.getInt("player1Rank") >=11 && rs.getInt("player1Rank") <=20){
					                    	ab.setAbEmailRank("SEMI-SEMI-GOD");
					                        }else  if(rs.getInt("player1Rank") >=21){
					                        	ab.setAbEmailRank("평민");
					                        }
						  if(rs.getInt("player2Rank") ==1){
								ab.setAbToidRank("유일신");
					            }else  if(rs.getInt("player2Rank") >1 && rs.getInt("player2Rank") <4){
					            	ab.setAbToidRank("GOD");
					                }else  if(rs.getInt("player2Rank") >=4 && rs.getInt("player2Rank") <=10){
					                	ab.setAbToidRank("SEMI-GOD");
					                    }else  if(rs.getInt("player2Rank") >=11 && rs.getInt("player2Rank") <=20){
					                    	ab.setAbToidRank("SEMI-SEMI-GOD");
					                        }else  if(rs.getInt("player2Rank") >=21){
					                        	ab.setAbToidRank("평민");
					                        }
						return ab;
					}
				});
		return abList;
	}

	@Override
	public AskBoard getAskBoard(int abNo) {
		SqlParameterSource abNoParam = new MapSqlParameterSource("abNo", abNo);
		return namedParameterJdbcTemplate.query(
				"select a.askNumber, m.email toid, a.fightDate, a.approval, a.place, a.writeDate, a.tell, a.email, m.accpoint, m.nickname from ask a, member m "
				+ "where asknumber = :abNo and a.toid = m.nickname;",
				abNoParam, dm.getAskBoardRowMapperResultSetExtractor());
	}
	
	@Override

	public void addFight(AskBoard ab) {
		String sql = "insert into fight values(0, now(), :abFightDate, :abEmail, :abToid, 0, null, null)";
		SqlParameterSource abParam = new BeanPropertySqlParameterSource(ab);
		namedParameterJdbcTemplate.update(sql, abParam);
	}

	
	@Override
	public void askApproval(int abNo) {
		SqlParameterSource abNoparam = new MapSqlParameterSource("abNo", abNo);
		namedParameterJdbcTemplate.update("update ask set approval = 1 where asknumber = :abNo", abNoparam);
	}

	@Override
	public void askCancel(int abNo) {
		SqlParameterSource abNoparam = new MapSqlParameterSource("abNo", abNo);
		namedParameterJdbcTemplate.update("update ask set approval = 2 where asknumber = :abNo", abNoparam);
	}
	
	@Override
	public List<Member> getMemberId(String name, String birthday) {
		SqlParameterSource memParam = new MapSqlParameterSource("name", name).addValue("birthday", birthday);
		return namedParameterJdbcTemplate.query("select * from member where name=:name and birthday=:birthday", memParam, dm.getMemberRowMapper());
	}
}
