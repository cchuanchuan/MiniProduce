package jpanel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import jpanel.DrawJPanel.DrawElement;
import jpanel.DrawJPanel.MyCanvas;

public class PddlJFrame extends JFrame implements ActionListener {
	public JTextField textfield;
	private JButton button;
	private DrawElement drawelement;
	private MyCanvas cans;
	String mypath = "";
	public PddlJFrame(String path,DrawElement drawelement,MyCanvas cans) {
		super("NameJframe");
		this.mypath = path;
		this.drawelement = drawelement;
		this.cans = cans;
		
		this.setLayout(new FlowLayout());
		this.setSize(400, 120);
		this.add(new JLabel("Please Input Name"));
		
		
		this.add(textfield = new JTextField(20));
		button = new JButton("È·¶¨");
        this.add(button);
        button.addActionListener(this);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.button) {
			String name = this.textfield.getText();
			if(name!=null&&!name.equals("")) {
				drawelement.setName(name);
			}
			FileUtils.createFile(mypath, drawelement.getName()+".pddl");
			drawelement.setFilename(mypath+"//"+drawelement.getName()+".pddl");
			cans.addName(drawelement);
			this.dispose();
		}
	}
	

}
