package MainJFrame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CheckWorktime extends JFrame implements ActionListener
{
	public JButton button;
	public JTextField textfields[];
	DatabaseConnect dc;
	public CheckWorktime(DatabaseConnect dc)
	{
		super("上班时间");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(300,180);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(3,1));
		this.dc=dc;
		String sql="use EnterprisePersonnel select* from worktime";
		try {
			ResultSet rs=dc.executeQuery(sql);
			this.add(new JLabel("上班时间",JLabel.CENTER));
			rs.next();
			int hour=rs.getInt("hour");
			int minute=rs.getInt("minute");
			this.add(new JLabel("上班时间为："+hour+":"+minute,JLabel.CENTER));
			this.add(this.button=new JButton("确认"));
			this.button.addActionListener(this);
			this.setVisible(true);
			
		} catch (SQLException e) {JOptionPane.showMessageDialog(null, e.getMessage());}
	}
	
	public void actionPerformed(ActionEvent arg0)
	{
		this.dispose();
	}
}