package com.bookmanage.member;

public class Book {
private int bookNumber;
private String bookName;
private String bookCompany;
private String author;
private String price;
private String photo;

public Book(String bookName, String author, String bookCompany, String price, String photo){
	this.bookName=bookName;
	this.author=author;
	this.bookCompany=bookCompany;
	this.price=price;
	this.photo=photo;
}

public Book(String bookName, String author, String bookCompany, String price, int BookNum,String photo){
	this.bookName=bookName;
	this.author=author;
	this.bookCompany=bookCompany;
	this.price=price;
	this.bookNumber=BookNum;
	this.photo = photo;
}

public Book(String bookName, String author, String bookCompany, String price, int BookNum){
	this.bookName=bookName;
	this.author=author;
	this.bookCompany=bookCompany;
	this.price=price;
	this.bookNumber=BookNum;
}

public Book() {
	
}

public int getBookNumber() {
	return bookNumber;
}
public void setBookNumber(int bookNumber) {
	this.bookNumber = bookNumber;
}
public String getBookName() {
	return bookName;
}
public void setBookName(String bookName) {
	this.bookName = bookName;
}
public String getBookCompany() {
	return bookCompany;
}
public void setBookCompany(String bookCompany) {
	this.bookCompany = bookCompany;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public String getPhoto() {
	return photo;
}
public void setPhoto(String photo) {
	this.photo = photo;
}
}
