import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane ;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javazoom.jl.player.Player;

public class Modify extends JFrame implements MouseListener {
	private Icon icon;
	private JLabel label;
	JTextField text=new JTextField(10);
	JButton button1,button2,button3,button4,button5; 
	static File f;
	static URI uri; 
	JPanel contentPane = new JPanel();
	MyFrame gameframe = null;
	
	 /*URI类在某些特定情况下对其组成字段执行转义。建议使用URI管理URL的编码和解码，并使用toURI()和
	  * URI.toURL()实现这两个类之间的转换。在JAVA类库中，URI类不包含任何访问资源的方法，它唯一的作
	  * 用就是解析。而URL类可以打开一个到达资源的流。所以URL类智能作用于那些JAVA类库知道该如何处理
	  * 的模式，例如：http:,heeps:,ftp:,本地文件系统（file:),和Jar文件（jar:）
	  * URI类的作用之一是解析标识符并将它们分解成各个不同的组成部分。可以用如下方法读取：
	  * getSchema
	  * getHost
	  * getPort
	  * getPath
	  * getQuery
	  */
	 static URL url; 
	
	public Modify(String o){
		super(o);
		setBounds(200,200,1000,1000);
		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		setLayout(null);
		
		
	    
		//JButton 要绑定正确
		button1=new JButton("Play");
		button2=new JButton("Introduction");
		button3=new JButton("Music");
		button4=new JButton("Exit");
		button5=new JButton("Scord");
		button1.setBounds(400,100,100,40);
		button2.setBounds(400,200,100,40);
		button3.setBounds(400,300,100,40);
		button4.setBounds(400,400,100,40);
		button5.setBounds(400,500,100,40);
		add(button1);
		add(button2);
	    add(button3);
		add(button4);
		add(button5);
		text=new JTextField(10);
		button2.addMouseListener(this);
		
		
		
		icon = new ImageIcon("file//imag.gif");
		label = new JLabel(icon);
		label.setBounds(-100,-50,900,900);
		this.myEvent();
		
		add(label);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void myEvent(){
		button1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new MyFrame("");
			}
		});
		button3.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			int n =JOptionPane.showConfirmDialog(contentPane,"是否要开启音乐播放？","音效",JOptionPane.YES_NO_OPTION);
			if (n==JOptionPane.YES_OPTION) {
			 Modify.Music();
			}}
		});
		button5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println(123132);
				CountImpl c = new CountImpl();
				JOptionPane.showMessageDialog(null, "您的得分是："+c.getScore());
			}
		});
		
	}
	
	public void mouseClicked(MouseEvent e) {
		JOptionPane.showMessageDialog(this,"你好哦","oo",JOptionPane.INFORMATION_MESSAGE);
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void	mouseReleased(MouseEvent e) {}
	
	
			
	static void Music() {
		  try {
		      f = new File("file//music.mp3"); 
		      /*uri = f.toURI();
		      url = uri.toURL();  //解析地址
		      AudioClip aau; 
		      aau = Applet.newAudioClip(url);
		      aau.loop();  //循环播放
*/		      
		      AudioPlayer musicthread = new AudioPlayer(f);
		      musicthread.start();
		  } catch (Exception e) {
			  System.out.println("2");
			  e.printStackTrace();
		  }
	}
	
	
	
	
	static class MyFrame extends JFrame implements KeyListener {
		//需要重写接口中所有方法
		JPanel contentPane = new JPanel(null);
		private Icon icon,icon2;
		private JLabel lab;
		public MyFrame(String s) {
			super(s);
			setVisible(true);
			setSize(700, 500);
			setLocationRelativeTo(null); //设置窗口相对于组件的位置
			
			// contentPane.setLayout();
			setContentPane(contentPane);
			/*虽然Frame是窗口，但是它不是一个容器，只是一个框架，所以无法直接用
			于添加组件，否则抛出异常，JFrame中有一个Content Pane用于添加窗口能显
			示的组件，提供两种方法：
			1.用getContentPane()方法获得JFrame的内容面板，在对其加入组件
			frame.getContentPane().add(组件名)
			2.建立一个JPanel或JDesktopPane之类的中间容器，把组件添加到容器中，
			用setContentPane()方法把该容器置为JFrame的内容面板：
			JPanel contentPane=new JPanel();
			...//把其他组件添加到JPanel中;
			frame.setContentPane(contentPane);//把contentPane对象设置为frame的内容
			面板
			*/
	
	
			icon = new ImageIcon("file//move.jpg");
			//此处引用图片路径，但注意需要写两个，因为第一个是转义字符。
																						
			lab = new JLabel(icon);
			lab.setBounds(0, 0, 100, 100);
			addKeyListener(this);
	
			contentPane.add(lab);
			JMenuBar menubar = new JMenuBar();
			menubar.add(new JButton("123"));
			this.setJMenuBar(menubar);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
		
		icon2 = new ImageIcon("file//move.jpg");
		lab.setIcon(icon2);
		}
		

		@Override
		public void keyReleased(KeyEvent e) {
			lab.setIcon(icon);
			// setIcon()用于设置此组件的图标
			int x = lab.getX();
			int y = lab.getY();
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				lab.setBounds(x - 50, y, 100, 100);
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				lab.setBounds(x + 50, y, 100, 100);
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				lab.setBounds(x, y - 50, 100, 100);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				lab.setBounds(x, y + 50, 100, 100); 
			}
			if(x==50 && y+50==150) {//因为是释放后realse才会执行，所以y+50,if这最后一句最后执行。
				JOptionPane.showMessageDialog(this,"您是否要继续游戏？","询问",JOptionPane.INFORMATION_MESSAGE );
			}
			System.out.println(lab.getX()+","+lab.getY());
			CountImpl c = new CountImpl();
			boolean boo = c.f(lab.getX(), lab.getY());
			if(!boo) {
				new EndJFrame();
			}
		}
	}
}
