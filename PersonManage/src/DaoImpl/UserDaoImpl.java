package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import util.JDBCUtil;
import model.Department;
import model.Staff;
import Dao.UserDao;

public class UserDaoImpl implements UserDao {
	private static Connection con=null;
	private static ResultSet rs=null;
	private static Statement stmt=null;
	private static PreparedStatement pstmt = null;
	
	@Override
	public Staff login(String id, String psw) {
		// TODO Auto-generated method stub
		Staff staff = new Staff();
		boolean flag = false;
		try {
			con = JDBCUtil.getConnection();
			String sql = "select * from staff where id= ? and psw = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, psw);
			rs = pstmt.executeQuery();
			if(rs.next()){
				flag = true;
				staff.setId(rs.getInt("id"));
				staff.setName(rs.getString("name"));
				staff.setPsw(rs.getString("psw"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.close(con, stmt, pstmt, rs);
		}
		if(flag){
			return staff;
		}
		else return null;
	}

	@Override
	public int addStaff(Staff staff) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delStaff(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyPsw(String id, String psw) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyStaffInfo(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Staff> selectStaffrById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Staff> selectStaffByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Staff> selectStaffByDep(String depName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Staff> selectStaffByInDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addDep(Department dep) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delDep(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyDep(String id, String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Department> selectDep() {
		// TODO Auto-generated method stub
		return null;
	}

}
