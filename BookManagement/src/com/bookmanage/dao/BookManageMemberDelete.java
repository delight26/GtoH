package com.bookmanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookManageMemberDelete {
	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;

	private static BookManageMemberDelete mDao = new BookManageMemberDelete();

	private BookManageMemberDelete() {

	}

	public static BookManageMemberDelete getInstance() {
		if (mDao == null) {
			mDao = new BookManageMemberDelete();
		}
		return mDao;
	}
	
	public void getMemberDelete(String id){
		String delete =  "delete bookmember where id = ?";

		
		try {
			conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(delete);
			pstmt.setString(1, id);
			pstmt.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}
}
