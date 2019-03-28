package managerpackage;

import java.io.File;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import model.ProductType;
import model.Tender;

public class TypeTableModel extends DefaultTableModel{
	private Map<String, ProductType> map;
	private File file;
	public TypeTableModel(Map<String, ProductType> types)
	{
		super();
		this.map = types;
		this.addColumn("TypeNumber");
		this.addColumn("TypeName");
		
		int i = 0;
		for(String key : this.map.keySet()) {
			this.addRow(new Object[]{});
			this.setValueAt(this.map.get(key).getTypenumber(), i, 0);
			this.setValueAt(this.map.get(key).getTypename(), i, 1);
			
			i++;
		}
	}
}
