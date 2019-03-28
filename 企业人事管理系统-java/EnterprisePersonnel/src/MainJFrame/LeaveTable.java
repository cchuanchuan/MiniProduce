package MainJFrame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class LeaveTable extends JPanel implements ActionListener
{
	JTextField textfields[];
	JButton button_confirm;
	JComboBox comboxs[];
	JRadioButton radiobutton[];
	JPanel panel_right,panel_left;
	DatabaseConnect dc;
	public LeaveTable(DatabaseConnect dc,String no)
	{
		super();
		this.dc=dc;
		String sql="use EnterprisePersonnel select person.* from Person where person.No='"+no+"'";
		try {
			ResultSet rs=dc.executeQuery(sql);
			rs.next();
			panel_right=new JPanel();
			panel_left=new JPanel(new GridLayout(11,2));
			this.setLayout(new GridLayout(1,2));
			this.add(panel_left, "West");
			this.add(panel_right, "East");
			panel_left.setBackground(Color.white);
			panel_right.setBackground(Color.white);
			button_confirm=new JButton("确认离职");
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
			textfields[0].setText(rs.getString("No"));
			textfields[0].setEditable(false);
			panels[1].add(new JLabel("姓名"));
			panels[1].add(textfields[1]);
			textfields[1].setText(rs.getString(2));
			radiobutton=new JRadioButton[2];
			radiobutton[0]=new JRadioButton("男");
			radiobutton[1]=new JRadioButton("女");
			radiobutton[0].setBackground(Color.white);
			radiobutton[1].setBackground(Color.white);
			ButtonGroup buttongroup=new ButtonGroup();
			buttongroup.add(radiobutton[0]);
			buttongroup.add(radiobutton[1]);
			if(rs.getString("Sex").equals("男"))
				radiobutton[0].setSelected(true);
			else
				radiobutton[1].setSelected(true);
			panels[2].add(new JLabel("性别"));
			JPanel panel_sex=new JPanel(new GridLayout(1,2));
			panel_sex.add(radiobutton[0]);
			panel_sex.add(radiobutton[1]);
			panels[2].add(panel_sex);
			panels[3].add(new JLabel("电话"));
			panels[3].add(textfields[2]);
			textfields[2].setText(rs.getString("Phone"));
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
			String birthday=rs.getDate("Birthday").toString();
			String byear=birthday.substring(0,4);
			int bmonth=Integer.parseInt(birthday.substring(5,7));
			int bday=Integer.parseInt(birthday.substring(8));
			System.out.println(rs.getDate("Birthday"));
			panel_birthday.add(textfields[3]);
			textfields[3].setText(byear);
			panel_birthday.add(comboxs[0]);
			comboxs[0].setSelectedItem(bmonth);
			panel_birthday.add(comboxs[1]);
			comboxs[1].setSelectedItem(bday);
			panels[5].add(new JLabel("学历"));
			panels[5].add(textfields[4]);
			textfields[4].setText(rs.getString("Education"));
			JPanel panel_entitytime=new JPanel(new GridLayout());
			panels[6].add(new JLabel("入职时间"));
			panels[6].add(panel_entitytime);
			panel_entitytime.add(textfields[5]);
			panel_entitytime.add(comboxs[2]);
			panel_entitytime.add(comboxs[3]);
			String entitytime=rs.getDate("EntryTime").toString();
			String eyear=entitytime.substring(0,4);
			int emonth=Integer.parseInt(entitytime.substring(5,7));
			int eday=Integer.parseInt(entitytime.substring(8,9));
			textfields[5].setText(eyear);
			comboxs[2].setSelectedItem(emonth);
			comboxs[2].setSelectedItem(eday);
			panels[7].add(new JLabel("部门"));
			panels[7].add(comboxs[4]);
			panels[8].add(new JLabel("职称"));
			panels[8].add(comboxs[5]);
			String sql1="use EnterprisePersonnel select deptno,DeptName from Dept";
			String sql2="use EnterprisePersonnel select PositionNo,PositionName from Position";

			ResultSet rs1=dc.executeQuery(sql1);
			ResultSet rs2=dc.executeQuery(sql2);
			int m=2,n=2;
			int i=0;
			while(rs1.next())
			{
				comboxs[4].addItem(rs1.getString("Deptno")+": "+rs1.getString("DeptName"));
				if(rs1.getString("Deptno").equals(rs.getString("DeptNo")))
					m=i;
				i++;
				
			}
			i=0;
			while(rs2.next())
			{
				comboxs[5].addItem(rs2.getString("PositionNo")+": "+rs2.getString("PositionName"));
				if(rs2.getString("PositionNo").equals(rs.getString("PositionNo")))
					n=i;
				i++;
			}
			comboxs[4].setSelectedIndex(m);
			comboxs[5].setSelectedIndex(n);
			
			
			panel_left.add(new JLabel("请确认离职员工信息",JLabel.CENTER));
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
		} catch (SQLException e1) {JOptionPane.showMessageDialog(null, e1.getMessage());}
		
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==button_confirm)
		{
			int year=Calendar.getInstance().get(Calendar.YEAR);
		    int month = Calendar.getInstance().get(Calendar.MONTH)+1;
		    int day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		    String date=year+"-"+month+"-"+day;
		    String str1="";
		    for(int i=0;comboxs[4].getSelectedItem().toString().charAt(i)!=':';i++)
				str1+=comboxs[4].getSelectedItem().toString().charAt(i);
			String str2="";
			for(int i=0;comboxs[5].getSelectedItem().toString().charAt(i)!=':';i++)
				str2+=comboxs[5].getSelectedItem().toString().charAt(i);
			String sql1="use EnterprisePersonnel delete from person where person.No='"+textfields[0].getText()+"'";
			String sql2="use EnterprisePersonnel insert into PersonChange values('"+
					textfields[0].getText()+"','"+textfields[1].getText()+
					"','员工离职','"+date+"','"+str1+"','"+str2+"')";
			try {
				String check="use EnterprisePersonnel select* from Users where Users.No='"+textfields[0].getText()+"'";
				ResultSet rs=dc.executeQuery(check);
				if(rs.next())
				{
					String no=textfields[0].getText();
					String name=rs.getString("username");
					String word=rs.getString("password");
					String login1="use EnterprisePersonnel drop user "+name+" drop login "+name;
					dc.executeUpdate(login1);
				}
				
				dc.executeUpdate(sql1);
				dc.executeUpdate(sql2);
				JOptionPane.showMessageDialog(null, "离职成功");
			} catch (SQLException e1) {JOptionPane.showMessageDialog(null, e1.getMessage());}
		}
		
	}
}
