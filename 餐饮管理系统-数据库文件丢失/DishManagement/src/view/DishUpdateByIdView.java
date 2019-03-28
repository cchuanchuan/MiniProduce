package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.DishDao;
import model.DishModel;


public class DishUpdateByIdView extends JFrame{
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;
	private String s;
	private int d_id;
	
	
	
	
	public DishUpdateByIdView(){
		s=JOptionPane.showInputDialog(this,"Input Dish id");
		if(s == null) {
			return;
		}
		int i=Integer.valueOf(s);
		DishDao DD=new DishDao();
		DishModel dm=DD.equeryDishInfBy_id(i);
		if(dm==null)
		{
			JOptionPane.showMessageDialog(this, "The Id You Input Is Not Exist");
		}
	else{
		init();
		textField.setText(dm.getDish_name());
	    Double d=new Double(dm.getDish_price());
	    String S_1=d.toString(); 
		textField_1.setText(S_1);
		textArea.setText(dm.getDish_description());
		d_id=dm.getDish_id();
	    }
	}

	public void init(){
		setVisible(true);
		setTitle("Update Dish");
		setBounds(400, 100, 450, 300);
		setSize(600,600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(70, 130, 180));
		contentPane.add(panel);
		
		JLabel label = new JLabel("Dish Name：");
		panel.add(label);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(20);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 230, 140));
		contentPane.add(panel_1);
		
		JLabel label_1 = new JLabel("Dish Price：");
		panel_1.add(label_1);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(20);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(221, 160, 221));
		contentPane.add(panel_2);
		
		JLabel label_2 = new JLabel("Dish Discription：");
		panel_2.add(label_2);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		
		textArea = new JTextArea();
		textArea.setColumns(50);
		textArea.setRows(8);
		panel_3.add(textArea);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(245, 245, 245));
		contentPane.add(panel_4);
		
		JButton button = new JButton("Conmfirm Update");
		button.addActionListener(new DishAddAction());
		button.setActionCommand("提交");
		button.setBackground(new Color(255, 0, 255));
		panel_4.add(button);
		
		JButton button_1 = new JButton("Cancel Update");
		button_1.addActionListener(new DishAddAction());
		button_1.setActionCommand("取消");
		button_1.setBackground(new Color(255, 20, 147));
		panel_4.add(button_1);
	}
	
	private class DishAddAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("提交")){
				DishDao DD=new DishDao();
				String d_newname=textField.getText().trim();
				String d_newprice=textField_1.getText().trim();
				
				Double d=Double.valueOf(d_newprice);
				String d_newdesc=textArea.getText().trim();
				DD.updateDishInfBy_Id(d_newname, d, d_newdesc, null, d_id);
				JOptionPane.showMessageDialog(contentPane, "Update Success");
			}else if(e.getActionCommand().equals("取消")){
				dispose();
			}
		}
		
	}

}
