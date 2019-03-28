package MainJFrame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BonusJFrame extends JFrame implements ActionListener
{
	JPanel panel;
	JPanel panel2;
	JTextField text_no,text_bonus;
	JButton button_confirm,button_confirm2;
	DatabaseConnect dc;
	public BonusJFrame(DatabaseConnect dc)
	{
		super("发放奖金页面");
		this.setSize(300,260);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.white);
		this.dc=dc;
		
		this.setLayout(new GridLayout(1,1));
		panel=new JPanel(new GridLayout(4,1));
		panel.setBackground(Color.white);
		panel.setOpaque(true);
		this.add(panel);
		
		panel.add(new JLabel("奖金信息",JLabel.CENTER));
		JPanel panel_no=new JPanel(new GridLayout(1,2));
		panel.add(panel_no);
		JLabel l1=new JLabel("员工工号：");
		l1.setOpaque(true);
		l1.setBackground(Color.white);
		panel_no.add(l1);
		panel_no.add(this.text_no=new JTextField());
		JPanel panel_bonus=new JPanel(new GridLayout(1,2));
		panel.add(panel_bonus);
		JLabel l2=new JLabel("奖金金额：");
		l2.setOpaque(true);
		l2.setBackground(Color.white);
		panel_bonus.add(l2);
		panel_bonus.add(this.text_bonus=new JTextField());
		panel.add(this.button_confirm=new JButton("确认发放"));
		this.button_confirm.setBackground(Color.white);
		this.button_confirm.addActionListener(this);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand()=="确认发放")
		{
			if(this.text_no.getText().equals("")||this.text_bonus.getText().equals(""))
				JOptionPane.showMessageDialog(null, "以上信息必须填写");
			else
			{
				double d=Double.parseDouble(this.text_bonus.getText());
				String str=this.text_no.getText();
				String sql="use EnterprisePersonnel select Name from Person where no='"+str+"'";
				try {
					ResultSet rs=dc.executeQuery(sql);
					rs.next();
					this.remove(panel);
					this.add(panel2=new JPanel(new GridLayout(5,1)));
					JLabel label0=new JLabel("请确认员工信息",JLabel.CENTER);
					JLabel label1=new JLabel("工号："+this.text_no.getText(),JLabel.CENTER);
					JLabel label2=new JLabel("姓名："+rs.getString("Name"),JLabel.CENTER);
					JLabel label3=new JLabel("奖励金额："+d,JLabel.CENTER);
					this.button_confirm2=new JButton("确认");
					button_confirm2.addActionListener(this);
					label0.setOpaque(true);label0.setBackground(Color.white);
					label1.setOpaque(true);label1.setBackground(Color.white);
					label2.setOpaque(true);label2.setBackground(Color.white);
					label3.setOpaque(true);label3.setBackground(Color.white);
					button_confirm2.setBackground(Color.white);
					panel2.add(label0);
					panel2.add(label1);
					panel2.add(label2);
					panel2.add(label3);
					panel2.add(button_confirm2);
					this.setVisible(true);
				} catch (SQLException e1) {JOptionPane.showMessageDialog(null, e1.getMessage());}
			}
		}
		if(e.getSource()==button_confirm2)
		{
			double d=Double.parseDouble(this.text_bonus.getText());
			String str=this.text_no.getText();
			int year=Calendar.getInstance().get(Calendar.YEAR);
		    int month = Calendar.getInstance().get(Calendar.MONTH)+1;
			String sql="use EnterprisePersonnel update Attendance "
					+ "set Bonus=Bonus+"+d+" where No='"+str+"' and year="+year+" and month="+month;
			try {
				dc.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "奖金发放成功");
				this.dispose();
			} catch (SQLException e1) {JOptionPane.showMessageDialog(null, e1.getMessage());}
		}
		
	}
}
