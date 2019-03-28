package model;

import java.io.Serializable;

public class ProductType implements Serializable{
	private String typenumber;
	private String typename;
	public String getTypenumber() {
		return typenumber;
	}
	public void setTypenumber(String typenumber) {
		this.typenumber = typenumber;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public ProductType(String typenumber, String typename) {
		super();
		this.typenumber = typenumber;
		this.typename = typename;
	}
	@Override
	public String toString() {
		return "ProductType [typenumber=" + typenumber + ", typename=" + typename + "]";
	}
	
}
