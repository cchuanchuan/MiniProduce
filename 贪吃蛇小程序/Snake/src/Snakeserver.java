import java.net.*;

import javax.swing.JOptionPane;

import java.awt.Point;
import java.io.*;


public class Snakeserver {
	private Point[] body;
	private int length;
	public static final int MAXLENTH=50;
	private int head=0;
	private int tail=0;
	private int j;
	ServerSocket server;
	Socket client;
	ObjectOutputStream oout;
	ObjectInputStream oin;
	private int i=10;
	public Snakeserver(int port) throws Exception
	{
		Reader rin=new FileReader("D:\\上机实验\\Java文件\\贪吃蛇.txt");
		BufferedReader bin=new BufferedReader(rin);
		i=Integer.parseInt(bin.readLine());
		
		this.body=new Point[Snakeserver.MAXLENTH];
			try {
				this.server= new ServerSocket(port);
				this.client=server.accept();//等待接收客户端的TCP连接申请
				this.oout=new ObjectOutputStream(client.getOutputStream());
				this.oin=new ObjectInputStream(client.getInputStream());
			} catch (IOException e1) {}
			
			while(true)
			{boolean isbreak=false;
				try {
						head++;
						body[head%body.length]=(Point) this.oin.readObject();
						length=(Integer)this.oin.readObject();
						//JOptionPane.showMessageDialog(null, body[head].toString()+"\n"+length);
					} catch (ClassNotFoundException | IOException e) {
						/*System.out.println(e.getClass().getName());*/break;}	
				for(int i=1;i<length;i++) {
					//if(head-i<0) {j=head-i+50;}
						if(body[head%body.length].equals(body[(head-i)%body.length])==true||
								body[head%body.length].x<20||body[head%body.length].y<20||
								body[head%body.length].x>950||body[head%body.length].y>730)
						{
							isbreak=true;
							//JOptionPane.showInputDialog(123);
							break;
						}
					}
				if(isbreak)
					break;
			}
			try {
				if(i<length)
				{
					FileOutputStream fout=new FileOutputStream("E:\\贪吃蛇.txt");
					PrintWriter pout=new PrintWriter(fout,true);
					pout.println(length+"");
					pout.close();
					fout.close();
				}
				this.oout.writeObject(new Point(length,i));
				this.server.close();
				client.close();
			} catch (IOException e) {}
	}
	
	public static void main(String []arg) throws Exception
	{
		new Snakeserver(3000);
	}
}
