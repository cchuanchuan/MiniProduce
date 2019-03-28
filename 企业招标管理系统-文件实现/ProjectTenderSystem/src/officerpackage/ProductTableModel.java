package officerpackage;

import java.io.File;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import model.Product;
import model.ProductMaterial;
import model.Staff;
import model.Tender;

public class ProductTableModel extends DefaultTableModel{
	private Map<String, Product> map;
	public ProductTableModel(Map<String, Product> products)
	{
		super();
		this.map = products;
		this.addColumn("Number");
		this.addColumn("Name");
		this.addColumn("Brand");
		this.addColumn("Model");
		this.addColumn("Type");
		this.addColumn("Material");
		
		int i = 0;
		for(String key : this.map.keySet()) {
			this.addRow(new Object[]{});
			this.setValueAt(this.map.get(key).getProductnumber(), i, 0);
			this.setValueAt(this.map.get(key).getProductname(), i, 1);
			this.setValueAt(this.map.get(key).getProductbrand(), i, 2);
			this.setValueAt(this.map.get(key).getProductmodel(), i, 3);
			this.setValueAt(this.map.get(key).getProducttype().getTypename(), i, 4);
			this.setValueAt(this.map.get(key).getProductmaterial().getMaterialname(), i, 5);
			
			i++;
		}
	}
}
