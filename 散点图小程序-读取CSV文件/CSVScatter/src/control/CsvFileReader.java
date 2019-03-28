package control;
/**Read the chosen file and store the three column values in ArrayList.*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.CsvModel;

public class CsvFileReader {
	/**open and read file*/
	public static List<CsvModel> FileOpener(File file) {
		List<CsvModel> dataList = new ArrayList<CsvModel>();
		//如果文件存在
		if (file.exists()) {
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
	                    CsvModel date = new CsvModel(d1,d2,d3);
	                    dataList.add(date);
	                }catch(Exception e) {//如有异常及表示该列数据有问题，即跳过该数据
	                	System.out.println(e.getMessage());
	                }
	            }
	        }catch (Exception e) {
	            System.out.println("File Reader Exception："+e.getMessage());
	        }
		}
		return dataList;
	}
	
	
	public static List<String> FileTitles(File file) {
		List<String> dataList = new ArrayList<String>();
		//如果文件存在
		if (file.exists()) {
	        BufferedReader br = null;
	        try {
	            br = new BufferedReader(new FileReader(file));
	            String line = "";
	            line = br.readLine();
	            String strs[] = line.split(",");
	            for(int i=0;i<strs.length;i++) {
	            	dataList.add(strs[i]);
	            }
	        }catch (Exception e) {
	            System.out.println("File Reader Exception："+e.getMessage());
	        }
		}
		return dataList;
	}
}
