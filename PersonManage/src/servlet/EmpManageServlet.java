package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sql.ConnectSql;


//@WebServlet(name="lineServlet",urlPatterns="/lineServlet")

public class EmpManageServlet extends HttpServlet {
	private static final long serialVersionUID = 366512729238484827L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			// TODO Auto-generated method stub
			this.doPost(req, resp);
		}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String Operation = request.getParameter("op");

		try{
			//根据传入的Operation参数决定具体的操作
			//F:fresh
			//D:delete
			//E:edit
			//A:add
			//R:retrive
			
			//参数名
			String id,name,gender,nation,position,adm,edu,dept,birthday,nativeplace,nowaddress,idcard,contactway,worktime,school,major = "";
			
			String	sql = "SELECT T_Employee.Employee_ID, T_Employee.EmployeeName,"
					+ "T_Employee.Gender,T_Position. PositionName,T_Adms.AdmName,"
					+ "T_EduBackground.EduBackgroundName,T_Department.Department_Name,"
					+ "T_Employee.Birthday,T_Employee.NativePlace,T_Employee.NowAddress,"
					+ "T_Employee.IDcardNo,T_Employee.Nation,T_Employee.ContactWay,"
					+ "T_Employee.WorkTime,T_Employee.GraduatedSchool,T_Employee.Major "
					+ "FROM T_Employee, T_Adms,T_Department,T_EduBackground,T_Position "
					+ "WHERE T_Employee.Position_ID = T_Position.Position_ID AND "
					+ "T_Employee.EduBackground_ID = T_EduBackground.EduBackground_ID AND "
					+ "T_Employee.Adm_ID = T_Adms.Adm_ID AND "
					+ "T_Employee.Department_ID = T_Department.Department_ID ";
			
			switch(Operation){
			
			case "F":

				
				ConnectSql cs_f = new ConnectSql();
				Statement stmt_f = cs_f.DB_init();
				ResultSet rs_f  = cs_f.QueryData(stmt_f, sql);
				JSONObject FinalJson_f = this.getJson2Front(rs_f);							
				
		        out.write(FinalJson_f.toString());
				break;
				
				
				
			case "D":
				
				String [] deleteid = request.getParameter("id").split(" ");
				ConnectSql cs_d = new ConnectSql();
				
				for(int i =0;i<deleteid.length;i++){
					String del_sql = "DELETE FROM T_Employee "
							+ "WHERE Employee_ID = " + deleteid[i];
					cs_d.DB_init().execute(del_sql);
					del_sql="";
				}
				out.write("OK");
				
				break;
				
				
				
			case "E":
				//具体实现与add函数一致
				break;
				
				
				
			case "A":
				
				ConnectSql cs_a = new ConnectSql();
				
				if(request.getParameter("flag")!=null){
					cs_a.DB_init().execute("DELETE FROM T_Employee WHERE Employee_ID = " + request.getParameter("id"));
				}
				
				
				//获取表单参数，准备插入数据库
				id = request.getParameter("id");
				name = request.getParameter("name");
				gender = request.getParameter("gender");
				position= request.getParameter("position");//要转换为positionid
				adm= request.getParameter("adm");//要转换为admid
				edu= request.getParameter("edu");//要转换为eduid
				dept= request.getParameter("dept");//要转换为deptid
				birthday= request.getParameter("birthday");
				nativeplace= request.getParameter("nativeplace");
				nowaddress= request.getParameter("nowaddress");
				idcard= request.getParameter("idcard");
				nation= request.getParameter("nation");
				contactway= request.getParameter("contactway");
				worktime= request.getParameter("worktime");
				school= request.getParameter("school");
				major= request.getParameter("major");
							
				ResultSet rp = cs_a.QueryData(cs_a.DB_init(), "SELECT * FROM T_Position ");
				ResultSet ra = cs_a.QueryData(cs_a.DB_init(), "SELECT * FROM T_Adms ");
				ResultSet re = cs_a.QueryData(cs_a.DB_init(), "SELECT * FROM T_EduBackground ");
				ResultSet rd = cs_a.QueryData(cs_a.DB_init(), "SELECT * FROM T_Department ");
				
				while(rp.next()){
					if(position.equals(rp.getString("PositionName"))){
						position = rp.getString("Position_ID");
						break;
					}
				}
				
				while(ra.next()){
					if(adm.equals(ra.getString("AdmName"))){
						adm = ra.getString("Adm_ID");
						break;
					}
				}
				
				while(re.next()){
					if(edu.equals(re.getString("EduBackgroundName"))){
						edu = re.getString("EduBackground_ID");
						break;
					}
				}
				
				while(rd.next()){
					if(dept.equals(rd.getString("Department_Name"))){
						dept = rd.getString("Department_ID");
						break;
					}
				}
				
				
				String add_sql = "INSERT INTO T_Employee VALUES("
						+ id +",\'"+name+"\',"+"\'"+gender+"\',"
						+ position+","+adm+","+edu+","+dept+","
						+ "\'"+birthday+"\',"+"\'"+nativeplace+"\',"
						+ "\'"+nowaddress+"\',"+"\'"+idcard+"\',"
						+ "\'"+nation+"\',"+"\'"+contactway+"\',"
						+worktime+","+"\'"+school+"\',"+"\'"+major+"\'"
						+ ")";
				//System.out.println(add_sql);
				cs_a.InsertData(cs_a.DB_init(), add_sql);
				//JSONObject FinalJson_r = this.getJson2Front(rs_r);												
		        //out.write(FinalJson_r.toString());
				out.write("OK");
				
				
				
				break;
				
				
				
			case "R":
				id = request.getParameter("id");
				name = request.getParameter("name");
				dept = request.getParameter("dept");
				
				//System.out.println(date + ","+ name +","+dept);
				if(!id .equals("")){
					sql = sql + "AND T_Employee.Employee_ID = " + id + " ";
				}
				if(!name.equals("")){
					sql = sql + "AND T_Employee.EmployeeName = " + "\'" + name.toString() + "\' ";
				}
				if(!dept.equals("")){
					sql = sql + "AND T_Department.Department_Name = " + "\'" + dept.toString() + "\' ";
				}
				
				//System.out.println(sql);
				
				ConnectSql cs_r = new ConnectSql();
				Statement stmt_r = cs_r.DB_init();
				ResultSet rs_r  = cs_r.QueryData(stmt_r, sql);
				JSONObject FinalJson_r = this.getJson2Front(rs_r);								
				
		        out.write(FinalJson_r.toString());
				break;
								
			}			
			
			out.flush();
			out.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//封装json打包函数
	public JSONObject getJson2Front(ResultSet rs)throws Exception{
		JSONObject finaljson = new JSONObject();
				
		//开始组装向前端返回的datagrid的json数据
		int count = 0;
		JSONArray jsonArray = new JSONArray();		
		while(rs.next()){			
			JSONObject jsonObject = new JSONObject();
	        jsonObject.put("employeeid", rs.getInt("Employee_ID"));
	        jsonObject.put("name", rs.getString("EmployeeName"));
	        jsonObject.put("gender", rs.getString("Gender"));
	        jsonObject.put("position", rs.getString("PositionName"));
	        jsonObject.put("adm", rs.getString("AdmName"));
	        jsonObject.put("edubackground", rs.getString("EduBackgroundName"));
	        jsonObject.put("department", rs.getString("Department_Name"));
	        jsonObject.put("birthday", rs.getString("Birthday"));
	        jsonObject.put("nativeplace", rs.getString("NativePlace"));
	        jsonObject.put("nowaddress", rs.getString("NowAddress"));
	        jsonObject.put("idcardno", rs.getString("IDcardNo"));
	        jsonObject.put("nation", rs.getString("Nation"));
	        jsonObject.put("contactway", rs.getString("ContactWay"));
	        jsonObject.put("worktime", rs.getInt("WorkTime"));
	        jsonObject.put("graduateschool", rs.getString("GraduatedSchool"));
	        jsonObject.put("major", rs.getString("Major"));
	        jsonArray.add(count, jsonObject);
	        count++;
		}				
		
		finaljson.put("total", count); //总条数有多少
		finaljson.put("rows", jsonArray); //需要显示的条数是多少
		return finaljson;
		
	}
	
}


