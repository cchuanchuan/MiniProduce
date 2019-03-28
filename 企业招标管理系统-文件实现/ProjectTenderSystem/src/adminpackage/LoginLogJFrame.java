package adminpackage;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.Login;
import model.ProductType;
import model.Tender;

public class LoginLogJFrame extends JFrame{
	JTextPane text = new JTextPane();
	List<Login> loginlist = new LinkedList<Login>();
	public LoginLogJFrame() {
		super("Login Log");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screensize.getWidth();
		int height = (int)screensize.getHeight();
		this.setSize(width,height-20);
		this.setLayout(new GridLayout(1,1));
		
		
		File directory = new File("");
		File loginfile = new File(directory.getAbsolutePath()+"\\src\\FileStore\\Login.txt");
		
		try {
			FileInputStream fin = new FileInputStream(loginfile.getAbsolutePath());
			ObjectInputStream oin = new ObjectInputStream(fin);
			this.loginlist = (List<Login>) oin.readObject();
			System.out.println("1321465465");
		} catch (IOException | ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null,"There is no Login,Create new Login File");
		}
		
		this.add(this.text);
		this.getText();
		
		
		
		this.setVisible(true);
	}
	public void getText() {
		String str = "Login Log"+"\n";
		for(Login log:this.loginlist) {
			str += log.toString()+"\n";
		}
		this.text.setText(str);
	}
}
