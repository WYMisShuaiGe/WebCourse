package com.haley.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.haley.DAO.TeacherDAO;
import com.haley.bean.Teachers;

public class Init  implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		SearchTeacher s=new SearchTeacher();
		//ArrayList<Teachers> list=new ArrayList<Teachers>();
		
		ArrayList<Teachers> list=s.getAllTeacher();
		//System.out.println(list.size());
		TeacherDAO td=new TeacherDAO();
		try {
			td.delete();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
				try {
					td.insertTeacher(list);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("+++++++++++++++++++");
			} 
		
	}
