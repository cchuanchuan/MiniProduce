package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.filechooser.FileFilter;

import model.*;
import control.*;

public class MainJFrame extends JFrame implements MouseListener, ActionListener, ItemListener, ComponentListener {
	JSplitPane split1,split2;
	JPanel panel_top,panel_bottom;
	ScatterJPanel panel_scatter;
	List<BondTrading> datalist = new ArrayList<BondTrading>();
	List<String>titles = null;
	List<Point1> drawlist = new ArrayList<Point1>();
	double maxx = 0,maxy = 0,minx = 0,miny = 0;
	JComboBox comboxx = new JComboBox();
	JComboBox comboxy = new JComboBox();
	JButton buttonopen = new JButton("Open");
	JButton buttonclose = new JButton("Exit");
	public MainJFrame() {
		super("Scatter Diagram");
		this.setSize(1500, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addComponentListener(this);
		this.panel_top=new JPanel();
		this.panel_bottom = new JPanel();
		File back = new File("src\\back.png");
		if(back.exists()) {
			Image imag=new ImageIcon(back.getAbsolutePath()).getImage();
			this.panel_bottom.add(new BackgroundPanel(imag));
		}else {
			this.panel_bottom.add(new JLabel("Scatter Diagram"));
		}
		
		
		this.panel_bottom.setLayout(new GridLayout());
		
		this.split1=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		this.split1.setDividerLocation(60);
		split1.setDividerSize(0);
		this.add(split1);
		split1.setLeftComponent(this.panel_top);
		split1.setRightComponent(this.panel_bottom);
		
		this.panel_top.add(this.buttonopen);
		this.panel_top.add(this.buttonclose);
		this.buttonopen.addActionListener(this);
		this.buttonclose.addActionListener(this);
		this.panel_top.add(new JLabel("X Axes"));
		this.panel_top.add(this.comboxx);
		this.panel_top.add(new JLabel("Y Axes"));
		this.panel_top.add(this.comboxy);
		this.comboxx.addItemListener(this);
		this.comboxy.addItemListener(this);

		this.panel_bottom.setBackground(new Color(246,237,220));
		this.panel_bottom.addMouseListener(this);
		this.setVisible(true);
	}
	
	public void setCombox() {
		for(String str : this.titles) {
			this.comboxx.addItem(str);
			this.comboxy.addItem(str);
		}
		this.comboxx.setSelectedIndex(0);
		this.comboxy.setSelectedIndex(1);
	}
	
	public List<Point1> getDrawList(int width,int height) {
		Point1 pointo = new Point1(50,height-80);
		Point1 pointx = new Point1(width-50,height-80);
		Point1 pointy = new Point1(50,50);
		
		List<Point1> list = new ArrayList<Point1>();
		List<Point2> templist = new ArrayList<Point2>();
		
		//Find Max adn Min
		
		double x = this.comboxx.getSelectedIndex()==0?this.datalist.get(0).getYIELD():this.comboxx.getSelectedIndex()==1?this.datalist.get(0).getDAYS_TO_MATURITY():this.datalist.get(0).getAMOUNT_CHF();
		double y = this.comboxy.getSelectedIndex()==0?this.datalist.get(0).getYIELD():this.comboxy.getSelectedIndex()==1?this.datalist.get(0).getDAYS_TO_MATURITY():this.datalist.get(0).getAMOUNT_CHF();
		maxx=minx=x;
		maxy=miny=y;
        for(BondTrading bon: this.datalist) {
        	x = this.comboxx.getSelectedIndex()==0?bon.getYIELD():this.comboxx.getSelectedIndex()==1?bon.getDAYS_TO_MATURITY():bon.getAMOUNT_CHF();
			y = this.comboxy.getSelectedIndex()==0?bon.getYIELD():this.comboxy.getSelectedIndex()==1?bon.getDAYS_TO_MATURITY():bon.getAMOUNT_CHF();
        	if(x>maxx) {
        		maxx = x;
        	}
        	if(x<minx) {
        		minx = x;
        	}
        	if(y>maxy) {
        		maxy=y;
        	}
        	if(y<miny) {
        		miny=y;
        	}
        	templist.add(new Point2(x,y));
        }
        //Calculate point coordinates
        double scalexx = (pointx.getX()-pointo.getX())/(maxx - minx);
        double scaleyy = (pointo.getY()-pointy.getY())/(maxy - miny);
        for(Point2 p:templist) {
        	
        	x = p.getX();
        	y = p.getY();
        	int xx = (int)Math.round(x*scalexx + pointo.getX());
        	int yy = (int)Math.round(pointo.getY() - y*scaleyy);
        	list.add(new Point1(xx,yy));
        }
		return list;
	}
	
	public List<Point2>getDataList(){
		List<Point2> list = new ArrayList<Point2>();
		for(BondTrading bon:this.datalist) {
			double x = this.comboxx.getSelectedIndex()==0?bon.getYIELD():this.comboxx.getSelectedIndex()==1?bon.getDAYS_TO_MATURITY():bon.getAMOUNT_CHF();
			double y = this.comboxy.getSelectedIndex()==0?bon.getYIELD():this.comboxy.getSelectedIndex()==1?bon.getDAYS_TO_MATURITY():bon.getAMOUNT_CHF();
			Point2 p = new Point2(x,y);
			list.add(p);
		}
		return list;
	}
	
	
	public static void main(String args[]) {
		new MainJFrame();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}
		if(e.getActionCommand().equals("Open")) {
			JFileChooser chooser = new JFileChooser(".\\src\\model");
			FileFilter filter = new CsvFilter();
			chooser.setFileFilter(filter);
			chooser.showOpenDialog(this);
			File file = chooser.getSelectedFile();
			if(file != null && file.exists()) {
				FileControl filecontrol = new FileControl(file);
				this.datalist = filecontrol.getList();
				this.titles = filecontrol.getTitles();
				this.setCombox();
				
				this.drawlist = this.getDrawList(this.getWidth()-20,this.getHeight()-108);
				
				
				
				this.panel_bottom.remove(0);
				this.panel_scatter = new ScatterJPanel(this.drawlist,this.getWidth()-20,this.getHeight()-108,maxx-minx,maxy-miny);
				panel_scatter.setOpaque(false);
				this.panel_bottom.add(panel_scatter);
				this.setVisible(true);
			}
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i = 0;i<this.drawlist.size(); i++) {
			if(Math.sqrt(
					Math.pow((e.getX()-this.drawlist.get(i).getX()),2)
					+Math.pow((e.getY()-this.drawlist.get(i).getY()),2)
					) < 10){
				JOptionPane.showMessageDialog(this, this.datalist.get(i));
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			this.drawlist = this.getDrawList(this.getWidth()-20,this.getHeight()-108);
			this.panel_bottom.remove(0);
			this.panel_scatter = new ScatterJPanel(this.drawlist,this.getWidth()-20,this.getHeight()-108,maxx-minx,maxy-miny);
			panel_scatter.setOpaque(false);
			this.panel_bottom.add(panel_scatter);
			this.setVisible(true);
		}
	}

	@Override
	public void componentResized(ComponentEvent e) {
		if(this.comboxx.getItemCount()!=0) {
			this.drawlist = this.getDrawList(this.getWidth()-20,this.getHeight()-108);
			this.panel_bottom.remove(0);
			this.panel_scatter = new ScatterJPanel(this.drawlist,this.getWidth()-20,this.getHeight()-108,maxx-minx,maxy-miny);
			panel_scatter.setOpaque(false);
			this.panel_bottom.add(panel_scatter);
			this.setVisible(true);
		}
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {}
	@Override
	public void componentShown(ComponentEvent e) {}
	@Override
	public void componentHidden(ComponentEvent e) {}

}
