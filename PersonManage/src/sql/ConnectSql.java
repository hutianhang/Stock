package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectSql {
	
	//数据库初始化，包括连接数据库等操作，返回statement对象
	public Statement DB_init() throws Exception{
		String JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
		String db="jdbc:sqlserver://127.0.0.1:1433;DatabaseName = Employee";//数据源
		Class.forName(JDriver);
		String user = "sa";
		String password = "1234";
		Connection con = DriverManager.getConnection(db,user,password);//连接数据库对象
		System.out.println("连接数据库成功");
		Statement stmt = con.createStatement();//创建SQL命令对象
		return stmt;
	}	
	
	//创建表
	public void CreateTable(Statement stmt,String TableName)
			throws Exception{
		String query="create table "+TableName+"(id int,name text,price int)";//创建表SQL语句
		stmt.executeUpdate(query);//执行SQL命令对象
	}
	
	//插入数据
	public void InsertData(Statement stmt,String sql)
			throws Exception{			
		//执行SQL命令对象,插入数据
		stmt.execute(sql);
	}
	
	//读取整张表的数据
	public ResultSet QueryData(Statement stmt,String sql)
			throws Exception{
		ResultSet rs = stmt.executeQuery(sql);	
		return rs;
	}
	
	//删除表数据
	public void DeleteData(Statement stmt,String sql)
			throws Exception{
		stmt.execute(sql);
	}
	
	//删除数据库表
	public void DropTable(Statement stmt,String TableName)
			throws Exception{
		String drop_sql = "DROP TABLE "+TableName;
		stmt.execute(drop_sql);
	}

}
