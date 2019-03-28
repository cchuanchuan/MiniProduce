/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

public class Staff implements Serializable{
    public static final String SystemAdmin = "SystemAdmin";
    public static final String Manager = "Manager";
    public static final String TenderingOfficer = "TenderOfficer";
    private String staffnumber;
    private String password;
    private String staffname;
    private String staffaddress;
    private String staffcontactnumber;
    private String staffrole;
    private String staffintroduction;
	
	public Staff(String staffnumber, String password, String staffname, String staffaddress, String staffcontactnumber,
			String staffrole, String staffintroduction) {
		super();
		this.staffnumber = staffnumber;
		this.password = password;
		this.staffname = staffname;
		this.staffaddress = staffaddress;
		this.staffcontactnumber = staffcontactnumber;
		this.staffrole = staffrole;
		this.staffintroduction = staffintroduction;
	}

	public String getStaffnumber() {
		return staffnumber;
	}

	public void setStaffnumber(String staffnumber) {
		this.staffnumber = staffnumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStaffname() {
		return staffname;
	}

	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}

	public String getStaffaddress() {
		return staffaddress;
	}

	public void setStaffaddress(String staffaddress) {
		this.staffaddress = staffaddress;
	}

	public String getStaffcontactnumber() {
		return staffcontactnumber;
	}

	public void setStaffcontactnumber(String staffcontactnumber) {
		this.staffcontactnumber = staffcontactnumber;
	}

	public String getStaffrole() {
		return staffrole;
	}

	public void setStaffrole(String staffrole) {
		this.staffrole = staffrole;
	}

	public String getStaffintroduction() {
		return staffintroduction;
	}

	public void setStaffintroduction(String staffintroduction) {
		this.staffintroduction = staffintroduction;
	}

	public static String getSystemadmin() {
		return SystemAdmin;
	}

	public static String getManager() {
		return Manager;
	}

	public static String getTenderingofficer() {
		return TenderingOfficer;
	}
	
	@Override
	public String toString() {
		return "Staff [staffnumber=" + staffnumber + ", password=" + password + ", staffname=" + staffname
				+ ", staffaddress=" + staffaddress + ", staffcontactnumber=" + staffcontactnumber + ", staffrole="
				+ staffrole + ", staffintroduction=" + staffintroduction + "]";
	}

	public Staff() {
		super();
	}
	
    
}
