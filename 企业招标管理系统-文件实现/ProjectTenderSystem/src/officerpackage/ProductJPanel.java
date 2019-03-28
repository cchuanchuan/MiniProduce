package officerpackage;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import managerpackage.TenderTableModels;
import model.Product;
import model.ProductMaterial;
import model.ProductType;

public class ProductJPanel extends JPanel implements ActionListener, ItemListener {
	JPanel panel_top = new JPanel();
	JPanel panel_button = new JPanel();
	JPanel left = new JPanel();
	JPanel right = new JPanel();
	JSplitPane split2;
	Map<String,Product> products;
	JComboBox typebox = new JComboBox();
	JComboBox materialbox = new JComboBox();
	int count = 0;
	public ProductJPanel(Map<String,Product>products,int width,int height) {
		super();
		this.products = products;
		this.setLayout(new GridLayout(1,1));
		this.split2=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		this.split2.setDividerLocation(100);
		split2.setDividerSize(0);
		this.add(split2);
		split2.setLeftComponent(panel_top);
		split2.setRightComponent(panel_button);
		this.panel_top.setLayout(new GridLayout(1,2));
		this.panel_button.setLayout(new GridLayout(1,1));
		
		this.panel_top.add(left);
		this.panel_top.add(right);
		
		JTable table=new JTable(new ProductTableModel(this.products));
		DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.setDefaultRenderer(Object.class, renderer);
		this.panel_button.add(new JScrollPane(table));
		
		this.left.setLayout(new GridLayout(2,1));
		this.left.add(new JLabel("Type"));
		this.left.add(typebox);
		this.right.setLayout(new GridLayout(2,1));
		this.right.add(new JLabel("Material"));
		this.right.add(materialbox);
		
		this.typebox.addItemListener(this);
		this.materialbox.addItemListener(this);
		this.typebox.addItem("");
		this.materialbox.addItem("");
		
		Map<String,ProductType>typemap = new HashMap();
		Map<String,ProductMaterial>materialmap = new HashMap();
		for(String key:this.products.keySet()) {
			if(typemap.get(this.products.get(key).getProducttype().getTypenumber()) == null) {
				this.typebox.addItem(this.products.get(key).getProducttype().getTypename());
				typemap.put(this.products.get(key).getProducttype().getTypenumber(), this.products.get(key).getProducttype());
			}
			if(materialmap.get(this.products.get(key).getProductmaterial().getMaterialnumber())==null) {
				this.materialbox.addItem(this.products.get(key).getProductmaterial().getMaterialname());
				materialmap.put(this.products.get(key).getProductmaterial().getMaterialnumber(), this.products.get(key).getProductmaterial());
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		System.out.println("131");
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if(e.getStateChange() == ItemEvent.SELECTED) {
			count++;
			if(count > 2) {
				Map<String,Product>productmap = new HashMap();
				for(String key : this.products.keySet()) {
					Product pro = this.products.get(key);
					if((pro.getProducttype().getTypename().equals(this.typebox.getSelectedItem().toString())
								||this.typebox.getSelectedItem().equals(""))
							&&(pro.getProductmaterial().getMaterialname().equals(this.materialbox.getSelectedItem().toString())
									||this.materialbox.getSelectedItem().equals(""))
							) {
						productmap.put(pro.getProductnumber(), pro);
					}
				}
				this.panel_button.remove(0);
				JTable table=new JTable(new ProductTableModel(this.products));
				DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
				renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				table.setDefaultRenderer(Object.class, renderer);
				this.panel_button.add(new JScrollPane(table));
				this.repaint();
				this.setVisible(true);
			}
			
		}
		
		
	}

}
