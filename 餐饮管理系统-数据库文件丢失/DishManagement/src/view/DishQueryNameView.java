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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import dao.DishDao;
import model.DishModel;


public class DishQueryNameView extends JFrame {
	private JScrollPane jsp;
	private JTextField jtf;
	private String[] s={"Dish Id","Dish Name","Dish Price","Dish Disciption"};
	private DefaultTableModel dtm;
	
	private JTable table;
	
	
	public DishQueryNameView(){
		init();
	}

	public void init(){
		JPanel jp1=new JPanel();
		JLabel label1=new JLabel("Please Input DishName");
		jp1.add(label1);
		jtf=new JTextField(10);
		jp1.add(jtf);
		JButton jb1=new JButton("Check");
		jp1.add(jb1);
		jb1.addActionListener(new queryDishAction());
		jb1.setActionCommand("name");
		
         this.add(jp1,BorderLayout.NORTH);
		jsp=new JScrollPane();
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(jsp,BorderLayout.CENTER);
		setResizable(false);
		setVisible(true);
		setBounds(300, 150, 450, 300);
		setSize(400,400);
		setTitle("Check Dish By Name");
	}
	
	
	private class queryDishAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("name")){
				String name=jtf.getText().trim();
				DishDao dd=new DishDao();
				List<DishModel> ldm=dd.equeryDishInfBy_name(name);
				if(ldm.size()==0){
					JOptionPane.showMessageDialog(jsp, "The Dish Is Not Exist");
				}else{
				dtm=new DefaultTableModel(s, 0);
				table=new JTable(dtm);
				table.setGridColor(Color.orange);
//				jsp=new JScrollPane(table);
				
				table.setSize(jsp.getWidth(),jsp.getHeight());
//				jsp.setRowHeaderView(table);
				jsp.setViewportView(table);
//				jsp.setColumnHeaderView(table);
				for(DishModel dm:ldm){
					Vector v=new Vector();
					v.add(dm.getDish_id());
					v.add(dm.getDish_name());
					v.add(dm.getDish_price()+"Ԫ");
					v.add(dm.getDish_description());
					dtm.addRow(v);
				}
				
//				contentPane.add(jsp,BorderLayout.CENTER);
//				contentPane.repaint();
				jsp.repaint();
				
			}
			}
			}
		}
		
}
