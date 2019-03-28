package MainJFrame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import MainJFrame.*;
public class DatabaseConnect
{
	//SQL Server 2005(及以上)JDBC驱动
	private static String DRIVERNAME="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	private Connection dbConn;
	private java.sql.Statement statement;
	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	public DatabaseConnect(String name,String password)
	{
		//数据库SQL server 2005(及以上)URL
		String dbURL="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=EnterprisePersonnel";
		try{
			//指定并加载驱动程序
			Class.forName(DRIVERNAME);
			
			//通过DriverManager类的getConnection()方法创建一个数据库连接
			dbConn=DriverManager.getConnection(dbURL,name,password);
			
			//如果连接成功，控制台输出Connection Successful！
			System.out.println("Connection Successful!");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	//非查询语句
	public int executeUpdate(String sql) throws SQLException
	{
		//通过Connection对象的createStatement()方法创建一个Statement对象
		statement=dbConn.createStatement();
		return statement.executeUpdate(sql);
	}
	//查询语句
	public ResultSet executeQuery(String sql) throws SQLException
	{
		statement=dbConn.createStatement();
		return statement.executeQuery(sql);
	}
	public void closeConn() throws SQLException
	{
		if(statement!=null)
			statement.close();
		System.out.println("Disconnection Successful!");
		dbConn.close();
	}
	public PreparedStatement PreparedStatement(String sql) throws SQLException
	{
		// TODO Auto-generated method stub
		return dbConn.prepareStatement(sql);
	}
}
