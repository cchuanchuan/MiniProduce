import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MainJFrame extends JFrame implements ActionListener {
	JTable table;
	TableModel tablemodel;
	JPanel paneltable,panelbuttom;
	List<Product> list;
	JButton buttonadd,buttonall,buttondelete,buttonchange,buttonexit;
	public MainJFrame() {
		super("商品信息管理");
		this.setSize(500,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.add(this.panelbuttom = new JPanel(), BorderLayout.NORTH);
		this.add(this.paneltable = new JPanel(new GridLayout(1,1)),BorderLayout.CENTER);
		
		this.list = FileUtils.readFromFile();
		this.tablemodel = this.setTableModel(list);
		this.table = new JTable(this.tablemodel);
		paneltable.add(new JScrollPane(this.table));
		
		panelbuttom.add(this.buttonadd = new JButton("增加商品"));
		panelbuttom.add(this.buttonchange = new JButton("修改商品"));
		panelbuttom.add(this.buttonall= new JButton("所有商品"));
		panelbuttom.add(this.buttondelete = new JButton("删除商品"));
		panelbuttom.add(this.buttonexit = new JButton("退出"));
		
		this.buttonadd.addActionListener(this);
		this.buttonall.addActionListener(this);
		this.buttonchange.addActionListener(this);
		this.buttondelete.addActionListener(this);
		this.buttonexit.addActionListener(this);
		
		this.setVisible(true);
	}
	
	public TableModel setTableModel(List<Product> list) {
		String titles[] = {"商品编号","商品名称","商品价格","商品类别"};
		DefaultTableModel model = new DefaultTableModel(titles,0);
		for(Product pro:list) {
			Vector v = new Vector();
			v.add(pro.getID());
			v.add(pro.getName());
			v.add(pro.getPrice());
			v.add(pro.getCategory());
			model.addRow(v);
		}
		return model;
	}
	
	public static void main(String args[]) {
		new MainJFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.buttonadd) {
			this.paneltable.remove(0);
			this.paneltable.add(new AddJPanel());
			this.setVisible(true);
		}
		
		if(e.getSource() == this.buttonall) {
			this.paneltable.remove(0);
			this.list = FileUtils.readFromFile();
			this.tablemodel = this.setTableModel(list);
			this.table = new JTable(this.tablemodel);
			paneltable.add(new JScrollPane(this.table));
			this.setVisible(true);
		}
		
		if(e.getSource() == this.buttonchange) {
			String id = JOptionPane.showInputDialog("请输入修改商品id");
			Product product = null;
			for(Product pro:list) {
				if(pro.getID().equals(id)) {
					product = pro;
					break;
				}
			}
			if(product == null) {
				JOptionPane.showMessageDialog(this, "未查询到改商品");
				return;
			}
			
			this.paneltable.remove(0);
			DeleteJPanel panel = new DeleteJPanel();
			this.paneltable.add(panel);
			panel.texts[0].setText(product.getID());
			panel.texts[1].setText(product.getName());
			panel.texts[2].setText(product.getPrice());
			panel.texts[3].setText(product.getCategory());
			this.setVisible(true);
		}
		
		if(e.getSource() == this.buttondelete) {
			String id = JOptionPane.showInputDialog("请输入修改商品id");
			Product product = null;
			for(Product pro:list) {
				if(pro.getID().equals(id)) {
					product = pro;
					break;
				}
			}
			if(product == null) {
				JOptionPane.showMessageDialog(this, "未查询到改商品");
			}else {
				this.paneltable.remove(0);
				this.list.remove(product);
				FileUtils.saveToFile(list);
				this.tablemodel = this.setTableModel(list);
				this.table = new JTable(this.tablemodel);
				paneltable.add(new JScrollPane(this.table));
				this.setVisible(true);
				JOptionPane.showMessageDialog(this, "删除成功");
			}
		}
		
		if(e.getSource() == this.buttonexit) {
			System.exit(0);
		}


		
	}

}
