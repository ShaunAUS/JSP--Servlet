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
import javax.servlet.http.HttpSession;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
		respone.setCharacterEncoding("UTF-8");  //출력값 한글
		respone.setContentType("text/html; charset=UTF-8");
		
		
		ServletContext application=request.getServletContext();	//서블릿 컨텍스트에 값 저장
		HttpSession session=request.getSession();
		
		Cookie[] cookies=request.getCookies();//쿠키값 받기	
		
			
			String v_=request.getParameter("v");            
			String op =request.getParameter("operator");
			
			
			int v= 0; //기본값
			if(!v_.equals(""))v=Integer.parseInt(v_);
			
			

			
			if(op.equals("=")) {
				
				
				
				//int x=(Integer)application.getAttribute("value");   //값꺼내기//반환갑 object//이전v값
				//int x=(Integer)session.getAttribute("value");
				
				
				//이전v 쿠키값 꺼내기
				int x=0;
				for(Cookie c:cookies) {
				
				if(c.getName().equals("value"))
					x=Integer.parseInt(c.getValue());
					break;
				}
				
				
				
								
				
				int y=v;  //새로운 v값
				//String operator=(String)application.getAttribute("op");  //+ or -
				
				
				String operator="";
				
				for(Cookie c:cookies) {
					
					if(c.getName().equals("op"))
						operator=c.getValue();
						break;
					}
				
				//결과 기본값
				int result=0; 
				
				//계산
				if(operator.equals("+")) {
					result=x+y;
				}else {
					result=x-y;
				}
				

				PrintWriter out =respone.getWriter();
				out.printf("result is %d\n", result);
				
				
				
			}
			//값을 저장	
			else {
					
				
				//application.setAttribute("value", v);  //key, value 값 저장
				//application.setAttribute("op", op);
				//session.setAttribute("op", op);
				
				
				Cookie valueCookie=new Cookie("value",String.valueOf(v));  //쿠키정보담기//쿠키값은 반드시 문자형
				Cookie opCookie=new Cookie("op",op);
				
				
				respone.addCookie(valueCookie);  //클라한테 쿠키보내기
				respone.addCookie(opCookie);
				
				
				respone.sendRedirect("calc2.html"); //사용자 백지상태 방지
				
				valueCookie.setPath("/calc2");	//그경로로만(서블릿) 쿠키를 가져갈수잇다 //여러서블릿중
				valueCookie.setMaxAge(60*60*24); // 그시간동안 브라우저,컴퓨터 닫혀도 살아있다//만료시간
				
				opCookie.setPath("/calc2");
			}
			
			
			
		
	}
}
