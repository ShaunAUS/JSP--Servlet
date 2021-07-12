package com.newlecgure.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc3")
public class Calc3withPage extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
		
		
		ServletContext application=request.getServletContext();	//서블릿 컨텍스트에 값 저장	
		
		
			Cookie[] cookies=request.getCookies();//쿠키값 받기// 클라에서 읽고 서버로 보내서 서버가계산
				
			
			String value=request.getParameter("value");            
			String operator =request.getParameter("operator");
			String dot=request.getParameter("dot");
			
			
			
			String exp="";
			

			Cookie[] cookies=request.getCookies();
			
			String exp=0;
			
			for(Cookie c:cookies) {
				
				if(c.getName(  ).equals("exp"))
					exp=c.getValue();
					break;
				}
			
			
			if(operator !=null&& operator.equals("=")) {
				ScriptEngine engine= new ScriptEngineManager().getEngineByName("nashorn");
				try {
					exp=String.copyValueOf(engine.eval(exp));
				} catch (ScriptException e) {
					
					e.printStackTrace();
				}
			}else {
			//시작
			exp+=(value==null)?"":value;
			exp+=(operator==null)?"":operator;
			exp+=(dot==null)?"":dot;
			}
			Cookie expCookie=new Cookie("exp",exp);
			
			
			respone.addCookie(expCookie);
			respone.sendRedirect("calc2.html"); //사용자 백지상태 방지
			}
			
			
			
		
	}
}
