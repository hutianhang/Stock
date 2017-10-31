package sql;
import java.sql.*;

public class insert_test {

	public static void main(String[] args)  throws Exception{
		// TODO Auto-generated method stub
		ConnectSql cs = new ConnectSql();
		Statement st = cs.DB_init();
		//String sql = "INSERT INTO T_Employee VALUES(10000,'张三','男',1,2,3,4,'2017-05-23','四川成都','四川绵阳','510123198010000134','汉','123456789',3,'清华大学','计算机')";
		//String sql = "INSERT INTO T_Employee VALUES(10001,'李四','男',1,1,1,1,'2017-05-24','四川成都郫县','四川绵阳','510123198010000135','汉','11111111111',4,'北京大学','计算机')";
		//String sql = "INSERT INTO T_Employee VALUES('李四','123456','男',2,2,2,2,'2017-05-25','云南昆明','四川成都','510123198010000136','汉','22222222222',3,'浙江大学','软件工程')";
		String sql = "INSERT INTO T_Employee VALUES('王五','123456','男',1,1,2,2,'2017-05-26','西藏拉萨','四川德阳','510123198010000137','藏','33333333333',2,'南京大学','自动化')";
		//String sql = "INSERT INTO T_Employee VALUES(10004,'赵柳','女',2,2,1,1,'2017-05-27','甘肃兰州','四川阿坝州','510123198010000138','彝','44444444444',1,'武汉大学','土木工程')";
		//String sql = "INSERT INTO T_Employee VALUES(99999,'胡天航','男',2,2,1,1,'2017-05-29','甘肃兰州','四川阿坝州','510123198010000138','彝','44444444444',1,'武汉大学','土木工程')";
		st.execute(sql);
	}

}
