package adminpackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import model.Product;
import model.ProductMaterial;
import model.ProductType;
import model.Staff;
import model.Tender;

public class AdminFunction {
	private Staff staff;
	private Map<String,Staff> staffs;
	File stafffile;
	public AdminFunction() {
		File directory = new File("");
		this.stafffile = new File(directory.getAbsolutePath()+"\\src\\FileStore\\Staff.txt");
		FileInputStream fin;
		try {
			fin = new FileInputStream(stafffile.getAbsolutePath());
			ObjectInputStream oin=new ObjectInputStream(fin);
			this.staffs =  (Map<String, Staff>) oin.readObject();
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File Not Found , Please Check Stuff.txt File");
			this.staffs = new HashMap<String,Staff>();
		}
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Map<String, Staff> getStaffs() {
		return staffs;
	}
	public void setStaffs(Map<String, Staff> staffs) {
		this.staffs = staffs;
	}
	public File getStafffile() {
		return stafffile;
	}
	public void setStafffile(File stafffile) {
		this.stafffile = stafffile;
	}
	public AdminFunction(Staff staff, Map<String, Staff> staffs, File stafffile) {
		super();
		this.staff = staff;
		this.staffs = staffs;
		this.stafffile = stafffile;
	}
	@Override
	public String toString() {
		return "AdminiFunction [staff=" + staff + ", staffs=" + staffs + ", stafffile=" + stafffile + "]";
	}
	
	
}
