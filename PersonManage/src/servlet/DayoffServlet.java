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

public class DayoffServlet extends HttpServlet {
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
			String id,date,employeeid,applydate,reason,begindate,enddate = "";
			
			
			String sql = "SELECT * FROM T_Dayoff "
					+ "WHERE ";
			
			switch(Operation){
			
			case "F":

				
				ConnectSql cs_f = new ConnectSql();
				Statement stmt_f = cs_f.DB_init();
				ResultSet rs_f  = cs_f.QueryData(stmt_f, "SELECT * FROM T_Dayoff");
				JSONObject FinalJson_f = this.getJson2Front(rs_f);							
				
		        out.write(FinalJson_f.toString());
				break;
				
				
				
			case "D":
				
				String [] dayoffid = request.getParameter("id").split(" ");
				ConnectSql cs_d = new ConnectSql();
				
				for(int i =0;i<dayoffid.length;i++){
					String del_sql = "DELETE FROM T_Dayoff "
							+ "WHERE Dayoff_ID = " + dayoffid[i];
					cs_d.DB_init().execute(del_sql);
					del_sql="";
				}
				out.write("OK");
				
				break;
				
				
				
			case "E":
				break;
				
				
				
			case "A":
				//获取表单参数，准备插入数据库
				id = request.getParameter("id");
				employeeid = request.getParameter("employeeid");
				applydate = request.getParameter("applydate");
				reason = request.getParameter("reason");
				begindate= request.getParameter("begindate");
				enddate= request.getParameter("enddate");
				
				
				ConnectSql cs_a = new ConnectSql();
				//ResultSet rp = cs_a.QueryData(cs_a.DB_init(), "SELECT Employee_ID FROM T_Employee ");
				
				/*while(rp.next()){
					if(!employeeid.equals(rp.getString("Employee_ID"))){
						position = rp.getString("Position_ID");
						break;
					}
				}*/
				
				String add_sql = "INSERT INTO T_Dayoff VALUES("
						+ id +"," + employeeid + ",\'" + applydate + "\'," + "\'" + reason + "\'," + "\'" + begindate + "\',"
						+ "\'" + enddate + "\'"
						+ ")";

				cs_a.InsertData(cs_a.DB_init(), add_sql);
								
				break;
				
				
				
			case "R":
				id = request.getParameter("id");
				date = request.getParameter("date");

				
				if(!id .equals("")){
					sql = sql + "T_Dayoff.Employee_ID = " + id + " ";
				}
				if(!id .equals("")&&!date.equals("")){
					sql = sql + "AND T_Dayoff.ApplyDate = " + "\'" + date.toString() + "\' ";
				}
				if(id .equals("")&&!date.equals("")){
					sql = sql + "T_Dayoff.ApplyDate = " + "\'" + date.toString() + "\' ";
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
	        jsonObject.put("dayoffid", rs.getInt("Dayoff_ID"));
	        jsonObject.put("employeeid", rs.getInt("Employee_ID"));
	        jsonObject.put("applydate", rs.getString("ApplyDate"));
	        jsonObject.put("reason", rs.getString("Reason"));
	        jsonObject.put("begindate", rs.getString("BeginDate"));
	        jsonObject.put("enddate", rs.getString("EndDate"));
	        
	        jsonArray.add(count, jsonObject);
	        count++;
		}				
		
		finaljson.put("total", count); //总条数有多少
		finaljson.put("rows", jsonArray); //需要显示的条数是多少
		return finaljson;
		
	}
	
}