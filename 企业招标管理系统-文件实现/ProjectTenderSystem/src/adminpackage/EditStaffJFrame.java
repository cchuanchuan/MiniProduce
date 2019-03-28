package adminpackage;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Staff;

public class EditStaffJFrame extends JFrame implements ActionListener {
	private JTextField numbertext;
	private JTextField nametext;
	private JTextField passwordtext;
	private JTextField addresstext;
	private JTextField contactnumbertext;
	private JComboBox staffrolebox;
	private JButton buttonconfirm;
	private JButton buttoncancel;
	File file;
	Map<String, Staff> map;
	
	public EditStaffJFrame(Map<String, Staff> staffs, File stafffile, String staffstr) {
		super("Edit Staff JFrame");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(400, 400);
		this.setLayout(new GridLayout(7,2));
		
		Staff staff = staffs.get(staffstr);
		
		this.add(new JLabel("Staff Number*"));
		this.add(this.numbertext = new JTextField());
		this.numbertext.setText(staff.getStaffnumber());
		
		this.add(new JLabel("Staff Name*"));
		this.add(this.nametext = new JTextField());
		this.nametext.setText(staff.getStaffname());
		
		this.add(new JLabel("Staff Password*"));
		this.add(this.passwordtext = new JTextField());
		this.passwordtext.setText(staff.getPassword());
		
		this.add(new JLabel("Staff Address"));
		this.add(this.addresstext = new JTextField());
		this.addresstext.setText(staff.getStaffaddress());
		
		this.add(new JLabel("Staff Contact Number"));
		this.add(this.contactnumbertext = new JTextField());
		this.contactnumbertext.setText(staff.getStaffaddress());
		
		this.add(new JLabel("Staff Role*"));
		this.add(this.staffrolebox = new JComboBox());
		this.staffrolebox.addItem(Staff.Manager);
		this.staffrolebox.addItem(Staff.SystemAdmin);
		this.staffrolebox.addItem(Staff.TenderingOfficer);
		this.staffrolebox.setSelectedItem(staff.getStaffrole());
		this.staffrolebox.setBackground(Color.WHITE);
		
		this.add(this.buttoncancel = new JButton("Cancel"));
		this.add(this.buttonconfirm = new JButton("Confirm"));
		this.buttoncancel.addActionListener(this);
		this.buttonconfirm.addActionListener(this);
		
		this.file = stafffile;
		this.map = staffs;
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Confirm")) {
			if(!this.nametext.getText().equals("")
					&&!this.numbertext.getText().equals("")
					&&!this.passwordtext.getText().equals("") ) {
				Staff staff = new Staff();
				staff.setStaffnumber(this.numbertext.getText());
				staff.setStaffname(this.nametext.getText());
				staff.setPassword(this.passwordtext.getText());
				staff.setStaffaddress(this.addresstext.getText());
				staff.setStaffrole(this.staffrolebox.getSelectedItem().toString());
				staff.setStaffcontactnumber(this.contactnumbertext.getText());
				this.map.put(staff.getStaffnumber(), staff);
				try {
					FileOutputStream fout = new FileOutputStream(this.file.getAbsolutePath());
					ObjectOutputStream oout = new ObjectOutputStream(fout);
					oout.writeObject(this.map);
					JOptionPane.showMessageDialog(null, "Add Success:"+staff);
					this.dispose();
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, "File Not Found,Fail To Add");
					this.dispose();
				}
					
			}else {
				JOptionPane.showMessageDialog(null, "Please Input Number,Name and Password");
			}
			
		}
	}
	
}
