package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserModel;

public class UserDao {
	//用户登录查询
	public UserModel loginUser(String u_name,String u_password) {
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		//获取数据库连接
		UserModel user = null;
		String sql="select * from usertable where user_name=? and user_password=?";
		try {
			PreparedStatement psmt=con.prepareStatement(sql);
			//执行sql语句
			psmt.setString(1,u_name);
			//设置参数1
			psmt.setString(2, u_password);
			//设置参数2
			ResultSet rs=psmt.executeQuery();
			//进行查询
			if(rs.next()) {
				//如果查询结果存在，那么创建UserModel对象，并且赋值
				user = new UserModel();
				user.setId(rs.getInt("user_id"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_password(rs.getString("user_password"));
				user.setUser_dept(rs.getString("user_dept"));
				user.setUser_position(rs.getString("user_position"));
				user.setUser_salary(rs.getInt("user_salary"));
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	
	/*
	 * 用户注册
	 */
	public boolean registerUser(UserModel user) {
		boolean b = false;
		Jdbc jdbc = new Jdbc();
		Connection con = jdbc.getConnection();
		String sql = "insert into usertable(user_name,user_password,user_dept,user_position,user_salary)"
				+ " values(?,?,?,?,?)";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, user.getUser_name());
			psmt.setString(2, user.getUser_password());
			psmt.setString(3, user.getUser_dept());
			psmt.setString(4, user.getUser_position());
			psmt.setInt(5, user.getUser_salary());
			
			psmt.execute();
			psmt.close();
			con.close();
			b = true;
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}
	
	//用户登录查询
		public List<UserModel> AllUser() {
			List<UserModel>list = new ArrayList<UserModel>();
			Jdbc j=new Jdbc();
			Connection con=j.getConnection();
			//获取数据库连接
			String sql="select * from usertable";
			try {
				PreparedStatement psmt=con.prepareStatement(sql);
				//执行sql语句
				ResultSet rs=psmt.executeQuery();
				//进行查询
				while(rs.next()) {
					//如果查询结果存在，那么创建UserModel对象，并且赋值
					UserModel user = new UserModel();
					user.setId(rs.getInt("user_id"));
					user.setUser_name(rs.getString("user_name"));
					user.setUser_password(rs.getString("user_password"));
					user.setUser_dept(rs.getString("user_dept"));
					user.setUser_position(rs.getString("user_position"));
					user.setUser_salary(rs.getInt("user_salary"));
					list.add(user);
				}
				rs.close();
				psmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return list;
		}
	
	/*
	 * 删除用户所有信息
	 */
	public boolean deleteWaiter(int u_id){
		boolean b=false;
		Jdbc j = new Jdbc();
		Connection con=j.getConnection();
		String sql = "delete from usertable where user_id = ?";
		PreparedStatement psmt;
		try {
			psmt = con.prepareStatement(sql);
			//先执行语句1，查询用户是否已经存在
			psmt.setInt(1, u_id);
			//设置参数
			psmt.executeUpdate();
			psmt.close();
			con.close();
			//关闭所有数据库资源
			b = true;
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

}
