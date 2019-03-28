package test;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class NewJFrame extends JFrame implements ActionListener {
	private JTextField textbegin,textend;
	JButton button;
	private String[] V = {"鸽子广场","凯旋门","飞翔门","树人广场","情人长廊",
			"月亮湾","墨湖","青青草原","求知泉"};
	int[][] adjmatrix= {{0,2230,2000,1150,800,850,930,1700,900,910},
						{2230,0,3200,1220,830,1520,700,1530,730,720},
						{2000,3200,0,3010,2400,2450,2530,1500,2300,2310},
						{1150,1220,3010,0,3050,300,520,1310,510,500}, {800,830,2400,3050,0,50,480,900,100,110,180,950,150,160},
						{930,700,2530,520,480,180,0,830,30,20},
						{1700,1530,1500,1310,900,950,830,0,800,810},
						{900,730,2300,510,100,150,30,800,0,10},
						{910,720,2310,500,110,160,20,810,10,0}
						};
	
	public NewJFrame() {
		super("最短路径");
		this.setSize(400, 100);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(new JLabel("Begin"));
		this.add(this.textbegin = new JTextField(10));
		this.add(new JLabel("End"));
		this.add(this.textend = new JTextField(10));
		this.add(this.button = new JButton("Check"));
		
		this.button.addActionListener(this);
		this.setVisible(true);
	}

	
	
	 public String solve(int S, int T, int[][] E, String V[]){
		 String answer = "";
	        int[] D = new int[V.length];  //起点到每个点的距离
	        int[] ifin = new int[V.length];       //每个点是否已经被加入最短路径的集合
	        int[] pre = new int[V.length];      //每个点最短路前驱
	        int[] path = new int[V.length];     //记录最短路径
	        for(int i = 0; i < V.length; ++i){
	            D[i] = 111111111;                  //初始化所有点的距离为无穷大
	            ifin[i] = 0;//最短路径的集合为空，0表示没有被加入集合
	            pre[i]=-1;
	        }
	        int s = S;//起点
	        ifin[s] = 1;//起点被加入最短路径集合
	        D[s] = 0;
	        for(int loop = 1; loop < V.length; ++loop){
	            for(int i = 0; i < V.length; ++i){
	                if(ifin[i] == 1){
                        continue;//检查这个顶点是否被加入最短路径集合
                 }
	                if(D[i] > D[s] + E[s][i]){
	                    //路径优化选择
	                	
	                    D[i] = D[s] + E[s][i];
	                    pre[i] = s;
	                    //System.out.println("11111"+" "+loop+" "+i+" "+D[i]);
	                }else{
	                	//System.out.println("aaaaa"+" "+loop+" "+i+" "+D[i]);
	                }
	     
	            }
	            int m=-1;
	            for(int j = 0; j < V.length; ++j){
	                //找到未加入的距离最短的点,下一轮从这个点开始
	                if(ifin[j] == 1){
                         continue;
                     }
	                if(m == -1){
                         m = j;
                         //System.out.println("22222");
                         continue;
                 }
	                if(D[j] < D[m]){
	                	//System.out.println("33333");
	                    m = j;
	                }
	            }
	            if(m==-1){
                     break;
             }
	            //如果所有点都加进最短路径集合，算法结束
	            ifin[m] = 1;
	            s=m;
	        }
	        //for(int j = 0; j < V.length; ++j){
	        	//System.out.println(D[j]);
	        //}
	        answer += "最短路距离为" + D[T]+"\n";
	        answer += "路径为:"+"\n";
	        //System.out.println("最短路距离为" + D[T]);
	        //System.out.println("路径为:");
	        int num = 0;
	        while(T!=S){
	            //通过前驱数组记录最短路
	            path[num]=T;
	            T=pre[T];
	            num++;
	        }
	        path[num]=S;
	        while(num>=0){
	            //输出最短路
	        	answer += path[num]+" ";
	            //System.out.print(path[num]+" ");
	            num--;
	        }
	        //System.out.println("");
	        return answer;
	    }




	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.button) {
			int begin = Integer.parseInt(this.textbegin.getText());
			int end = Integer.parseInt(this.textend.getText());
			String answer = solve(begin	,end,adjmatrix,V);
			JOptionPane.showMessageDialog(this, answer);
			
		}
	}
	
	public static void main(String arg[]) {
		new NewJFrame();
	}


}
