package EntityAndTablemodel;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import MainJFrame.DatabaseConnect;

public class PayTableModel extends DefaultTableModel
{
	public PayTableModel(DatabaseConnect dc)
	{
		String sql="use EnterprisePersonnel select Person.Name,Pay.* from pay,Person where pay.No=Person.No";
		try {
			ResultSet rs=dc.executeQuery(sql);
			ResultSetMetaData rsmt=rs.getMetaData();
			for(int i=1;i<=rsmt.getColumnCount();i++)
				this.addColumn(rsmt.getColumnName(i));
			while(rs.next())
			{
				this.addRow(new Object[]{rs.getString("Name"),rs.getString("No"),
						rs.getInt("Year"),rs.getInt("Month"),rs.getDouble("Base"),
						rs.getDouble("Bonus"),rs.getDouble("Deduct"),rs.getDouble("Fact")});
			}
		} catch (SQLException e) {JOptionPane.showMessageDialog(null, e.getSQLState());}
		
	}
}
