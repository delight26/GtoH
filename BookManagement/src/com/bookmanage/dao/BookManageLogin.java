package com.bookmanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bookmanage.member.Member;

public class BookManageLogin {
	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	private static BookManageLogin mDao = new BookManageLogin();

	private BookManageLogin() {

	}
	public static BookManageLogin getInstance() {
		if (mDao == null) {
			mDao = new BookManageLogin();
		}
		return mDao;
	}

	public ArrayList<Member> getLogin() {
		String login = "select id, pass from bookmember";
		
		ArrayList<Member> loginList = new ArrayList<Member>();

		try {
			conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(login);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString(1);
				String pass = rs.getString(2);
				Member mem = new Member(id, pass);
				loginList.add(mem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return loginList;
	}
	public Member getMember(String id) {
		String login = "select * from bookmember where id=?";
		Member mem = null;
		try {
			conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(login);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String id1 = rs.getString(1);
				String pass = rs.getString(2);
				String name = rs.getString(3);
				String phone = rs.getString(4);
				String birthday = rs.getString(5);
				String job = rs.getString(6);
				String adress = rs.getString(7);
				mem = new Member(id1, pass, name, phone, birthday, job, adress);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return mem;
	}
}
