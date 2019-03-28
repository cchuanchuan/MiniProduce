/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cw3;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
//用户操作菜单
public class UserInterface {
    private static Scanner kb;
    
    private static Cw3 cw3;
    
    public UserInterface() {
    	this.cw3 = new Cw3();
        this.showMenu();
    }
    //显示主菜单
    public void showMenu(){
        kb = new Scanner(System.in);
        System.out.println("Welcome to my garden!\n");
        System.out.println("Please select:");
        System.out.println("1.\tAdd flowers");
        System.out.println("2.\tRemove flowers"); 
        System.out.println("3.\tSave to file");
        System.out.println("4.\tExit Program");
        int command = getIntInput();
        switch(command){
            case 1:
                choosePattern();
                break;
            case 2:
            	deletePattern();
                break;
            case 3:
            	cw3.saveGarden();
            	this.showMenu();
                break;
            case 4:
            	System.exit(0);
            	break;
            default:
                System.out.println("Please input a value from 1 to 3");
                this.showMenu();
        }    
    }
    
    //选择花坛排列方式
    public void choosePattern(){
        System.out.println("Please select:");
        System.out.println("1.\tOnly one kind of flowers");
        System.out.println("2.\tHorizontal stripe ");
        System.out.println("3.\tVertical stripe");  
        System.out.println("=====================================================");
        int command2 = getIntInput();
        if(command2==1 || command2==2 || command2==3) {
        	getGardenInput(command2);
        }else {
        	System.out.println("Please input a value from 1 to 3");
        	this.choosePattern();
        }
    }
    //删除花坛菜单
    public void deletePattern() {
        System.out.println("Please select:");
        ArrayList<Flower> mygardens = cw3.getMyflowers();
        int count = 1;
        for(Flower garden:mygardens) {
        	System.out.println(count+"."+"X="+garden.getMyX()
        			+",Y="+garden.getMyY()
        			+",Width="+garden.getMyWidth()
        			+",Height="+garden.getMyHeight()
        			+",Type="+garden.getType());
        	count++;
        }
        int command3 = getIntInput();
        if(mygardens.get(command3-1)!=null) {
        	mygardens.remove(command3-1);
        	cw3.saveGarden();
        	cw3.getMyWindow().dispose();
        	cw3 = new Cw3();
        	this.showMenu();
        }else {
        	System.out.println("Please input a value from 1 to "+(mygardens.size()+1));
        	this.deletePattern();
       }
   }
    
    //输入一个int类型数据
    @SuppressWarnings("finally")
	private int getIntInput(){
        int input = 0;
        try{
            input = Integer.parseInt(kb.nextLine());
            if(input>1000){
                System.out.println("The number is too big");
                System.out.println("Please input a number smaller than the window");
                input = getIntInput();
            }
        }
        catch(NumberFormatException e){
            System.out.println("That is not an int;please try again");
            input = getIntInput();
        }
        finally{
        	return input;
        }
    }
    

    //增加一个花坛
    private void getGardenInput(int inttype) {
        System.out.println("please input image X");
        int x = getIntInput();
        System.out.println("please input image Y");
        int y = getIntInput();
        System.out.println("please input image width");
        int width = getIntInput();
        System.out.println("please input image height");
        int height = getIntInput();
        
        Flower garden = null;
        //choose Only one kind of flowers
        if(inttype == 1) {
        	System.out.println("Adding Flowerbed:");
            ArrayList<String> fileNames = FileUtils.getFileNames("flower");
            int counter=1;
            System.out.println("Please choose the kind of flowers:");
            for(String s:fileNames){
                if(s.endsWith(".png")) {
                    System.out.println(counter+"."+s);
                    counter++;
                }
            }
            int input = getIntInput();
            String path = fileNames.get(input-1);
            System.out.println("you selected"+path);
            garden = new Flower(x,y,width,height,Flower.normal);
            garden.setPath(path);
        }
        
        //choose Horizontal stripe
        if(inttype == 2) {
        	garden = new Flower(x,y,width,height,Flower.Horizontal);
        }
        
        //choose Vertical stripe
        if(inttype == 3) {
        	garden = new Flower(x,y,width,height,Flower.Vertical);
        }
        
        if(garden != null) {
        	if(cw3.addGarden(garden)) {
        		cw3.getMyflowers().add(garden);
        		cw3.saveGarden();
        	}else {
        		System.out.println("Garden doublication,Please Input again");
        	}
        }
        
        showMenu();
    }
    
    public static void main(String[] args) {
        new UserInterface();
    }
}
