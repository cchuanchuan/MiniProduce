package managerpackage;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.ProductMaterial;
import model.ProductType;

public class AddMeterialsJFrame extends JFrame implements ActionListener {
	private JTextField numbertext;
	private JTextField nametext;
	private JButton buttonconfirm;
	private JButton buttoncancel;
	File file;
	Map<String, ProductMaterial> map;
	public AddMeterialsJFrame(Map<String, ProductMaterial> productmaterials, File productmaterialsfile) {
		super("Add Meterials JFrame");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(350, 200);
		this.setLayout(new GridLayout(3,2));
		
		this.add(new JLabel("Material Number"));
		this.add(this.numbertext = new JTextField());
		this.add(new JLabel("Material Name"));
		this.add(this.nametext = new JTextField());
		this.add(this.buttoncancel = new JButton("Cancel"));
		this.add(this.buttonconfirm = new JButton("Confirm"));
		this.buttoncancel.addActionListener(this);
		this.buttonconfirm.addActionListener(this);
		
		this.file = productmaterialsfile;
		this.map = productmaterials;
		
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cancel")) {
			this.dispose();
		}
		if(e.getActionCommand().equals("Confirm")) {
			if(!this.nametext.getText().equals("")
					&&!this.numbertext.getText().equals("")) {
				ProductMaterial productmeatrial = new ProductMaterial(this.numbertext.getText(),this.nametext.getText());
				if(this.map.get(productmeatrial.getMaterialnumber()) == null) {
					this.map.put(productmeatrial.getMaterialnumber(), productmeatrial);
					try {
						FileOutputStream fout = new FileOutputStream(this.file.getAbsolutePath());
						ObjectOutputStream oout = new ObjectOutputStream(fout);
						oout.writeObject(this.map);
						JOptionPane.showMessageDialog(null, "Add Success:"+productmeatrial);
						this.dispose();
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
						JOptionPane.showMessageDialog(null, "File Not Found,Fail To Add");
						this.dispose();
					}
				}else {
					JOptionPane.showMessageDialog(null, "The Material Has Exists,Please Add again.");
				}
				
				
			}
		}
	}
}
