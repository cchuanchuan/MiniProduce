package temp;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import managerpackage.BackgroundPanel;

public class EnterprisePersonnel extends JFrame implements TreeSelectionListener, ActionListener
{
	JMenu menus[];
	JSplitPane split1,split2;
	JPanel panel_top,panel_bottom,panel_left;
	PersonJTree personjtree;
	public EnterprisePersonnel()
	{
		super("Project Tender System");
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
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
		
		String strmenu[]={"企业管理","工资管理","考勤管理","部门设置"};
		String strmenuitem[][]={{"部门信息","职位信息","上班时间设置","上班时间查询"},{"工资信息","发放奖金","开个罚单"},
				{"打卡上班","加班打卡","考勤信息报表"},
				{"部门信息","增加部门","修改部门信息","删除部门"}};
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
		String strperson[]={"员工入职","异动记录","员工离职","职务更变"};
		JMenu menuperson=new JMenu("人事异动");
		menuperson.setOpaque(true);
		menuperson.setBackground(Color.white);
		menus[0].add(menuperson);
		for(int i=0;i<strperson.length;i++)
		{
			JMenuItem menuitem=new JMenuItem(strperson[i]);
			menuitem.setBackground(Color.white);
			menuperson.add(menuitem);
			menuitem.addActionListener(this);
		}
		
		this.personjtree=new PersonJTree();
		this.panel_left.add(personjtree);
		personjtree.addTreeSelectionListener(this);
		
		File directory = new File("");
		File logo = new File(directory.getAbsolutePath()+"\\src\\FileStore\\logo.jpg");
		Image imag=new ImageIcon(logo.getAbsolutePath()).getImage();
		panel_bottom.add(new BackgroundPanel(imag));
		
		
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}

	public void valueChanged(TreeSelectionEvent e) {
		
	}
	
	public static void main(String arg[]) {
		EnterprisePersonnel t=new EnterprisePersonnel();
	}

	

}
