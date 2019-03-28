package managerpackage;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Product;
import model.Tender;

public class EditTenderJFrame extends JFrame implements ActionListener {
	private JTextField numbertext;
	private JTextField nametext;
	private JTextField yeartext;
	private JTextField monthtext;
	private JTextField daytext;
	private JTextField standpricetext;
	private JTextField discounttext;
	private JTextField markuptext;
	private JTextField labourcosttext;
	private JComboBox statuscombox;
	private JComboBox productcombox;
	private JButton buttonconfirm;
	private JButton buttoncancel;
	File file;
	Map<String, Tender> tendermap;
	Map<String, Product> productmap;
	String key;
	public EditTenderJFrame(Map<String, Tender> tenders, Map<String, Product> products, File tenderfile,String tendernumber) {
		super("Edit Tender JFrame");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(350, 500);
		this.setLayout(new GridLayout(10,2));
		
		Tender tender = tenders.get(tendernumber);
		
		this.add(new JLabel("Tender Numner"));
		this.add(this.numbertext = new JTextField());
		this.numbertext.setText(tender.getTendernumber());
		this.numbertext.setEditable(false);
		
		this.add(new JLabel("Tender Name"));
		this.add(this.nametext = new JTextField());
		this.nametext.setText(tender.getTendername());
		
		this.add(new JLabel("Tender Deadline(YYYY-MM-DD)"));
		JPanel deadline = new JPanel();
		deadline.setLayout(new GridLayout(1,3));
		this.add(deadline);
		deadline.add(this.yeartext=new JTextField());
		deadline.add(this.monthtext = new JTextField());
		deadline.add(this.daytext = new JTextField());
		this.yeartext.setText(tender.getTenderdeadline().getYear()+"");
		this.monthtext.setText(tender.getTenderdeadline().getMonth()+"");
		this.daytext.setText(tender.getTenderdeadline().getDate()+"");
		
		this.add(new JLabel("Tender StandPrice"));
		this.add(this.standpricetext = new JTextField());
		this.standpricetext.setText(tender.getStandprice()+"");
		
		this.add(new JLabel("Tender Discount"));
		this.add(this.discounttext = new JTextField());
		this.discounttext.setText(tender.getDiscount()+"");
		
		this.add(new JLabel("Tender Mark-Up"));
		this.add(this.markuptext = new JTextField());
		this.markuptext.setText(tender.getMarkup()+"");
		
		this.add(new JLabel("Tender LabourCost"));
		this.add(this.labourcosttext = new JTextField());
		this.labourcosttext.setText(tender.getLabourcost()+"");
		
		this.add(new JLabel("Tender TenderStatus"));
		String[] statusstr = {Tender.Pending,Tender.InProgress,Tender.Submitted};
		this.add(this.statuscombox = new JComboBox(statusstr));
		this.statuscombox.setSelectedItem(tender.getTenderstatus());
		this.statuscombox.setBackground(Color.white);
		
		this.add(new JLabel("Tender Products"));
		this.add(this.productcombox = new JComboBox());
		for(String key: products.keySet()) {
			if(tender.getProduct()!=null&&tender.getTendernumber().equals(key)) {
				this.productcombox.setSelectedItem(key);
			}else {
				this.productcombox.addItem(key);
			}
		}
		this.productcombox.setBackground(Color.white);
		
		this.add(this.buttoncancel = new JButton("Cancel"));
		this.add(this.buttonconfirm = new JButton("Confirm"));
		this.buttoncancel.addActionListener(this);
		this.buttonconfirm.addActionListener(this);
		
		this.file = tenderfile;
		this.tendermap = tenders;
		this.productmap = products;
		this.key = tendernumber;
		
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cancel")) {
			this.dispose();
		}
		if(e.getActionCommand().equals("Confirm")) {
			
			if(!this.nametext.getText().equals("")
					&&!this.numbertext.getText().equals("")
					&&!this.standpricetext.getText().equals("")
					&&!this.discounttext.getText().equals("")
					&&!this.markuptext.getText().equals("")
					&&!this.labourcosttext.getText().equals("")) {
				Tender tender = new Tender();
				if(this.tendermap.get(this.numbertext) == null) {
					tender.setTendernumber(this.numbertext.getText());
					tender.setTendername(this.nametext.getText());
					tender.setTenderstatus((String) this.statuscombox.getSelectedItem());
					tender.setProduct(this.productmap.get(this.statuscombox.getSelectedItem()));
					Date deadline = new Date();
					try {
					deadline.setYear(Integer.parseInt(this.yeartext.getText()));
					deadline.setMonth(Integer.parseInt(this.monthtext.getText()));
					deadline.setDate(Integer.parseInt(this.daytext.getText()));
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "Data In Deadline Must Be Integer");
					}
					tender.setTenderdeadline(deadline);
					try {
						double standprice = Double.parseDouble(this.standpricetext.getText());
						double discount = Double.parseDouble(this.standpricetext.getText());
						double markup = Double.parseDouble(this.standpricetext.getText());
						double labourcost = Double.parseDouble(this.standpricetext.getText());
						tender.setStandprice(standprice);
						tender.setDiscount(discount);
						tender.setMarkup(markup);
						tender.setLabourcost(labourcost);
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "Data In SatndPrice,Discount,Mark-Up,LabourCost Must Be Integer");
					}
					tender.setTenderamount();
					tender.setTendercreatetime(new Date());
					this.tendermap.put(tender.getTendernumber(), tender);
					try {
						FileOutputStream fout = new FileOutputStream(this.file.getAbsolutePath());
						ObjectOutputStream oout = new ObjectOutputStream(fout);
						oout.writeObject(this.tendermap);
						JOptionPane.showMessageDialog(null, "Edit Success : "+tender);
						this.dispose();
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
						JOptionPane.showMessageDialog(null, "File Not Found,Fail To Add");
						this.dispose();
					}
				}else {
					JOptionPane.showMessageDialog(null, "The Material Has Exists,Please Add again.");
				}
			}else {
				JOptionPane.showMessageDialog(null, "Please Input All TextFields");
			}
		}
	}
	

}
