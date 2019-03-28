package control;

public class Point1 {
	public int x,y;
	public Point1(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public Point1() {
		this(0,0);
	}
	public Point1(Point1 p) {
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
