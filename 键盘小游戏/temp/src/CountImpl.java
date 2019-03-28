import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

public class CountImpl implements Count {

	Point points[] = {new Point(200,300),
			//new Point(100,200),
			new Point(50,100),
			new Point(100,100)};
	//初始顶点
	private static List<Point>listset = null;
	
	//移动走过顶点
	public static List<Point>listmove = new ArrayList<Point>();
	private static String score = "";
	public CountImpl() {
		if(listset == null) {
			listset = new ArrayList<Point>();
			//初始化走过顶点序列
			for(int i = 0;i<points.length;i++) {
				listset.add(points[i]);
			}
		}
		
	}
	@Override
	public boolean f(int x, int y) {
		if(listset.size() == 0) {
			listset = null;
			return false;
		}
		for(Point p:listset) {
			if(x==p.getX()&&y==p.getY()) {
				listmove.add(p);
				listset.remove(p);
				break;
			}
		}
		if(listset.size() == 0) {
			score = listmove.size()+"/"+listset.size()+"√";
			this.listset = null;
			this.listmove = new ArrayList<Point>();
			return false;
		}else {
			score = listmove.size()+"/"+listset.size();
			return true;
		}
	}
	
	public static String getScore() {
		return score;
	}

}
