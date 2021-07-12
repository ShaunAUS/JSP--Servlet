package com.newlecgure.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoGetDoPost extends HttpServlet{
	
	
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		//서비스 함수는 (겟,요청이오면 doGet)  (post요청이오면 doPost실행한다),//=부모 서비스=오리지널
		
		// 서비스 자식에서 오버라이딩하면 공통부분
		
		
		super.service(arg0, arg1);
	}
}
