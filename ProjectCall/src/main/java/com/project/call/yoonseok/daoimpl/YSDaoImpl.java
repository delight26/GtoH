package com.project.call.yoonseok.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.project.call.daomapper.DaoMapper;
import com.project.call.domain.Member;
import com.project.call.domain.NoticeBoard;
import com.project.call.yoonseok.dao.YSDao;

@Repository
public class YSDaoImpl implements YSDao {

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
   public List<Member> ranking() {
      return namedParameterJdbcTemplate.query("SELECT @RNUM:=@RNUM + 1 AS rank, t.* FROM "
      		+ "(SELECT * FROM member where email not like '삭제된계정입니다%' ORDER BY accpoint desc  ) t, (SELECT @RNUM := 0) R", 
            new RowMapper<Member>() {
         @Override
         public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member m = new Member();
            m.setRank(rs.getString("rank"));
            m.setAddr(rs.getString("address"));
            m.setEmail(rs.getString("email"));
            m.setLevel(rs.getString("level"));
            m.setName(rs.getString("name"));
            m.setNickName(rs.getString("nickname"));
            m.setPass(rs.getString("pass"));
            m.setPhone(rs.getString("phone"));
            m.setPoint(rs.getInt("accpoint"));
            m.setProfilPhoto(rs.getString("photo"));
            
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
      // open <- 안읽음0 읽음1
      SqlParameterSource pram = new MapSqlParameterSource()
    		  .addValue("toid", note.getNbToid())
            .addValue("title", note.getNbTitle())
            .addValue("content", note.getNbContent())
            .addValue("email", note.getNbEmail())
            .addValue("time", new Timestamp(System.currentTimeMillis()));
      namedParameterJdbcTemplate.update("insert into note(toid, title, content, writeDate, email, opennote)"
            + " value(:toid, :title, :content, :time , :email, 0)", pram);

   }

   @Override
   public List<NoticeBoard> getNote(String toid, int pageNum) {
      
      int count = jdbcTemplate.queryForObject("select count(*) from note where toid = ?",
            Integer.class, toid);
      int start =0;
      int end = 5;
      if(pageNum == 1){
         start = 0;
      }else if(pageNum == 2){
         start = 5;
      }else{
         start = pageNum*5-5;
      }
      return namedParameterJdbcTemplate.query(
    		  "select (select Ceil(count(*)/5) from note where toid = :toid) count, n.*, m.nickname,"
    		  + " (select count(*) from note where toid = :toid) size from "
    		  + "(select * from note where toid =:toid ) n inner "
            + "join member m on n.email = m.email "
            + "where toid = :toid order by n.noteNumber desc limit :start, :end" ,
            new MapSqlParameterSource()
            .addValue("toid", toid)
            .addValue("start", start)
            .addValue("end", end),

            new RowMapper<NoticeBoard>() {

               @Override
               public NoticeBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
                  NoticeBoard n = new NoticeBoard();
                  n.setSize(rs.getInt("size"));
                  n.setNbClick(rs.getInt("opennote"));
                  n.setNbContent(rs.getString("content"));
                  n.setNbDate(rs.getTimestamp("writeDate"));
                  n.setNbNickName(rs.getString("nickname"));
                  n.setNbEmail(rs.getString("email"));
                  n.setNbNo(rs.getInt("noteNumber"));
                  n.setNbTitle(rs.getString("title"));
                  n.setNbToid(rs.getString("toid"));
                  n.setNbMaxPage(rs.getInt("count"));
                 
				return n;
               }
            });
   }
	


   @Override
   public NoticeBoard noteContent(int nbNo, String check) {
	   
	   if(check.equals("content")){
		   System.out.println("content? "+ check);
      namedParameterJdbcTemplate.update("update note set opennote = 1 where noteNumber = :noteNumber",
            new MapSqlParameterSource().addValue("noteNumber", nbNo));
	   }
      return namedParameterJdbcTemplate.query(
            "select n.*, m.nickname from note n inner join member m on n.email = m.email where noteNumber=:noteNumber",
            new MapSqlParameterSource().addValue("noteNumber", nbNo), new ResultSetExtractor<NoticeBoard>() {

               @Override
               public NoticeBoard extractData(ResultSet rs) throws SQLException, DataAccessException {
                  NoticeBoard n = new NoticeBoard();
                  if (rs.next()) {
                     n.setNbNickName(rs.getString("nickname"));
                     System.out.println("dao "+rs.getString("nickname"));
                     n.setNbClick(rs.getInt("opennote"));
                     n.setNbContent(rs.getString("content"));
                     n.setNbDate(rs.getTimestamp("writeDate"));
                     n.setNbEmail(rs.getString("email"));
                     n.setNbNo(nbNo);
                     n.setNbTitle(rs.getString("title"));
                     n.setNbToid(rs.getString("toid"));

                  }
                  return n;
               }
            });
   }

   @Override
   public void deleteNote(int nbNo) {
      namedParameterJdbcTemplate.update("delete from note where notenumber = :notenumber;",
            new MapSqlParameterSource().addValue("notenumber", nbNo));

   }

	@Override
	public int noteCheck(String toid) {
		int open =namedParameterJdbcTemplate.queryForObject("select count(*) from note where toid =:toid and opennote = 0",
				new MapSqlParameterSource().addValue("toid", toid), Integer.class);
		return open;
	}

	@Override
	public List<String> nickNameSearch(String nickName) {
		return namedParameterJdbcTemplate.query("select nickname from member "
				+ "where nickname like :nickname", 
				new MapSqlParameterSource().addValue("nickname", "%"+nickName+"%"),
				new RowMapper<String>() {

					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					
						return rs.getString("nickname");
						
					}
				});
	}
	
	@Override
	public int getCount(String toId) {
		String sql = "select count(*) from note where toid = :toId ";
		SqlParameterSource namedParam = 
				new MapSqlParameterSource("toId", toId);					
		return namedParameterJdbcTemplate.query(sql, namedParam, new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				return rs.getInt(1);
			}
		});
	}



   

   @Override
   public List<NoticeBoard> getNoticeBoard(String toId, int startRow, int endRow) {
      String sql = "select (select Ceil(count(*)/5) from note where toid = :toid) count, n.*, m.nickname, count(*) size from note n inner "
            + "join member m on n.email = m.email "
            + "where toid = :toid limit :start, :end";
      SqlParameterSource namedParam = 
            new MapSqlParameterSource("toId", toId)               
                     .addValue("start", startRow)
                     .addValue("end", endRow);
      return namedParameterJdbcTemplate.query(sql, namedParam ,new RowMapper<NoticeBoard>() {
               @Override
               public NoticeBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
                  NoticeBoard n = new NoticeBoard();
                  n.setSize(rs.getInt("size"));
                  n.setNbClick(rs.getInt("opennote"));
                  n.setNbContent(rs.getString("content"));
                  n.setNbDate(rs.getTimestamp("writeDate"));
                  n.setNbNickName(rs.getString("nickname"));
                  n.setNbEmail(rs.getString("email"));
                  n.setNbNo(rs.getInt("noteNumber"));
                  n.setNbTitle(rs.getString("title"));
                  n.setNbToid(rs.getString("toid"));
                  n.setNbMaxPage(rs.getInt("count"));
                  return n;
               }
            });
      
   }
   
   @Override
   public Member getMember(String toId) {
      String sql = "select * from member where email = :email ";
      SqlParameterSource namedParam = 
            new MapSqlParameterSource("email", toId);

      return namedParameterJdbcTemplate.query(sql, namedParam, mapper.getMemberResultSetExtractor());

   }

@Override
public List<NoticeBoard> sendNote(String email, int pageNum) {

    int count = jdbcTemplate.queryForObject("select count(*) from note where email = ?",
          Integer.class, email);
    int start =0;
    int end = 5;
    if(pageNum == 1){
       start = 0;
    }else if(pageNum == 2){
       start = 5;
    }else{
       start = pageNum*5-5;
    }
    return namedParameterJdbcTemplate.query(
  		  "select (select Ceil(count(*)/5) from note where email= :email) count, n.*, m.nickname from note n inner"
  		  + " join member m on n.email = m.email  where n.email = :email order by notenumber desc limit :start, :end"  ,
          new MapSqlParameterSource()
          .addValue("email", email)
          .addValue("start", start)
          .addValue("end", end),

          new RowMapper<NoticeBoard>() {

             @Override
             public NoticeBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
                NoticeBoard n = new NoticeBoard();
                
                n.setNbClick(rs.getInt("opennote"));
                n.setNbContent(rs.getString("content"));
                n.setNbDate(rs.getTimestamp("writeDate"));
                n.setNbNickName(rs.getString("nickname"));
                n.setNbEmail(rs.getString("email"));
                n.setNbNo(rs.getInt("noteNumber"));
                n.setNbTitle(rs.getString("title"));
                n.setNbToid(rs.getString("toid"));
                n.setNbMaxPage(rs.getInt("count"));
               
				return n;
             }
          });
}

@Override
public Member modalSearch(String nickName) {
	
	return jdbcTemplate.queryForObject("select m.* from (select  @RNUM:=@RNUM + 1 "
			+ "AS rank, t.* FROM(SELECT * FROM member ORDER BY accpoint desc) t,"
			+ "(SELECT @RNUM := 0) R) m where nickname= ?",
			
			new RowMapper<Member>() {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
           Member m = new Member();
           m.setRank(rs.getString("rank"));
           m.setAddr(rs.getString("address"));
           m.setEmail(rs.getString("email"));
           m.setLevel(rs.getString("level"));
           m.setName(rs.getString("name"));
           m.setNickName(rs.getString("nickname"));
           m.setPass(rs.getString("pass"));
           m.setPhone(rs.getString("phone"));
           m.setPoint(rs.getInt("accpoint"));
           m.setProfilPhoto(rs.getString("photo"));
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
     },nickName );
}
   
   
}
