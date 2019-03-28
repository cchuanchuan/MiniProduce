package officerpackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import model.Product;
import model.ProductMaterial;
import model.ProductType;
import model.Staff;
import model.Tender;

public class OfficeFunction {
	private Staff office;
	private Map<String,ProductMaterial> productmaterials;
	private Map<String,ProductType> producttype;
	private Map<String,Tender> tenders;
	private Map<String,Product>products;
	File tenderfile;
	File productmaterialsfile;
	File producttypefile;
	File productfile;
	public OfficeFunction() {
		File directory = new File("");
		
		this.productfile = new File(directory.getAbsolutePath()+"\\src\\FileStore\\Product.txt");
		FileInputStream fin;
		try {
			fin = new FileInputStream(productfile.getAbsolutePath());
			ObjectInputStream oin=new ObjectInputStream(fin);
			this.products =  (Map<String, Product>) oin.readObject();
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File Not Found , AutoCreate Product  object");
			this.products = new HashMap<String,Product>();
		}
		
		this.tenderfile = new File(directory.getAbsolutePath()+"\\src\\FileStore\\Tender.txt");
		try {
			fin = new FileInputStream(tenderfile.getAbsolutePath());
			ObjectInputStream oin=new ObjectInputStream(fin);
			this.tenders = (Map<String, Tender>) oin.readObject();
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File Not Found , AutoCreate Tender  object");
			this.tenders = new HashMap<String,Tender>();
		}
		
		this.productmaterialsfile = new File(directory.getAbsolutePath()+"\\src\\FileStore\\ProductMaterial.txt");
		try {
			fin = new FileInputStream(productmaterialsfile.getAbsolutePath());
			ObjectInputStream oin = new ObjectInputStream(fin);
			this.productmaterials = (Map<String, ProductMaterial>) oin.readObject();
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File Not Found , AutoCreate ProductMaterials  object");
			this.productmaterials = new HashMap<String,ProductMaterial>();
		}
		
		this.producttypefile = new File(directory.getAbsolutePath()+"\\src\\FileStore\\ProductType.txt");
		try {
			fin = new FileInputStream(producttypefile.getAbsolutePath());
			ObjectInputStream oin = new ObjectInputStream(fin);
			this.producttype = (Map<String, ProductType>) oin.readObject();
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File Not Found , AutoCreate ProductType  object");
			this.producttype = new HashMap<String,ProductType>();
		}
	}
	public Staff getOffice() {
		return office;
	}
	public void setOffice(Staff office) {
		this.office = office;
	}
	public Map<String, ProductMaterial> getProductmaterials() {
		return productmaterials;
	}
	public void setProductmaterials(Map<String, ProductMaterial> productmaterials) {
		this.productmaterials = productmaterials;
	}
	public Map<String, ProductType> getProducttype() {
		return producttype;
	}
	public void setProducttype(Map<String, ProductType> producttype) {
		this.producttype = producttype;
	}
	public Map<String, Tender> getTenders() {
		return tenders;
	}
	public void setTenders(Map<String, Tender> tenders) {
		this.tenders = tenders;
	}
	public Map<String, Product> getProducts() {
		return products;
	}
	public void setProducts(Map<String, Product> products) {
		this.products = products;
	}
	public File getTenderfile() {
		return tenderfile;
	}
	public void setTenderfile(File tenderfile) {
		this.tenderfile = tenderfile;
	}
	public File getProductmaterialsfile() {
		return productmaterialsfile;
	}
	public void setProductmaterialsfile(File productmaterialsfile) {
		this.productmaterialsfile = productmaterialsfile;
	}
	public File getProducttypefile() {
		return producttypefile;
	}
	public void setProducttypefile(File producttypefile) {
		this.producttypefile = producttypefile;
	}
	public File getProductfile() {
		return productfile;
	}
	public void setProductfile(File productfile) {
		this.productfile = productfile;
	}
	@Override
	public String toString() {
		return "OfficeFunction [office=" + office + ", productmaterials=" + productmaterials + ", producttype="
				+ producttype + ", tenders=" + tenders + ", products=" + products + ", tenderfile=" + tenderfile
				+ ", productmaterialsfile=" + productmaterialsfile + ", producttypefile=" + producttypefile
				+ ", productfile=" + productfile + "]";
	}
}

