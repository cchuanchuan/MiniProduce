package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.AdminUtils;
import model.Reader;

public class AddReaderJFrame extends JFrame implements ActionListener {
	private JButton button_add,button_cancel;
	private JTextField text_readerno,text_readername,text_readerpass,text_readerpassagain;
	
	public AddReaderJFrame() {
		super("读者注册界面");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new GridLayout(5,2));
		this.add(new JLabel("用户编号"));
		this.add(text_readerno = new JTextField());
		
		this.add(new JLabel("用户姓名"));
		this.add(text_readername = new JTextField());
		
		this.add(new JLabel("用户密码"));
		this.add(text_readerpass = new JTextField());
		
		this.add(new JLabel("再次输入密码"));
		this.add(text_readerpassagain = new JTextField());
		
		
		this.add(button_add = new JButton("添加"));
		button_add.addActionListener(this);
		
		this.add(button_cancel = new JButton("取消"));
		button_cancel.addActionListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//点击添加
		if(e.getSource() == button_add) {
			if(!text_readerno.getText().equals("")
					&&!text_readername.getText().equals("")
					&&!text_readerpass.getText().equals("")
					&&!text_readerpassagain.getText().equals("")) {
				if(!text_readerpass.getText().equals(text_readerpassagain.getText())) {
					JOptionPane.showMessageDialog(this, "两次密码输入不一致");
					return;
				}
				String readerno = text_readerno.getText();
				String readername = text_readername.getText();
				String readerpass = text_readerpass.getText();
				Reader reader = new Reader(readerno,readername,readerpass,new ArrayList<String>());
				AdminUtils admin = new AdminUtils();
				if(admin.addReader(reader)) {
					JOptionPane.showMessageDialog(this, "增加成功");
					this.dispose();
				}else {
					JOptionPane.showMessageDialog(this, "增加失败");
				}
			}
		}
		
		if(e.getSource() == button_cancel) {
			this.dispose();
		}
		
	}
	
}
