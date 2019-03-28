package jpanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;


/**
  * »­Í¼´°Ìå
 */
public class ImageFrame extends JFrame {
	
	//³õÊ¼»¯ÆÁÄ»µÄ³ß´ç
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public ImageFrame() {
		super("test");
		this.setSize((int)screenSize.getWidth()/3,(int)(screenSize.getHeight()/1.5));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(1,1));
		
		DrawJPanel panel = new DrawJPanel("src//code");
		this.add(panel);
		this.setVisible(true);
	}

	public static void main(String args[]) {
		new ImageFrame();
	}
}
