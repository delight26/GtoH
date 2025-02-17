package com.project.call.daomapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.project.call.domain.AskBoard;
import com.project.call.domain.Comment;
import com.project.call.domain.FreeBoard;
import com.project.call.domain.Member;
import com.project.call.domain.PointProduct;

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

	private ProductRowMapper productRowMapper = new ProductRowMapper();

	public ProductRowMapper getProductRowMapper() {
		return productRowMapper;
	}

	private class ProductRowMapper implements RowMapper<PointProduct> {
		@Override
		public PointProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
			PointProduct p = new PointProduct();

			p.setpProductCode(rs.getInt("productcode"));
			p.setpName(rs.getString("productname"));
			p.setpPrice(rs.getInt("point"));
			p.setpAmount(rs.getInt("amount"));
			p.setpImage(rs.getString("image"));
			p.setpBuy(rs.getInt("buy"));

			return p;
		}
	}

	private ProductResultSetExtractor productResultSetExtractor = new ProductResultSetExtractor();

	public ProductResultSetExtractor getProductResultSetExtractor() {
		return productResultSetExtractor;
	}

	private class ProductResultSetExtractor implements ResultSetExtractor<PointProduct> {
		@Override
		public PointProduct extractData(ResultSet rs) throws SQLException, DataAccessException {
			PointProduct p = new PointProduct();
			if (rs.next()) {
				p.setpProductCode(rs.getInt("productcode"));
				p.setpName(rs.getString("productname"));
				p.setpPrice(rs.getInt("point"));
				p.setpAmount(rs.getInt("amount"));
				p.setpImage(rs.getString("image"));
				p.setpBuy(rs.getInt("buy"));

				return p;
			}
			return null;
		}
	}

	private FreeBoardRowMapper freeBoardRowMapper = new FreeBoardRowMapper();

	public FreeBoardRowMapper getFreeBoardRowMapper() {
		return freeBoardRowMapper;
	}

	private class FreeBoardRowMapper implements RowMapper<FreeBoard> {

		@Override
		public FreeBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
			FreeBoard f = new FreeBoard();
			f.setFrbNo(rs.getInt("no"));
			f.setFrbTitle(rs.getString("title"));
			f.setFrbPass(rs.getString("pass"));
			f.setFrbContent(rs.getString("content"));
			f.setPhoto1(rs.getString("photo"));
			f.setFrbWriteDate(rs.getTimestamp("writeDate"));
			f.setFrbHit(rs.getInt("hit"));
			f.setFrbArea(rs.getString("area"));
			f.setFrbEmail(rs.getString("email"));
			f.setFrbWriter(rs.getString("writer"));
			f.setFrbComment(rs.getInt("comm"));

			return f;
		}
	}

	private FreeBoardResultSetExtractor freeBoardResultSetExtractor = new FreeBoardResultSetExtractor();

	public FreeBoardResultSetExtractor getFreeBoardResultSetExtractor() {
		return freeBoardResultSetExtractor;
	}

	private class FreeBoardResultSetExtractor implements ResultSetExtractor<FreeBoard> {
		@Override
		public FreeBoard extractData(ResultSet rs) throws SQLException, DataAccessException {
			if (rs.next()) {
				FreeBoard f = new FreeBoard();
				f.setFrbNo(rs.getInt("no"));
				f.setFrbTitle(rs.getString("title"));
				f.setFrbPass(rs.getString("pass"));
				f.setFrbContent(rs.getString("content"));
				f.setPhoto1(rs.getString("photo"));
				f.setFrbWriteDate(rs.getTimestamp("writeDate"));
				f.setFrbHit(rs.getInt("hit"));
				f.setFrbArea(rs.getString("area"));
				f.setFrbEmail(rs.getString("email"));
				f.setFrbWriter(rs.getString("writer"));
				f.setFrbComment(rs.getInt("comm"));

				return f;
			}
			return null;
		}
	}

	private CommentResultSetExtractor commentResultSetExtractor = new CommentResultSetExtractor();

	public CommentResultSetExtractor getCommentResultSetExtractor() {
		return commentResultSetExtractor;
	}

	private class CommentResultSetExtractor implements ResultSetExtractor<Comment> {
		@Override
		public Comment extractData(ResultSet rs) throws SQLException, DataAccessException {
			if (rs.next()) {
				Comment c = new Comment();
				c.setbNo(rs.getInt("bno"));
				c.setcContent(rs.getString("comment"));
				c.setWriteDate(rs.getTimestamp("writedate"));
				c.setcNo(rs.getInt("no"));
				c.setcEmail(rs.getString("email"));
				c.setcWriter(rs.getString("nickname"));

				return c;
			}
			return null;
		}
	}

	private CommentRowMapper commentRowMapper = new CommentRowMapper();

	public CommentRowMapper getCommentRowMapper() {
		return commentRowMapper;
	}

	private class CommentRowMapper implements RowMapper<Comment> {
		@Override
		public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
			Comment c = new Comment();
			c.setbNo(rs.getInt("bno"));
			c.setcContent(rs.getString("comment"));
			c.setcNo(rs.getInt("no"));
			c.setWriteDate(rs.getTimestamp("writedate"));
			c.setcEmail(rs.getString("email"));
			c.setcWriter(rs.getString("nickname"));
		
			c.setcPhoto(rs.getString("photo"));

			return c;
		}
	}

	private AskBoardRowMapperResultSetExtractor askBoardRowMapperResultSetExtractor = new AskBoardRowMapperResultSetExtractor();
	
	public AskBoardRowMapperResultSetExtractor getAskBoardRowMapperResultSetExtractor() {
		return askBoardRowMapperResultSetExtractor;
	}
	
	private class AskBoardRowMapperResultSetExtractor implements RowMapper<AskBoard>, ResultSetExtractor<AskBoard> {
		@Override
		public AskBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
			AskBoard ab = new AskBoard();
			ab.setAbNickName(rs.getString("nickname"));
			ab.setAbNo(rs.getInt("asknumber"));
			ab.setAbToid(rs.getString("toid"));
			ab.setAbFightDate(rs.getDate("fightdate"));
			ab.setAbApproval(rs.getInt("approval"));
			ab.setAbPlace(rs.getString("place"));
			ab.setAbWriteDate(rs.getDate("writedate"));
			ab.setAbTell(rs.getString("tell"));
			ab.setAbEmail(rs.getString("email"));
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

		@Override
		public AskBoard extractData(ResultSet rs) throws SQLException, DataAccessException {
			if (rs.next()) {
				AskBoard ab = new AskBoard();
				ab.setAbNickName(rs.getString("nickname"));
				ab.setAbNo(rs.getInt("asknumber"));
				ab.setAbToid(rs.getString("toid"));
				ab.setAbFightDate(rs.getDate(("fightdate")));
				ab.setAbApproval(rs.getInt("approval"));
				ab.setAbPlace(rs.getString("place"));
				ab.setAbWriteDate(rs.getDate(("writedate")));
				ab.setAbTell(rs.getString("tell"));
				ab.setAbEmail(rs.getString("email"));
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
			return null;
		}
	}
}
