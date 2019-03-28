package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Book;
import model.Reader;

public class FileUtils {
	private static File booksave = new File("file//book.txt");
	private static File readersave = new File("file//reader.txt");
	//判断读者用户名密码是否正确
	public static boolean isReader(String no,String password) {
		ArrayList<Reader> readerlist = FileUtils.ReadReader();
		for(Reader reader:readerlist) {
			if(reader.getReaderno().equals(no)) {
				if(reader.getReaderpassword().equals(password)) {
					return true;
				}
			}
		}
		return false;
	}
	//读取书本数据
	public static ArrayList<Book> readBook(){
		File file = new File("file//book.txt");
		try {
			FileInputStream fin = new FileInputStream(file.getAbsolutePath());
			ObjectInputStream oin = new ObjectInputStream(fin);
			ArrayList<Book> booklist = (ArrayList<Book>) oin.readObject();
			oin.close();
			fin.close();
			return booklist;
		} catch (IOException | ClassNotFoundException e1) {
			System.out.println("error"+e1.getMessage());
		}
		return null;
	}
	//读取读者信息
	public static ArrayList<Reader> ReadReader(){
		File file = new File("file//reader.txt");
		try {
			FileInputStream fin = new FileInputStream(file.getAbsolutePath());
			ObjectInputStream oin = new ObjectInputStream(fin);
			ArrayList<Reader> readerlist = (ArrayList<Reader>) oin.readObject();
			oin.close();
			fin.close();
			return readerlist;
		} catch (IOException | ClassNotFoundException e1) {
			System.out.println("error"+e1.getMessage());
		}
		return null;
	}
	//写入书本数据
	public static boolean WriteBook(ArrayList<Book> booklist) {
		File file = new File("file//book.txt");
		try {
			FileOutputStream fout = new FileOutputStream(file.getAbsolutePath());
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(booklist);
			oout.close();
			fout.close();
			return true;
		} catch (IOException e1) {
			System.out.println("error"+e1.getMessage());
		}
		return false;
	}
	//写入读者信息
	public static boolean WriteReader(ArrayList<Reader> readerlist) {
		File file = new File("file//reader.txt");
		try {
			FileOutputStream fout = new FileOutputStream(file.getAbsolutePath());
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(readerlist);
			oout.close();
			fout.close();
			return true;
		} catch (IOException e1) {
			System.out.println("error"+e1.getMessage());
		}
		return false;
	}

}
