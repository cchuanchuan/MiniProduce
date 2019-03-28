package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {
	 private String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Person" ;
	 private String user="ccc";
	 private String password="257173";
	 private Connection con;
	 
	 public Jdbc(){
	 }
	
	 public Connection getConnection() {
		 try {
			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();//获得数据库的连接
			 con = DriverManager.getConnection(url, user, password);
		 } catch (ClassNotFoundException e) {
			 e.printStackTrace();
		 }
		 catch (SQLException e) {
			 e.printStackTrace();
		 } catch (InstantiationException e) {
			 e.printStackTrace();
		 } catch (IllegalAccessException e) {
			 e.printStackTrace();
		 }
		 return con;
	 }
}
