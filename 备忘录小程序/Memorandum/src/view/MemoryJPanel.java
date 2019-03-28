package view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MemoryJPanel extends JPanel {
	JPanel paneltop,panelcenter;
	JTextField textsite;
	JTextArea textarea;
	public MemoryJPanel() {
		super();
		this.setLayout(new BorderLayout());
		this.add(this.paneltop = new JPanel(),BorderLayout.NORTH);
		this.paneltop.add(new JLabel("µØµã"));
		this.paneltop.add(this.textsite = new JTextField(10));
		
		this.add(this.panelcenter = new JPanel(),BorderLayout.CENTER);
	}

}
