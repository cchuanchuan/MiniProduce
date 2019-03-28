/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cw3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileUtils{
	private static File filesave = new File("file//garden.txt");
	//获取目下下所有文件名
	public static ArrayList<String> getFileNames(String dirPath){
        ArrayList<String> names = new ArrayList<String>();
        File dir = new File(dirPath);
        if(dir.isDirectory()){
            for(File f: dir.listFiles()){
                names.add(f.getPath());
            }
        }
        return names;
    }
    //保存文件
    public static boolean saveFBToFile(ArrayList<Flower> toWrite) {
    	if(filesave.exists()) {
			try {
				FileOutputStream fout = new FileOutputStream(filesave.getAbsolutePath());
				ObjectOutputStream oout = new ObjectOutputStream(fout);
	    		oout.writeObject(toWrite);
	    		System.out.println("Garden has been saved to file");
	    		oout.close();
	    		fout.close();
	    		return true;
			} catch (IOException e) {
				System.out.println("Saved Exception" + e.getMessage());
				e.printStackTrace();
			}
    	}else {
    		System.out.println("There is no file to write to.");
    	}
    	return false;
    }
    //读取文件
    public static ArrayList<Flower> readFBFromFile(){
        if(filesave.exists()){
        	try {
        		ArrayList<Flower> list;
				FileInputStream fin = new FileInputStream(filesave.getAbsolutePath());
				ObjectInputStream oin = new ObjectInputStream(fin);
				list = (ArrayList<Flower>) oin.readObject();
				oin.close();
				fin.close();
				return list;
			} catch (IOException e) {
				System.out.println("There is no file to read from.");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("There is no garden to read.");
				e.printStackTrace();
			}
        }else {
        	System.out.println("There is no file to read from.");
        }
        return null;
    }



}
