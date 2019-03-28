package MainJFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class registerframe extends JFrame implements ActionListener
{
	private JTextField username,jobnumber;
	private JPasswordField password,passwordagain;
	private JButton register,cancel;
	DatabaseConnect dc;
	public registerframe(DatabaseConnect dc)
	{
		super();
		this.setSize(400,300);
		this.setTitle("注册公司账户");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.dc=dc;
		
		this.setLayout(new GridLayout(5,2));
		
		this.add(new JLabel("工号"));
		this.jobnumber=new JTextField();
		this.add(jobnumber);
		
		this.add(new JLabel("用户名"));
		this.username=new JTextField();
		this.add(this.username);
	
		this.add(new JLabel("密码"));
		this.password=new JPasswordField(10);
		this.add(this.password);
		
		this.add(new JLabel("再输一次密码"));
		this.passwordagain=new JPasswordField(10);
		this.add(this.passwordagain);
		
		this.register=new JButton("注册");
		this.cancel=new JButton("取消");
		this.register.addActionListener(this);
		this.cancel.addActionListener(this);
		this.add(this.register);
		this.add(this.cancel);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==register)
		{
			if(this.jobnumber.getText().equals("")||this.username.getText().equals("")||
					this.password.getText().equals("")||this.passwordagain.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "请输入以上信息");
				return;
			}
			String no=this.jobnumber.getText();
			String user=this.username.getText();
			String pass=new String(password.getPassword());
			String passagain=new String(passwordagain.getPassword());
			if(passagain.equals(pass))
			{
				String sql1="use EnterprisePersonnel select no,positionNo from person where no='"+no+"'";
				String sql2="use EnterprisePersonnel select no from Users where no='"+no+"'";
				String sql3="use EnterprisePersonnel select UserName from Users where UserName='"+user+"'";
				try {
					ResultSet rs1=dc.executeQuery(sql1);
					ResultSet rs2=dc.executeQuery(sql2);
					ResultSet rs3=dc.executeQuery(sql3);
					if(rs1.next())
					{
						if(!rs2.next())
						{
							if(!rs3.next())
							{
								if(rs1.getString("PositionNo").equals("00201"))
								{
									String login1="create login "+user+" with password='"+pass+"',default_database=EnterprisePersonnel "
											+"use EnterprisePersonnel create user "+user+" exec sp_addrolemember 'db_owner','"+user+"'";
									String login2="use EnterprisePersonnel insert into Users(No,UserName,PassWord) values('"+no+"','"+user+"','"+pass+"')";
									dc.executeUpdate(login1);
									dc.executeUpdate(login2);
									JOptionPane.showMessageDialog(null, "注册成功");
									this.dispose();
								}
								else
								{
									String login1="create login "+user+" with password='"+pass+"',default_database=EnterprisePersonnel "
											+"use EnterprisePersonnel create user "+user+" exec sp_addrolemember 'worker','"+user+"'";
									String login2="use EnterprisePersonnel insert into Users(No,UserName,PassWord) values('"+no+"','"+user+"','"+pass+"')";
									dc.executeUpdate(login1);
									dc.executeUpdate(login2);
									JOptionPane.showMessageDialog(null, "注册成功");
									this.dispose();
								}
								
							}
							else
								JOptionPane.showMessageDialog(null, "用户名已存在");
							
						}
						else
							JOptionPane.showMessageDialog(null, "此工号已注册");
						
					}
					else
						JOptionPane.showMessageDialog(null, "此工号不存在");
				} catch (SQLException e1) {JOptionPane.showMessageDialog(null, e1.getMessage());}
			}
			else
				JOptionPane.showMessageDialog(null, "密码不一致");
		}
		if(e.getSource()==cancel)
		{
			this.dispose();
		}
		
	}
	
}
