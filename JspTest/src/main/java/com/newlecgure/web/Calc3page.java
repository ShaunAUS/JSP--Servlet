package com.newlecgure.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc3")
public class Calc3page extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
		
		Cookie[] cookies=request.getCookies();
		
		String exp=0;
		for(Cookie c:cookies) {
			
			if(c.getName(  ).equals("exp"))
				exp=c.getValue();
				break;
			}
		respone.setCharacterEncoding("UTF-8");  //출력값 한글
		respone.setContentType("text/html; charset=UTF-8");
		
		
		
		
		
			
			
		/*
		 	<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<form action="calc2" method="post">
		<div>
			<label>입력:</label>
			<input type="text" name="v"/>
		</div>
		
		<div>
			<input type="submit" name="operator" value="+"/>   <!-- value값으로 버튼 구분// operator= value -->
			<input type="submit" name="operator" value="-"/>	<!-- value값도 전달된다 x,y랑 -->
			<input type="submit" name="operator" value="="/>
		
		</div>
		<div>
			결과:0
		</div>
		</form>
</body>
</html>
		 */
			
		
	}
}
