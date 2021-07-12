package com.newlecgure.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spag")
public class Spag extends HttpServlet {
	
//MVC 모델2   controller+model
	
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
			   //내장 객체 있어서 request바로쓸수잇다
			
			//controller 영역
		    int num=0;
		    String num_=request.getParameter("n");
		    if(num_!=null&&num_!=("")){
		    	num=Integer.parseInt(num_);
		    }
		    
		    
		    String result;
		    if(num%2!=0)
			result="홀수";	
			else
			result="짝수";
		    
		    //controller 영역
		    
		    Map<String,Object> notice=new HashMap();
		    notice.put("id", 1);
		    notice.put("title", "안녕하세요");
		    //무조건 request 공동저장소에 넣어야 부를수있다
		    request.setAttribute("notice",notice);
		    
		    
		    String[] names= {"newlec","dragon"};
		    request.setAttribute("names", names);
		    
		    //forward 현재 작업한내용 이어갈수있도록
		    //redirect 새로운요청
		    
		    						//키    value  양쪽 공용저장소 request 에 result넣는다
		    request.setAttribute("result", result);
		    
		    
		    //request 는 spag.java(controller+model) 와 spag.jsp(view) 의 공통 저장소
		    RequestDispatcher dispatcher=request.getRequestDispatcher("spag.jsp");  //spag.jsp 연결주소
		    dispatcher.forward(request, respone);
		}
		
}
