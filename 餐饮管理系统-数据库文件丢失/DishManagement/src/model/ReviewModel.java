package model;

public class ReviewModel {
	private int order_id;
	private String review_user;
	private String review_waiter;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getReview_user() {
		return review_user;
	}
	public void setReview_user(String review_user) {
		this.review_user = review_user;
	}
	public String getReview_waiter() {
		return review_waiter;
	}
	public void setReview_waiter(String review_waiter) {
		this.review_waiter = review_waiter;
	}
	public ReviewModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ReviewModel [order_id=" + order_id + ", review_user=" + review_user + ", review_waiter=" + review_waiter
				+ "]";
	}
	public ReviewModel(int order_id, String review_user, String review_waiter) {
		super();
		this.order_id = order_id;
		this.review_user = review_user;
		this.review_waiter = review_waiter;
	}
	
	
	
}
