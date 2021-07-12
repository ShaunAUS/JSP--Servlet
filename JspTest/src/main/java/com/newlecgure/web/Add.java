package com.newlecgure.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class Add extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
				
		respone.setCharacterEncoding("UTF-8");  //출력값 한글
		respone.setContentType("text/html; charset=UTF-8");
		
		
		
		
			String x_=request.getParameter("x");
			String y_=request.getParameter("y");
		
			int x=0;
			int y=0;    //빈문자열이면 0  기본값
			
			if(!x_.equals(""))x=Integer.parseInt(x_);
			if(!y_.equals(""))y=Integer.parseInt(y_);
			
			//x,y 는 들어오는값, 아니면 0 둘중에 하나
		
		int result=x+y;
		PrintWriter out =respone.getWriter();
		out.printf("result is %d\n", result);
		
	}
}
