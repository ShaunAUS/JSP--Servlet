package com.newlecgure.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add2")
public class Add2 extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
				
		respone.setCharacterEncoding("UTF-8");  //출력값 한글
		respone.setContentType("text/html; charset=UTF-8");
		
		
		
		
			String[] num_=request.getParameterValues("num");//동일한 이름 여러개올떄 배열로 값 받는다
			
			int result=0;
			
			for(int i=0; i<num_.length; i++) {
				int num= Integer.parseInt(num_[i]);
				result+=num;
			}
			
			PrintWriter out =respone.getWriter();
			out.printf("result is %d\n", result);
		
	}
}
