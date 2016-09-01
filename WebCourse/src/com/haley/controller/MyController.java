package com.haley.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.haley.DAO.CourseDAO;
import com.haley.DAO.TeacherDAO;
import com.haley.bean.Teachers;
@Controller
public class MyController {
	public MyController() {
		System.out.println("000000000000000");
		// TODO Auto-generated constructor stub
	}
	@RequestMapping("getTeacher")
	@ResponseBody
	public  ArrayList<Teachers> getTeacher() throws SQLException{
		
		TeacherDAO d1=new TeacherDAO();
		ArrayList<Teachers> list=new ArrayList<Teachers>();
		try {
			list=d1.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("获取老师信息。。。");
		return list;
	}
	@RequestMapping("getyzm")
	public void getyzm(HttpSession session,OutputStream out){
		System.out.println("获得验证码。。。");
		HttpClient client=HttpClientBuilder.create().build();
		session.setAttribute("client", client);
		
		HttpUriRequest request=new HttpGet("http://121.248.70.214/jwweb/sys/ValidateCode.aspx");
		try {
			HttpResponse rep=client.execute(request);
			byte [] img =EntityUtils.toByteArray(rep.getEntity());
			out.write(img);
			out.close();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("getTeachercourse")
	@ResponseBody
	public ArrayList<String> course(String id){
		System.out.println("获得已保存的课表。。。");
		ArrayList<String> list=new ArrayList<String>();
		CourseDAO cd=new CourseDAO();
		
		String course="";
		try {
			course = cd.findall(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("11111111111111111111111"+course);
		list.add(course);
		System.out.println("已经查询");
		System.out.println(list.get(0));
		
		return list;
	}
	
	@RequestMapping("getcoursebyYZM")
	@ResponseBody
	public ArrayList<String> getcoursebyYZM(HttpSession session,String id,String text_yzm) throws Exception{
		ArrayList<String> list=new ArrayList<String>();
		System.out.println("获取课程表。。。");
		 JSONObject js;
		 HttpClient client=(HttpClient) session.getAttribute("client");
		 HttpPost post = new HttpPost("http://121.248.70.214/jwweb/ZNPK/TeacherKBFB_rpt.aspx");
         List<NameValuePair> pairs = new ArrayList<NameValuePair>();
         NameValuePair p1 = new BasicNameValuePair("Sel_XNXQ", "20150");
         NameValuePair p2 = new BasicNameValuePair("Sel_JS", id);
         NameValuePair p3 = new BasicNameValuePair("type", "1");
         NameValuePair p4 = new BasicNameValuePair("txt_yzm", text_yzm);
         pairs.add(p1);
         pairs.add(p2);
         pairs.add(p3);
         pairs.add(p4);
         UrlEncodedFormEntity entity=new UrlEncodedFormEntity(pairs,"GBK");
         post.setEntity(entity);
         post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
         post.setHeader("Accept-Encoding","gzip, deflate");
         post.setHeader("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
         post.setHeader("Connection","keep-alive");
         post.setHeader("Host","121.248.70.214");
         post.setHeader("Referer","http://121.248.70.214/jwweb/ZNPK/TeacherKBFB.aspx");
         post.setEntity(entity);
         client.execute(post);
         HttpResponse response = client.execute(post);
         HttpEntity entity1 = response.getEntity();

         String s = EntityUtils.toString(entity1);
         //Log.i("FR", s);
//         System.out.println(s);
         Document doc=Jsoup.parse(s);
         Elements table= doc.select("table[borderColorLight=#000000]");
         //doc.getElementById(id)
//         System.out.println(table.size());
   //      Log.i("00000000000", table.html());
     //    String s1=table.html();

         Document doc1=Jsoup.parse(table.toString());
         Elements trs= doc1.select("tr");
         String a1="";
      // System.out.println(trs.html()+trs.size());
         for(int day=0;day<7;day++){
            a1="";

             for(int i=1;i<trs.size();i++){
             Elements tds=trs.get(i).select("td[valign=top]");

                 int SS=day+1;
                 js =new JSONObject();
                 switch (i){
                 
                     case 1:
                         if (tds.get(day).text().isEmpty()==false)             
                         a1+=  js.put(SS+"",tds.get(day).text())+",";
                         break;
                     case 2:
                         if (tds.get(day).text().isEmpty()==false)
                            
                         a1+=  js.put(SS+"",tds.get(day).text())+",";

                         break;
                     case 3:
                         if (tds.get(day).text().isEmpty()==false)
                           
                         a1+=  js.put(SS+"",tds.get(day).text())+",";
                         break;
                     case 4:
                         if (tds.get(day).text().isEmpty()==false)
                             
                         a1+=  js.put(SS+"",tds.get(day).text())+",";
                         break;
                     case 5:
                         if (tds.get(day).text().isEmpty()==false)
                            
                         a1+=  js.put(SS+"",tds.get(day).text())+",";
                         break;

                 }
             } 
            list.add(a1);
            System.out.println(a1);
         }
        
         //存数据库
         TeacherDAO td=new TeacherDAO();
         CourseDAO cd=new CourseDAO();
         cd.insertCourse(id,list);
         String identity=1+"";
         td.update(id,identity);//更新老师状态码
         
         
        return list;
     } 
		
}

