package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import model.BondTrading;

public class FileControl {

	private List<BondTrading> list = new ArrayList<BondTrading>();
	private List<String> titles = new ArrayList<String>();
	
	public FileControl (File file){
        List<BondTrading> dataList = new ArrayList<BondTrading>();
        BufferedReader br = null;
        try { 
            br = new BufferedReader(new FileReader(file));
            String line = "";
            line = br.readLine();
            String str[] = line.split(",");
            titles.add(str[0]);
            titles.add(str[1]);
            titles.add(str[2]);
            while ((line = br.readLine()) != null) { 
            	String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
                try {
                	double d1 = Double.parseDouble(item[0]);
                    double d2 = Double.parseDouble(item[1]);
                    double d3 = Double.parseDouble(item[2]);
                    BondTrading bondtrading = new BondTrading(d1,d2,d3);
                    dataList.add(bondtrading);
                }catch(Exception e) {
                	System.out.println("1:"+e.getMessage());
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.list = dataList;
    }

	public List<BondTrading> getList() {
		return list;
	}

	public void setList(List<BondTrading> list) {
		this.list = list;
	}

	public List<String> getTitles() {
		return titles;
	}

	public void setTitles(List<String> titles) {
		this.titles = titles;
	}
	
	
	
}
