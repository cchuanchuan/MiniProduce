package MainJFrame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class EntryTable extends JPanel implements ActionListener
{
	JTextField textfields[];
	JButton button_confirm;
	JComboBox comboxs[];
	JRadioButton radiobutton[];
	JPanel panel_right,panel_left;
	DatabaseConnect dc;
	public EntryTable(DatabaseConnect dc)
	{
		super();
		this.dc=dc;
		panel_right=new JPanel();
		panel_left=new JPanel(new GridLayout(11,2));
		this.setLayout(new GridLayout(1,2));
		this.add(panel_left, "West");
		this.add(panel_right, "East");
		panel_left.setBackground(Color.white);
		panel_right.setBackground(Color.white);
		button_confirm=new JButton("确认添加");
		button_confirm.addActionListener(this);
		button_confirm.setBackground(Color.white);
		this.textfields=new JTextField[6];
		for(int i=0;i<6;i++)
			textfields[i]=new JTextField();
		JPanel panels[]=new JPanel[9];
		for(int i=0;i<9;i++)
		{
			panels[i]=new JPanel(new GridLayout(1,2));
			panels[i].setBackground(Color.white);
		}
		this.comboxs=new JComboBox[6];
		for(int i=0;i<6;i++)
		{
			comboxs[i]=new JComboBox();
			comboxs[i].setBackground(Color.white);
		}
		
		panels[0].add(new JLabel("工号:"));
		panels[0].add(textfields[0]);
		panels[1].add(new JLabel("姓名"));
		panels[1].add(textfields[1]);
		radiobutton=new JRadioButton[2];
		radiobutton[0]=new JRadioButton("男",true);
		radiobutton[1]=new JRadioButton("女");
		radiobutton[0].setBackground(Color.white);
		radiobutton[1].setBackground(Color.white);
		ButtonGroup buttongroup=new ButtonGroup();
		buttongroup.add(radiobutton[0]);
		buttongroup.add(radiobutton[1]);
		panels[2].add(new JLabel("性别"));
		JPanel panel_sex=new JPanel(new GridLayout(1,2));
		panel_sex.add(radiobutton[0]);
		panel_sex.add(radiobutton[1]);
		panels[2].add(panel_sex);
		panels[3].add(new JLabel("电话"));
		panels[3].add(textfields[2]);
		panels[4].add(new JLabel("出生日期(年,月,日)"));
		JPanel panel_birthday=new JPanel(new GridLayout());
		panels[4].add(panel_birthday);
		for(int i=1;i<=12;i++)
		{
			comboxs[0].addItem(i);
			comboxs[2].addItem(i);
		}
		for(int i=1;i<=31;i++)
		{
			comboxs[1].addItem(i);
			comboxs[3].addItem(i);
		}
		panel_birthday.add(textfields[3]);
		panel_birthday.add(comboxs[0]);
		panel_birthday.add(comboxs[1]);
		panels[5].add(new JLabel("学历"));
		panels[5].add(textfields[4]);
		JPanel panel_entitytime=new JPanel(new GridLayout());
		panels[6].add(new JLabel("入职时间"));
		panels[6].add(panel_entitytime);
		panel_entitytime.add(textfields[5]);
		panel_entitytime.add(comboxs[2]);
		panel_entitytime.add(comboxs[3]);
		panels[7].add(new JLabel("部门"));
		panels[7].add(comboxs[4]);
		panels[8].add(new JLabel("职称"));
		panels[8].add(comboxs[5]);
		String sql1="use EnterprisePersonnel select deptno,DeptName from Dept";
		String sql2="use EnterprisePersonnel select PositionNo,PositionName from Position";
		try {
			ResultSet rs1=dc.executeQuery(sql1);
			ResultSet rs2=dc.executeQuery(sql2);
			while(rs1.next())
			{
				comboxs[4].addItem(rs1.getString("Deptno")+": "+rs1.getString("DeptName"));
			}
			while(rs2.next())
			{
				comboxs[5].addItem(rs2.getString("PositionNo")+": "+rs2.getString("PositionName"));
			}
		} catch (SQLException e) {JOptionPane.showMessageDialog(null, e.getSQLState());}
		
		
		panel_left.add(new JLabel("员工入职信息",JLabel.CENTER));
		panel_left.add(panels[0]);
		panel_left.add(panels[1]);
		panel_left.add(panels[2]);
		panel_left.add(panels[3]);
		panel_left.add(panels[4]);
		panel_left.add(panels[5]);
		panel_left.add(panels[6]);
		panel_left.add(panels[7]);
		panel_left.add(panels[8]);
		panel_left.add(button_confirm);
		
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==button_confirm)
		{
			if(textfields[0].getText().equals("")||textfields[1].getText().equals("")||textfields[2].getText().equals(""))
				JOptionPane.showMessageDialog(this, "工号，用户名和手机号\n          不能为空!");
			else
			{
				String sql="insert into person(No,Name,Sex,Phone,Birthday,Education,Entrytime,DeptNo,PositionNo) values(?,?,?,?,?,?,?,?,?)";
				PreparedStatement prestate;
				try {
					prestate = dc.PreparedStatement(sql);
					prestate.setString(1, textfields[0].getText());
					prestate.setString(2, textfields[1].getText());
					prestate.setString(3, radiobutton[0].isSelected()?radiobutton[0].getText():radiobutton[1].getText());
					prestate.setString(4, textfields[2].getText());
					prestate.setString(5, textfields[3].getText()+"-"+comboxs[0].getSelectedItem()+"-"+comboxs[1].getSelectedItem());
					prestate.setString(6, textfields[4].getText());
					prestate.setString(7, textfields[5].getText()+"-"+comboxs[2].getSelectedItem()+"-"+comboxs[3].getSelectedItem());
					String str1="";
					for(int i=0;comboxs[4].getSelectedItem().toString().charAt(i)!=':';i++)
						str1+=comboxs[4].getSelectedItem().toString().charAt(i);
					prestate.setString(8, str1);
					String str2="";
					for(int i=0;comboxs[5].getSelectedItem().toString().charAt(i)!=':';i++)
						str2+=comboxs[5].getSelectedItem().toString().charAt(i);
					prestate.setString(9, str2);
					prestate.executeUpdate();
					JOptionPane.showMessageDialog(null, "插入成功");
				} catch (SQLException e1) {JOptionPane.showMessageDialog(null, e1.getMessage());}
				
			}
		}
		
	}
}
