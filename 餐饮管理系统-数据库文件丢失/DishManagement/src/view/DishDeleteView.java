package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.DishDao;
import model.DishModel;


public class DishDeleteView extends JFrame{
	private JScrollPane jsp;
	private JTextField jtf;
	private String[] s={"Dish Id","Dish Name","Dish Price","Dish Disciption"};
	private DefaultTableModel dtm;
	
	private JTable table;
	
	
	public DishDeleteView(){
		init();
	}

	public void init(){
		JPanel jp1=new JPanel();
		JLabel label1=new JLabel("Please Input Id");
		jp1.add(label1);
		jtf=new JTextField(4);
		jp1.add(jtf);
		JButton jb1=new JButton("Check");
		JButton jb2=new JButton("Delete");
		jp1.add(jb1);
		jp1.add(jb2);
		jb1.addActionListener(new queryDishAction());
		jb1.setActionCommand("id");
		jb2.addActionListener(new queryDishAction());
		jb2.setActionCommand("É¾³ý");
		
         this.add(jp1,BorderLayout.NORTH);
		jsp=new JScrollPane();
		this.add(jsp,BorderLayout.CENTER);
		setResizable(false);
		setVisible(true);
		setBounds(300, 150, 450, 300);
		setSize(400,400);
		setTitle("Delete Dish By Id");
	}
	
	
	private class queryDishAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("id")){
				String id=jtf.getText().trim();
				int i=Integer.valueOf(id);
				DishDao dd=new DishDao();
				DishModel dm=dd.equeryDishInfBy_id(i);
				if(dm==null){
					JOptionPane.showMessageDialog(jsp, "The Id is Not Exists");
				}else{
				dtm=new DefaultTableModel(s, 0);
				table=new JTable(dtm);
				table.setGridColor(Color.orange);
				jsp.setColumnHeaderView(table);
				Vector v=new Vector();
				v.add(dm.getDish_id());
				v.add(dm.getDish_name());
				v.add(dm.getDish_price()+"Ôª");
				v.add(dm.getDish_description());
				dtm.addRow(v);
				jsp.repaint();
			}
			}else if(e.getActionCommand().equals("É¾³ý")){
				DishDao dd=new DishDao();
				String id=jtf.getText().trim();
				int i=Integer.valueOf(id);
				dd.deleteDish(i);
				JOptionPane.showMessageDialog(jsp, "Delete Success");
			}
		}
		
	}
	

}
