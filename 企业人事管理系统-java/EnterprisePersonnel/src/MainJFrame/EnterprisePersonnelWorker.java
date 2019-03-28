package MainJFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

public class EnterprisePersonnelWorker extends JFrame implements TreeSelectionListener, ActionListener, WindowListener
{
	JMenu menus[];
	JSplitPane split1,split2;
	JPanel panel_top,panel_bottom,panel_left;
	DatabaseConnect dc;
	PersonJTree personjtree;
	public EnterprisePersonnelWorker(DatabaseConnect dc)
	{
		super("企业人事管理系统--员工界面");
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.BLUE);
		
		this.split1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		this.split1.setDividerLocation(200);
		split1.setDividerSize(0);
		this.add(split1);
		this.panel_left=new JPanel(new GridLayout(1,1));
		JPanel panel_right=new JPanel(new GridLayout(1,1));
		panel_left.setBackground(Color.red);
		split1.setLeftComponent(panel_left);
		split1.setRightComponent(panel_right);
		
		this.split2=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		panel_right.add(split2);
		split2.setDividerLocation(60);
		split2.setDividerSize(0);
		this.panel_top=new JPanel(new GridLayout(1,1));
		this.panel_bottom=new JPanel(new GridLayout());
		panel_bottom.setBackground(Color.white);
		split2.setLeftComponent(panel_top);
		split2.setRightComponent(panel_bottom);
		
		String strmenu[]={"企业信息","工资信息","考勤信息","部门信息"};
		String strmenuitem[][]={{"部门信息","职位信息","上班时间查询","人事异动"},{"工资信息"},
				{"打卡上班","加班打卡","考勤信息报表"},{"部门信息"}};
		this.menus=new JMenu[strmenu.length];
		JMenuBar menubar=new JMenuBar();
		menubar.setLayout(new GridLayout(1,5));
		menubar.setBackground(Color.WHITE);
		panel_top.add(menubar);
		for(int i=0;i<strmenu.length;i++)
		{
			this.menus[i]=new JMenu(strmenu[i]);
			menubar.add(menus[i]);
			for(int j=0;j<strmenuitem[i].length;j++)
			{
				JMenuItem menuitem=new JMenuItem(strmenuitem[i][j]);
				menuitem.setBackground(Color.white);
				this.menus[i].add(menuitem);
				menuitem.addActionListener(this);
			}
		}
		JButton backbutton=new JButton("返回");
		menubar.add(backbutton);
		backbutton.setBackground(Color.white);
		backbutton.setBorderPainted(false);
		backbutton.addActionListener(this);
		
		this.dc=dc;
		this.personjtree=new PersonJTree(dc);
		this.panel_left.add(personjtree);
		personjtree.addTreeSelectionListener(this);
		
		ImageIcon imag=new ImageIcon("D:\\上机实验\\SQL实验\\back.jpg");
		panel_bottom.add(new JLabel(imag));
		
		
		this.addWindowListener(this);
		this.setVisible(true);
		//以上为面板界面内容
		
		
	}
	
	
	public void valueChanged(TreeSelectionEvent e) 
	{
		if(e.getPath().toString().equals("[川川的企业, 部门信息]"))
		{
			this.panel_bottom.remove(0);
			String sql="use EnterprisePersonnel "
					+ "select Dept.*,person.Name,person.Phone "
					+ "from Dept,person "
					+ "where dept.managerno=person.No";
			JTable table=new JTable(new TableModels(dc,sql));
			DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			table.setDefaultRenderer(Object.class, renderer); 
			table.setOpaque(true);
			table.setBackground(Color.white);
			this.panel_bottom.add(new JScrollPane(table));
			this.setVisible(true);
		}
		if(e.getPath().toString().equals("[川川的企业, 所有员工]"))
		{
			this.panel_bottom.remove(0);
			String sql="use EnterprisePersonnel select Person.No,"
					+ "Person.Name,Person.Sex,Person.Phone,Person.Birthday,"
					+ "Person.Education,Person.Entrytime,Dept.DeptName,"
					+ "Position.PositionName from Person,Dept,"
					+ "Position where Person.DeptNo=Dept.DeptNo and Person.PositionNo=Position.PositionNo";
			JTable table=new JTable(new TableModels(dc,sql));
			DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			table.setDefaultRenderer(Object.class, renderer);
			panel_bottom.add(new JScrollPane(table));
			this.setVisible(true);
		}
		if(e.getPath().toString().equals("[川川的企业, 职位信息]"))
		{
			this.panel_bottom.remove(0);
			String sql="use EnterprisePersonnel select * from Position";
			JTable table=new JTable(new TableModels(dc,sql));
			DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			table.setDefaultRenderer(Object.class, renderer);
			panel_bottom.add(new JScrollPane(table));
			this.setVisible(true);
		}
		if(e.getPath().toString().equals("[川川的企业, 工资信息]"))
		{
			this.panel_bottom.remove(0);
			String sql="use EnterprisePersonnel select Person.Name,Pay.* from pay,Person where pay.No=Person.No";
			JTable table=new JTable(new TableModels(dc,sql));
			DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			table.setDefaultRenderer(Object.class, renderer);
			panel_bottom.add(new JScrollPane(table));
			this.setVisible(true);
		}
		if(e.getPath().toString().equals("[川川的企业, 考勤信息]"))
		{
			this.panel_bottom.remove(0);
			String sql="use EnterprisePersonnel select name,attendance.* from person,attendance where person.no=attendance.no";
			JTable table=new JTable(new TableModels(dc,sql));
			DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			table.setDefaultRenderer(Object.class, renderer); 
			table.setOpaque(true);
			table.setBackground(Color.white);
			this.panel_bottom.add(new JScrollPane(table));
			this.setVisible(true);
		}
		if(e.getPath().toString().substring(0, 13).equals("[川川的企业, 部门信息,"))
		{
			String str=e.getPath().toString().substring(14,e.getPath().toString().length()-1);
			System.out.println(str);
			String select="use EnterprisePersonnel select deptno from dept where deptname='"+str+"'";
			try {
				ResultSet rs=dc.executeQuery(select);
				rs.next();
				String sql="use EnterprisePersonnel select person.* from person where deptNo='"+rs.getString(1)+"'";
				JTable table=new JTable(new TableModels(dc,sql));
				DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
				renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				table.setDefaultRenderer(Object.class, renderer); 
				table.setOpaque(true);
				table.setBackground(Color.white);
				this.panel_bottom.remove(0);
				this.panel_bottom.add(new JScrollPane(table));
				this.setVisible(true);
			} catch (SQLException e1) {}
		}
		if(e.getPath().toString().substring(0, 13).equals("[川川的企业, 职位信息,"))
		{
			String str=e.getPath().toString().substring(14,e.getPath().toString().length()-1);
			System.out.println(str);
			String select="use EnterprisePersonnel select positionno from position where positionname='"+str+"'";
			try {
				ResultSet rs=dc.executeQuery(select);
				rs.next();
				String sql="use EnterprisePersonnel select person.* from person where PositionNo='"+rs.getString(1)+"'";
				JTable table=new JTable(new TableModels(dc,sql));
				DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
				renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				table.setDefaultRenderer(Object.class, renderer); 
				table.setOpaque(true);
				table.setBackground(Color.white);
				this.panel_bottom.remove(0);
				this.panel_bottom.add(new JScrollPane(table));
				this.setVisible(true);
			} catch (SQLException e1) {JOptionPane.showMessageDialog(null, e1.getMessage());}
		}
		if(e.getPath().toString().equals("[川川的企业, 人事异动]"))
		{
			this.panel_bottom.remove(0);
			String sql="use EnterprisePersonnel select* from PersonChange";
			JTable table=new JTable(new TableModels(dc,sql));
			DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			table.setDefaultRenderer(Object.class, renderer); 
			table.setOpaque(true);
			table.setBackground(Color.white);
			this.panel_bottom.add(new JScrollPane(table));
			this.setVisible(true);
		}
	}


	public void actionPerformed(ActionEvent e) 
	{
		System.out.println(e.getActionCommand());
		if(e.getActionCommand().equals("返回"))
		{
			panel_bottom.remove(0);
			ImageIcon imag=new ImageIcon("D:\\上机实验\\SQL实验\\back.jpg");
			panel_bottom.add(new JLabel(imag));
			this.setVisible(true);
		}
		if(e.getActionCommand().equals("职位信息"))
		{
			this.panel_bottom.remove(0);
			String sql="use EnterprisePersonnel select * from Position";
			JTable table=new JTable(new TableModels(dc,sql));
			DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			table.setDefaultRenderer(Object.class, renderer);
			panel_bottom.add(new JScrollPane(table));
			this.setVisible(true);
		}
		if(e.getActionCommand().equals("工资信息"))
		{
			this.panel_bottom.remove(0);
			String sql="use EnterprisePersonnel select Person.Name,Pay.* from pay,Person where pay.No=Person.No";
			JTable table=new JTable(new TableModels(dc,sql));
			DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			table.setDefaultRenderer(Object.class, renderer);
			panel_bottom.add(new JScrollPane(table));
			this.setVisible(true);
		}
		if(e.getActionCommand().equals("部门信息"))
		{
			this.panel_bottom.remove(0);
			String sql="use EnterprisePersonnel "
					+ "select Dept.*,person.Name,person.Phone "
					+ "from Dept,person "
					+ "where dept.managerno=person.No";
			JTable table=new JTable(new TableModels(dc,sql));
			DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			table.setDefaultRenderer(Object.class, renderer); 
			table.setOpaque(true);
			table.setBackground(Color.white);
			this.panel_bottom.add(new JScrollPane(table));
			this.setVisible(true);
		}
		if(e.getActionCommand().equals("考勤信息"))
		{
			this.panel_bottom.remove(0);
			String sql="use EnterprisePersonnel select name,attendance.* from person,attendance where person.no=attendance.no";
			JTable table=new JTable(new TableModels(dc,sql));
			DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			table.setDefaultRenderer(Object.class, renderer); 
			table.setOpaque(true);
			table.setBackground(Color.white);
			this.panel_bottom.add(new JScrollPane(table));
			this.setVisible(true);
		}
		if(e.getActionCommand().equals("考勤信息报表"))
		{
			this.panel_bottom.remove(0);
			String sql="use EnterprisePersonnel select name,attendance.* from person,attendance where person.no=attendance.no";
			JTable table=new JTable(new TableModels(dc,sql));
			DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			table.setDefaultRenderer(Object.class, renderer); 
			table.setOpaque(true);
			table.setBackground(Color.white);
			this.panel_bottom.add(new JScrollPane(table));
			this.setVisible(true);
		}
		if(e.getActionCommand().equals("打卡上班"))
		{
			String str_no=JOptionPane.showInputDialog(this,"请输入工号");
			if(str_no!=null&&!str_no.equals(""))
			{
				int year=Calendar.getInstance().get(Calendar.YEAR);
			    int month = Calendar.getInstance().get(Calendar.MONTH)+1;
			    int day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			    int dhour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			    int dminute=Calendar.getInstance().get(Calendar.MINUTE);
				try {
					ResultSet rs=dc.executeQuery("use EnterprisePersonnel select * from WorkTime");
					rs.next();
					int hour=new Integer(rs.getInt("hour"));
					int minute=new Integer(rs.getInt("minute"));
					
					if(dhour<hour||dhour==hour&&dminute<=minute)
					{
						String sql="use EnterprisePersonnel insert into CheckIn(No,Year,Month,Day,Night)"
								+"values('"+str_no+"',"+year+","+month+","+day+",0)";
						dc.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "恭喜你,"+str_no+"上班打卡成功！");
					}
					else
					{
						String sql1="use EnterprisePersonnel insert into CheckIn(No,Year,Month,Day,Night)"
								+"values('"+str_no+"',"+year+","+month+","+day+",0)";
						String sql2="use EnterprisePersonnel update Attendance set Latedays=latedays+1,Deduct=Deduct+50 where Attendance.No='"+
								str_no+"' and Attendance.Year='"+year+"' and Attendance.Month='"+month+"'";
						dc.executeUpdate(sql1);
						dc.executeUpdate(sql2);
						JOptionPane.showMessageDialog(null,str_no+"上班打卡成功,你今天上班迟到了！");
					}
				} catch (SQLException e1) {JOptionPane.showMessageDialog(this,"您今天已打卡");}
			}
		}
		if(e.getActionCommand().equals("加班打卡"))
		{
			String str_no=JOptionPane.showInputDialog(this,"请输入工号");
			if(str_no!=null&&!str_no.equals(""))
			{
				int year=Calendar.getInstance().get(Calendar.YEAR);
			    int month = Calendar.getInstance().get(Calendar.MONTH)+1;
			    int day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
				String sql="use EnterprisePersonnel insert into CheckIn(No,Year,Month,Day,Night)"
						+"values('"+str_no+"',"+year+","+month+","+"0,"+day+")";
				try {
					dc.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "恭喜你,"+str_no+"加班打卡成功,勤劳的你将获得100元奖金!");
				} catch (SQLException e1) {JOptionPane.showMessageDialog(this,"您今天已打卡");}
			}
		}
		if(e.getActionCommand().equals("查询考勤信息"))
		{
			this.panel_bottom.remove(0);
			String sql="use EnterprisePersonnel select name,attendance.* from person,attendance where person.no=attendance.no";
			JTable table=new JTable(new TableModels(dc,sql));
			DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			table.setDefaultRenderer(Object.class, renderer); 
			table.setOpaque(true);
			table.setBackground(Color.white);
			this.panel_bottom.add(new JScrollPane(table));
			this.setVisible(true);
		}
		if(e.getActionCommand().equals("上班时间查询"))
		{
			CheckWorktime cw=new CheckWorktime(dc);
		}
		if(e.getActionCommand().equals("人事异动"))
		{
			this.panel_bottom.remove(0);
			String sql="use EnterprisePersonnel select* from PersonChange";
			JTable table=new JTable(new TableModels(dc,sql));
			DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			table.setDefaultRenderer(Object.class, renderer); 
			table.setOpaque(true);
			table.setBackground(Color.white);
			this.panel_bottom.add(new JScrollPane(table));
			this.setVisible(true);
		}
		
	}

	public void windowClosing(WindowEvent arg0) {
		try {
			this.dc.closeConn();
		} catch (SQLException e) {System.out.println(e.getSQLState());}
		
	}
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
	
	
	public static void main(String arg[])
	{
		EnterprisePersonnelWorker t=new EnterprisePersonnelWorker(new DatabaseConnect("chuanchuan","257173"));
		
	}


	
}
