package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import control.FileUtils;
import model.Memory;

public class MainJFrame extends JFrame implements ActionListener {
	private List<Memory> list = new ArrayList<Memory>();
	private JPanel panelcenter,panelbuttom;
	private JTextArea textcontent;
	private JButton buttonadd,buttondeleltone,buttondeleteall,buttonnumber,buttonchange;
	private JList<Memory> jlist;
	private DefaultListModel<Memory> listmodel;
	
	public MainJFrame() {
		super("Mamory System");
		this.setSize(500,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.add(this.panelcenter = new JPanel(),BorderLayout.CENTER);
		this.add(this.panelbuttom = new JPanel(),BorderLayout.SOUTH);
		panelcenter.setLayout(new GridLayout(1,1));
		
		this.list = FileUtils.ReadFileMemory();
		this.listmodel = setListModel(this.list);
		panelcenter.add(this.jlist = new JList<Memory>(listmodel));
		
		this.setMenu();
		
		this.setVisible(true);
	}
	
	private DefaultListModel<Memory> setListModel(List<Memory> lists) {
		DefaultListModel<Memory> model = new DefaultListModel<Memory>();
		for(Memory mem:lists) {
			model.addElement(mem);
		}
		return model;
	}
	
	//菜单栏
	private void setMenu() {
		String menulist[] = {"备忘录管理","备忘录查询"};
		String menuitemlist[][] = {{"添加备忘录","删除选中备忘录","修改备忘录","显示备忘录总数","删除所有备忘录","显示当天备忘录","显示所有备忘录"},
									{"按照日期查询","按照内容查询"}};
		JMenuBar menubar = new JMenuBar();
		for(int i=0;i<menulist.length;i++) {
			JMenu menu = new JMenu(menulist[i]);
			menubar.add(menu);
			for(int j=0;j<menuitemlist[i].length;j++) {
				JMenuItem menuitem = new JMenuItem(menuitemlist[i][j]);
				menu.add(menuitem);
				menuitem.addActionListener(this);
			}
		}
		this.add(menubar,"North");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if(e.getActionCommand().equals("添加备忘录")) {
			new AddJFrame();
			this.dispose();
		}
		
		if(e.getActionCommand().equals("删除选中备忘录")) {
			Memory m = this.jlist.getSelectedValue();
			if(m == null) {
				JOptionPane.showMessageDialog(this, "请选择");
			}else {
				this.list.remove(m);
				this.listmodel.removeElement(m);
				FileUtils.saveMemory(list);
			}
		}
		
		if(e.getActionCommand().equals("修改备忘录")) {
			Memory m = this.jlist.getSelectedValue();
			if(m == null) {
				JOptionPane.showMessageDialog(this, "请选择");
			}else {
				new ChangeJFrame(m);
				this.dispose();
			}
		}
		
		if(e.getActionCommand().equals("显示备忘录总数")) {
			JOptionPane.showMessageDialog(this, "总数："+this.list.size());
		}
		
		if(e.getActionCommand().equals("删除所有备忘录")) {
			this.list = new ArrayList<Memory>();
			this.listmodel = this.setListModel(list);
			FileUtils.saveMemory(list);
		}
		
		if(e.getActionCommand().equals("显示当天备忘录")) {
			String time = JOptionPane.showInputDialog(this, "请输入现在日期");
			List<Memory>templist = new ArrayList<Memory>();
			for(Memory m:this.list) {
				if(m.getDate().contains(time)) {
					templist.add(m);
				}
			}
			this.listmodel = this.setListModel(templist);
			this.jlist.setModel(listmodel);
		}
		
		if(e.getActionCommand().equals("按照日期查询")) {
			String time = JOptionPane.showInputDialog(this, "请输入查找日期");
			List<Memory>templist = new ArrayList<Memory>();
			for(Memory m:this.list) {
				if(m.getDate().contains(time)) {
					templist.add(m);
				}
			}
			this.listmodel = this.setListModel(templist);
			this.jlist.setModel(listmodel);
		}
		
		if(e.getActionCommand().equals("按照内容查询")) {
			String time = JOptionPane.showInputDialog(this, "请输入查找内容");
			List<Memory>templist = new ArrayList<Memory>();
			for(Memory m:this.list) {
				if(m.getContent().contains(time)) {
					templist.add(m);
				}
			}
			this.listmodel = this.setListModel(templist);
			this.jlist.setModel(listmodel);
		}
		
		if(e.getActionCommand().equals("显示所有备忘录")) {
			this.listmodel = this.setListModel(this.list);
			this.jlist.setModel(listmodel);
		}
	}
	
	public static void main(String args[]) {
		new MainJFrame();
	}
}
