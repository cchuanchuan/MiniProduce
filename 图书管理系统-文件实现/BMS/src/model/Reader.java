package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Reader implements Serializable{
	private String readerno;
	private String readername;
	private String readerpassword;
	private ArrayList<String> readerborrow;
	public Reader() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reader(String readerno, String readername, String readerpassword, ArrayList<String> readerborrow) {
		super();
		this.readerno = readerno;
		this.readername = readername;
		this.readerpassword = readerpassword;
		this.readerborrow = readerborrow;
	}
	@Override
	public String toString() {
		return "Reader [readerno=" + readerno + ", readername=" + readername + ", readerpassword=" + readerpassword
				+ ", readerborrow=" + readerborrow + "]";
	}
	public String getReaderno() {
		return readerno;
	}
	public void setReaderno(String readerno) {
		this.readerno = readerno;
	}
	public String getReadername() {
		return readername;
	}
	public void setReadername(String readername) {
		this.readername = readername;
	}
	public String getReaderpassword() {
		return readerpassword;
	}
	public void setReaderpassword(String readerpassword) {
		this.readerpassword = readerpassword;
	}
	public ArrayList<String> getReaderborrow() {
		return readerborrow;
	}
	public void setReaderborrow(ArrayList<String> readerborrow) {
		this.readerborrow = readerborrow;
	}
	
}
