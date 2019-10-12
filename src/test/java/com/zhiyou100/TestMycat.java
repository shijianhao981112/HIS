package com.zhiyou100;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TestMycat {

	public static void main(String[] args) throws Exception  {
		Class.forName("com.mysql.jdbc.Driver");
		// mycat的用户名
		String username = "swf";
		// mycat的密码
		String password = "123456";
		// mycat的连接地址
		String url =  "jdbc:mysql://192.168.72.132:8066/swf_table";
		Connection conn = DriverManager.getConnection(url,username,password);
		String sql1 = "insert into dept (d_id,d_name,d_desc) values (2,'mycat研发部2','客户端插入2')";
		String sql2 = "insert into user (u_id,u_name,u_desc) values (4,'u_lisi','客户端插入用户lisi')";
		String sql3 = "insert into student (s_id,s_name,s_desc) values (6,'s_zs','客户端插入学生zs')";
		PreparedStatement ps1 = conn.prepareStatement(sql1);
		PreparedStatement ps2 = conn.prepareStatement(sql2);
		PreparedStatement ps3 = conn.prepareStatement(sql3);
		ps1.execute();
		ps2.execute();
		ps3.execute();
		ps1.close();
		ps2.close();
		ps3.close();
		conn.close();
		System.out.println("结束");
		
	}
}
