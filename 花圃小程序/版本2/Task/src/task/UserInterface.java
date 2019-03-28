/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.util.Scanner;
import java.io.IOException;
import static java.lang.System.exit;

import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
/**
 *
 * @author Xitroen
 * My Chinese name is Tielong Xue
 * My student id is 1821649
 */
public class UserInterface {
    private static Scanner kb;
    public static void showMenu(){
        kb = new Scanner(System.in);
        System.out.println("Welcome to my Flowerbed!\n");
        System.out.println("Please select:");
        System.out.println("1.\tAdd flowerbed");
        System.out.println("2.\tRemove flowerbed");  
        System.out.println("3.\tSave and exit");  
        int command = getIntInput();
        switch(command){
            case 1:
            	System.out.println("1.\tSingle color");
                System.out.println("2.\tHorizontal multiple colour");
                System.out.println("3.\tVertical multiple colour");
                int kind1 = getIntInput();
                switch(kind1){
                    case 1:
                        getImageInputSingle();
                        break;
                    case 2:
                        getImageInputMultipleHorizontal();
                        break;
                    case 3:
                        getImageInputMultipleVertical();
                        break;
                    default:
                    	System.out.println("Please input a value from 1 to 3");
                        showMenu();
                        return;
                }
                
	        	break;//choose Add flowerbed
        
            case 2://choose remove
            	if(Task.getMyFlowerbeds2().size()==0) {
            		System.out.println("There is no flower");
            		showMenu();
            		return;
            	}
                for(Flowerbed flow:Task.getMyFlowerbeds2()) {
                	System.out.println(flow.getNum()+","+flow);
                }
                System.out.println("Remove the number of flowerbed (Please select a number from 1 to "+Task.getMyFlowerbeds2().size()+"):");
                int removeid = getremoveInput();
                	for(int i=0;i<Task.getMyFlowerbeds2().size();i++) {
                    	if(Task.getMyFlowerbeds2().get(i).getNum() == removeid) {
                    		//System.out.println("remove2"+Task.getMyFlowerbeds2().get(i));
                    		Task.getMyFlowerbeds2().remove(i);
                    		i = i-1;
                    	}
                    }
                	for(int i = 0;i<Task.getMyFlowerbeds().size();i++) {
                		if(Task.getMyFlowerbeds().get(i).getNum() == removeid) {
                			//System.out.println("remove"+Task.getMyFlowerbeds().get(i));
                    		Task.getMyFlowerbeds().remove(i);
                    		i = i-1;
                    	}
                	}
                Task.saveFlowerbed();
                Task.reSet();
                showMenu();
                return;
            case 3://choose exit
                Task.saveFlowerbed();
                System.out.println("Save successfully");
                System.exit(0);
                break;
            default:
                System.out.println("Please input a value from 1 to 3");
                showMenu();
        }
       
    }
    
    
    @SuppressWarnings("finally")
	private static int getIntInput() {
        int input = 0;
        try{
            input = Integer.parseInt(kb.nextLine());
        }
        catch(NumberFormatException e) {
            System.out.println("That is not an int; please try again");
            input = getIntInput();
        }
        finally{
            return input;    
        }
    }
    
    @SuppressWarnings("finally")
	private static int getImageInput() {
        int input = 0;
        try{
            input = Integer.parseInt(kb.nextLine());
            if(input<1||input>5) {
            	System.out.println("please input number between 1 to 5");
            	input = getImageInput();
            }
        }
        catch(NumberFormatException e) {
            System.out.println("That is not an int; please try again");
            input = getImageInput();
        }
        finally{
            return input;    
        }
    }
    @SuppressWarnings("finally")
	private static int getremoveInput() {
        int input = 0;
        try{
            input = Integer.parseInt(kb.nextLine());
            if(input<1||input>Task.getMyFlowerbeds2().size()) {
            	System.out.println("please input number between 1 to "+Task.getMyFlowerbeds2().size());
            	input = getremoveInput();
            }
        }
        catch(NumberFormatException e) {
            System.out.println("That is not an int; please try again");
            input = getremoveInput();
        }
        finally{
            return input;    
        }
    }
    

    private static void getImageInputSingle() {
    	int id = 0;
    	for(int i = 1;i<Task.getMyFlowerbeds2().size()+2;i++) {
    		boolean isid = true;
    		for(Flowerbed flow:Task.getMyFlowerbeds2()) {
    			if(flow.getNum() == i) {
    				isid = false;
    				break;
    			}
    		}
    		if(isid) {
    			id = i;
    			break;
    		}
    	}
    	
        System.out.println("Adding flower:");
        ArrayList<String> fileNames = FileUtils.getFileNames("pic");
        int counter = 1; 
        System.out.println("Please input the number of the flower you want:");
        String Flowers[] = new String[5];
        Flowers[0]="Red";Flowers[1]="Blue";Flowers[2]="Green";Flowers[3]="Pink";Flowers[4]="Yellow";
        for(String s :fileNames){
            if(s.endsWith(".png")){
                System.out.println(counter + "." + Flowers[counter-1]);
            }
            counter++;
        }
        int input = getImageInput();
        String path = fileNames.get(input - 1);
        System.out.println("you selected " + path);
        System.out.println("Please input the x of garden:");
        int x = getIntInput();
        while(x>1000){
            System.out.println("please again");
            x = getIntInput();
        }
        System.out.println("Please input the y of garden:");
        int y = getIntInput();
        while(y>1000){
            System.out.println("please again");
            y = getIntInput();
        }
        System.out.println("Please input the width of garden:");
        int width = getIntInput();
        System.out.println("Please input the height of garden:");
        int height = getIntInput();
        
        for(int i = x, m = 30; width - m >= 0;i = i + 30, m = m + 30){
            for(int j = y, n = 30; height - n >= 0; j = j + 30, n = n +30){
                Task.addImage(path, i, j, 30 , 30, id);
            }
        }
        //add方法写入文件
        Flowerbed garden = new Flowerbed(x,y,width,height,id,"Single");
        Task.getMyFlowerbeds2().add(garden);
        Task.saveFlowerbed();
        showMenu();
    } 
 
        private static void getImageInputMultipleHorizontal(){
        	int id = 0;
        	for(int i = 1;i<Task.getMyFlowerbeds2().size()+2;i++) {
        		boolean isid = true;
        		for(Flowerbed flow:Task.getMyFlowerbeds2()) {
        			if(flow.getNum() == i) {
        				isid = false;
        				break;
        			}
        		}
        		if(isid) {
        			id = i;
        			break;
        		}
        	}
                    System.out.println("Please input the x of garden:");
                    int x = getIntInput();
                    System.out.println("Please input the y of garden:");
                    int y = getIntInput();
                    System.out.println("Please input the width of garden:");
                    int width = getIntInput();
                    System.out.println("Please input the height of garden:");
                    int height = getIntInput();
                    int k = height/30;
                    int j = y;
                    for(int p=1;p<=k;p++){
                    System.out.println("Please input the color of line " + p +" :");
                        
                    ArrayList<String> fileNames = FileUtils.getFileNames("pic");
                    int counter = 1; 
                    String Flowers[] = new String[6];
                    Flowers[0]="Red";Flowers[1]="Blue";Flowers[2]="Green";Flowers[3]="Pink";Flowers[4]="Yellow";
                    for(String s :fileNames){
                        if(s.endsWith(".png")){
                            System.out.println(counter + "." + Flowers[counter-1]);
                        }
                        counter++;
                    }                        
                        
                        int kind = getImageInput();
                        String path = "pic/" + kind + ".png";
                        for(int i = x, m = 30; width - m >= 0;i = i + 30, m = m + 30){
                            Task.addImage(path, i, j, 30 , 30, id);
                        }                         
                        j = j + 30;
                    }
                    
                    //add方法写入文件
                    Flowerbed garden = new Flowerbed(x,y,width,height,id,"Single");
                    Task.getMyFlowerbeds2().add(garden);
                    Task.saveFlowerbed();
                    showMenu();
        }
        
        
        private static void getImageInputMultipleVertical(){
        	int id = 0;
        	for(int i = 1;i<Task.getMyFlowerbeds2().size()+2;i++) {
        		boolean isid = true;
        		for(Flowerbed flow:Task.getMyFlowerbeds2()) {
        			if(flow.getNum() == i) {
        				isid = false;
        				break;
        			}
        		}
        		if(isid) {
        			id = i;
        			break;
        		}
        	}
                    System.out.println("Adding flower:");
                    System.out.println("Please input the x of garden:");
                    int x = getIntInput();
                    System.out.println("Please input the y of garden:");
                    int y = getIntInput();
                    System.out.println("Please input the width of garden:");
                    int width = getIntInput();
                    System.out.println("Please input the height of garden:");
                    int height = getIntInput();
                    int k = width/30;
                    int i = x;
                    for(int p=1;p<=k;p++){
                        System.out.println("Please input the color of row " + p +" :");
                        
	                    ArrayList<String> fileNames = FileUtils.getFileNames("pic");
	                    int counter = 1; 
	                    String Flowers[] = new String[6];
	                    Flowers[0]="Red";Flowers[1]="Blue";Flowers[2]="Green";Flowers[3]="Pink";Flowers[4]="Yellow";
	                    for(String s :fileNames) {
	                        if(s.endsWith(".png")) {
	                            System.out.println(counter + "." + Flowers[counter-1]);
	                        }
	                        counter++;
	                    }
	                        
                        int kind = getImageInput();
                        String path = "pic/" + kind + ".png";
                        for(int j = y, m = 30; width - m >= 0;j = j + 30, m = m + 30){
                            Task.addImage(path, i, j, 30 , 30, id);
                        }                         
                        i = i + 30;
                    }
                    
                    //add方法写入文件
                    Flowerbed garden = new Flowerbed(x,y,width,height,id,"Single");
                    Task.getMyFlowerbeds2().add(garden);
                    Task.saveFlowerbed();
                    showMenu();
                }

        
        
    }
        

