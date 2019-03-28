package adminpackage;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class AdminJTree extends JTree
{
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode root;
	DefaultMutableTreeNode nodeposition1 = new DefaultMutableTreeNode("Add Account");
	DefaultMutableTreeNode nodeposition2 = new DefaultMutableTreeNode("Delete Account");
	DefaultMutableTreeNode nodeposition3 = new DefaultMutableTreeNode("View Account");
	DefaultMutableTreeNode nodeposition4 = new DefaultMutableTreeNode("Edit Account");
	DefaultMutableTreeNode nodeposition5 = new DefaultMutableTreeNode("Login Log");
	public AdminJTree()
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
