package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import databaseConnection.Jdbc;
import model.DishModel;
import model.OrderModel;


public class OrderDao {
	/*
	 * 添加订单,作为流水查看
	 */
	//增加订单
	public int addOrder(int user_id,double order_price
			,String order_status,List<DishModel> dishes){
		int i1=0;
		int[] i2=null;
		Jdbc j = new Jdbc();
		Connection con=j.getConnection();
		 
		String sql="insert into ordertable(order_price,order_status,order_time,user_id)"
				+ " values(?,?,?,?)";
		String sql2 = "insert into itemtable(order_id,dish_id) "
				+ "values(?,?)";
		try {
			PreparedStatement psmt=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			psmt.setDouble(1, order_price);
			psmt.setString(2, order_status);
			psmt.setDate(3, new Date(System.currentTimeMillis()));
			psmt.setInt(4, user_id);
			i1 = psmt.executeUpdate();
			ResultSet rs = psmt.getGeneratedKeys();
			rs.next();
			
			if(dishes!=null) {
				PreparedStatement psmt2 = con.prepareStatement(sql2);
				for(DishModel dish:dishes) {
					psmt2.setInt(1, rs.getInt(1));
					psmt2.setInt(2, dish.getDish_id());
					psmt2.addBatch();
				}
				i2 = psmt2.executeBatch();
				psmt2.close();
			}
			psmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return i1;
		 
	}
	
	public int UpdateOrderById(int order_id,String order_status) {
		int i=0;
		Jdbc j = new Jdbc();
		Connection con=j.getConnection();
		 
		String sql="update ordertable set order_status=? where order_id=?";
		try {
			PreparedStatement psmt=con.prepareStatement(sql);
			psmt.setString(1, order_status);
			psmt.setInt(2, order_id);
			i = psmt.executeUpdate();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return i;
	}
	
	//删除订单
	public int deleteOrder(int order_id){
		int i1=0,i2=0;
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql="delete from itemtable where order_id = ?";
		String sql2 = "delete from ordertable where order_id = ?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, order_id);
			i1 = psmt.executeUpdate();
			psmt.close();
			
			PreparedStatement psmt2 = con.prepareStatement(sql2);
			psmt2.setInt(1, order_id);
			i2 = psmt2.executeUpdate();
			psmt2.close();
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return (i1==i2)&&i1==1?1:0;
	}
	
	//查询订单
	public List<OrderModel> queryOrder(){
		Jdbc j = new Jdbc();
		Connection con=j.getConnection();
		List<OrderModel> lom=new ArrayList<OrderModel>();
		String sql= "select * from ordertable";
		String sql2 = "select * from dish where dish_id in (select dish_id from itemtable where itemtable.order_id = ?)";
		try {
			PreparedStatement psmt=con.prepareStatement(sql);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				OrderModel order = new OrderModel();
				List<DishModel> dishes = new LinkedList<DishModel>();
				order.setOrder_id(rs.getInt("order_id"));
				order.setOrder_price(rs.getDouble("order_price"));
				order.setOrder_status(rs.getString("order_status"));
				order.setOrder_time(rs.getDate("order_time"));
				order.setUser_id(rs.getInt("user_id"));
				order.setDishes(dishes);
				PreparedStatement psmt2 = con.prepareStatement(sql2);
				psmt2.setInt(1, order.getOrder_id());
				ResultSet rs2 = psmt2.executeQuery();
				while(rs2.next()) {
					DishModel dish = new DishModel();
					dish.setDish_id(rs2.getInt("dish_id"));
					dish.setDish_name(rs2.getString("dish_name"));
					dish.setDish_description(rs2.getString("dish_description"));
					dish.setDish_image(rs2.getString("dish_image"));
					dishes.add(dish);
				}
				lom.add(order);
				psmt2.close();
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lom;
	}
}
 