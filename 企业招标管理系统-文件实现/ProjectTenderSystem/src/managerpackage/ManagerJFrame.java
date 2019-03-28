package managerpackage;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import model.ProductMaterial;
import model.ProductType;
import model.Tender;

public class ManagerJFrame extends JFrame implements TreeSelectionListener
{
	JSplitPane split1;
	JPanel panel_right,panel_left;
	ManagerJTree managerjtree;
	ManagerFunction manager = new ManagerFunction();
	public ManagerJFrame()
	{
		super("Project Tender System - Manager");
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.split1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		this.split1.setDividerLocation(220);
		split1.setDividerSize(0);
		this.add(split1);
		this.panel_left=new JPanel(new GridLayout(1,1));
		panel_right=new JPanel(new GridLayout(1,1));
		panel_left.setBackground(Color.red);
		split1.setLeftComponent(panel_left);
		split1.setRightComponent(panel_right);
		
		
		this.managerjtree=new ManagerJTree();
		this.panel_left.add(managerjtree);
		managerjtree.addTreeSelectionListener(this);
		
		File directory = new File("");
		File logo = new File(directory.getAbsolutePath()+"\\src\\FileStore\\logo.jpg");
		Image imag=new ImageIcon(logo.getAbsolutePath()).getImage();
		panel_right.add(new BackgroundPanel(imag));
		
		
		
		this.setVisible(true);
	}

	public void valueChanged(TreeSelectionEvent e) {
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Tendering Requests, Add]")) {
			new AddTenderJFrame(this.manager.getTenders(),this.manager.getProducts(),this.manager.getTenderfile());
		}
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Tendering Requests, Delete]")) {
			String tenderstr = JOptionPane.showInputDialog(null, "Please Input TenderNumber");
			if(tenderstr != null) {
				Tender tender = this.manager.getTenders().remove(tenderstr);
				if(tender != null) {
					try {
						FileOutputStream fout = new FileOutputStream(this.manager.getTenderfile().getAbsolutePath());
						ObjectOutputStream oout = new ObjectOutputStream(fout);
						oout.writeObject(this.manager.getTenders());
						JOptionPane.showMessageDialog(null, "Delete Success : " + tender);
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
						JOptionPane.showMessageDialog(null, "File Not Found , Fail To Delete");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Tender Not Found , Fail To Delete");
				}
			}
		}
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Tendering Requests, View]")) {
			this.panel_right.remove(0);
			JTable table=new JTable(new TenderTableModels(this.manager.getTenders()));
			DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			table.setDefaultRenderer(Object.class, renderer);
			this.panel_right.add(new JScrollPane(table));
			this.setVisible(true);
		}
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Tendering Requests, Edit]")) {
			String tenderstr = JOptionPane.showInputDialog(null, "Please Input TenderNumber");
			if(this.manager.getTenders().get(tenderstr) != null) {
				new EditTenderJFrame(this.manager.getTenders(), this.manager.getProducts(), this.manager.getTenderfile(),tenderstr);
			}
		}
		
		
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Product Material Specification, Add]")) {
			new AddMeterialsJFrame(this.manager.getProductmaterials(),this.manager.getProductmaterialsfile());
		}
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Product Material Specification, Delete]")) {
			String materialstr = JOptionPane.showInputDialog(null, "Please Input MaterialNumber");
			if(materialstr != null) {
				//System.out.println(this.manager.getProductmaterials());
				//
				ProductMaterial meterial = this.manager.getProductmaterials().remove(materialstr);
				if(meterial != null) {
					try {
						FileOutputStream fout = new FileOutputStream(this.manager.getProductmaterialsfile().getAbsolutePath());
						ObjectOutputStream oout = new ObjectOutputStream(fout);
						oout.writeObject(this.manager.getProductmaterials());
						JOptionPane.showMessageDialog(null, "Delete Success:" + meterial);
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
						JOptionPane.showMessageDialog(null, "File Not Found , Fail To Delete");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Material Not Found , Fail To Delete");
				}
			}
		}
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Product Material Specification, Edit]")) {
			String meterial = JOptionPane.showInputDialog(null, "Please Input MeterialNumber");
			if(this.manager.getProductmaterials().get(meterial) != null) {
				new EditMaterialJFrame(this.manager.getProductmaterials(), this.manager.getProductmaterialsfile(),meterial);
			}
		}
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Product Material Specification, View]")) {
			this.panel_right.remove(0);
			JTable table=new JTable(new MaterialTableModel(this.manager.getProductmaterials()));
			DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			table.setDefaultRenderer(Object.class, renderer);
			this.panel_right.add(new JScrollPane(table));
			this.setVisible(true);
		}
		
		
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Product Category Specification, Add]")) {
			new AddTypeJFrame(this.manager.getProducttype(),this.manager.getProducttypefile());
		}
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Product Category Specification, Delete]")) {
			String typestr = JOptionPane.showInputDialog(null, "Please Input TypeNumber");
			if(typestr != null) {
				ProductType type = this.manager.getProducttype().remove(typestr);
				if(type != null) {
					try {
						FileOutputStream fout = new FileOutputStream(this.manager.getProducttypefile().getAbsolutePath());
						ObjectOutputStream oout = new ObjectOutputStream(fout);
						oout.writeObject(this.manager.getProducttype());
						JOptionPane.showMessageDialog(null, "Delete Success:" + type);
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
						JOptionPane.showMessageDialog(null, "File Not Found , Fail To Delete");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Type Not Found , Fail To Delete");
				}
			}
		}
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Product Category Specification, Edit]")) {
			String type = JOptionPane.showInputDialog(null, "Please Input TypeNumber");
			if(this.manager.getProducttype().get(type) != null) {
				new EditTypeJFrame(this.manager.getProducttype(), this.manager.getProducttypefile(),type);
			}
		}
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Product Category Specification, View]")) {
			this.panel_right.remove(0);
			JTable table=new JTable(new TypeTableModel(this.manager.getProducttype()));
			DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			table.setDefaultRenderer(Object.class, renderer);
			this.panel_right.add(new JScrollPane(table));
			this.setVisible(true);
		}
		
		if(e.getPath().toString().equals("[PROJECT TENDER SYSTEM, Generate Tender Reports]")) {
			new TenderReportsJFrame(this.manager.getTenders());
		}
		System.out.println(e.getPath());
	}
	

}
