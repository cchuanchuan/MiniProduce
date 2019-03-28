package managerpackage;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.ProductType;
import model.Tender;

public class TenderReportsJFrame extends JFrame{
	JTextPane text = new JTextPane();
	Map<String, Tender> tenders;
	public TenderReportsJFrame(Map<String, Tender> tenders) {
		super("Project Reports");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screensize.getWidth();
		int height = (int)screensize.getHeight();
		this.setSize(width,height-20);
		this.setLayout(new GridLayout(1,1));
		
		this.tenders = tenders;
		this.add(this.text);
		this.getText();
		
		
		
		this.setVisible(true);
	}
	public void getText() {
		String str = "Trender Project Reports"+"\n";
		for(String key:this.tenders.keySet()) {
			str += this.tenders.get(key).toString()+"\n";
		}
		this.text.setText(str);
	}
}
