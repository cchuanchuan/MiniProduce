package managerpackage;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class ManagerJTree extends JTree
{
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode root;
	DefaultMutableTreeNode nodeposition1 = new DefaultMutableTreeNode("Tendering Requests");
	DefaultMutableTreeNode nodeposition2 = new DefaultMutableTreeNode("Product Material Specification");
	DefaultMutableTreeNode nodeposition3 = new DefaultMutableTreeNode("Product Category Specification");
	DefaultMutableTreeNode nodeposition4 = new DefaultMutableTreeNode("Generate Tender Reports");
	public ManagerJTree()
	{
		super();
		
		this.treeModel=(DefaultTreeModel)this.getModel();
		this.root=new DefaultMutableTreeNode("PROJECT TENDER SYSTEM");

		this.root.add(nodeposition1);
		nodeposition1.add(new DefaultMutableTreeNode("Add"));
		nodeposition1.add(new DefaultMutableTreeNode("Delete"));
		nodeposition1.add(new DefaultMutableTreeNode("View"));
		nodeposition1.add(new DefaultMutableTreeNode("Edit"));
		
		this.root.add(nodeposition2);
		nodeposition2.add(new DefaultMutableTreeNode("Add"));
		nodeposition2.add(new DefaultMutableTreeNode("Delete"));
		nodeposition2.add(new DefaultMutableTreeNode("Edit"));
		nodeposition2.add(new DefaultMutableTreeNode("View"));
		
		this.root.add(nodeposition3);
		nodeposition3.add(new DefaultMutableTreeNode("Add"));
		nodeposition3.add(new DefaultMutableTreeNode("Delete"));
		nodeposition3.add(new DefaultMutableTreeNode("Edit"));
		nodeposition3.add(new DefaultMutableTreeNode("View"));
		
		this.root.add(nodeposition4);
		this.treeModel.setRoot(root);
	}

}
