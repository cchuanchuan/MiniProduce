package test;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JPanel;

//此类为地图JPanel类，传入景点名字，路线信息，景点坐标，自动绘制景点地图
public class MapJPanel extends JPanel {
	String[] vertices;//景点名字
	Point location[];//位置信息
	Edge[] edges;//景点路线信息
	
	public MapJPanel(String[] vertices, Point[] location, Edge[] edges) {
		super();
		this.vertices = vertices;
		this.location = location;
		this.edges = edges;
		this.setLayout(new GridLayout(1,1));
	}

	protected void paintComponent(Graphics g) {
        int count = 0;
        for(Point p:location) {
        	g.fillOval(p.x-5, p.y-5, 10, 10);
        	g.drawString((count+1)+"."+vertices[count], p.x+10, p.y+5);
        	count++;
        }
        for(Edge e:edges) {
        	if(e.getStart()<e.getDest()) {
        		Point start = location[e.getStart()];
        		Point end = location[e.getDest()];
        		g.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
        	}
        }
    }

}
