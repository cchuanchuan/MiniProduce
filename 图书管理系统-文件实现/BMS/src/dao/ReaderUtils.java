package dao;

import java.util.ArrayList;

import model.Book;
import model.Reader;

public class ReaderUtils {
	private ArrayList<Book> booklist;
	private ArrayList<Reader> readerlist;
	public ReaderUtils() {
		this.booklist = FileUtils.readBook();
		this.readerlist = FileUtils.ReadReader();
		if(booklist == null) {
			booklist = new ArrayList<Book>();
		}
		if(readerlist == null) {
			readerlist = new ArrayList<Reader>();
		}
	}
	
	public boolean borrowBook(String bookno,String readerno) {
		Boolean isborrow = false;//书本是否存在
		for(Book book:this.booklist) {
			if(book.getBookno().equals(bookno)) {
				if(book.getBooknumber() >= 1) {
					isborrow = true;
					book.setBooknumber(book.getBooknumber()-1);
				}
			}
		}
		Boolean isreader = false;//读者是否存在
		if(isborrow) {
			for(Reader reader:this.readerlist) {
				if(reader.getReaderno().equals(readerno)) {
					reader.getReaderborrow().add(bookno);
					isreader = true;
				}
			}
		}
		return isreader&&isborrow&&FileUtils.WriteBook(booklist)&&FileUtils.WriteReader(readerlist);
	}
	//通过读者好获取读者借阅书本信息
	public ArrayList<Book> getBorrowBook(String readerno){
		ArrayList<Book> templist = new ArrayList<Book>();
		ArrayList<String> stringlist = new ArrayList<String>();
		for(Reader reader:this.readerlist) {
			if(reader.getReaderno().equals(readerno)) {
				stringlist = reader.getReaderborrow();
				break;
			}
		}
		for(Book book:this.booklist) {
			for(String str:stringlist) {
				if(book.getBookno().equals(str)) {
					templist.add(book);
					stringlist.remove(str);
					break;
				}
			}
		}
		return templist;
	}
	
	//归还图书
	public boolean returnBook(String bookno,String readerno){
		
		Boolean isreader = false;//读者是否借阅此书
		for(Reader reader:this.readerlist) {
			if(reader.getReaderno().equals(readerno)) {
				isreader = reader.getReaderborrow().remove(bookno);
				break;
			}
		}
		Boolean isborrow = false;//书本是否存在
		if(isreader) {
			for(Book book:this.booklist) {
				if(book.getBookno().equals(bookno)) {
					book.setBooknumber(book.getBooknumber()+1);
					isborrow = true;
				}
			}
		}
		
		return isreader&&isborrow&&FileUtils.WriteBook(booklist)&&FileUtils.WriteReader(readerlist);
	}
	
	
}
