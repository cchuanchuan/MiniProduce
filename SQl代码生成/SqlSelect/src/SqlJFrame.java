import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SqlJFrame extends JFrame implements ActionListener {
	private JTextField text1;
	private JTextField text2;
	private JTextArea textarea1;
	private JTextArea textarea2;
	private JButton button;
	
	
	public SqlJFrame() {
		super("Sql代码生成");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(800, 530);
		
		JSplitPane split = new JSplitPane();
		JPanel panelleft = new JPanel(null);
		JPanel panelright = new JPanel(null);
		split.setLeftComponent(panelleft);
		split.setRightComponent(panelright);
		this.add(split);
		split.setDividerLocation(300);
		split.setDividerSize(0);
		this.textarea1 = new JTextArea();
		JScrollPane scrollpane1 = new JScrollPane(textarea1);
		panelleft.add(scrollpane1);
		panelleft.add(this.text1 = new JTextField());
		panelleft.add(this.text2 = new JTextField());
		
		scrollpane1.setBounds(25, 30, 250, 250);
		scrollpane1.setBorder(BorderFactory.createTitledBorder("Title1"));
		this.text1.setBounds(25, 300, 250, 40);
		this.text2.setBounds(25, 390, 250, 40);
		this.text1.setBorder(BorderFactory.createTitledBorder("Title2"));
		this.text2.setBorder(BorderFactory.createTitledBorder("Title3"));
		
		panelright.add(this.button = new JButton("查询"));
		this.button.addActionListener(this);
		this.button.setBounds(180, 30, 100, 50);
		
		this.textarea2 = new JTextArea();
		JScrollPane scrollpane2 = new JScrollPane(textarea2);
		panelright.add(scrollpane2);
		scrollpane2.setBounds(30, 100, 400, 320);
		
		
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.button) {
			String t1 = this.text1.getText();
			String t2 = this.text2.getText();
			String ta1 = this.textarea1.getText();
			String []ta1arr = ta1.trim().split("\n");
			
			String output = "select * from "+t1+"\n"+"where "+t2+" in (";
			if(ta1arr.length>0) {
				for(int i=0;i<ta1arr.length-1;i++) {
					output+="\""+ta1arr[i]+"\",";
					if(i%5==0) {
						output+="\n";
					}
				}
				output+="\""+ta1arr[ta1arr.length-1]+"\")";
			}
			this.textarea2.setText(output);
		}
		
	}
	
	public static void main(String args[]) {
		new SqlJFrame();
	}

}
