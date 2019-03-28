/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Xitroen
 * My Chinese name is Tielong Xue
 * My student id is 1821649
 */
public class Task {
    public static JFrame myWindow;
    private static ArrayList<ImageDisplay> myFlowerbeds = new ArrayList<ImageDisplay>(); 
    private static ArrayList<Flowerbed> myFlowerbeds2 = new ArrayList<Flowerbed>(); 
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        myWindow = new JFrame();
        myWindow.setVisible(true);
        int myWidth = 1000;
        int myHeight = 1000;
        myWindow.setSize(myWidth, myHeight);
        Color color = new Color(34,139,34);
        myWindow.getContentPane().setBackground(color);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FileUtils.readFlowerbedFromFile();
        UserInterface.showMenu();
    }
    
    protected static void saveFlowerbed() {
        FileUtils.saveFlowerbedToFile(myFlowerbeds,myFlowerbeds2);
    }
    
    

    protected static void addImage(String imagePath, int x, int y, int width, int height, int num){
        ImageDisplay myImage;
        myImage = new ImageDisplay(imagePath, x, y, width, height, num);
        myWindow.add(myImage);
        myFlowerbeds.add(myImage);
        myWindow.repaint();
    }
    
    protected static void reSet() {
    	myFlowerbeds = new ArrayList<ImageDisplay>(); 
        myFlowerbeds2 = new ArrayList<Flowerbed>();
        myWindow = new JFrame();
        myWindow.setVisible(true);
        int myWidth = 1000;
        int myHeight = 1000;
        myWindow.setSize(myWidth, myHeight);
        Color color = new Color(34,139,34);
        myWindow.getContentPane().setBackground(color);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FileUtils.readFlowerbedFromFile();
    }
    
    protected static void addFlower(Flowerbed flowered) {
    	myFlowerbeds2.add(flowered);
    }
    
    protected static void removeImage(String imagePath, int x, int y, int width, int height, int num) {
        ImageDisplay myImage;
        myImage = new ImageDisplay(imagePath, x, y, width, height, num);
        myFlowerbeds.remove(myImage);
    }
   
    public static JFrame getMyWindow() {
	    return myWindow;
	}
	public static ArrayList<ImageDisplay> getMyFlowerbeds() {
        return myFlowerbeds;
    }

	public static ArrayList<Flowerbed> getMyFlowerbeds2() {
		return myFlowerbeds2;
	}

	public static void setMyFlowerbeds2(ArrayList<Flowerbed> myFlowerbeds2) {
		Task.myFlowerbeds2 = myFlowerbeds2;
	}

	public static void setMyFlowerbeds(ArrayList<ImageDisplay> myFlowerbeds) {
		Task.myFlowerbeds = myFlowerbeds;
	}

	public static void setMyWindow(JFrame myWindow) {
		Task.myWindow = myWindow;
	}
	
}
