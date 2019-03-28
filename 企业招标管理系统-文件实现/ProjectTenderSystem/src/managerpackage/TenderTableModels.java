package managerpackage;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Tender;

public class TenderTableModels extends DefaultTableModel {
	private Map<String, Tender> map;
	private File file;
	public TenderTableModels(Map<String, Tender> tenders)
	{
		super();
		this.map = tenders;
		this.addColumn("Number");
		this.addColumn("Name");
		this.addColumn("CreateTime");
		this.addColumn("DeadLine");
		this.addColumn("Amount");
		this.addColumn("Status");
		this.addColumn("ProductNumber");
		
		int i = 0;
		for(String key : this.map.keySet()) {
			this.addRow(new Object[]{});
			this.setValueAt(this.map.get(key).getTendernumber(), i, 0);
			this.setValueAt(this.map.get(key).getTendername(), i, 1);
			this.setValueAt(this.map.get(key).getTendercreatetime(), i, 2);
			this.setValueAt(this.map.get(key).getTenderdeadline(), i, 3);
			this.setValueAt(this.map.get(key).getTenderamount(), i, 4);
			this.setValueAt(this.map.get(key).getTenderstatus(), i, 5);
			if(this.map.get(key).getProduct() != null) {
				this.setValueAt(this.map.get(key).getProduct().getProductnumber(), i, 6);
			}else {
				this.setValueAt("null", i, 6);
			}
			
			i++;
		}
	}
}
