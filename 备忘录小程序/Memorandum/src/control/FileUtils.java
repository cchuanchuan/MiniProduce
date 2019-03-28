package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.Memory;

public class FileUtils {
	static File file = new File("file//Memory.txt");
	//读取文件中的备忘录信息
	public static List<Memory> ReadFileMemory() {
		ArrayList<Memory> list = new ArrayList<Memory>();
		if(!file.exists()) {
			return list;
		}
		try {
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream oin = new ObjectInputStream(fin);
			
			list = (ArrayList<Memory>) oin.readObject();
			
			oin.close();
			fin.close();
			return list;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("file is empty.");
		}
		return list;
	}
	
	//保存备忘录信息到文件
	public static boolean saveMemory(List<Memory>list) {
		if(!file.exists()) {
			return false;
		}
		try {
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			
			oout.writeObject(list);
			
			oout.close();
			fout.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
