package EntityAndTablemodel;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import MainJFrame.DatabaseConnect;

public class PersonTableModel extends DefaultTableModel
{
	public PersonTableModel(DatabaseConnect dc)
	{
		String sql="use EnterprisePersonnel select * from Person";
		try {
			ResultSet rs=dc.executeQuery(sql);
			ResultSetMetaData rsmd=rs.getMetaData();
			for(int i=1;i<=rsmd.getColumnCount();i++)
				this.addColumn(rsmd.getColumnName(i) );
			for(int i=0;rs.next();i++)
			{
				this.addRow(new Object[]{1,null});
				for(int j=0;j<rsmd.getColumnCount();j++)
				{
					this.setValueAt(rs.getObject(j+1), i, j);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getSQLState());}
	}

}
