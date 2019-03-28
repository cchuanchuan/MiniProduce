import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class EndJFrame extends JFrame {
	public EndJFrame() {
		this.setVisible(true);
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setLayout(new GridLayout());
		ImageIcon icon = new ImageIcon("file//move.jpg");
		this.add(new JLabel(icon));
		
		this.setVisible(true);
	}

}
