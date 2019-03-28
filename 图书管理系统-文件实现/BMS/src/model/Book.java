package model;

import java.io.Serializable;

public class Book implements Serializable{
	private String bookno;//书本编号
	private String bookname;//书本名称
	private String bookauthor;//作者名字
	private String bookpress;//出版社
	private int booknumber;
	private String bookcategory;//书本类别
	private String bookintro;//书本简介
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String bookno, String bookname, String bookauthor, String bookpress, int booknumber,
			String bookcategory, String bookintro) {
		super();
		this.bookno = bookno;
		this.bookname = bookname;
		this.bookauthor = bookauthor;
		this.bookpress = bookpress;
		this.booknumber = booknumber;
		this.bookcategory = bookcategory;
		this.bookintro = bookintro;
	}
	@Override
	public String toString() {
		return "Book [bookno=" + bookno + ", bookname=" + bookname + ", bookauthor=" + bookauthor + ", bookpress="
				+ bookpress + ", booknumber=" + booknumber + ", bookcategory=" + bookcategory + ", bookintro="
				+ bookintro + "]";
	}
	public String getBookno() {
		return bookno;
	}
	public void setBookno(String bookno) {
		this.bookno = bookno;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getBookauthor() {
		return bookauthor;
	}
	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}
	public String getBookpress() {
		return bookpress;
	}
	public void setBookpress(String bookpress) {
		this.bookpress = bookpress;
	}
	public int getBooknumber() {
		return booknumber;
	}
	public void setBooknumber(int booknumber) {
		this.booknumber = booknumber;
	}
	public String getBookcategory() {
		return bookcategory;
	}
	public void setBookcategory(String bookcategory) {
		this.bookcategory = bookcategory;
	}
	public String getBookintro() {
		return bookintro;
	}
	public void setBookintro(String bookintro) {
		this.bookintro = bookintro;
	}
	
}
