package model;

public class MyPoint2 {
	public double x,y;
	public MyPoint2(double x,double y) {
		this.x=x;
		this.y=y;
	}
	public MyPoint2() {
		this(0,0);
	}
	public MyPoint2(MyPoint2 p) {
		this(p.x,p.y);
	}
	public String toString() {
		return "point"+"("+this.x+","+this.y+")";
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
}
