package adminpackage;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import managerpackage.BackgroundPanel;
import managerpackage.EditTenderJFrame;
import managerpackage.ManagerFunction;
import managerpackage.ManagerJTree;
import managerpackage.TenderTableModels;
import model.Staff;
import model.Tender;

public class AdminJFrame extends JFrame implements TreeSelectionListener{
	JSplitPane split1;
	JPanel panel_right,panel_left;
	AdminJTree admintree;
	AdminFunction admin = new AdminFunction();
	public AdminJFrame() {
		super("Project Tender System - Admin");
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.split1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		this.split1.setDividerLocation(220);
		split1.setDividerSize(0);
		this.add(split1);
		this.panel_left=new JPanel(new GridLayout(1,1));
		panel_right=new JPanel(new GridLayout(1,1));
		panel_left.setBackground(Color.red);
		split1.setLeftComponent(panel_left);
		split1.setRightComponent(panel_right);
		
		
		this.admintree=new AdminJTree();
		this.panel_left.add(admintree);
		admintree.addTreeSelectionListener(this);
		
		File directory = new File("");
		File logo = new File(directory.getAbsolutePath()+"\\src\\FileStore\\logo.jpg");
		Image imag=new ImageIcon(logo.getAbsolutePath()).getImage();
		panel_right.add(new BackgroundPanel(imag));
		
		
		
		this.setVisible(true);
		System.out.println(panel_right.getSize());
	}
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Add Account]")) {
			new AddAccountJFrame(this.admin.getStaffs(),this.admin.getStafffile());
		}
		
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Delete Account]")) {
			String staffstr = JOptionPane.showInputDialog(null, "Please Input Staff Number");
			if(staffstr != null) {
				Staff staff = this.admin.getStaffs().remove(staffstr);
				if(staff != null) {
					try {
						FileOutputStream fout = new FileOutputStream(this.admin.getStafffile().getAbsolutePath());
						ObjectOutputStream oout = new ObjectOutputStream(fout);
						oout.writeObject(this.admin.getStaffs());
						JOptionPane.showMessageDialog(null, "Delete Success : " + staff);
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
						JOptionPane.showMessageDialog(null, "File Not Found , Fail To Delete");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Staff Not Found , Fail To Delete");
				}
			}
		}
		
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, View Account]")) {
			this.panel_right.remove(0);
			JTable table=new JTable(new StaffTableModel(this.admin.getStaffs()));
			DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			table.setDefaultRenderer(Object.class, renderer);
			this.panel_right.add(new JScrollPane(table));
			this.setVisible(true);
		}

		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Edit Account]")) {
			String staffstr = JOptionPane.showInputDialog(null, "Please Input Staff Number");
			if(this.admin.getStaffs().get(staffstr) != null) {
				new EditStaffJFrame(this.admin.getStaffs(), this.admin.getStafffile(), staffstr);
			}
		}
		
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Login Log]")) {
			new LoginLogJFrame();
		}
		
		
		System.out.println(e.getPath().toString());
		
	}
	
}
