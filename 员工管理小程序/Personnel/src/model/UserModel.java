package model;

public class UserModel {
	private int id=0;
	private String user_name="";
	private String user_password="";
	private String user_dept="";
	private String user_position="";
	private int  user_salary=0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_dept() {
		return user_dept;
	}
	public void setUser_dept(String user_dept) {
		this.user_dept = user_dept;
	}
	public String getUser_position() {
		return user_position;
	}
	public void setUser_position(String user_position) {
		this.user_position = user_position;
	}
	public int getUser_salary() {
		return user_salary;
	}
	public void setUser_salary(int user_salary) {
		this.user_salary = user_salary;
	}
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", user_name=" + user_name + ", user_password=" + user_password + ", user_dept="
				+ user_dept + ", user_position=" + user_position + ", user_salary=" + user_salary + "]";
	}
	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserModel(String user_name, String user_password, String user_dept, String user_position, int user_salary) {
		super();
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_dept = user_dept;
		this.user_position = user_position;
		this.user_salary = user_salary;
	}
}
