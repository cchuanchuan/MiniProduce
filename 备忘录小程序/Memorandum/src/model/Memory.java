package model;

import java.io.Serializable;
import java.util.Date;

//备忘录信息类，一个Memory对象表示一条备忘录
public class Memory implements Serializable{
	private String date;
	private String site;
	private String content;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Memory [date=" + date + ", site=" + site + ", content=" + content + "]";
	}
	public Memory(String date, String site, String content) {
		super();
		this.date = date;
		this.site = site;
		this.content = content;
	}
	public Memory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
