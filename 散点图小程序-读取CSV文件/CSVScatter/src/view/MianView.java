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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileFilter;

import control.CsvFileFilter;
import control.CsvFileReader;
import model.CsvModel;
import model.MyPoint;
import model.MyPoint2;

class MainView extends JFrame {
	private JButton buttonopen,buttonquit;
	private JLabel labelname;
	private JComboBox comboxx,comboxy;
	JTextPane textarea;
	JPanel panelplot;
	//以上为界面模块
	
	//以下为功能数据
	private List<CsvModel> listdata;//读取数据
	private List<MyPoint> listdraw;//绘图数据
	private double maxx = 0,maxy = 0,minx = 0,miny = 0;
	
	//构造方法
	public MainView() {
		super("Plot View");
		this.setSize(600, 600);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.white);
		
		//上，中，下三个JPanel
		JPanel paneltop = new JPanel();
		JPanel panelbuttom = new JPanel();
		panelplot = new JPanel();
		this.add(panelplot,BorderLayout.CENTER);
		this.add(paneltop,BorderLayout.NORTH);
		this.add(panelbuttom,BorderLayout.SOUTH);
		paneltop.setBackground(Color.WHITE);
		panelbuttom.setBackground(Color.WHITE);
		
		//中间绘图面板
		panelplot.setBackground(Color.WHITE);
		panelplot.setLayout(new GridLayout(1,1));
		panelplot.add(new JLabel(""));
		
		//顶部菜单
		paneltop.add(this.buttonopen = new JButton("Open"));
		paneltop.add(this.labelname = new JLabel("<Name of file>"));
		paneltop.add(this.buttonquit = new JButton("Quit"));
		buttonopen.setBackground(Color.white);
		buttonquit.setBackground(Color.WHITE);
		labelname.setPreferredSize(new Dimension(250,30));
		labelname.setBorder(BorderFactory.createBevelBorder(1));
		
		//底部菜单
		panelbuttom.add(this.comboxx = new JComboBox());
		panelbuttom.add(this.comboxy = new JComboBox());
		panelbuttom.add(this.textarea = new JTextPane());
		textarea.setText("<Detail of a selected trade>");
		textarea.setBorder(BorderFactory.createBevelBorder(1));
		textarea.setPreferredSize(new Dimension(200,60));
		comboxx.setBackground(Color.white);
		comboxy.setBackground(Color.white);
		comboxx.setPreferredSize(new Dimension(160,30));
		comboxy.setPreferredSize(new Dimension(160,30));
		
		//监听器
		comboxx.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					comboxMethod();
				}
			}
		});
		comboxy.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					comboxMethod();
				}
			}
		});
		//点击打开按钮
		buttonopen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadFile();
			}
		});
		//点击退出按钮
		buttonquit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panelplot.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickMethod(e.getX(),e.getY());
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		this.addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				sizeChange();
			}
			@Override
			public void componentMoved(ComponentEvent e) {}
			@Override
			public void componentShown(ComponentEvent e) {}
			@Override
			public void componentHidden(ComponentEvent e) {}
			
		});
		
		this.setVisible(true);
	}
	
	//点击打开按钮调用方法
	public void loadFile() {
		//新建一个JFileChooser并初始化
		JFileChooser chooser = new JFileChooser(".");
		FileFilter filter = new CsvFileFilter();
		chooser.setFileFilter(filter);
		chooser.showOpenDialog(this);
		File file = chooser.getSelectedFile();
		//如果选取了文件
		if(file != null) {
			this.labelname.setText(file.getName());
			this.listdata = CsvFileReader.FileOpener(file);
			this.listdraw = this.getDrawList(this.getWidth()-20,this.getHeight()-108);
			
			List<String> listitles = CsvFileReader.FileTitles(file);
			for(String str:listitles) {
				this.comboxx.addItem(str);
				this.comboxy.addItem(str);
			}
			comboxy.setSelectedIndex(1);
			
			this.panelplot.remove(0);
			PlotView panel = new PlotView(this.listdraw,this.getWidth()-20,this.getHeight()-108,maxx-minx,maxy-miny);
			panel.setOpaque(false);
			this.panelplot.add(panel);
			this.setVisible(true);
		}
	}
	
	//点击图片上点方法
	public void clickMethod(int x,int y) {
		//已经绘图
		if(this.listdraw != null) {
			for(int i = 0;i<this.listdraw.size(); i++) {
				if(Math.sqrt(
						Math.pow((x-this.listdraw.get(i).getX()),2)
						+Math.pow((y-this.listdraw.get(i).getY()),2)
						) < 10){
					this.textarea.setText(listdata.get(i).toString());
				}
			}
		}

	}
	
	//Combox选项改变调用方法
	public void comboxMethod() {
		//如果选项改变，且数据已读入
		if(this.listdata != null) {
			this.listdraw = this.getDrawList(this.getWidth()-20,this.getHeight()-108);
			this.panelplot.remove(0);
			PlotView panel = new PlotView(this.listdraw,this.getWidth()-20,this.getHeight()-108,maxx-minx,maxy-miny);
			panel.setOpaque(false);
			this.panelplot.add(panel);
			this.setVisible(true);
		}
	}
	
	public void sizeChange() {
		//已有绘图
		if(this.listdata != null) {
			this.listdraw = this.getDrawList(this.getWidth()-20,this.getHeight()-108);
			this.panelplot.remove(0);
			PlotView panel = new PlotView(this.listdraw,this.getWidth()-20,this.getHeight()-108,maxx-minx,maxy-miny);
			panel.setOpaque(false);
			this.panelplot.add(panel);
			this.setVisible(true);
		}
	}
	
	//主函数
	public static void main(String[] args) {
		new MainView();
	}
	
	//计算画图点坐标，根据JComboBox选着的坐标系计算
	public List<MyPoint> getDrawList(int width,int height) {
		MyPoint pointo = new MyPoint(50,height-80);
		MyPoint pointx = new MyPoint(width-50,height-80);
		MyPoint pointy = new MyPoint(50,50);
		
		List<MyPoint> list = new ArrayList<MyPoint>();
		List<MyPoint2> templist = new ArrayList<MyPoint2>();
		
		//求最值，及点坐标
		//intcomx,intcomy分别表示下拉列表x，y选中项
		int intcomx = this.comboxx.getSelectedIndex();
		int intcomy = this.comboxy.getSelectedIndex();
		double x = intcomx==0?this.listdata.get(0).getYIELD():intcomx==1?this.listdata.get(0).getDAYS_TO_MATURITY():this.listdata.get(0).getAMOUNT_CHF();
		double y = intcomy==0?this.listdata.get(0).getYIELD():intcomy==1?this.listdata.get(0).getDAYS_TO_MATURITY():this.listdata.get(0).getAMOUNT_CHF();
		
		maxx=minx=x;
		maxy=miny=y;
        for(CsvModel csv: this.listdata) {
        	x = intcomx==0?csv.getYIELD():intcomx==1?csv.getDAYS_TO_MATURITY():csv.getAMOUNT_CHF();
			y = intcomy==0?csv.getYIELD():intcomy==1?csv.getDAYS_TO_MATURITY():csv.getAMOUNT_CHF();
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

}