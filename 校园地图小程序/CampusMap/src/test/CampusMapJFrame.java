package test;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class CampusMapJFrame extends JFrame implements ActionListener, MouseListener {
	String[] vertices = {"鸽子广场","凯旋门","飞翔门","树人广场","情人长廊",
						"月亮湾","墨湖","青青草原","求知泉","图书馆"};
	Edge[] edges = {new Edge(0,4,800),new Edge(0,8,1500),new Edge(0,2,2000),
			new Edge(1,6,700),new Edge(1,3,2000),
			new Edge(2,7,1500),new Edge(2,8,2500),new Edge(2,0,2000),
	        new Edge(3,1,2000),new Edge(3,9,500),new Edge(3,5,300),
	        new Edge(4,5,50),new Edge(4,8,100),new Edge(4,0,800),
	        new Edge(5,4,50),new Edge(5,8,2400),new Edge(5,3,300),
	        new Edge(6,1,700),new Edge(6,9,20),new Edge(6,7,1000),
	        new Edge(7,6,1000),new Edge(7,8,800),new Edge(7,2,1500),
	        new Edge(8,9,10),new Edge(8,7,800),new Edge(8,2,2500),
	        new Edge(8,0,1500),new Edge(8,4,100),new Edge(8,5,400),
	        new Edge(9,8,10),new Edge(9,3,500),new Edge(9,6,20)
	};
	Point location[] = {new Point(150,100),new Point(600,450),
						new Point(550,50),new Point(320,350),
						new Point(100,200),new Point(50,300),
						new Point(550,340),new Point(700,260),
						new Point(380,230),new Point(430,280)
	};
	AdjMatrixGraph<String> graph;
	JPanel panel_top,panel_button;
	JTextField textbegin,textend;
	JButton button;
	public CampusMapJFrame() {
		super();
		this.setSize(800,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		graph = new AdjMatrixGraph<String>(vertices,edges);
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		split.setDividerLocation(50);
		this.panel_top = new JPanel();
		this.panel_button  = new MapJPanel(vertices,location,edges);
		split.setLeftComponent(panel_top);
		split.setRightComponent(panel_button);
		split.setDividerSize(1);
		panel_button.repaint();
		panel_button.addMouseListener(this);
		
		button = new JButton("查询");
		textbegin = new JTextField(15);
		textend = new JTextField(15);
		panel_top.add(new JLabel("起点"));
		panel_top.add(textbegin);
		panel_top.add(new JLabel("终点"));
		panel_top.add(textend);
		panel_top.add(button);
		button.addActionListener(this);
		
		this.add(split);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("查询")) {
			try {
				int start = Integer.parseInt(textbegin.getText())-1;
				int end = Integer.parseInt(textend.getText())-1;
				ArrayList<Integer> list = this.graph.shortestPath(start, end);
				String path = "路线:";
				for(int i=0;list!=null&&i<list.size()-1;i++) {
					path = path+graph.vertexlist.get(list.get(i))+",";
		        }
				path = path.substring(0, path.length()-1);
				path = path+"。距离："+list.get(list.size()-1);
				JOptionPane.showMessageDialog(this, path);
			}catch(Exception e1) {
				JOptionPane.showConfirmDialog(this, "请输入景点序号");
			}
			
			System.out.println("查询");
		}
	}
	
	public static void main(String agrs[]) {
		CampusMapJFrame campmap = new CampusMapJFrame();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i = 0;i < location.length;i++) {
			double dis = Math.sqrt(Math.pow(location[i].getX()-e.getX(), 2)+Math.pow(location[i].getY()-e.getY(), 2));
			if(dis<20) {
				String message = vertices[i];
				for(Edge ee:edges) {
					if(ee.getStart() == i) {
						message = message+"到 "+vertices[ee.getDest()]+"距离："+ee.getWeight()+"; ";
					}
				}
				JOptionPane.showMessageDialog(this, (i+1)+"."+message);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
