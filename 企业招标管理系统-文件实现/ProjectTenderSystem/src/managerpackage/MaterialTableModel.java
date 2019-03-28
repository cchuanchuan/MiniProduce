package managerpackage;

import java.io.File;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import model.ProductMaterial;
import model.Tender;

public class MaterialTableModel extends DefaultTableModel{
	private Map<String, ProductMaterial> map;
	private File file;
	public MaterialTableModel(Map<String, ProductMaterial> tenders)
	{
		super();
		this.map = tenders;
		this.addColumn("MaterialNumber");
		this.addColumn("MaterialName");
		
		int i = 0;
		for(String key : this.map.keySet()) {
			this.addRow(new Object[]{});
			this.setValueAt(this.map.get(key).getMaterialnumber(), i, 0);
			this.setValueAt(this.map.get(key).getMaterialname(), i, 1);
			
			i++;
		}
	}
}
