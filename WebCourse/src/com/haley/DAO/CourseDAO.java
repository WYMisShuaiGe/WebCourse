package com.haley.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.haley.bean.Teachers;
import com.haley.conn.MysqlConnection;

public class CourseDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private int iRs;
	public CourseDAO(){
		conn = MysqlConnection.getInstance().getConn();
	}
	public void insertCourse(String id,ArrayList<String> List) throws SQLException {
		
        
        String course="";
      // System.out.println(List.size());
       for(int i=0; i<List.size(); i++){
    	   course+=List.get(i).toString();
                 }
       String sql = "insert into course(teacherid,course)" + "values('" + id + "','" +
               course + "')";
       ps=conn.prepareStatement(sql);
       iRs=ps.executeUpdate();
       conn.close();
       ps.close();
	}
	public String findall(String id) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from course";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		String course="";
		while(rs.next()){
			if(rs.getString("teacherid").equals(id)){
				course=rs.getString("course");
				break;
			}
		}
		
		rs.close();
		ps.close();
		return course;
	}

}
