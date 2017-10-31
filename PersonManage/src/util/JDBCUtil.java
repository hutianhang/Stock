package util;

import java.sql.*;


public class JDBCUtil {
	public static Connection getConnection(){
		Connection con = null;
		String cfn = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=my";
		String userName = "chenjiao";
		String psw = "123";
		
		try {
			Class.forName(cfn);
			con = DriverManager.getConnection(url,userName,psw);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	public static void close(Connection con,Statement stmt,PreparedStatement pstmt,ResultSet rs){
		try {
			if(con!=null){
				con.close();
			}
			if(stmt!=null){
				stmt.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
