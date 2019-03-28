import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtils{
	private static File filesave = new File("file//product.txt");
    //保存文件
    public static boolean saveToFile(List<Product> toWrite) {
    	if(filesave.exists()) {
			try {
				FileOutputStream fout = new FileOutputStream(filesave.getAbsolutePath());
				ObjectOutputStream oout = new ObjectOutputStream(fout);
	    		oout.writeObject(toWrite);
	    		System.out.println("file has been saved to file");
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
    public static ArrayList<Product> readFromFile(){
    	ArrayList<Product> list = new ArrayList<Product>();
        if(filesave.exists()){
        	try {
				FileInputStream fin = new FileInputStream(filesave.getAbsolutePath());
				ObjectInputStream oin = new ObjectInputStream(fin);
				list = (ArrayList<Product>) oin.readObject();
				oin.close();
				fin.close();
			} catch (IOException e) {
				System.out.println("There is no file to read from.");
			} catch (ClassNotFoundException e) {
				System.out.println("There is no file to read.");
			}
        }else {
        	System.out.println("There is no file to read from.");
        }
        return list;
    }
    
  	//增加商品
    public static boolean addToFile(Product product) {
    	ArrayList<Product> list = readFromFile();
    	boolean issave = false;
    	list.add(product);
    	issave = saveToFile(list);
    	return issave;
    }



}
