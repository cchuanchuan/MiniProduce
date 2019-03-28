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

import control.UserDao;
import model.UserModel;



public class LoginView extends JFrame implements ActionListener{
	private JLabel lab_1;//总标题
	private JLabel usernameLabel;//用户名
	private JLabel passwordLabel;//密码
	private JPanel indexPanel;//主页容器
	private JPanel usernamePanel;//用户名这一项的容器
	private JPanel passwordPanel;//密码这一项容器
	private JPanel bottonPannel;//普通按钮这一选项的容器
	private JTextField usernameText;//用户名文字输入框
	private JPasswordField passwordText;//密码文字输入框
	private JButton loginBotton;//登陆按钮
	private JButton registerBotton;//注册按钮
	public LoginView(){
		//构造方法，让它执行init方法
		init();
	}

public void init(){
	   setTitle("登录页面");
	   this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	   JPanel jp=new JPanel();
	   jp.setOpaque(false);//设置透明
	   
	   lab_1=new JLabel("欢迎登录");
	   JPanel lab_1panel=new JPanel();
	   lab_1panel.add(lab_1);

	   
	   indexPanel=new JPanel();
	   BoxLayout bl=new BoxLayout(indexPanel, BoxLayout.Y_AXIS);
	   indexPanel.setLayout(bl);
	   //进行盒式布局，垂直布局
	   indexPanel.setOpaque(false);
	   
	   
	   usernameLabel=new JLabel("用户名:");
	   usernameText=new JTextField(18);
	   usernamePanel=new JPanel();
	   usernamePanel.add(usernameLabel);
	   usernamePanel.add(usernameText);
	   usernamePanel.setSize(100, 100);
	   //用户名这一栏进行布局
	   
	   passwordLabel=new JLabel("密    码:");
	   passwordText=new JPasswordField(18);
	   passwordPanel=new JPanel();
	   passwordPanel.add(passwordLabel);
	   passwordPanel.add(passwordText);
	   //密码这一栏进行布局
	   
	   bottonPannel=new JPanel();
	   loginBotton=new JButton("登录");
	   loginBotton.addActionListener(this);
	   //登陆按钮添加事件
	   registerBotton=new JButton("注册");
	   registerBotton.addActionListener(this);
	   //重置按钮添加事件
	   bottonPannel.add(loginBotton);
	   bottonPannel.add(registerBotton);
	   //按钮组件进行布局
	   
	   indexPanel.add(lab_1panel);
	   indexPanel.add(usernamePanel);
	   indexPanel.add(passwordPanel);
	   indexPanel.add(bottonPannel);
	   //把各个pannel加到主页pannel上去
	   
	   this.add(indexPanel);
	 
	   //窗口加上主页
	   this.setResizable(false);
	   
	   this.setSize(300, 300);
	   this.setLocation(500, 200);
	   this.setVisible(true);
   }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("登录")) {
			//触发按钮事件的时候，对数据库进行连接并且进行比对
			String u_name = usernameText.getText().trim();
			String u_password = passwordText.getText().trim();
			if(u_name.equals("")||u_password.equals("")) {
				JOptionPane.showMessageDialog(indexPanel, "请输入用户名或密码");
			}
			
			UserDao userdao = new UserDao();
			UserModel usermodel = userdao.loginUser(u_name, u_password);
			if(usermodel == null) {
				JOptionPane.showMessageDialog(indexPanel, "用户名或密码错误");
			}else {
				JOptionPane.showMessageDialog(indexPanel, "登录成功");
				new UserDeleteView();
			}
		}
		
		if(e.getActionCommand().equals("注册")){
			new UserRegisterView();
		}
	}
	
	public static void main(String[] args) {
		new LoginView();
	}

}
