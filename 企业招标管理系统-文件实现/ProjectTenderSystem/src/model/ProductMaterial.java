package model;

import java.io.Serializable;

public class ProductMaterial implements Serializable{
	private String materialnumber;
	private String materialname;
	public String getMaterialnumber() {
		return materialnumber;
	}
	public void setMaterialnumber(String materialnumber) {
		this.materialnumber = materialnumber;
	}
	public String getMaterialname() {
		return materialname;
	}
	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}
	@Override
	public String toString() {
		return "ProductMaterial [materialnumber=" + materialnumber + ", materialname=" + materialname + "]";
	}
	public ProductMaterial(String materialnumber, String materialname) {
		super();
		this.materialnumber = materialnumber;
		this.materialname = materialname;
	}
	
}
