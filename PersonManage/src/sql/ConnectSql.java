package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectSql {
	
	//���ݿ��ʼ���������������ݿ�Ȳ���������statement����
	public Statement DB_init() throws Exception{
		String JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL���ݿ�����
		String db="jdbc:sqlserver://127.0.0.1:1433;DatabaseName = Employee";//����Դ
		Class.forName(JDriver);
		String user = "sa";
		String password = "1234";
		Connection con = DriverManager.getConnection(db,user,password);//�������ݿ����
		System.out.println("�������ݿ�ɹ�");
		Statement stmt = con.createStatement();//����SQL�������
		return stmt;
	}	
	
	//������
	public void CreateTable(Statement stmt,String TableName)
			throws Exception{
		String query="create table "+TableName+"(id int,name text,price int)";//������SQL���
		stmt.executeUpdate(query);//ִ��SQL�������
	}
	
	//��������
	public void InsertData(Statement stmt,String sql)
			throws Exception{			
		//ִ��SQL�������,��������
		stmt.execute(sql);
	}
	
	//��ȡ���ű������
	public ResultSet QueryData(Statement stmt,String sql)
			throws Exception{
		ResultSet rs = stmt.executeQuery(sql);	
		return rs;
	}
	
	//ɾ��������
	public void DeleteData(Statement stmt,String sql)
			throws Exception{
		stmt.execute(sql);
	}
	
	//ɾ�����ݿ��
	public void DropTable(Statement stmt,String TableName)
			throws Exception{
		String drop_sql = "DROP TABLE "+TableName;
		stmt.execute(drop_sql);
	}

}
