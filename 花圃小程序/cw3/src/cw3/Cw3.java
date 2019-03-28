/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cw3;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
//花圆类，表示一个花园对象
public class Cw3 {
    private JFrame myWindow;//窗口对象
    private ArrayList<Flower> myflowers;//花坛对象，使用ArrayList保存
    
    //构造方法
    public Cw3() {
    	myWindow = new JFrame();
    	myWindow.setTitle("Garden");
        myWindow.setSize(800, 800);
        myWindow.setLayout(null);
        myWindow.getContentPane().setBackground(new Color(20,100,20));
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.myflowers = FileUtils.readFBFromFile();
        if(myflowers!=null) {
        	for(Flower flo:myflowers) {
        		this.addGarden(flo);
        	}
        }else {
        	this.myflowers = new ArrayList<Flower>();
        }
        myWindow.setVisible(true);
    }
    
    //保存花园对象
    protected void saveGarden(){
        FileUtils.saveFBToFile(this.myflowers);
    }
    
    //增加花坛到JFrame
    protected boolean addGarden(Flower flower) {
    	myWindow.add(flower);
    	flower.setSize(flower.getWidth(), flower.getHeight());
    	flower.setLocation(flower.getMyX(), flower.getMyY());
    	
        myWindow.repaint();
        return true;
    }

    //以下方法为get，set方法
	public JFrame getMyWindow() {
		return myWindow;
	}

	public void setMyWindow(JFrame myWindow) {
		this.myWindow = myWindow;
	}

	public ArrayList<Flower> getMyflowers() {
		return myflowers;
	}

	public void setMyflowers(ArrayList<Flower> myflowers) {
		this.myflowers = myflowers;
	}
    
}
    

