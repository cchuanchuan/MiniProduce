package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import model.BondModel;

public class FileUtils {
	//读取文件信息，并存入List链表
	public static List<BondModel> ReadCSV (File file){
        List<BondModel> dataList = new ArrayList<BondModel>();
        BufferedReader br = null;
        try { 
            br = new BufferedReader(new FileReader(file));
            String line = "";
            line = br.readLine();
            while ((line = br.readLine()) != null) { 
            	String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
                try {
                	double d1 = Double.parseDouble(item[0]);
                    double d2 = Double.parseDouble(item[1]);
                    double d3 = Double.parseDouble(item[2]);
                    BondModel bondtrading = new BondModel(d1,d2,d3);
                    dataList.add(bondtrading);
                }catch(Exception e) {
                	System.out.println("1:"+e.getMessage());
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dataList;
    }
}
