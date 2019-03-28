package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import control.Point1;
import control.Point2;
import model.BondTrading;

public class ScatterJPanel extends JPanel{
	List<Point1> drawlist;//传值的点
	private Point1 pointx,pointy,pointo;
	double lenghtx,lengthy;
	

	
	public ScatterJPanel(List<Point1> drawlist, 
			int width, int height,
			double lengthx,double lengthy) {
		super();
		this.drawlist = drawlist;
		
		this.pointo = new Point1(50,height-80);
        this.pointx = new Point1(width-50,height-80);
        this.pointy = new Point1(50,50);
        this.lenghtx = lengthx;
        this.lengthy = lengthy;
	}

	protected void paintComponent(Graphics g ) {
        g.setColor(Color.BLACK);
        //坐标系
        g.drawLine(pointo.getX(),pointo.getY(),pointx.getX(),pointx.getY());
        g.drawLine(pointo.getX(),pointo.getY(),pointy.getX(),pointy.getY());
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
        
        //画点
        for(Point1 p:this.drawlist) {
        	g.fillRect(p.getX(), p.getY(), 5, 5);
        }

    }


}
