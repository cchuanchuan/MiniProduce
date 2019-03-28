package adminpackage;

import java.io.File;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import model.ProductMaterial;
import model.Staff;
import model.Tender;

public class StaffTableModel extends DefaultTableModel{
	private Map<String, Staff> map;
	private File file;
	public StaffTableModel(Map<String, Staff> staffs)
	{
		super();
		this.map = staffs;
		this.addColumn("Number");
		this.addColumn("Name");
		this.addColumn("Password");
		this.addColumn("Address");
		this.addColumn("ContactNumber");
		this.addColumn("Role");
		
		int i = 0;
		for(String key : this.map.keySet()) {
			this.addRow(new Object[]{});
			this.setValueAt(this.map.get(key).getStaffnumber(), i, 0);
			this.setValueAt(this.map.get(key).getStaffname(), i, 1);
			this.setValueAt(this.map.get(key).getPassword(), i, 2);
			this.setValueAt(this.map.get(key).getStaffaddress(), i, 3);
			this.setValueAt(this.map.get(key).getStaffcontactnumber(), i, 4);
			this.setValueAt(this.map.get(key).getStaffrole(), i, 5);
			
			i++;
		}
	}
}
