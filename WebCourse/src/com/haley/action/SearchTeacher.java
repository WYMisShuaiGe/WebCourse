package com.haley.action;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.haley.bean.Teachers;

public class SearchTeacher {
	public SearchTeacher() {
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Teachers> getAllTeacher() {
		ArrayList<Teachers> teacherList=new ArrayList<Teachers>();
		try {
             HttpClient client = new DefaultHttpClient();
             HttpUriRequest request=new HttpGet("http://121.248.70.214/jwweb/ZNPK/Private/List_JS.aspx?xnxq=20150&js=");
             HttpResponse response =client.execute(request);
             HttpEntity entity = response.getEntity();
             String s = EntityUtils.toString(entity);
             Document doc= Jsoup.parse(s);
             Elements es=doc.getElementsByTag("script");

             String ss=es.html();
            // System.out.println(ss+"8888888888888888888");
             Document doc1=Jsoup.parse(ss);
             Elements elements =doc1.getElementsByTag("option");
             
             //Controller controll = new Controller();
             Teachers teacher = null;
             
             for(int i=0;i<elements.size();i++){
            	 teacher = new Teachers();
                 String id=elements.get(i).attr("value");
                 String name=elements.get(i).html();
                 teacher.setTeacherId(id);
                 teacher.setTeachername(name);
                 teacherList.add(teacher);
             }

         } catch (Exception e) {
        	 	e.printStackTrace();
         }
		 return teacherList;
	}
}
