package MainJFrame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChangeDeptJFrame extends JFrame implements ActionListener
{
	PersonJTree personjtree;
	DatabaseConnect dc;
	JTextField textfields[];
	JButton button_confirm;
	String no;
	public ChangeDeptJFrame(PersonJTree personjtree,DatabaseConnect dc,String no)
	{
		super("修改部门");
		this.personjtree=personjtree;
		this.dc=dc;
		this.no=no;
		String sql="use EnterprisePersonnel select dept.* from Dept where DeptNo='"+no+"'";
		try {
			ResultSet rs=dc.executeQuery(sql);		
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setSize(300, 260);
			this.setLocationRelativeTo(null);
			
			this.setLayout(new GridLayout(4,1));
			this.textfields=new JTextField[3];
			for(int i=0;i<this.textfields.length;i++)
				this.textfields[i]=new JTextField();
			JPanel panels[]=new JPanel[3];
			for(int i=0;i<panels.length;i++)
			{
				panels[i]=new JPanel(new GridLayout(1,2));
				this.add(panels[i]);
			}
			rs.next();
			panels[0].add(new JLabel("部门编号："));
			panels[0].add(this.textfields[0]);
			textfields[0].setText(rs.getString(1));
			textfields[0].setEditable(false);
			panels[1].add(new JLabel("部门名字："));
			panels[1].add(this.textfields[1]);
			textfields[1].setText(rs.getString(2));
			panels[2].add(new JLabel("经理工号："));
			panels[2].add(this.textfields[2]);
			textfields[2].setText(rs.getString(3));
			this.add(this.button_confirm=new JButton("确认修改"));
			this.button_confirm.addActionListener(this);
			this.setVisible(true);
		} catch (SQLException e) {JOptionPane.showMessageDialog(null, e.getMessage());}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==this.button_confirm)
		{
			if(this.textfields[0].getText().equals("")||this.textfields[1].getText().equals("")
					||this.textfields[2].getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "请输入信息");
				return;
			}
			String sql1="use EnterprisePersonnel  select no from Person where no='"+this.textfields[2].getText()+"'";
			String sql3="use EnterprisePersonnel  select deptname from dept where DeptName='"+this.textfields[1].getText()+"'";
			try {
				ResultSet rs1=dc.executeQuery(sql1);
				ResultSet rs3=dc.executeQuery(sql3);
				if(rs1.next())
				{
					if(!(rs3.next()))
					{	
						String sql4="use EnterprisePersonnel update dept set DeptName='"+
					this.textfields[1].getText()+"',ManagerNo='"+this.textfields[2].getText()+"' where DeptNo='"+no+"'";
						dc.executeUpdate(sql4);
						JOptionPane.showMessageDialog(null, "修改部门成功");
						this.personjtree.setVisible(true);
						this.dispose();
					}
					else
						JOptionPane.showMessageDialog(null, "部门名字已存在");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "员工表里无工号："+this.textfields[2].getText());
				}
				
				
			} catch (SQLException e1) {JOptionPane.showMessageDialog(null, e1.getMessage());}
			
		}
		
	}

}
