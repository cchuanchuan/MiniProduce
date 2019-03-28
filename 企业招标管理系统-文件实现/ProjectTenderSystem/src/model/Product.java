package model;

import java.io.Serializable;

public class Product implements Serializable{
	private String productnumber;
	private String productname;
	private String productbrand;
	private String productmodel;
	private ProductType producttype;
	private ProductMaterial productmaterial;
	public String getProductnumber() {
		return productnumber;
	}
	public void setProductnumber(String productnumber) {
		this.productnumber = productnumber;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductbrand() {
		return productbrand;
	}
	public void setProductbrand(String productbrand) {
		this.productbrand = productbrand;
	}
	public String getProductmodel() {
		return productmodel;
	}
	public void setProductmodel(String productmodel) {
		this.productmodel = productmodel;
	}
	public ProductType getProducttype() {
		return producttype;
	}
	public void setProducttype(ProductType producttype) {
		this.producttype = producttype;
	}
	public ProductMaterial getProductmaterial() {
		return productmaterial;
	}
	public void setProductmaterial(ProductMaterial productmaterial) {
		this.productmaterial = productmaterial;
	}
	public Product(String productnumber, String productname, String productbrand, String productmodel,
			ProductType producttype, ProductMaterial productmaterial) {
		super();
		this.productnumber = productnumber;
		this.productname = productname;
		this.productbrand = productbrand;
		this.productmodel = productmodel;
		this.producttype = producttype;
		this.productmaterial = productmaterial;
	}
	@Override
	public String toString() {
		return "Product [productnumber=" + productnumber + ", productname=" + productname + ", productbrand="
				+ productbrand + ", productmodel=" + productmodel + ", producttype=" + producttype
				+ ", productmaterial=" + productmaterial + "]";
	}
	public Product() {
		super();
	}
	
	
}
