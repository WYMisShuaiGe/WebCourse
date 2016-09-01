package com.haley.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.haley.bean.Teachers;
import com.haley.conn.MysqlConnection;

public class TeacherDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private int iRs;
	public TeacherDAO(){
		conn = MysqlConnection.getInstance().getConn();
	}
	public void insertTeacher(ArrayList<Teachers> teacherList) throws SQLException {
	
        String id=null;
        String name=null;
       //System.out.println(teacherList.size());
       for(int i=2; i<80; i++){
           id=teacherList.get(i).getTeacherId();
        	//id=22+"";
            name=teacherList.get(i).getTeachername();
        //	name=1111111+"";
        //    Log.i(">>>>>>>>>>", id + "-----" + name);
           // System.out.println(id+"<<<<<<<<<"+name);
            String sql = "insert into teacher(teacherid,teachername,flag)" + "values('" + id + "','" +
                    name + "','0')";
            ps=conn.prepareStatement(sql);
            iRs=ps.executeUpdate();
            //ps.execute(sql);
           
        }
        
       conn.close();
       ps.close();
	}
	public ArrayList<Teachers> findAll() throws SQLException{
		ArrayList<Teachers> list =new ArrayList<Teachers>();
		String sql="select * from teacher";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while(rs.next()){
			Teachers tea =new Teachers();
			tea.setTeacherId(rs.getString("teacherid"));
			tea.setTeachername(rs.getString("teachername"));
			tea.setIdentityCode(rs.getString("flag"));
			list.add(tea);
			
		}
		
		rs.close();
		ps.close();
		return list;
	
	}
	
	public void delete() throws SQLException {
		// TODO Auto-generated method stub
		String sql="DELETE FROM teacher;";
		ps=conn.prepareStatement(sql);
		iRs=ps.executeUpdate();
		ps.close();
		
	}
	
	
	public void update(String id, String identity) throws SQLException {
		
		// TODO Auto-generated method stub
		String sql="UPDATE teacher SET flag=? WHERE teacherid=?";
		ps=conn.prepareStatement(sql);

		ps.setString(1, identity);
		ps.setString(2, id);
		iRs=ps.executeUpdate();
		
		ps.close();
		System.out.println("老师状态已更新");
	}
}