package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.FileUtils;
import model.Memory;

public class ChangeJFrame extends JFrame implements ActionListener {
	private List<Memory>list;
	private JButton buttonadd,buttonback;
	private JTextField textsite,texttime;
	private JTextArea textarea;
	private Memory change;
	public ChangeJFrame(Memory mem) {
		super("修改备忘录");
		this.setSize(300, 450);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());
		this.list = FileUtils.ReadFileMemory();
		this.change = mem;
		
		this.add(new JLabel("时间"));
		this.add(this.texttime = new JTextField(20));
		texttime.setText(mem.getDate());
		this.add(new JLabel("地点"));
		this.add(this.textsite = new JTextField(20));
		textsite.setText(mem.getSite());
		this.add(new JLabel("内容"));
		this.add(this.textarea = new JTextArea(15,20));
		textarea.setText(mem.getContent());
		
		this.add(this.buttonadd = new JButton("修改"));
		this.add(this.buttonback = new JButton("取消"));
		buttonadd.addActionListener(this);
		buttonback.addActionListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.buttonadd) {
			String site = this.textsite.getText();
			String time = this.texttime.getText();
			String content = this.textarea.getText();
			Memory m = new Memory(time,site,content);
			list.remove(change);
			list.add(m);
			FileUtils.saveMemory(list);
			this.dispose();
			new MainJFrame();
		}
		
		if(e.getSource() == this.buttonback) {
			this.dispose();
			new MainJFrame();
		}
	}
	
}
