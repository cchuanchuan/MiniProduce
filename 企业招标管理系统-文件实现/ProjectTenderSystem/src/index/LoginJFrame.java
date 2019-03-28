package index;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import adminpackage.AdminJFrame;
import managerpackage.ManagerJFrame;
import model.Login;
import model.Staff;
import officerpackage.OfficerJFrame;

public class LoginJFrame extends JFrame implements ActionListener {
	private JTextField staffnumber;
	private JPasswordField password;
	private JButton button_login,button_cancel;
	private Map<String,Staff> staffs;
	private File stafffile;
	public LoginJFrame() {
		super("Login Tender System");
		this.setSize(400, 180);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		File directory = new File("");
		
		this.stafffile = new File(directory.getAbsolutePath()+"\\src\\FileStore\\Staff.txt");
		FileInputStream fin;
		try {
			fin = new FileInputStream(stafffile.getAbsolutePath());
			ObjectInputStream oin=new ObjectInputStream(fin);
			this.staffs = (Map<String, Staff>) oin.readObject();
		} catch (IOException |ClassNotFoundException e) {
			Staff staff = new Staff();
			staff.setStaffnumber("000001");
			staff.setPassword("12345");
			staff.setStaffname("Admin");
			staff.setStaffaddress("Earth");
			staff.setStaffcontactnumber("11111111111");
			staff.setStaffrole(Staff.SystemAdmin);
			this.staffs = new HashMap<String,Staff>();
			this.staffs.put(staff.getStaffnumber(), staff);
			try {
				FileOutputStream fout = new FileOutputStream(stafffile.getAbsolutePath());
				ObjectOutputStream oout = new ObjectOutputStream(fout);
				oout.writeObject(this.staffs);
				
				JOptionPane.showMessageDialog(null, "File Not Found , AutoCreate SystemAdmin object"+"\n"
						+"staffnumber=000001"+"\n"
						+"staffpassword=12345"+"\n"
						+"staffname=Admin"+"\n"
						+"staffaddress=Earth"+"\n"
						+"contactnumber=11111111111"+"\n"
						+"staffrole=SystemAdmin"+"\n"
						+"You can login by this account");
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,"File Not Found,Please Create File First!");
			}
			
		}
		
		this.setLayout(new GridLayout(3,2));
		this.add(new JLabel("StuffNumber"));
		this.staffnumber=new JTextField(10);
		this.add(this.staffnumber);
		this.add(new JLabel("PassWord"));
		this.password=new JPasswordField(10);
		this.add(this.password);
		
		button_login=new JButton("Login");
		button_cancel=new JButton("Cancel");
		this.add(this.button_cancel);
		this.add(this.button_login);
		button_login.addActionListener(this);
		button_cancel.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Login")) {
			if(!this.staffnumber.getText().equals("")
					&&!this.password.getText().equals("")) {
				String number = this.staffnumber.getText();
				String passw = this.password.getText();
				if(this.staffs.get(number) != null 
						&& this.staffs.get(number).getPassword().equals(passw)) {
					Staff loginstaff = this.staffs.get(number);
					String role = "";
					if(loginstaff.getStaffrole().equals(Staff.Manager)) {
						new ManagerJFrame();
						role = "Manager";
					}else if(loginstaff.getStaffrole().equals(Staff.SystemAdmin)) {
						new AdminJFrame();
						role = "SystemAdmin";
					}else if(loginstaff.getStaffrole().equals(Staff.TenderingOfficer)) {
						new OfficerJFrame();
						role = "TenderingOfficer";
					}
					String loginnumber = UUID.randomUUID().toString().replace("-", "").toLowerCase();
					Date logintime = new Date();
					String staffnumber = loginstaff.getStaffnumber();
					Login log = new Login(loginnumber, logintime, staffnumber);
					
					File directory = new File("");
					File loginfile = new File(directory.getAbsolutePath()+"\\src\\FileStore\\Login.txt");
					
					List<Login> loginlist = new LinkedList<Login>();
					
					try {
						FileInputStream fin = new FileInputStream(loginfile.getAbsolutePath());
						ObjectInputStream oin = new ObjectInputStream(fin);
						loginlist = (List<Login>) oin.readObject();
					} catch (IOException | ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null,"There is no Login,Create new Login File");
					}
					loginlist.add(log);
					
					try {
						FileOutputStream fout = new FileOutputStream(loginfile.getAbsolutePath());
						ObjectOutputStream oout = new ObjectOutputStream(fout);
						oout.writeObject(loginlist);
						JOptionPane.showMessageDialog(null,"Welcome To Login Project Tender System"+"\n"
								+"Your Role Is "+role+"\n"
								+"Login Log Add Success");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					this.dispose();
				}else {
					JOptionPane.showMessageDialog(null,"StaffNumber Or Password Wrong,Please Input Again");
				}
			}else {
				JOptionPane.showMessageDialog(null,"Please Input Number And Password");
			}
			
		}
		
		if(e.getActionCommand().equals("Cancel")) {
			System.exit(0);
		}
		
	}
	
	public static void main(String[]agr) {
		new LoginJFrame();
	}

}