import java.io.Serializable;

public class Product implements Serializable{
	private String ID;
	private String name;
	private String price;
	private String category;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Product() {
		super();
	}
	public Product(String iD, String name, String price, String category) {
		super();
		ID = iD;
		this.name = name;
		this.price = price;
		this.category = category;
	}
	@Override
	public String toString() {
		return "Products [ID=" + ID + ", name=" + name + ", price=" + price + ", category=" + category + "]";
	}
}
