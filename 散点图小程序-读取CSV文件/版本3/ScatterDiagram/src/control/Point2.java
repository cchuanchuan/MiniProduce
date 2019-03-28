package control;

public class Point2 {
	public double x,y;
	public Point2(double x,double y) {
		this.x=x;
		this.y=y;
	}
	public Point2() {
		this(0,0);
	}
	public Point2(Point2 p) {
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
