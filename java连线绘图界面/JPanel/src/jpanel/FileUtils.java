package jpanel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileUtils {
	public static void Write(String path,String write,int linecount) {
		int intcount = linecount;
		File filesave =new File(path);
		try {
			FileReader fin = new FileReader(filesave);
			BufferedReader bin = new BufferedReader(fin);
			String line = "";
			ArrayList<String>templist = new ArrayList<String>();
			while((line = bin.readLine())!=null) {
				templist.add(line);
			}
            FileOutputStream fos = new FileOutputStream(filesave);
            PrintWriter pw = new PrintWriter(fos);
            for(int i=0;i<templist.size();i++) {
            	if(i<intcount-1) {
            		pw.println(templist.get(i));
            	}else {
            		pw.println(write);
            		pw.println(templist.get(i));
            		intcount = Integer.MAX_VALUE;
            	}
            }
            if(intcount != Integer.MAX_VALUE) {
            	pw.println(write);
            }
            pw.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static ArrayList<String> reader(String path) {
		File filesave =new File(path);
		ArrayList<String>templist = new ArrayList<String>();
		try {
			FileReader fin = new FileReader(filesave);
			BufferedReader bin = new BufferedReader(fin);
			String line = "";
			while((line = bin.readLine())!=null) {
				templist.add(line);
			}
			bin.close();
			fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return templist;
	}
	
	public static void createFile(String path ,String name) {
		File file = new File(path+"//"+name);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
