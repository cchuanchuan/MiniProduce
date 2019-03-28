package jpanel;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class DrawJPanel extends JPanel implements ActionListener, MouseMotionListener, MouseListener{
	MyCanvas cans;
	JButton buttons[];
	JButton paintButton = null;
	DrawElement beginele,endele;
	int flag = 0;//flag = 0不做图，=1绘制图形=2绘制直线
	int xx=0,yy=0;//鼠标按下位置
	int size = 60;
	DrawElement presselement = null;
	String mypath;
	// 构造方法
	DrawJPanel(String str) {
		this.mypath = str;
		this.setLayout(new BorderLayout());
		JPanel toolpanel = this.createToolPanel();
		
		cans = new MyCanvas();
		cans.setBackground(Color.white);
		cans.addMouseMotionListener(this);
		cans.addMouseListener(this);
		this.add(new JScrollPane(cans));
		this.add(toolpanel,BorderLayout.WEST);
	}
	
	//创建工具栏
	private JPanel createToolPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(108,218,180));
		JToolBar toolBar = new JToolBar();
		toolBar.setOrientation(JToolBar.VERTICAL);
		toolBar.setOpaque(false);
		toolBar.setFloatable(false);//工具栏不可移动
		toolBar.setMargin(new Insets(2, 2, 200, 2));//设置工具栏边框
		
		String names[] = {"Agent","Planning","ROSNode","Connection"};
		//ArrayList<String> toolstr = FileUtils.getFileNames("img");
		toolBar.setLayout(new GridLayout(names.length+1, 1));
		buttons = new JButton[names.length];
		for (int i = 0;i<names.length;i++) {
			ImageIcon action = new ImageIcon("img//"+names[i]+".png");
			buttons[i] = new JButton();
			toolBar.add(buttons[i]);
			buttons[i].setVerticalTextPosition(SwingConstants.BOTTOM);
			buttons[i].setHorizontalTextPosition(SwingConstants.CENTER);
			buttons[i].setIcon(action);
			buttons[i].setText(names[i]);
			buttons[i].setOpaque(false);
			buttons[i].setBorder(null);
		}
		buttons[0].addMouseListener(this);
		buttons[1].addMouseListener(this);
		buttons[2].addMouseListener(this);
		buttons[3].addActionListener(this);
		JButton tempbutton = new JButton("Design Palette");
		tempbutton.addActionListener(this);
		tempbutton.setBorder(BorderFactory.createEtchedBorder());
		panel.add(tempbutton,"North");
		panel.add(toolBar);
		return panel;
	}

	public void clearCancas() {
		this.cans.drawlist = new ArrayList<DrawElement>();
		this.cans.repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttons[3]) {
			flag = 2;
			this.paintButton = null;
			this.presselement = null;
		}
		
		if(e.getActionCommand().equals("Design Palette")) {
			this.clearCancas();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(flag == 1) {
			if(this.paintButton != null) {
				String name = paintButton.getText();
				String path = "img//"+ paintButton.getText()+".png";
				Point p = new Point(e.getX(),e.getY());
				DrawElement drawelement = new DrawElement(name,path,p,"");
				cans.addIcon(drawelement);
				
				
				if(paintButton == buttons[0]) {
					new JavaJFrame(this.mypath,drawelement,this.cans);
				}
				if(paintButton == buttons[1]) {
					new PddlJFrame(this.mypath,drawelement,this.cans);
				}
				if(paintButton == buttons[2]) {
					new PythonJFrame(this.mypath,drawelement,this.cans);
				}
				this.paintButton = null;
				flag = 0;
			}
		}
		
		if(flag == 2) {
			if(e.getSource() instanceof MyCanvas) {
				xx = e.getX();
				yy = e.getY();
				boolean isIcon = false;
				for(DrawElement drawelement:cans.getDrawlist()) {
					if(xx>drawelement.getP1().getX()&&xx<(drawelement.getP1().getX()+size)
							&&yy>drawelement.getP1().getY()&&yy<(drawelement.getP1().getY()+size)) {
						Cursor coursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(drawelement.getPath()).getImage(),new Point(10,20), "stick");
						this.setCursor(coursor);
						this.presselement = drawelement;
						isIcon = true;
						break;
					}
				}
				if(!isIcon) {
					this.setCursor(null);
				}
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		//选择画直线时点击按钮
		if(flag == 2 && !(e.getSource() instanceof JButton)) {
			return;
		}
		if(e.getSource() instanceof JButton) {
			this.beginele = null;
			this.endele = null;
			this.paintButton = (JButton) e.getSource();
			Cursor coursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("img//"+paintButton.getText()+".png").getImage(),new Point(10,20), "stick");
			this.setCursor(coursor);
			this.flag = 1;
		}
		if(e.getSource() instanceof MyCanvas) {
			xx = e.getX();
			yy = e.getY();
			for(DrawElement drawelement:cans.getDrawlist()) {
				if(xx>drawelement.getP1().getX()&&xx<(drawelement.getP1().getX()+size)
						&&yy>drawelement.getP1().getY()&&yy<(drawelement.getP1().getY()+size)) {
					Cursor coursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(drawelement.getPath()).getImage(),new Point(10,20), "stick");
					this.setCursor(coursor);
					this.presselement = drawelement;
				}
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		this.setCursor(null);
		if(e.getSource() instanceof MyCanvas && this.presselement!=null) {
			if(Math.abs(xx-e.getX())<10&&Math.abs(yy-e.getY())<10) {
				return;
			}
			xx = e.getX();
			yy = e.getY();
			this.presselement.setP1(new Point(xx,yy));
			this.presselement = null;
			cans.repaint();
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2) {
			for(DrawElement drawelement:cans.getDrawlist()) {
				if(xx>drawelement.getP1().getX()&&xx<(drawelement.getP1().getX()+size)
						&&yy>drawelement.getP1().getY()&&yy<(drawelement.getP1().getY()+size)) {
					new EditorJFrame(drawelement.getFilename());
					return;
				}
			}
		}
		int xx = e.getX();
		int yy = e.getY();
		
		if(flag == 2) {//画连线
			DrawElement templist=null;
			for(DrawElement drawelement:cans.getDrawlist()) {
				if(xx>drawelement.getP1().getX()&&xx<(drawelement.getP1().getX()+size)
						&&yy>drawelement.getP1().getY()&&yy<(drawelement.getP1().getY()+size)) {
					if(beginele == null) {
						beginele = drawelement;
					}else if(endele == null && beginele != drawelement){
						endele = drawelement;
						templist = new DrawElement(beginele,endele);
						
						String end1 = beginele.getFilename().substring(beginele.getFilename().lastIndexOf(".")+1);
						String end2 = endele.getFilename().substring(endele.getFilename().lastIndexOf(".")+1);
						
						
						
						if(end1.equals("java")&&end2.equals("java")) {
							String strcount = JOptionPane.showInputDialog(this,"请输入插入第一个文件的行数(默认末尾)：");
							int linecount1 = Integer.MAX_VALUE-1;
							try {
								linecount1 = Integer.parseInt(strcount);
							}catch(Exception e1){}
							FileUtils.Write(beginele.getFilename(), "SendDataToAgentBehaviour("+endele.getName()+")",linecount1);
							
							strcount = JOptionPane.showInputDialog(this,"请输入插入第二个文件的行数(默认末尾)：");
							int linecount2 = Integer.MAX_VALUE-1;
							try {
								linecount2 = Integer.parseInt(strcount);
							}catch(Exception e1){}
							FileUtils.Write(endele.getFilename(), "ReceiveDataFromAgentBehaviour("+beginele.getName()+")",linecount2);
						}
						if(end1.equals("java")&&end2.equals("python")) {
							String strcount = JOptionPane.showInputDialog(this,"请输入插入java文件的行数(默认末尾)：");
							int linecount1 = Integer.MAX_VALUE-1;
							try {
								linecount1 = Integer.parseInt(strcount);
							}catch(Exception e1){}
							FileUtils.Write(beginele.getFilename(), "SendDataToROSBehaviour("+endele.getName()+")",linecount1);
						}
						if(end1.equals("python")&&end2.equals("java")) {
							String strcount = JOptionPane.showInputDialog(this,"请输入插入java文件的行数(默认末尾)：");
							int linecount1 = Integer.MAX_VALUE-1;
							try {
								linecount1 = Integer.parseInt(strcount);
							}catch(Exception e1){}
							FileUtils.Write(endele.getFilename(), "ReceiveDataFromROSBehaviour("+beginele.getName()+")",linecount1);
						}
						
						beginele = null;
						endele = null;
					}
				}
			}
			if(templist!=null) {
				flag = 0;
				cans.addIcon(templist);
			}
		}
	}

	class MyCanvas extends Canvas {
		private int size = 60;
		private ArrayList<DrawElement> drawlist = new ArrayList<DrawElement>();
		public void addIcon(DrawElement drawelement) {
			if(drawelement.getType() == 1) {
				Graphics g = this.getGraphics();
				int x = (int)drawelement.getP1().getX();
				int y = (int)drawelement.getP1().getY();
				Image image = new ImageIcon(drawelement.getPath()).getImage();
				g.drawImage(image, x, y,size,size, this);
			}
			
			if(drawelement.getType() == 2) {
				Graphics2D g = (Graphics2D) this.getGraphics();
				Point begin = drawelement.getDrawlist().get(0).getP1();
				Point end = drawelement.getDrawlist().get(1).getP1();
				int xx1 = (int)begin.getX()+size/2;
				int yy1 = (int)begin.getY()+size/2;
				int xx2 = (int)end.getX()+size/2;
				int yy2 = (int)end.getY()+size/2;
				if(xx1 < xx2) {
					if(Math.abs(xx1-xx2)>Math.abs(yy1-yy2)) {
						xx1 = xx1+size/2;
						xx2 = xx2-size/2;
					}else {
						if(yy1>yy2) {
							yy2 += size/2;
							yy1 -= size/2;
						}else {
							yy1 += size/2;
							yy2 -= size/2;
						}
					}
				}else {
					if(Math.abs(xx1-xx2)>Math.abs(yy1-yy2)) {
						xx2 = xx2+size/2;
						xx1 = xx1-size/2;
					}else {
						if(yy1>yy2) {
							yy2 += size/2;
							yy1 -= size/2;
						}else {
							yy1 += size/2;
							yy2 -= size/2;
						}
					}
				}
				drawAL(xx1, yy1, xx2, yy2, g);//调取画又向箭头的方法
				
			}
			this.drawlist.add(drawelement);
		}
		public void addName(DrawElement drawelement) {
			Graphics g = this.getGraphics();
			int x = (int)drawelement.getP1().getX();
			int y = (int)drawelement.getP1().getY();
			g.setFont(new Font("Tahoma", Font.BOLD, 12));
			g.drawString(drawelement.getName(), x, y+size+10);
		}
		
		public void drawAL(int sx, int sy, int ex, int ey, Graphics2D g2) {

			double H = 10; // 箭头高度
			double L = 4; // 底边的一半
			int x3 = 0;
			int y3 = 0;
			int x4 = 0;
			int y4 = 0;
			double awrad = Math.atan(L / H); // 箭头角度
			double arraow_len = Math.sqrt(L * L + H * H); // 箭头的长度
			double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
			double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
			double x_3 = ex - arrXY_1[0]; // (x3,y3)是第一端点
			double y_3 = ey - arrXY_1[1];
			double x_4 = ex - arrXY_2[0]; // (x4,y4)是第二端点
			double y_4 = ey - arrXY_2[1];

			Double X3 = new Double(x_3);
			x3 = X3.intValue();
			Double Y3 = new Double(y_3);
			y3 = Y3.intValue();
			Double X4 = new Double(x_4);
			x4 = X4.intValue();
			Double Y4 = new Double(y_4);
			y4 = Y4.intValue();
			// 画线
			g2.drawLine(sx, sy, ex, ey);
			//
			GeneralPath triangle = new GeneralPath();
			triangle.moveTo(ex, ey);
			triangle.lineTo(x3, y3);
			triangle.lineTo(x4, y4);
			triangle.closePath();
			//实心箭头
			g2.fill(triangle);
			//非实心箭头
			//g2.draw(triangle);

		}
		// 计算
		public double[] rotateVec(int px, int py, double ang,boolean isChLen, double newLen) {

			double mathstr[] = new double[2];
			// 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度
			double vx = px * Math.cos(ang) - py * Math.sin(ang);
			double vy = px * Math.sin(ang) + py * Math.cos(ang);
			if (isChLen) {
				double d = Math.sqrt(vx * vx + vy * vy);
				vx = vx / d * newLen;
				vy = vy / d * newLen;
				mathstr[0] = vx;
				mathstr[1] = vy;
			}
			return mathstr;
		}
		
		public void paint(Graphics g) {
			for(DrawElement drawelement:drawlist) {
				if(drawelement.getType() == 1) {
					int x = (int)drawelement.getP1().getX();
					int y = (int)drawelement.getP1().getY();
					Image image = new ImageIcon(drawelement.getPath()).getImage();
					g.drawImage(image, x, y,size,size, this);
					g.setFont(new Font("Tahoma", Font.BOLD, 12));
					g.drawString(drawelement.getName(), x, y+size+10);
				}
				
				if(drawelement.getType() == 2) {
					Point begin = drawelement.getDrawlist().get(0).getP1();
					Point end = drawelement.getDrawlist().get(1).getP1();
					
					int xx1 = (int)begin.getX()+size/2;
					int yy1 = (int)begin.getY()+size/2;
					int xx2 = (int)end.getX()+size/2;
					int yy2 = (int)end.getY()+size/2;
					if(xx1 < xx2) {
						if(Math.abs(xx1-xx2)>Math.abs(yy1-yy2)) {
							xx1 = xx1+size/2;
							xx2 = xx2-size/2;
						}else {
							if(yy1>yy2) {
								yy2 += size/2;
								yy1 -= size/2;
							}else {
								yy1 += size/2;
								yy2 -= size/2;
							}
						}
					}else {
						if(Math.abs(xx1-xx2)>Math.abs(yy1-yy2)) {
							xx2 = xx2+size/2;
							xx1 = xx1-size/2;
						}else {
							if(yy1>yy2) {
								yy2 += size/2;
								yy1 -= size/2;
							}else {
								yy1 += size/2;
								yy2 -= size/2;
							}
						}
					}
					drawAL(xx1, yy1, xx2, yy2, (Graphics2D)g);//调取画又向箭头的方法
				}
			}
		}
		public ArrayList<DrawElement> getDrawlist() {
			return drawlist;
		}
		public void setDrawlist(ArrayList<DrawElement> drawlist) {
			this.drawlist = drawlist;
		}
	}
	//内部内，表示一个图形
	class DrawElement{
		private String name;
		private String path;
		private String filename;//对应文件地址
		private Point p1;
		private ArrayList<DrawElement> drawlist=null;
		private int type=1;//type=1为图案，type=2为线条
		public DrawElement(String name, String path, Point p1,String filename) {
			super();
			this.name = name;
			this.path = path;
			this.p1 = p1;
			this.filename = filename;
		}
		
		public DrawElement(DrawElement beginele,DrawElement endele) {
			this(null,null,new Point(-1,-1),null);
			this.drawlist = new ArrayList<DrawElement>();
			drawlist.add(beginele);
			drawlist.add(endele);
			this.type=2;
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public Point getP1() {
			return p1;
		}
		public void setP1(Point p1) {
			this.p1 = p1;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}

		public ArrayList<DrawElement> getDrawlist() {
			return drawlist;
		}

		public void setDrawlist(ArrayList<DrawElement> drawlist) {
			this.drawlist = drawlist;
		}

		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}
		
		
	}
	
	
}
