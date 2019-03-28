package task;

public class Flowerbed  extends task.ImageDisplay{
	private String type;
	public Flowerbed(int myX, int myY, int myWidth, int myHeight, int num,String type) {
		this.myX = myX;
        this.myY = myY;
        this.myWidth = myWidth;
        this.myHeight = myHeight;
        this.num = num;
        this.type = type;
	}
	@Override
	public String toString() {
		return "Flowerbed Id=" + num + ",x=" + myX + ",y=" + myY + ",width=" + myWidth + ",height="
				+ myHeight + ",type=" + type + ",path=" + "null";
	}
	
	

}
