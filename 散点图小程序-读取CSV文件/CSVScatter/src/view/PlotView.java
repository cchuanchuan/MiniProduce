package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import model.MyPoint;


//JPanel子类，重构paintComponent方法，实现传入数据画图
public class PlotView extends JPanel{
	List<MyPoint> drawlist;//绘图点坐标
	private MyPoint pointx,pointy,pointo;//x，y轴终点及原点坐标
	double lenghtx,lengthy;//坐标轴长度
	
	//构造方法
	public PlotView(List<MyPoint> drawlist, 
			int width, int height,
			double lengthx,double lengthy) {
		super();
		this.drawlist = drawlist;
		
		this.pointo = new MyPoint(50,height-80);
        this.pointx = new MyPoint(width-50,height-80);
        this.pointy = new MyPoint(50,50);
        this.lenghtx = lengthx;
        this.lengthy = lengthy;
	}

	//paintComponent方法，实现绘制点
	protected void paintComponent(Graphics g ) {
        g.setColor(Color.BLACK);
        //绘制坐标系
        g.drawLine(pointo.getX(),pointo.getY(),pointx.getX(),pointx.getY());
        g.drawLine(pointo.getX(),pointo.getY(),pointy.getX(),pointy.getY());
        //绘制箭头
        g.drawLine(pointx.getX(),pointx.getY(),pointx.getX()-20,pointx.getY()+10);
        g.drawLine(pointx.getX(),pointx.getY(),pointx.getX()-20,pointx.getY()-10);
        g.drawLine(pointy.getX(),pointy.getY(),pointy.getX()-10,pointy.getY()+20);
        g.drawLine(pointy.getX(),pointy.getY(),pointy.getX()+10,pointy.getY()+20);
        
        //画刻度
        g.drawString("0", pointo.getX()-10, pointo.getY()+10);
        double scalex = (pointx.getX()-pointo.getX())/10;
        for(int i = 1;i<10;i++) {
        	int temp = (int)Math.round(i*scalex);
        	g.drawLine(pointo.getX()+temp, pointo.getY(), pointo.getX()+temp, pointo.getY()-10);
        	g.drawString(""+(int)Math.round(lenghtx/10*i), pointo.getX()+temp-10, pointo.getY()+15);
        }
        double scaley = (pointo.getY()-pointy.getY())/10;
        for(int i = 0;i<10;i++) {
        	int temp = (int)Math.round(i*scaley);
        	g.drawLine(pointo.getX(), pointo.getY()-temp, pointo.getX()+10, pointo.getY()-temp);
        	g.drawString(""+(int)Math.round(lengthy/10*i), pointo.getX()-30, pointo.getY()-temp+5);
        }
        
        //改变颜色
        g.setColor(new Color(28,97,165));
        //画点
        for(MyPoint p:this.drawlist) {
        	g.fillOval(p.getX(), p.getY(), 7, 7);
        }
    }
}
