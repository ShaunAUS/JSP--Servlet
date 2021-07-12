package com.newlecgure.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class Calc extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
				
		respone.setCharacterEncoding("UTF-8");  //출력값 한글
		respone.setContentType("text/html; charset=UTF-8");
		
		
			String op =request.getParameter("operator");		
		
			String x_=request.getParameter("x");
			String y_=request.getParameter("y");
		
			int x=0;
			int y=0;    //빈문자열이면 0  기본값
			
			if(!x_.equals(""))x=Integer.parseInt(x_);
			if(!y_.equals(""))y=Integer.parseInt(y_);
			
			
			
			
			
		int result=0; //빈문자열이면 기본값
		
		
		if(op.equals("덧셈")) {
			result=x+y;
		}else {
			result=x-y;
		}
		 
		
		PrintWriter out =respone.getWriter();
		out.printf("result is %d\n", result);
		
	}
}
