package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.DishDao;
import model.DishModel;



public class DishManagerView extends JFrame{
	private JPanel contentPane;
	private String[] s={"Dish Id","Dish Name","Dish Price","Dish Disciption"};
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	private JTable table;

	public DishManagerView(){
		DishManagerViewinit();
	}

	public void DishManagerViewinit() {
		setTitle("Dish Management");
		jsp=new JScrollPane();
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		setResizable(true);
		
		setBounds(300, 150, 450, 300);
		setSize(800,500);
		
		JMenuBar menuBar = new JMenuBar();
		
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Check Dish");
		menuBar.add(menu);
		
		JMenuItem mntmid = new JMenuItem("By ID");
		mntmid.addActionListener(new queryDishAction());
		mntmid.setActionCommand("id");
		menu.add(mntmid);
		
		JMenuItem menuItem = new JMenuItem("By Name");
		menuItem.addActionListener(new queryDishAction());
		menuItem.setActionCommand("name");
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Check All");
		menuItem_1.addActionListener(new queryDishAction());
		menuItem_1.setActionCommand("Check All");
		menu.add(menuItem_1);
		
		JButton button = new JButton("Add Dish");
		button.addActionListener(new DishAddAction());
		button.setActionCommand("Add");
		menuBar.add(button);
		
		JButton button_1 = new JButton("Delete Dish");
		button_1.addActionListener(new DishDeleteAction());
		button_1.setActionCommand("Delete");
		menuBar.add(button_1);
		
		JMenu menu_1 = new JMenu("Update Dish");
		menuBar.add(menu_1);
		
		JMenuItem mntmid_1 = new JMenuItem("By ID");
        mntmid_1.addActionListener(new UpdateDishAction());
        mntmid_1.setActionCommand("IDUpdate");
		menu_1.add(mntmid_1);
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
      
		JPanel jpanel=new JPanel();
		JLabel l=new JLabel("Welcome To Check Dish Information");
		jpanel.setBackground(Color.GREEN);
		jpanel.setForeground(Color.black);
		jpanel.add(l);
		
		contentPane.add(jpanel,BorderLayout.NORTH);
		
		contentPane.add(jsp,BorderLayout.CENTER);
		
		//默认显示全部菜品信息
		DishDao dd=new DishDao();
		List<DishModel> ldm=dd.equeryAllDish();
		dtm=new DefaultTableModel(s, 0);
		table=new JTable(dtm);
		table.setGridColor(Color.orange);
		table.setSize(jsp.getWidth(),jsp.getHeight());
		jsp.setViewportView(table);
		for(DishModel dm:ldm){
			Vector v=new Vector();
			v.add(dm.getDish_id());
			v.add(dm.getDish_name());
			v.add(dm.getDish_price()+"元");
			v.add(dm.getDish_description());
			dtm.addRow(v);
		}
		this.add(contentPane);
		this.setVisible(true);
	}
	
	
	private class queryDishAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Check All")){
				DishDao dd=new DishDao();
				List<DishModel> ldm=dd.equeryAllDish();
				//System.out.println(ldm.size());
				dtm=new DefaultTableModel(s, 0);
				table=new JTable(dtm);
				table.setGridColor(Color.orange);
				
				table.setSize(jsp.getWidth(),jsp.getHeight());
				
				jsp.setViewportView(table);
				for(DishModel dm:ldm){
					Vector v=new Vector();
					v.add(dm.getDish_id());
					v.add(dm.getDish_name());
					v.add(dm.getDish_price()+"元");
					v.add(dm.getDish_description());
					dtm.addRow(v);
				}
				
				jsp.repaint();
				
			}else if(e.getActionCommand().equals("id")){
				new DishQueryIdView();
			}else if(e.getActionCommand().equals("name")){
				new DishQueryNameView();
			}
		}
		
	}
	
	private class DishAddAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Add")){
				new DishAddView();
			}
		}
		
	}

	private class DishDeleteAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Delete")){
				new DishDeleteView();
			}
		}
		
	}
	
	private class UpdateDishAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("IDUpdate")){
				new DishUpdateByIdView();
			}
		}
		
	}

}
