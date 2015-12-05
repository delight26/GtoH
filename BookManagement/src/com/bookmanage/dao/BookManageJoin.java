package com.bookmanage.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bookmanage.member.Member;

public class BookManageJoin {

	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;

	private static BookManageJoin mDao = new BookManageJoin();

	private BookManageJoin() {

	}

	public static BookManageJoin getInstance() {
		if (mDao == null) {
			mDao = new BookManageJoin();
		}
		return mDao;
	}

	public void getMemberAdd(Member member) {
		String insert = "insert into bookmember values (?,?,?,?,?,?,?,member_seq.nextval)";
		
		try {
			conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(insert);

			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getBirthday());
			pstmt.setString(6, member.getJob());
			pstmt.setString(7, member.getAdress());
			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}
}
