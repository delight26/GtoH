package com.bookmanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bookmanage.member.Member;

public class BookManageMemberList {
	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;

	private static BookManageMemberList mDao = new BookManageMemberList();

	private BookManageMemberList() {

	}

	public static BookManageMemberList getInstance() {
		if (mDao == null) {
			mDao = new BookManageMemberList();
		}
		return mDao;
	}
	public ArrayList<Member> getMemberList() {

		String select = "select * from bookmember";
		ArrayList<Member> memberList = new ArrayList<Member>();

		try {
			conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(select);

			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String id = rs.getString(1);
				String pass = rs.getString(2);
				String name = rs.getString(3);
				String phone = rs.getString(4);
				String birthday = rs.getString(5);
				String job = rs.getString(6);
				String adress = rs.getString(7);
				birthday = birthday.substring(0, 10);
				Member member = new Member(id, pass, name, phone, birthday, job, adress);
				memberList.add(member);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return memberList;
	}
}
