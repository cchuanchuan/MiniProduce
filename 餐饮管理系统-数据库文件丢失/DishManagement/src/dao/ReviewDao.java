package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import databaseConnection.Jdbc;
import model.DishModel;
import model.ReviewModel;

public class ReviewDao {
	public void addReview(int order_id,String review_user,String review_waiter) {
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql = "insert into reviewtable(order_id,review_user,review_waiter) "
				+ "values(?,?,?)";
		PreparedStatement psmt;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, order_id);
			psmt.setString(2,review_user);
			psmt.setString(3,review_waiter);
			psmt.execute();
			
			psmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<ReviewModel> equaryReviewById(int order_id){
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		List<ReviewModel> reviews = new LinkedList<ReviewModel>();
		String sql = "select * from reviewtable where order_id = ?";
		
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, order_id);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				ReviewModel review = new ReviewModel();
				review.setOrder_id(rs.getInt("order_id"));
				review.setReview_user(rs.getString("review_user"));
				review.setReview_waiter(rs.getString("review_waiter"));
				reviews.add(review);
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reviews;
	}
	
	public List<ReviewModel> equaryReview(){
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		List<ReviewModel> reviews = new LinkedList<ReviewModel>();
		String sql = "select * from reviewtable";
		
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				ReviewModel review = new ReviewModel();
				review.setOrder_id(rs.getInt("order_id"));
				review.setReview_user(rs.getString("review_user"));
				review.setReview_waiter(rs.getString("review_waiter"));
				reviews.add(review);
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reviews;
	}

}
