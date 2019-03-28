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

import model.ProductType;

public class EditTypeJFrame extends JFrame implements ActionListener {
	private JTextField numbertext;
	private JTextField nametext;
	private JButton buttonconfirm;
	private JButton buttoncancel;
	File file;
	Map<String, ProductType> map;
	public EditTypeJFrame(Map<String, ProductType> producttype, File producttypefile, String typenumber) {
		super("Edit Type JFrame");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(350, 200);
		this.setLayout(new GridLayout(3,2));
		
		ProductType type = producttype.get(typenumber);
		
		this.add(new JLabel("Type Number"));
		this.add(this.numbertext = new JTextField());
		this.numbertext.setText(type.getTypenumber());
		
		this.add(new JLabel("Type Name"));
		this.add(this.nametext = new JTextField());
		this.nametext.setText(type.getTypename());
		
		this.add(this.buttoncancel = new JButton("Cancel"));
		this.add(this.buttonconfirm = new JButton("Confirm"));
		this.buttoncancel.addActionListener(this);
		this.buttonconfirm.addActionListener(this);
		
		this.file = producttypefile;
		this.map = producttype;
		
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
				ProductType producttype = new ProductType(this.numbertext.getText(),this.nametext.getText());
			
				this.map.put(producttype.getTypenumber(),producttype);
				try {
					FileOutputStream fout = new FileOutputStream(this.file.getAbsolutePath());
					ObjectOutputStream oout = new ObjectOutputStream(fout);
					oout.writeObject(this.map);
					JOptionPane.showMessageDialog(null, "Edit Success:"+producttype);
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
