package com.newlecgure.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

@WebServlet("/hi")
public class Nana extends HttpServlet {
	
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			resp.setCharacterEncoding("UTF-8");  //출력값 한글
			resp.setContentType("text/html; charset=UTF-8");
			//req.setCharacterEncoding("UTF-8");  //입력값 한글
			
			
			PrintWriter out =resp.getWriter(); //화면 출력
			
			
			String cnt_=req.getParameter("cnt");
			
			
			int cnt=100; //기본값
			
			
			if(cnt_!=null &&cnt_.equals("")) 
				cnt=Integer.parseInt(cnt_);
			
			 
				
			for(int i=0;i<cnt;i++) {
			out.println((i+1)+":hello 안녕<br>");
			}
		}
		
}
