package model;

import java.io.Serializable;
import java.util.Date;

public class Tender implements Serializable{
	public static final String Pending = "Pending";
    public static final String InProgress = "InProgress";
    public static final String Submitted = "Submitted";
	private String tendernumber;
	private Date tendercreatetime;
	private Date tenderdeadline;
	private String tendername;
	private double tenderamount;
	private double standprice = 0;
	private double discount = 0;
	private double markup = 0;
	private double labourcost = 0;
	private String tenderstatus;
	private Product product;
	public String getTendernumber() {
		return tendernumber;
	}
	public void setTendernumber(String tendernumber) {
		this.tendernumber = tendernumber;
	}
	public Date getTendercreatetime() {
		return tendercreatetime;
	}
	public void setTendercreatetime(Date tendercreatetime) {
		this.tendercreatetime = tendercreatetime;
	}
	public Date getTenderdeadline() {
		return tenderdeadline;
	}
	public void setTenderdeadline(Date tenderdeadline) {
		this.tenderdeadline = tenderdeadline;
	}
	public String getTendername() {
		return tendername;
	}
	public void setTendername(String tendername) {
		this.tendername = tendername;
	}
	public double getTenderamount() {
		return tenderamount;
	}
	public void setTenderamount() {
		this.tenderamount = this.standprice+this.discount+this.markup+this.labourcost;
	}
	public double getStandprice() {
		return standprice;
	}
	public void setStandprice(double standprice) {
		this.standprice = standprice;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getMarkup() {
		return markup;
	}
	public void setMarkup(double markup) {
		this.markup = markup;
	}
	public double getLabourcost() {
		return labourcost;
	}
	public void setLabourcost(double labourcost) {
		this.labourcost = labourcost;
	}
	public String getTenderstatus() {
		return tenderstatus;
	}
	public void setTenderstatus(String tenderstatus) {
		this.tenderstatus = tenderstatus;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public static String getPending() {
		return Pending;
	}
	public static String getInprogress() {
		return InProgress;
	}
	public static String getSubmitted() {
		return Submitted;
	}
	@Override
	public String toString() {
		return "Tender [tendernumber=" + tendernumber + ", tendercreatetime=" + tendercreatetime + ", tenderdeadline="
				+ tenderdeadline + ", tendername=" + tendername + ", tenderamount=" + tenderamount + ", standprice="
				+ standprice + ", discount=" + discount + ", markup=" + markup + ", labourcost=" + labourcost
				+ ", tenderstatus=" + tenderstatus + ", product=" + product + "]";
	}
	public Tender(String tendernumber, Date tendercreatetime, Date tenderdeadline, String tendername,
			double tenderamount, double standprice, double discount, double markup, double labourcost,
			String tenderstatus, Product product) {
		super();
		this.tendernumber = tendernumber;
		this.tendercreatetime = tendercreatetime;
		this.tenderdeadline = tenderdeadline;
		this.tendername = tendername;
		this.tenderamount = tenderamount;
		this.standprice = standprice;
		this.discount = discount;
		this.markup = markup;
		this.labourcost = labourcost;
		this.tenderstatus = tenderstatus;
		this.product = product;
	}
	public Tender() {
		super();
	}
	
	
}
