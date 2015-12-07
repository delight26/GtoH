package com.bookmanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bookmanage.member.Book;
import com.bookmanage.member.Member;

public class BookDao {
	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;

	private static BookDao mDao = new BookDao();

	public BookDao() {

	}

	public static BookDao getInstance() {
		if (mDao == null) {
			mDao = new BookDao();
		}
		return mDao;
	}

	public ArrayList<Book> getBookList() {

		String select = "select * from bookmenage";
		ArrayList<Book> bookList = new ArrayList<Book>();

		try {
			conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(select);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String bookName = rs.getString(1);
				String author = rs.getString(2);
				String bookCompany = rs.getString(3);
				String price = rs.getString(4);
				int BookNum = rs.getInt(5);
				String photo = rs.getString(6);
				Book book = new Book(bookName, author, bookCompany, price, BookNum, photo);
				bookList.add(book);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return bookList;
	}

	public void getaddBook(Book book) {
		String insert = "insert into bookmenage values (?,?,?,?,booknum_seq.nextval,?)";

		try {
			conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(insert);

			pstmt.setString(1, book.getBookName());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getBookCompany());
			pstmt.setString(4, book.getPrice());
			pstmt.setString(5, book.getPhoto());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	public Book getBookContent(int bookNumber) {
		Book book = null;
		String select = "select * from bookmenage where booknum = ?";
		try {
			conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(select);
			pstmt.setInt(1, bookNumber);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String bookName = rs.getString(1);
				String author = rs.getString(2);
				String bookCompany = rs.getString(3);
				String price = rs.getString(4);
				int BookNum = rs.getInt(5);
				String Photo = rs.getString(6);
				book = new Book(bookName, author, bookCompany, price, BookNum, Photo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return book;
	}

	public void getBookDelete(String bookNumber) {
		String delete = "delete bookmenage where booknum = ?";

		try {
			conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(delete);
			pstmt.setString(1, bookNumber);
			pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}
	public void getBookUpdate(Book book){
		String update="update bookmenage set bookname = ?, author = ?, company = ?, price = ?, photo =? where booknum=?";
		try {
			conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(update);
			pstmt.setString(1, book.getBookName());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getBookCompany());
			pstmt.setString(4, book.getPrice());
			pstmt.setString(5, book.getPhoto());
			pstmt.setInt(6, book.getBookNumber());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}
	public ArrayList<Book> getBookSearch(String noticeSearch) {

	String select = "select * from bookmenage where bookname like ?";
	ArrayList<Book> noticeList = new ArrayList<Book>();
	try {
		conn = DBManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(select);
		pstmt.setString(1, "%" + noticeSearch.toLowerCase() + "%");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			String bookName = rs.getString(1);
			String author = rs.getString(2);
			String bookCompany = rs.getString(3);
			String price = rs.getString(4);
			int BookNum = rs.getInt(5);
			String Photo = rs.getString(6);
			Book book = new Book(bookName, author, bookCompany, price, BookNum, Photo);
			noticeList.add(book);
		}
		pstmt = conn.prepareStatement(select);
		pstmt.setString(1, "%" + noticeSearch.toUpperCase() + "%");
		rs = pstmt.executeQuery();
		while (rs.next()) {
			String bookName = rs.getString(1);
			String author = rs.getString(2);
			String bookCompany = rs.getString(3);
			String price = rs.getString(4);
			int BookNum = rs.getInt(5);
			String Photo = rs.getString(6);
			Book book = new Book(bookName, author, bookCompany, price, BookNum, Photo);
			noticeList.add(book);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		DBManager.close(conn, pstmt, rs);
	}
	return noticeList;
}
}
