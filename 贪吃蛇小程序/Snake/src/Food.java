import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;


public class Food {
	
	private GamePanel gameP;
	private Snake snk;
	public Point location;
	public Point size;
	private Random rand;
	

	public Food(GamePanel gp,Snake sk){
		gameP=gp;
		snk=sk;
		rand=new Random();
		location=new Point(Math.abs(rand.nextInt(gp.getWidth()-30)%(gameP.getWidth()-59)+30),Math.abs(rand.nextInt(gp.getHeight()-30)%(gameP.getHeight()-59)+30));
		size=new Point(sk.r,sk.r);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillOval(location.x, location.y,size.x,size.y);
	}
	
	public void update(GamePanel gp){
		if((snk.x-location.x)*(snk.x-location.x)+(snk.y-location.y)*(snk.y-location.y)<(snk.r*snk.r)){
			location=new Point(Math.abs(rand.nextInt(gp.getWidth()-30)%(gameP.getWidth()-59)+30),Math.abs(rand.nextInt(gp.getHeight()-30)%(gameP.getHeight()-59)+30));
			if(snk.length<Snake.MAXLENTH){
				snk.length++;
			}	
		}
	}
}
