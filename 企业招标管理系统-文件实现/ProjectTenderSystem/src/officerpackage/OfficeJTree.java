package officerpackage;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class OfficeJTree extends JTree
{
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode root;
	DefaultMutableTreeNode nodeposition1 = new DefaultMutableTreeNode("Add Product");
	DefaultMutableTreeNode nodeposition2 = new DefaultMutableTreeNode("Delete Product");
	DefaultMutableTreeNode nodeposition3 = new DefaultMutableTreeNode("View Product");
	DefaultMutableTreeNode nodeposition4 = new DefaultMutableTreeNode("Edit Product");
	DefaultMutableTreeNode nodeposition5 = new DefaultMutableTreeNode("Add Tender");
	public OfficeJTree()
	{
		super();
		
		this.treeModel=(DefaultTreeModel)this.getModel();
		this.root=new DefaultMutableTreeNode("PROJECT TENDER SYSTEM");

		this.root.add(nodeposition1);
		
		this.root.add(nodeposition2);
		
		this.root.add(nodeposition3);
		
		this.root.add(nodeposition4);
		
		this.root.add(nodeposition5);
		this.treeModel.setRoot(root);
	}

}
