package MainJFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class loginframe extends JFrame implements ActionListener, WindowListener
{
	private JTextField useranme;
	private JPasswordField password;
	private JButton button_login,button_register;
	DatabaseConnect dc;
	public loginframe()
	{
		super("登录川川的公司");
		this.setSize(360, 180);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addWindowListener(this);
		
		this.setLayout(new GridLayout(3,2));
		this.add(new JLabel("用户名："));
		this.useranme=new JTextField(10);
		this.add(this.useranme);
		this.add(new JLabel("密码："));
		this.password=new JPasswordField(10);
		this.add(this.password);
		
		button_login=new JButton("登录");
		button_register=new JButton("注册");
		this.add(this.button_login);
		this.add(this.button_register);
		button_login.addActionListener(this);
		button_register.addActionListener(this);
		this.dc=new DatabaseConnect("chuanchuan","257173");
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==this.button_login)
		{
			if(this.password.getText().equals("")||this.useranme.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "请输入用户名和密码");
				return;
			}
			String name=this.useranme.getText();
			String word=this.password.getText();
			String sql1="use EnterprisePersonnel select* from Users where Users.UserName='"+
			name+"' and Users.PassWord='"+word+"'";
			
			try {
				ResultSet rs1=dc.executeQuery(sql1);
				if(rs1.next())
				{
					String sql2="use EnterprisePersonnel select positionNo from person where No='"+rs1.getString("No")+"'";
					ResultSet rs2=dc.executeQuery(sql2);
					rs2.next();
					if(rs2.getString("positionNo").equals("00201"))
					{
						this.dc.closeConn();
						new EnterprisePersonnel(new DatabaseConnect(name,word));
						this.dispose();
					}
					else
					{
						this.dc.closeConn();
						new EnterprisePersonnelWorker(new DatabaseConnect(name,word));
						this.dispose();
					}
				}
				else
					JOptionPane.showMessageDialog(null, "用户名或密码错误，请重新输入");
			} catch (SQLException e1) {JOptionPane.showMessageDialog(null, e1.getMessage());}
		}
		if(e.getSource()==this.button_register)
		{
			new registerframe(dc);
		}
		
	}
	
	public static void main(String[]agr)
	{
		new loginframe();
	}


	public void windowOpened(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		try {
			this.dc.closeConn();
		} catch (SQLException e1) {JOptionPane.showMessageDialog(null, e1.getMessage());}
	}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
}
