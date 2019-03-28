/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
/**
 *
 * @author Xitroen
 * My Chinese name is Tielong Xue
 * My student id is 1821649
 */
public class ImageDisplay extends JPanel {
    protected int myX, myY;
    protected int myWidth, myHeight;
    protected String myPath;
    protected BufferedImage myImage;
    protected int num;

    public ImageDisplay() {
    	
    }
    public ImageDisplay (String imagePath, int myX, int myY, int myWidth, int myHeight, int num) {
        this.myX = myX;
        this.myY = myY;
        this.myWidth = myWidth;
        this.myHeight = myHeight;
        this.myPath = imagePath;
        this.num = num;
        myImage = FileUtils.loadImage(imagePath);
    }

    public String toString(){
        return "ImageDisplay,"+"myX="+"'"+myX+ "'"+",myY="+"'"+myY+"'"+",myWidth="+"'"+myWidth+"'"+",myHeight="+"'"+myHeight+"'"+",myPath="+"'"+myPath+"'"+",flowerbedID=,"+num;
        
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(myImage, 0, 0, myWidth, myHeight, this);
    }

    @Override
    public int getX() {
        return myX;
    }

    @Override
    public int getY() {
        return myY;
    }

    @Override
    public int getWidth() {
        return myWidth;
    }

    @Override
    public int getHeight() {
        return myHeight;
    }
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
    
}

