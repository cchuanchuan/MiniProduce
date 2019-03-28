package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.UserDao;
import model.UserModel;

public class UserRegisterView extends JFrame implements ActionListener{
	private JTextField u_name_text;
    private JPasswordField u_pass_text;
    private JTextField u_dept_text;
    private JTextField u_pos_text;
    private JTextField u_sal_text; 
    JPanel indexpanel;
	public UserRegisterView(){
		init();
	}
	
	public void init(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		indexpanel=new JPanel();
		//主页的容器
		BoxLayout box=new BoxLayout(indexpanel, BoxLayout.Y_AXIS);
		indexpanel.setLayout(box);
		//采用盒式布局管理器
		
		JPanel usernamepanel=new JPanel();
		JLabel usernameLab=new JLabel("员工姓名：");
		u_name_text=new JTextField(20);
		usernamepanel.add(usernameLab);
		usernamepanel.add(u_name_text);
		//为用户名分配一个空间
		
        JPanel passwordpanel=new JPanel();
        JLabel passwordLab=new JLabel("员工密码：");
        u_pass_text=new JPasswordField(20);
        u_pass_text.setEchoChar('*');
        passwordpanel.add(passwordLab);
        passwordpanel.add(u_pass_text);
        //为密码分配一个空间
		
        JPanel passwordsurepanel=new JPanel();
        JLabel passwordsureLab=new JLabel("员工部门：");
        u_dept_text=new JTextField(20);
        passwordsurepanel.add(passwordsureLab);
        passwordsurepanel.add(u_dept_text);
        //为确认密码这个模块分配一个空间
        
        JPanel telephonepanel=new JPanel();
        JLabel telephoneLab=new JLabel("员工职位：");
        u_pos_text=new JTextField(20);
        telephonepanel.add(telephoneLab);
        telephonepanel.add(u_pos_text);
        //为电话号码分配一个空间

     
        JPanel addresspanel=new JPanel();
        JLabel addressLab=new JLabel("员工工资：");
        u_sal_text=new JTextField(20);
        addresspanel.add(addressLab);
        addresspanel.add(u_sal_text);
        //为电话号码分配一个空间
        
        JPanel bottonpanel=new JPanel();
        JButton j1=new JButton("注册");
        j1.addActionListener(this);
        JButton j2=new JButton("取消");
        j2.addActionListener(this);
        bottonpanel.add(j1);
        bottonpanel.add(j2);
        //为按钮组件添加事件和盒子
        
		indexpanel.add(usernamepanel);
		indexpanel.add(passwordpanel);
		indexpanel.add(passwordsurepanel);
		indexpanel.add(telephonepanel);

		indexpanel.add(addresspanel);
		indexpanel.add(bottonpanel);
		//把所有盒子添加到主页的空间上去。然后注册事件
		
		this.add(indexpanel);
		//把主页的空间添加到窗口中去
		
		this.setSize(400, 400);
		this.setLocation(500, 200);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("员工注册界面");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("注册")){
			//当确认按钮触发事件时候，就会调用相关数据库的操作，把数据写到数据库中。
			String u_name = u_name_text.getText().trim();
			String u_pass = u_dept_text.getText().trim();
			String u_dept = u_dept_text.getText().trim();
			String u_pos = u_pos_text.getText().trim();
			String u_sal = u_sal_text.getText().trim();
			int salary = 0;
				if(u_name.equals("")||u_pass.equals("")) {
					JOptionPane.showMessageDialog(indexpanel,"请输入用户名和密码");
				}else{
					try{
						if(!u_sal.equals("")) {
							salary = Integer.parseInt(u_sal);
						}
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(indexpanel, "薪水为整数");
						return;
					}
					UserModel usermodel = new UserModel(u_name,u_pass,u_dept,u_pos,salary);
					UserDao userdao=new UserDao();
					boolean b = userdao.registerUser(usermodel);
					if(b) {
						JOptionPane.showMessageDialog(this, "注册成功");
						this.dispose();
					}else {
						JOptionPane.showMessageDialog(this, "注册失败");
					}
				}
		}
		
		if(e.getActionCommand().equals("取消")) {
			new LoginView();
			this.dispose();
		}
	}
		
}
