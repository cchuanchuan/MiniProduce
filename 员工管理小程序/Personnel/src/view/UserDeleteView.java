package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.UserDao;
import model.UserModel;

public class UserDeleteView extends JFrame implements ActionListener {
	List<UserModel> list = new ArrayList<UserModel>();
	JPanel paneltop,panelcenter;
	JButton buttoncheck,buttondelete;
	JComboBox combox;
	JTextField textfield;
	JTextArea textarea;
	UserModel select = null;
	
	public UserDeleteView() {
		super("个人信息查询");
		this.setSize(500,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new BorderLayout());
		this.add(this.paneltop = new JPanel(),BorderLayout.NORTH);
		this.add(this.panelcenter = new JPanel(new BorderLayout()),BorderLayout.CENTER);
		
		paneltop.add(combox = new JComboBox());
		paneltop.add(textfield = new JTextField(10));
		paneltop.add(buttoncheck = new JButton("查询"));
		
		panelcenter.add(this.textarea = new JTextArea(),BorderLayout.CENTER);
		panelcenter.add(this.buttondelete = new JButton("删除"),BorderLayout.EAST);
		
		UserDao userdao = new UserDao();
		list = userdao.AllUser();
		
		combox.addItem("员工ID");
		combox.addItem("员工姓名");
		combox.addItem("员工部门");
		combox.addItem("员工职位");
		
		this.buttoncheck.addActionListener(this);
		this.buttondelete.addActionListener(this);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.buttondelete) {
			if(select == null) {
				JOptionPane.showMessageDialog(this, "请先查询用户");
				return;
			}
			UserDao userdao = new UserDao();
			boolean b = userdao.deleteWaiter(select.getId());
			if(b) {
				JOptionPane.showMessageDialog(this, "删除成功");
				this.list = userdao.AllUser();
				this.select = null;
				this.textarea.setText("");
			}else {
				JOptionPane.showMessageDialog(this, "删除失败");
			}
		}
		
		if(e.getSource() == this.buttoncheck) {
			if(combox.getSelectedIndex() == 0) {
				int id;
				try{
					id = Integer.parseInt(this.textfield.getText());
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(this, "请输入int类型的id值");
					return;
				}
				for(UserModel user:list) {
					if(id == user.getId()) {
						this.select = user;
						this.textarea.setText(user.toString());
						break;
					}
				}
			}
			
			if(combox.getSelectedIndex() == 1) {
				String property = this.textfield.getText();
				
				for(UserModel user:list) {
					if(property.equals(user.getUser_name())) {
						this.select = user;
						this.textarea.setText(user.toString());
						break;
					}
				}
			}
			
			if(combox.getSelectedIndex() == 2) {
				String property = this.textfield.getText();
				
				for(UserModel user:list) {
					if(property.equals(user.getUser_dept())) {
						this.select = user;
						this.textarea.setText(user.toString());
						break;
					}
				}
			}
			
			if(combox.getSelectedIndex() == 3) {
				String property = this.textfield.getText();
				
				for(UserModel user:list) {
					if(property.equals(user.getUser_position())) {
						this.select = user;
						this.textarea.setText(user.toString());
						break;
					}
				}
			}
		}
	}

}
