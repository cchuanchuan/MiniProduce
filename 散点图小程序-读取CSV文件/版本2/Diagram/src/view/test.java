package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import control.FileUtils;
import control.csvFileFilter;
import model.BondModel;
import model.MyPoint;
import model.MyPoint2;

public class test extends JFrame {
	public static void main(String[] args) throws Exception {
		new ConfigFrame();
	}
}

class ConfigFrame extends JFrame implements ActionListener, ItemListener, MouseListener, ComponentListener {
	JButton openBtn;
	JButton Close;
	JTextField label;
	JTextField textField;
	JPanel panelcenter;
	JComboBox comboBox1;
	JComboBox comboBox2;
	List<BondModel> datalist;
	List<MyPoint> drawlist;
	double maxx = 0,maxy = 0,minx = 0,miny = 0;

	//构造方法，构造界面
	public ConfigFrame() {
		this.setLayout(new BorderLayout());//设置布局
	    this.setPreferredSize(new Dimension(600, 500));//设置大小
	    this.setTitle("Ae2");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.pack();
	    this.setVisible(true);//设置dialog显示
	    this.addComponentListener(this);
	      
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		
		openBtn = new JButton("Open");
		Close = new JButton("Quit");
		openBtn.addActionListener(this);
		Close.addActionListener(this);
		label = new JTextField(40);
		label.setText("<Name of file>");
		panel1.add(openBtn);
		panel1.add(label);
		panel1.add(Close);
		
		comboBox1 = new JComboBox();
		comboBox1.addItem("X: YIELD");
		comboBox1.addItem("X: DAYS TO MATURITY");
		comboBox1.addItem("X: AMOUNT CHF");
		comboBox1.addItemListener(this);
		panel2.add(comboBox1);
		
		comboBox2 = new JComboBox();
		comboBox2.addItem("Y: YIELD");
		comboBox2.addItem("Y: DAYS TO MATURITY");
		comboBox2.addItem("Y: AMOUNT CHF");
		comboBox2.addItemListener(this);
		comboBox2.setSelectedIndex(2);
		panel2.add(comboBox2);
		textField = new JTextField("Details of Trade");
		textField.setColumns(20);
		panel2.add(textField);
		panelcenter = new JPanel();
		panelcenter.add(new JLabel(""));
		panelcenter.setBackground(Color.white);
		panelcenter.setLayout(new GridLayout(1,1));
		panelcenter.addMouseListener(this);
		
		add(panel1,BorderLayout.NORTH);//北边
		add(panel2,BorderLayout.SOUTH);//南边
		add(panelcenter,BorderLayout.CENTER);

		this.setVisible(true);
	}
	
	//计算画图点坐标，根据JComboBox选着的坐标系计算
	public List<MyPoint> getDrawList(int width,int height) {
		MyPoint pointo = new MyPoint(50,height-80);
		MyPoint pointx = new MyPoint(width-50,height-80);
		MyPoint pointy = new MyPoint(50,50);
		
		List<MyPoint> list = new ArrayList<MyPoint>();
		List<MyPoint2> templist = new ArrayList<MyPoint2>();
		
		//求最值，及点坐标
		double x = this.comboBox1.getSelectedIndex()==0?this.datalist.get(0).getY():this.comboBox1.getSelectedIndex()==1?this.datalist.get(0).getD():this.datalist.get(0).getA();
		double y = this.comboBox2.getSelectedIndex()==0?this.datalist.get(0).getY():this.comboBox2.getSelectedIndex()==1?this.datalist.get(0).getD():this.datalist.get(0).getA();
		maxx=minx=x;
		maxy=miny=y;
        for(BondModel bon: this.datalist) {
        	x = this.comboBox1.getSelectedIndex()==0?bon.getY():this.comboBox1.getSelectedIndex()==1?bon.getD():bon.getA();
			y = this.comboBox2.getSelectedIndex()==0?bon.getY():this.comboBox2.getSelectedIndex()==1?bon.getD():bon.getA();
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
        	templist.add(new MyPoint2(x,y));
        }

        //计算点坐标
        double scalexx = (pointx.getX()-pointo.getX())/(maxx - minx);
        double scaleyy = (pointo.getY()-pointy.getY())/(maxy - miny);
        for(MyPoint2 p:templist) {
        	x = p.getX();
        	y = p.getY();
        	int xx = (int)Math.round(x*scalexx + pointo.getX());
        	int yy = (int)Math.round(pointo.getY() - y*scaleyy);
        	list.add(new MyPoint(xx,yy));
        }
		return list;
	}

	//按钮事件监听
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.openBtn) {
			JFileChooser chooser = new JFileChooser(".");
			FileFilter filter = new csvFileFilter();
			chooser.setFileFilter(filter);
			chooser.showOpenDialog(this);
			File file = chooser.getSelectedFile();
			if(file != null && file.exists()) {
				this.label.setText(file.getName());
				this.datalist = FileUtils.ReadCSV(file);
				this.drawlist = this.getDrawList(this.getWidth()-20,this.getHeight()-108);
				
				this.panelcenter.remove(0);
				ScatterJPanel panel = new ScatterJPanel(this.drawlist,this.getWidth()-20,this.getHeight()-108,maxx-minx,maxy-miny);
				panel.setOpaque(false);
				this.panelcenter.add(panel);
				this.setVisible(true);
			}
		}
		
		if(e.getSource() == this.Close) {
			System.exit(0);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED 
				&&this.datalist != null) {
			this.drawlist = this.getDrawList(this.getWidth()-20,this.getHeight()-108);
			this.panelcenter.remove(0);
			ScatterJPanel panel = new ScatterJPanel(this.drawlist,this.getWidth()-20,this.getHeight()-108,maxx-minx,maxy-miny);
			panel.setOpaque(false);
			this.panelcenter.add(panel);
			this.setVisible(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.drawlist == null) {
			return;
		}
		for(int i = 0;i<this.drawlist.size(); i++) {
			if(Math.sqrt(
					Math.pow((e.getX()-this.drawlist.get(i).getX()),2)
					+Math.pow((e.getY()-this.drawlist.get(i).getY()),2)
					) < 10){
				this.textField.setText(datalist.get(i).toString());
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		if(this.drawlist == null) {
			return;
		}
		
		this.drawlist = this.getDrawList(this.getWidth()-20,this.getHeight()-108);
		this.panelcenter.remove(0);
		ScatterJPanel panel = new ScatterJPanel(this.drawlist,this.getWidth()-20,this.getHeight()-108,maxx-minx,maxy-miny);
		panel.setOpaque(false);
		this.panelcenter.add(panel);
		this.setVisible(true);
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}



