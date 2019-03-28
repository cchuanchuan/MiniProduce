package model;

import java.util.Date;
import java.util.List;

public class OrderModel {
	//¶©µ¥Ä£ÐÍ
	public final static String RECEIVE = "Received";
	public final static String PREPARING = "Preparing";
	public final static String COMPLISHED = "Complished";
	public final static String CANCEL = "Cancel";
	
	private int order_id;
	private int user_id;
	private double order_price;
	private Date order_time;
	private String order_status;
	private List<DishModel> dishes;
	
	
	public OrderModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public double getOrder_price() {
		return order_price;
	}
	public void setOrder_price(double order_price) {
		this.order_price = order_price;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public List<DishModel> getDishes() {
		return dishes;
	}
	public void setDishes(List<DishModel> dishes) {
		this.dishes = dishes;
	}
	@Override
	public String toString() {
		return "OrderModel [order_id=" + order_id + ", user_id=" + user_id + ", order_price=" + order_price
				+ ", order_time=" + order_time + ", order_status=" + order_status + ", dishes=" + dishes + "]";
	}
	public OrderModel(int order_id, int user_id, double order_price, Date order_time, String order_status,
			List<DishModel> dishes) {
		super();
		this.order_id = order_id;
		this.user_id = user_id;
		this.order_price = order_price;
		this.order_time = order_time;
		this.order_status = order_status;
		this.dishes = dishes;
	}
}
