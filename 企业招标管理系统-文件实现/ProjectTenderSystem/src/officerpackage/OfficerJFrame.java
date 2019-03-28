package officerpackage;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import managerpackage.AddTenderJFrame;
import managerpackage.BackgroundPanel;
import managerpackage.EditTenderJFrame;
import model.Product;
import model.ProductMaterial;
import model.ProductType;
import model.Staff;
import model.Tender;

public class OfficerJFrame extends JFrame implements TreeSelectionListener, ItemListener
{
	JSplitPane split1;
	JPanel panel_right,panel_left;
	JPanel panel_top = new JPanel();
	JPanel panel_button = new JPanel();
	JPanel left = new JPanel();
	JPanel right = new JPanel();
	int count = 0;
	Map<String,Product> products;
	OfficeJTree officejtree;
	OfficeFunction office = new OfficeFunction();
	JComboBox typebox = new JComboBox();
	JComboBox materialbox = new JComboBox();
	public OfficerJFrame()
	{
		super("Project Tender System - Manager");
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.split1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		this.split1.setDividerLocation(220);
		split1.setDividerSize(0);
		this.add(split1);
		this.panel_left=new JPanel(new GridLayout(1,1));
		panel_right=new JPanel(new GridLayout(1,1));
		panel_left.setBackground(Color.red);
		split1.setLeftComponent(panel_left);
		split1.setRightComponent(panel_right);
		
		products = this.office.getProducts();
		typebox.addItemListener(this);
		materialbox.addItemListener(this);
		this.officejtree = new OfficeJTree();
		this.panel_left.add(officejtree);
		officejtree.addTreeSelectionListener(this);
		
		File directory = new File("");
		File logo = new File(directory.getAbsolutePath()+"\\src\\FileStore\\logo.jpg");
		Image imag=new ImageIcon(logo.getAbsolutePath()).getImage();
		panel_right.add(new BackgroundPanel(imag));
		
		this.setVisible(true);
	}

	public void valueChanged(TreeSelectionEvent e) {
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Add Product]")) {
			new AddProductJFrame(this.office.getProducts(),this.office.getProducttype(),this.office.getProductmaterials(),this.office.productfile);
		}
		
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Delete Product]")) {
			String productstr = JOptionPane.showInputDialog(null, "Please Input Product Number");
			if(productstr != null) {
				Product product = this.office.getProducts().remove(productstr);
				if(product != null) {
					try {
						FileOutputStream fout = new FileOutputStream(this.office.getProductfile().getAbsolutePath());
						ObjectOutputStream oout = new ObjectOutputStream(fout);
						oout.writeObject(this.office.getProducts());
						JOptionPane.showMessageDialog(null, "Delete Success : " + product);
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
						JOptionPane.showMessageDialog(null, "File Not Found , Fail To Delete");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Product Not Found , Fail To Delete");
				}
			}
		}
		
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, View Product]")) {
			this.panel_right.remove(0);
			
			
			JSplitPane split2;
			
			
			split2=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			split2.setDividerLocation(100);
			split2.setDividerSize(0);
			this.panel_right.add(split2);
			split2.setLeftComponent(panel_top);
			split2.setRightComponent(panel_button);
			panel_top.setLayout(new GridLayout(1,2));
			panel_button.setLayout(new GridLayout(1,1));
			
			panel_top.add(left);
			panel_top.add(right);
			
			JTable table=new JTable(new ProductTableModel(products));
			DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			table.setDefaultRenderer(Object.class, renderer);
			panel_button.add(new JScrollPane(table));
			
			left.setLayout(new GridLayout(2,1));
			left.add(new JLabel("Type"));
			left.add(typebox);
			right.setLayout(new GridLayout(2,1));
			right.add(new JLabel("Material"));
			right.add(materialbox);
			
			typebox.addItemListener(this);
			//materialbox.addItemListener(this);
			typebox.addItem("");
			materialbox.addItem("");
			
			Map<String,ProductType>typemap = new HashMap();
			Map<String,ProductMaterial>materialmap = new HashMap();
			for(String key:products.keySet()) {
				if(typemap.get(products.get(key).getProducttype().getTypenumber()) == null) {
					this.typebox.addItem(products.get(key).getProducttype().getTypename());
					typemap.put(products.get(key).getProducttype().getTypenumber(), products.get(key).getProducttype());
				}
				if(materialmap.get(products.get(key).getProductmaterial().getMaterialnumber())==null) {
					this.materialbox.addItem(products.get(key).getProductmaterial().getMaterialname());
					materialmap.put(products.get(key).getProductmaterial().getMaterialnumber(), products.get(key).getProductmaterial());
				}
			}
			
			
			this.setVisible(true);
		}
		
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Edit Product]")) {
			String productstr = JOptionPane.showInputDialog(null, "Please Input Product Number");
			if(this.office.getProducts().get(productstr) != null) {
				new EditProductJFrame(this.office.getProducts(),this.office.getProducttype(),this.office.getProductmaterials(),this.office.productfile, productstr);
			}
		}
		
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Add Tender]")) {
			new AddTenderJFrame(this.office.getTenders(),this.office.getProducts(),this.office.getTenderfile());
		}
		
		
		System.out.println(e.getPath().toString());
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
				JTable table=new JTable(new ProductTableModel(productmap));
				DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
				renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				table.setDefaultRenderer(Object.class, renderer);
				this.panel_button.add(new JScrollPane(table));
				this.setVisible(true);
			}
			
		}
		
	}

}
