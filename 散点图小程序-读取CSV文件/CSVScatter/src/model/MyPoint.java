package model;

//int类型点类，用来作图，表示图上点坐标
public class MyPoint {
	public int x,y;
	public MyPoint(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public MyPoint() {
		this(0,0);
	}
	public MyPoint(MyPoint p) {
		this(p.x,p.y);
	}
	public String toString() {
		return "point"+"("+this.x+","+this.y+")";
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
