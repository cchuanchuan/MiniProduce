package dao;

import java.util.ArrayList;

import model.Book;
import model.Reader;

public class AdminUtils {
	private ArrayList<Book> booklist;//书本数据
	private ArrayList<Reader> readerlist;//读者信息
	public AdminUtils() {
		this.booklist = FileUtils.readBook();
		this.readerlist = FileUtils.ReadReader();
		if(booklist == null) {
			booklist = new ArrayList<Book>();
		}
		if(readerlist == null) {
			readerlist = new ArrayList<Reader>();
		}
	}
	//增加读者
	public boolean addReader(Reader reader) {
		this.readerlist.add(reader);
		return FileUtils.WriteReader(this.readerlist);
	}
	//查询读者
	public Reader checkReader(String readerno) {
		for(Reader reader:this.readerlist) {
			if(reader.getReaderno().trim().equals(readerno)) {
				return reader;
			}
		}
		return null;
	}
	//删除读者
	public boolean deleteReader(String readerno) {
		for(Reader reader:this.readerlist) {
			if(reader.getReaderno().trim().equals(readerno)) {
				readerlist.remove(reader);
				return FileUtils.WriteReader(readerlist);
			}
		}
		return false;
	}
	//增加书本
	public boolean addBook(Book book) {
		this.booklist.add(book);
		return FileUtils.WriteBook(this.booklist);
	}
	//删除书本
	public boolean deleteBook(String bookno) {
		for(Book book:this.booklist) {
			if(book.getBookno().trim().equals(bookno)) {
				booklist.remove(book);
				return FileUtils.WriteBook(booklist);
			}
		}
		return false;
	}
	
	public ArrayList<Book> getBookList(){
		return this.booklist;
	}
	
	public ArrayList<Reader> getReaderList(){
		return this.readerlist;
	}
	//通过书本编号得到书本
	public Book getBookByNo(String bookno) {
		for(Book book:this.booklist) {
			if(book.getBookno().trim().equals(bookno)) {
				return book;
			}
		}
		return null;
	}
	//通过关键字查询书本信息
	public ArrayList<Book> getBookByKeyWord(String bookno) {
		ArrayList<Book> templist = new ArrayList<Book>();
		for(Book book:this.booklist) {
			if(book.getBookname().indexOf(bookno)!=-1) {
				templist.add(book);
			}
		}
		return templist;
	}
	//通过书本名字查询书本信息
	public Book getBookByName(String bookname) {
		for(Book book:this.booklist) {
			if(book.getBookname().equals(bookname)) {
				return book;
			}
		}
		return null;
	}
	//通过关键字作者书本信息
	public ArrayList<Book> getBookByAuthor(String author) {
		ArrayList<Book> templist = new ArrayList<Book>();
		for(Book book:this.booklist) {
			if(book.getBookauthor().equals(author)) {
				templist.add(book);
			}
		}
		return templist;
	}
	//通过出版社查询书本信息
	public ArrayList<Book> getBookByPress(String press) {
		ArrayList<Book> templist = new ArrayList<Book>();
		for(Book book:this.booklist) {
			if(book.getBookpress().equals(press)) {
				templist.add(book);
			}
		}
		return templist;
	}
}
