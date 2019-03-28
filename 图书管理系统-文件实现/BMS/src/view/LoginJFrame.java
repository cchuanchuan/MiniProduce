package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.FileUtils;

public class LoginJFrame extends JFrame implements ActionListener{
	private JLabel lab_1;//总标题
	private JLabel usernameLabel;//用户名标签
	private JLabel passwordLabel;//密码标签
	private JPanel indexPanel;//主页容器
	private JPanel usernamePanel;//用户名这一项的容器
	private JPanel passwordPanel;//密码这一项容器
	private JPanel bottonPannel;//普通按钮这一选项的容器
	private JTextField usernameText;//用户名文字输入框
	private JPasswordField passwordText;//密码文字输入框
	private JButton loginBotton;//登陆按钮
	private JButton registerBotton;//注册按钮
	private JButton resetButton;
	private JRadioButton jr1;
	private JRadioButton jr2;
	public LoginJFrame(){
		setTitle("Login View");
		JPanel jp=new JPanel();
	   
	    lab_1=new JLabel("Welcome To Login");
	    JPanel lab_1panel=new JPanel();
	    lab_1panel.add(lab_1);
	    
	    //进行盒式布局，垂直布局
	    indexPanel=new JPanel();
	    BoxLayout bl=new BoxLayout(indexPanel, BoxLayout.Y_AXIS);
	    indexPanel.setLayout(bl);
	    
	    indexPanel.setOpaque(false);
	   
	    //用户名这一栏进行布局
	    usernameLabel=new JLabel("用户名:");
	    usernameText=new JTextField(18);
	    usernamePanel=new JPanel();
	    usernamePanel.add(usernameLabel);
	    usernamePanel.add(usernameText);
	    usernamePanel.setSize(100, 100);
	    
	    //密码这一栏进行布局
	    passwordLabel=new JLabel("密    码:");
	    passwordText=new JPasswordField(18);
	    passwordPanel=new JPanel();
	    passwordPanel.add(passwordLabel);
	    passwordPanel.add(passwordText);
	    
	    //添加普通用户和服务员按钮
	    ButtonGroup buttongroup = new ButtonGroup();
	    JPanel radiopanel=new JPanel();
	    jr1=new JRadioButton("读者");
	    jr2=new JRadioButton("管理员");
	    buttongroup.add(jr1);
	    buttongroup.add(jr2);
	    radiopanel.add(jr1);
	    radiopanel.add(jr2);
	   
	    //登陆按钮添加事件
	    bottonPannel=new JPanel();
	    loginBotton=new JButton("登    录");
	    loginBotton.addActionListener(this);
	    //注册按钮添加事件
	    registerBotton=new JButton("读者注册");
	    registerBotton.addActionListener(this);
	    //重置按钮添加事件
	    resetButton = new JButton("重    置");
	    resetButton.addActionListener(this);
	    //按钮组件进行布局
	    bottonPannel.add(loginBotton);
	    bottonPannel.add(registerBotton);
	    bottonPannel.add(resetButton);
	   
	    //把各个pannel加到主页pannel上去
	    indexPanel.add(lab_1panel);
	    indexPanel.add(usernamePanel);
	    indexPanel.add(passwordPanel);
	    indexPanel.add(radiopanel);
	    indexPanel.add(bottonPannel);
	   
	    //窗口加上主页
	    this.add(indexPanel);
	 
	    this.setResizable(false);
	   
	    this.setSize(300, 300);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//触发按钮事件的时候，对数据库进行连接并且进行比对
		if(e.getSource() == loginBotton) {
			if(!usernameText.getText().equals("")&&!passwordText.getText().equals("")) {
				//选择读者登录
				if(jr1.isSelected()) {
					String no = usernameText.getText();
					String password = passwordText.getText();
					if(FileUtils.isReader(no, password)) {
						new ReaderIndex(no);
					}else {
						JOptionPane.showMessageDialog(this, "用户名或密码错误");
					}
				}
				
				//选择管理员登录
				if(jr2.isSelected()) {
					if(usernameText.getText().toUpperCase().equals("ADMIN")
							&&passwordText.getText().equals("123456")) {
						this.dispose();
						new AdminIndex();
					}else {
						JOptionPane.showMessageDialog(this, "用户名或密码错误");
					}
					
				}
			}else {
				JOptionPane.showConfirmDialog(this, "请输入用户名和密码");
			}
			
			
		}
		//点击注册
		if(e.getSource() == registerBotton){
			new AddReaderJFrame();
		}
		//点击重置
		if(e.getSource() == resetButton) {
			this.usernameText.setText("");
			this.passwordText.setText("");
		}
		
	}
	
	public static void main(String[] args) {
		new LoginJFrame();
	}

}
