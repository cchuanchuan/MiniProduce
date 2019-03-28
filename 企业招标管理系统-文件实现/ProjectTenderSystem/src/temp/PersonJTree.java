package temp;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class PersonJTree extends JTree
{
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode root;
	DefaultMutableTreeNode NodeDept=new DefaultMutableTreeNode("部门信息");
	DefaultMutableTreeNode NodePosition=new DefaultMutableTreeNode("职位信息");
	public PersonJTree()
	{
		super();
		
		this.treeModel=(DefaultTreeModel)this.getModel();
		this.root=new DefaultMutableTreeNode("PROJECT TENDER SYSTEM");

		this.root.add(new DefaultMutableTreeNode("所有员工"));
		
		this.root.add(NodeDept);
		NodePosition.add(new DefaultMutableTreeNode("132321"));
		this.root.add(NodePosition);

		this.root.add(new DefaultMutableTreeNode("考勤信息"));

		this.root.add(new DefaultMutableTreeNode("工资信息"));
		this.root.add(new DefaultMutableTreeNode("人事异动"));
		this.treeModel.setRoot(root);
	}
	public void addDept(String name)
	{
		this.NodeDept.add(new DefaultMutableTreeNode(name));
	}
	
	public void addPosition(String name)
	{
		this.NodePosition.add(new DefaultMutableTreeNode(name));
	}
}
