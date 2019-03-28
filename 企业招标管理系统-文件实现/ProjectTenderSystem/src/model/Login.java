package model;

import java.io.Serializable;
import java.util.Date;

public class Login implements Serializable{
	private String loginnumber;
	private Date logintime;
	private String staffnumber;
	public String getLoginnumber() {
		return loginnumber;
	}
	public void setLoginnumber(String loginnumber) {
		this.loginnumber = loginnumber;
	}
	public Date getLogintime() {
		return logintime;
	}
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}
	public String getStaffnumber() {
		return staffnumber;
	}
	public void setStaffnumber(String staffnumber) {
		this.staffnumber = staffnumber;
	}
	@Override
	public String toString() {
		return "Login [loginnumber=" + loginnumber + ", logintime=" + logintime + ", staffnumber=" + staffnumber + "]";
	}
	public Login(String loginnumber, Date logintime, String staffnumber) {
		super();
		this.loginnumber = loginnumber;
		this.logintime = logintime;
		this.staffnumber = staffnumber;
	}
	

}
