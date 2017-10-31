package Dao;

import java.util.List;

import model.Department;
import model.Staff;

public interface UserDao {
	//登录
	public Staff login(String id,String psw);
	//增加员工
	public int addStaff(Staff staff);
	//删除员工
	public int delStaff(String id);
	//修改个人密码
	public int modifyPsw(String id,String psw);
	//还不确定修改职工的什么信息
	public int modifyStaffInfo(int id);
	//按职工id查询
	public List<Staff> selectStaffrById(int id);
	//按职工姓名查询
	public List<Staff> selectStaffByName(String name);
	//按部门查询
	public List<Staff> selectStaffByDep(String depName);
	//按入职时间查询
	public List<Staff> selectStaffByInDate(String date);
	
	//添加部门
	public int addDep(Department dep);
	//删除部门。
	//考虑这里是级联删除该部门的员工还是必须先给员工安排好后才能删除部门
	public int delDep(String id);
	//修改部门名称。
	public int modifyDep(String id,String name);
	//查询全部部门
	public List<Department> selectDep();
	
	
	
}
