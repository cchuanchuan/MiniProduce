/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cw3;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Flower extends JPanel implements Serializable {
	public final static String normal = "n";
	public final static String Horizontal = "h";
	public final static String Vertical = "v";
	
    private int myX;//定点横坐标
    private int myY;//定点纵坐标
    private int myWidth;//花坛长度
    private int myHeight;//花坛宽度
    private String type;//花坛类型，横着排放或竖着排列
    private String backgroundpath = "Background/Grass.png";//花坛背景地址
    private String path;//花地址
    
    //绘制花坛方法
    protected void paintComponent(Graphics g) {
    	File back = new File(this.backgroundpath);
    	Image imag = new ImageIcon(back.getAbsolutePath()).getImage();
		g.drawImage(imag, 0, 0, this.getWidth(), this.getHeight(), this);
		
		//one kind of flowers
		if(this.path != null && this.type.equals(Flower.normal)) {
			File flower = new File(this.path);
	    	Image imag2 = new ImageIcon(flower.getAbsolutePath()).getImage();
	    	int x = 0;
	    	int y = 0;
	    	while(y+30<=this.myHeight) {
	    		if(x+30<=this.myWidth) {
	    			g.drawImage(imag2, x, y, 30, 30, this);
	    			x = x+30;
	    		}else {
	    			x = 0;
	    			y = y+30;
	    		}
	    	}
		}
		
		//Horizontal stripe
		if(this.type.equals(Flower.Horizontal)) {
			int x = 0;
	    	int y = 0;
			ArrayList<String> fileNames = FileUtils.getFileNames("flower");
			
			int index =0;
			File flower = new File(fileNames.get(index));
	    	Image imag2 = new ImageIcon(flower.getAbsolutePath()).getImage();
	    	
			while(y+30<=this.myHeight) {
	    		if(x+30<=this.myWidth) {
	    			g.drawImage(imag2, x, y, 30, 30, this);
	    			x = x+30;
	    		}else {
	    			index = (index + 1)>=fileNames.size()?0:(index + 1);
	    			flower = new File(fileNames.get(index));
	    	    	imag2 = new ImageIcon(flower.getAbsolutePath()).getImage();
	    			x = 0;
	    			y = y + 30;
	    		}
	    	}
			
		}
		
		
		//Vertical stripe
		if(this.type.equals(Flower.Vertical)) {
			int x = 0;
	    	int y = 0;
			ArrayList<String> fileNames = FileUtils.getFileNames("flower");
			
			int index =0;
			File flower = new File(fileNames.get(index));
	    	Image imag2 = new ImageIcon(flower.getAbsolutePath()).getImage();
	    	
			while(x+30<=this.myWidth) {
	    		if(y+30<=this.myHeight) {
	    			g.drawImage(imag2, x, y, 30, 30, this);
	    			y = y+30;
	    		}else {
	    			index = (index + 1)>=fileNames.size()?0:(index + 1);
	    			flower = new File(fileNames.get(index));
	    	    	imag2 = new ImageIcon(flower.getAbsolutePath()).getImage();
	    			y = 0;
	    			x = x + 30;
	    		}
	    	}
		}
		
	}
    
    //花坛构造方法，将花坛数据初始化
    public Flower(int myX, int myY, int myWidth, int myHeight, String type) {
		super();
		this.myX = myX;
		this.myY = myY;
		this.myWidth = myWidth/30*30;
		this.myHeight = myHeight/30*30;
		this.type = type;
		this.setLayout(new GridLayout(1,1));
		this.setBackground(Color.BLACK);
		this.setSize(this.myWidth,this.myHeight);
	}

	public int getMyX() {
		return myX;
	}

	public void setMyX(int myX) {
		this.myX = myX;
	}

	public int getMyY() {
		return myY;
	}

	public void setMyY(int myY) {
		this.myY = myY;
	}

	public int getMyWidth() {
		return myWidth;
	}

	public void setMyWidth(int myWidth) {
		this.myWidth = myWidth;
	}

	public int getMyHeight() {
		return myHeight;
	}

	public void setMyHeight(int myHeight) {
		this.myHeight = myHeight;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBackgroundpath() {
		return backgroundpath;
	}

	public void setBackgroundpath(String backgroundpath) {
		this.backgroundpath = backgroundpath;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
    
}
