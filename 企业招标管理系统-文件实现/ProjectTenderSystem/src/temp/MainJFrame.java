package temp;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MainJFrame extends JFrame implements MouseListener {
	JSplitPane split1,split2;
	JPanel panel_top,panel_bottom,panel_left;
	public MainJFrame() {
		super("Project Tender System - Admin");
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.split1=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		this.split1.setDividerLocation(60);
		split1.setDividerSize(0);
		this.add(split1);
		this.panel_left=new JPanel();
		JPanel panel_right=new JPanel(new GridLayout(1,1));
		split1.setLeftComponent(panel_left);
		split1.setRightComponent(panel_right);
		
		this.split2=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		panel_right.add(split2);
		split2.setDividerLocation(60);
		split2.setDividerSize(0);
		this.panel_top=new JPanel(new GridLayout(1,1));
		this.panel_bottom=new JPanel(new GridLayout());
		split2.setLeftComponent(panel_top);
		split2.setRightComponent(panel_bottom);
		
		panel_bottom.setBackground(Color.white);
		panel_left.setBackground(Color.red);
		this.panel_left.add(new JButton("Open"));
		this.panel_left.add(new JButton("Close"));
		this.panel_top.setBackground(Color.BLUE);
		this.panel_bottom.addMouseListener(this);
		this.setVisible(true);
		System.out.println(this.panel_bottom.getAlignmentX());
		System.out.println(this.panel_left.getLocation().toString());
		System.out.println(this.panel_top.getLocation().toString());
	}
	
	public static void main(String args[]) {
		new MainJFrame();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX());
		System.out.println(e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
