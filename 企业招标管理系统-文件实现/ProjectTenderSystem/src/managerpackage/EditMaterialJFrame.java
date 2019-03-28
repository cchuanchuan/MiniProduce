package managerpackage;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.ProductMaterial;

public class EditMaterialJFrame extends JFrame implements ActionListener {

	private JTextField numbertext;
	private JTextField nametext;
	private JButton buttonconfirm;
	private JButton buttoncancel;
	File file;
	Map<String, ProductMaterial> map;
	String key;

	public EditMaterialJFrame(Map<String, ProductMaterial> productmaterials, File productmaterialsfile,String meterialnumber) {
		super("Edit Meterials JFrame");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(350, 200);
		this.setLayout(new GridLayout(3,2));
		
		ProductMaterial material = productmaterials.get(meterialnumber);
		
		this.add(new JLabel("Material Numner"));
		this.add(this.numbertext = new JTextField());
		this.numbertext.setText(material.getMaterialnumber());
		
		this.add(new JLabel("Material Name"));
		this.add(this.nametext = new JTextField());
		this.nametext.setText(material.getMaterialname());
		
		this.add(this.buttoncancel = new JButton("Cancel"));
		this.add(this.buttonconfirm = new JButton("Confirm"));
		this.buttoncancel.addActionListener(this);
		this.buttonconfirm.addActionListener(this);
		
		this.file = productmaterialsfile;
		this.map = productmaterials;
		this.key = meterialnumber;
		
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
				this.map.put(productmeatrial.getMaterialnumber(), productmeatrial);
				try {
					FileOutputStream fout = new FileOutputStream(this.file.getAbsolutePath());
					ObjectOutputStream oout = new ObjectOutputStream(fout);
					oout.writeObject(this.map);
					JOptionPane.showMessageDialog(null, "Edit Success:"+productmeatrial);
					this.dispose();
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, "File Not Found,Fail To Add");
					this.dispose();
				}
				
				
				
			}
		}
	}

}
