package officerpackage;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Product;
import model.ProductMaterial;
import model.ProductType;

public class AddProductJFrame extends JFrame implements ActionListener {
	private JTextField numbertext;
	private JTextField nametext;
	private JTextField brandtext;
	private JTextField modeltext;
	private JComboBox typebox;
	private JComboBox materialbox;
	private JButton buttonconfirm;
	private JButton buttoncancel;
	File file;
	Map<String, Product> productsmap;
	Map<String, ProductType> producttypemap;
	Map<String, ProductMaterial> productmaterialsmap;
	public AddProductJFrame(Map<String, Product> products, Map<String, ProductType> producttype,
			Map<String, ProductMaterial> productmaterials, File productfile) {
		super("Add Product JFrame");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(500, 400);
		this.setLayout(new GridLayout(7,2));
		
		this.file = productfile;
		this.productsmap = products;
		this.productmaterialsmap = productmaterials;
		this.producttypemap = producttype;
		
		this.add(new JLabel("Product Number"));
		this.add(this.numbertext = new JTextField());
		
		this.add(new JLabel("Product Name"));
		this.add(this.nametext = new JTextField());
		
		this.add(new JLabel("Product Brand"));
		this.add(this.brandtext = new JTextField());
		
		this.add(new JLabel("Product Model"));
		this.add(this.modeltext = new JTextField());
		
		this.add(new JLabel("Product Type"));
		this.add(this.typebox = new JComboBox());
		for(String key : this.producttypemap.keySet()) {
			this.typebox.addItem(key);
		}
		
		this.add(new JLabel("Product Material"));
		this.add(this.materialbox = new JComboBox());
		for(String key : this.productmaterialsmap.keySet()) {
			this.materialbox.addItem(key);
		}
		
		this.add(this.buttoncancel = new JButton("Cancel"));
		this.add(this.buttonconfirm = new JButton("Confirm"));
		this.buttoncancel.addActionListener(this);
		this.buttonconfirm.addActionListener(this);
		
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cancel")) {
			this.dispose();
		}
		if(e.getActionCommand().equals("Confirm")) {
			if(!this.nametext.getText().equals("")
					&&!this.numbertext.getText().equals("")) {
				Product product = new Product();
				product.setProductnumber(this.numbertext.getText());
				product.setProductname(this.nametext.getText());
				product.setProductbrand(this.brandtext.getText());
				product.setProductmodel(this.modeltext.getText());
				product.setProducttype(this.producttypemap.get(this.typebox.getSelectedItem().toString()));
				product.setProductmaterial(this.productmaterialsmap.get(this.materialbox.getSelectedItem().toString()));
				
				if(this.productsmap.get(product.getProductnumber()) == null) {
					this.productsmap.put(product.getProductnumber(), product);
					try {
						FileOutputStream fout = new FileOutputStream(this.file.getAbsolutePath());
						ObjectOutputStream oout = new ObjectOutputStream(fout);
						oout.writeObject(this.productsmap);
						JOptionPane.showMessageDialog(null, "Add Success:"+product);
						this.dispose();
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
						JOptionPane.showMessageDialog(null, "File Not Found,Fail To Add");
						this.dispose();
					}
				}else {
					JOptionPane.showMessageDialog(null, "The Product Has Exists,Please Add again.");
				}
			}
		}
		
	}
	

}
