import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.net.*;
public class GamePanel extends JPanel implements Runnable, KeyListener {
	private int x,y;
	private int dx,dy;
	private int direction;
	public static final int SOUTH=0, NORTH=1, EAST=2, WEST=3;
	private Snake sk;
	private Food bk;
	
	Image im;
	Graphics g;
	ImageIcon ima=new ImageIcon("D:\\上机实验\\Java文件\\123.png");

	Socket socket;
	ObjectOutputStream oout;
	ObjectInputStream oin;
	Thread thread1;
	public GamePanel(String host,int port) throws IOException {
		JFrame f=new JFrame("贪吃蛇");
		f.setLocation(250, 0);
		f.setSize(1000, 800);
		f.add(this);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

		x=50;
		y=50;
		dx=10;
		dy=10;
		addKeyListener(this);
		// this.setVisible(true);
		// System.out.println(this.getGraphics()); 
		f.setVisible(true);
		
		this.socket=new Socket(host,port);
		this.oin=new ObjectInputStream(this.socket.getInputStream());
		this.oout=new ObjectOutputStream(this.socket.getOutputStream());
		
		sk=new Snake(this);
		bk=new Food(this, sk);
		this.requestFocus();
		
		this.thread1=new Thread(this);
		thread1.start();
		System.out.println("客户端连接");
		Point p;
		while(true)
		{
			try {
				p=(Point)this.oin.readObject();
				if(p!=null) 
					break;
			} catch (ClassNotFoundException e) {}	
		}
		JOptionPane.showMessageDialog(null, "你死了,你的得分是："+p.x+",最高分是："+p.y);
		this.isPaused=!isPaused;
		this.oin.close();
		this.oout.close();
		this.socket.close();
		
	}

	/*public Integer tranx(int x) {
		if(x>this.getWidth())x=x-this.getWidth();
		if(x<0) x=x+this.getWidth();
		return x;
	}
	public Integer trany(int y) {
		if(y>this.getHeight())y=y-this.getHeight();
		if(y<0)y=y+this.getHeight();
		return y;
	}*/
	public void gameUpdate() throws IOException {
		
		this.oout.writeObject(new Point(x,y));
		this.oout.writeObject(sk.length);
		sk.update();
		bk.update(this);

		switch (direction) {
		case SOUTH:
			y=y+dy;
			break;
		case NORTH:
			y=y-dy;
			break;
		case EAST:
			x=x+dx;
			break;
		case WEST:
			x=x-dx;
			break;
		}
		
	}

	public void gameRender(Image im) {
		
		// System.out.println(im);
		
		Graphics dbg=im.getGraphics();
		dbg.drawImage(ima.getImage(),0,0,this.getWidth(),this.getHeight(),null);
		sk.draw(dbg);
		bk.draw(dbg);
	}

	public void gamePaint(Image im) {
		g = this.getGraphics();
		g.drawImage(im, 0, 0, null);
	}

	boolean isPaused = false;
	public void run() {
		while (true) {
			try {
				//Thread.sleep(20);
				Thread.sleep(115-2*sk.length);
			} catch (InterruptedException e) {}
			im=new BufferedImage(this.getWidth(), this.getHeight(),
					BufferedImage.TYPE_4BYTE_ABGR);
			
			if (isPaused==false) {
				try {
					gameUpdate();
				} catch (IOException e) {System.out.println(e.getClass().getName());}
				
			}
			gameRender(im);
			//sk.draw(g);
			//bk.draw(g);
			gamePaint(im);
		}
	}

	public void keyPressed(KeyEvent e) {
		int keycode=e.getKeyCode();
		//System.out.println("keycode=" + keycode);
		if (keycode==KeyEvent.VK_SPACE) {
			isPaused=!isPaused;
		}

		switch (keycode) {
		case KeyEvent.VK_DOWN:
			if(direction!=NORTH)
			direction=SOUTH;
			//System.out.println(direction);
			break;
		case KeyEvent.VK_UP:
			if(direction!=SOUTH)
			direction=NORTH;
			//System.out.println(direction);
			break;
		case KeyEvent.VK_RIGHT:
			if(direction!=WEST)
			direction=EAST;
			//System.out.println(direction);
			break;
		case KeyEvent.VK_LEFT:
			if(direction!=EAST)
			direction=WEST;
			//System.out.println(direction);
			break;

		}

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public int getDirection() {
		return direction;
	}

	public static void main(String[] args) throws IOException {
		//InetAddress address=InetAddress.getLocalHost();
        //System.out.println("本机的IP地址是"+address.getHostAddress());
		GamePanel g=new GamePanel("127.0.0.1",3000);

	}

}
